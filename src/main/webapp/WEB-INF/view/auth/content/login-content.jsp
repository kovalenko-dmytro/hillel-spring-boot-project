<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="row">
    <div class="col-sm-6 mx-auto mt-3">
        <div class="card mb-3">
            <div class="card-header text-white bg-success text-center">Login Form</div>
            <div class="card-body">
                <form:form method="post" action="/login" modelAttribute="loginForm">
                    <div class="form-group row">
                        <form:label path="login" class="col-sm-4 col-form-label">Login:</form:label>
                        <div class="col-sm-8">
                            <form:input type="text" class="form-control" path="login" id="login"  />
                            <form:errors path="login" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <form:label path="password" class="col-sm-4 col-form-label">Password:</form:label>
                        <div class="col-sm-8">
                            <form:input type="password" class="form-control" path="password" id="password" />
                            <form:errors path="password" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-12 mx-auto mt-1">
                            <button type="submit" class="btn btn-block btn-success">Login</button>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-12 mx-auto">
                            <a href="/register" class="btn btn-block btn-info text-center mt-2 mb-2">Register</a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
