<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="row">
    <div class="col-sm-6 mx-auto mt-3">
        <div id="document-create" class="card mb-3 ">
            <div class="card-header text-white bg-success text-center">Create Form</div>
            <div class="card-body">
                <form:form method="post" enctype="multipart/form-data" id="form-create" action="/documents" modelAttribute="document">
                    <div class="form-group row">
                        <form:label path="description" class="col-sm-4 col-form-label">Document description</form:label>
                        <div class="col-sm-8">
                            <form:input type="text" class="form-control" path="description" id="description"/>
                            <form:errors path="description" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <form:label path="file" class="col-sm-4 col-form-label">Upload file</form:label>
                        <div class="col-sm-8">
                            <form:input type="file" path="file" id="file"/>
                            <form:errors path="file" />
                        </div>
                    </div>
                    <div class="form-group row float-right">
                        <div class="col-sm-8 ">
                            <button type="submit" id="submit" class="btn btn-success">Save</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>