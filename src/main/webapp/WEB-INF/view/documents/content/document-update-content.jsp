<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="row">
    <div class="col-sm-6 mx-auto mt-3">
        <a href="/documents/${document.id}" id="back" class=" text-center mt-2 mb-2">Back to document</a>
        <div class="card mb-3 ">
            <div class="card-header text-white bg-warning text-center">Edit Form</div>
            <div class="card-body">
                <form:form method="post" action="/documents/${document.id}" modelAttribute="document">
                    <input type="hidden" name="_method" value="PUT"/>
                    <div class="form-group row">
                        <form:label path="title" class="col-sm-4 col-form-label">Document title</form:label>
                        <div class="col-sm-8">
                            <form:input type="text" class="form-control" path="title" id="title"/>
                            <form:errors path="title" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <form:label path="description" class="col-sm-4 col-form-label">Document description</form:label>
                        <div class="col-sm-8">
                            <form:input type="text" class="form-control" path="description" id="description"/>
                            <form:errors path="description" />
                        </div>
                    </div>
                    <div class="form-group row float-right">
                        <div class="col-sm-8 ">
                            <button type="submit" class="btn btn-warning">Update</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>