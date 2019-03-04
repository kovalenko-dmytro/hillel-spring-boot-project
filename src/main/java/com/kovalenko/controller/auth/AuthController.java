package com.kovalenko.controller.auth;

import com.kovalenko.binding.LoginForm;
import com.kovalenko.entity.user.User;
import com.kovalenko.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView view = new ModelAndView("auth/login");
        view.addObject("loginForm", new LoginForm());
        return view;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView view = new ModelAndView("auth/register");
        view.addObject("user", new User());
        return view;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@Valid @ModelAttribute("user") User user,
                                 BindingResult bindingResult) {

        ModelAndView view = new ModelAndView();

        if (bindingResult.hasErrors()) {
            view.addObject("user", user);
            view.addAllObjects(bindingResult.getModel());
            view.setViewName("auth/register");
            return view;
        }

        User userWithSameLogin = userService.getUserByLogin(user.getLogin());

        if (userWithSameLogin == null) {
            userService.register(user);
            view.setViewName("redirect:/login");
            return view;
        }

        view.setViewName("redirect:/register");
        return view;
    }
}
