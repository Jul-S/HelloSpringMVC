<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Users Page</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<a href="../../index.jsp">Back to main menu</a>

<br/>
<br/>

<h1>User List</h1>
<p>Here you can see the list of the users, edit them, remove or update.</p>

<c:url var="addAction" value="users/search"/>

<form:form action="${addAction}" commandName="user">
    <table>
    <tr>
    <input type="text" name="searchName" id="searchName"
           placeholder="type name here.."/>

    <input type="submit"
    value="<spring:message text="Search"/>"/>
    </tr>
    </table>
</form:form>

    <c:if test="${!empty listUsers}">
        <table class="tg">
            <tr>
                <th width="80">ID</th>
                <th width="120">Name</th>
                <th width="60">Age</th>
                <th width="60">isAdmin</th>
                <th width="120">Create Date</th>
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </tr>
            <c:forEach items="${listUsers}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.admin}</td>
                    <td>${user.createDate}</td>
                    <td><a href="<c:url value='/edit/${user.id}'/>">Edit</a></td>
                    <td><a href="<c:url value='/remove/${user.id}'/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <div id="pagination">

    <c:url value="/users" var="prev">
        <c:param name="page" value="${page-1}"/>
    </c:url>
    <c:if test="${page > 1}">
        <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
    </c:if>

    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
        <c:choose>
            <c:when test="${page == i.index}">
                <span>${i.index}</span>
            </c:when>
            <c:otherwise>
                <c:url value="/users" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <a href='<c:out value="${url}" />'>${i.index}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:url value="/users" var="next">
        <c:param name="page" value="${page + 1}"/>
    </c:url>
    <c:if test="${page + 1 <= maxPages}">
        <a href='<c:out value="${next}" />' class="pn next">Next</a>
    </c:if>
    </div>

    <h1>Add/Edit a User</h1>
    <p>To edit user click Edit link in the list above.</p>

    <c:url var="addAction" value="/users/add"/>

    <form:form action="${addAction}" commandName="user">
        <table>
            <c:if test="${!empty user.name}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>

                </c:if>

                <td>
                    <form:label path="name">
                        <spring:message text="Name"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="name"/>
                </td>


                <td>
                    <form:label path="age">
                        <spring:message text="Age"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="age"/>
                </td>


                <td>
                    <form:checkbox path="admin" value="isAdmin"/>isAdmin
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:if test="${!empty user.name}">
                        <input type="submit"
                               value="<spring:message text="Edit user"/>"/>
                    </c:if>
                    <c:if test="${empty user.name}">
                        <input type="submit"
                               value="<spring:message text="Add user"/>"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>

    </body>
    </html>
