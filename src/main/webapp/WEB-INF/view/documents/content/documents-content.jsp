<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="row">
    <div class="col-sm-6 mx-auto mt-3">
        <sec:authorize access="hasAuthority('ADMIN')">
            <a href="/documents/create" class="btn btn-create btn-block btn-success text-center mt-2 mb-2">Create</a>
        </sec:authorize>
    </div>
</div>
<div class="row">
    <div class="col-sm-12 mx-auto mt-3">
        <div id="data-list">
            <c:choose>
                <c:when test="${empty documents}">
                    <h2 class="text-center">No documents are available</h2>
                </c:when>
                <c:otherwise>
                    <table class="table text-center">
                        <thead class="table-info">
                        <tr>
                            <th>Title</th>
                            <th>Type</th>
                            <th>Description</th>
                            <th>Date Created</th>
                            <sec:authorize access="hasAuthority('ADMIN')">
                                <th colspan="3">Action</th>
                            </sec:authorize>
                            <sec:authorize access="hasAuthority('USER')">
                                <th>Action</th>
                            </sec:authorize>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="document" items="${documents}">
                            <tr>
                                <td>${document.title}</td>
                                <td>${document.type}</td>
                                <td>${document.description}</td>
                                <td>${document.created}</td>
                                <sec:authorize access="hasAuthority('ADMIN')">
                                    <td>
                                        <a href="/documents/${document.id}/download" class="btn btn-primary text-center">Download</a>
                                    </td>
                                    <td>
                                        <a href="/documents/${document.id}/update" class="btn btn-block btn-update btn-warning text-center">Update</a>
                                    </td>
                                    <td>
                                        <form:form method="post" action="/documents/${document.id}">
                                            <input type="hidden" name="_method" value="DELETE"/>
                                            <button type="submit" id="submit" class="btn btn-delete btn-danger text-center">Delete</button>
                                        </form:form>
                                    </td>
                                </sec:authorize>
                                <sec:authorize access="hasAuthority('USER')">
                                    <td>
                                        <a href="/documents/${document.id}/download" class="btn btn-primary text-center">Download</a>
                                    </td>
                                </sec:authorize>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>