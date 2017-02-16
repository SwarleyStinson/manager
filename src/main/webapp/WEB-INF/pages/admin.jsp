<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page session="true" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Admin page</title>
    <style>
        .allfields {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #a94442;
            background-color: #d9edf7;
            border-color: #bce8f1;
    </style>
</head>

<body>
<h1>Title : страница управления базой данных</h1>
<h1>Таблица : Client</h1>

<c:url value="/j_spring_security_logout" var="logoutUrl"/>
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h2>
        Добро пожаловать : ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()"> Logout</a>
    </h2>
</c:if>

<div align="left">
    <input value="Client Database" type="button" size="100" height="40"/>
    <input value="Bank Database" type="button" size="100"/>
    <input value="Order Database" type="button" size="100"/>
</div>
<div align="center">

    <table id="client_table" align="center" border="1" width="1000" cellpadding="05" cellspacing="0">
        <tr bordercolor="black">
            <th>ID</th>
            <th>NAME</th>
            <th>LOGIN</th>
            <th>PASSWORD</th>
            <th>EMAIL</th>
            <th>TYPE</th>
        </tr>
        <c:forEach items="${clientList}" var="c">
            <tr>
                <td align="center" width="5"> ${c.id}</td>
                <td width="40"> ${c.name}</td>
                <td width="10"> ${c.login}</td>
                <td width="10"> ${c.password}</td>
                <td width="20"> ${c.email}</td>
                <td width="15"> ${c.type}</td>
                <td>
                    <sf:form >
                        <input type="hidden" value="${c.id}" name="deleteByID">
                        <input type="submit" value="Удалить" align="center"/>
                    </sf:form>
                </td>
                <td>
                    <sf:form>

                        <input type="hidden" value="${c.id}" name="setByID">
                        <input type="submit" value="Изменить" align="center"/>
                    </sf:form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<c:if test="${not empty allfields}">
    <div class="allfields" align="center">${allfields}</div>
</c:if>
<div align="center" >

    <sf:form method="post">
        <tr>
            <td>Name:</td>
            <td><input name="name" type="text" width="5"/></td>
        </tr>
        <tr>
            <td>Login:</td>
            <td><input name="login" type="text" width="15"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input name="password" type="text" width="10"/></td>
        </tr>
        <tr>
            <td>E-mail:</td>
            <td><input name="email" type="email" width="20"/></td>
        </tr>
        <tr>
            <td>Type:</td>
            <td><input name="type" type="text" width="10"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Добавить"/></td>
        </tr>
    </sf:form>
</div>
</body>
</html>