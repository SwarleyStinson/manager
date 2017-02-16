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

<c:if test="${pageContext.request.userPrincipal.name != admin}">
    <h2>
        Добро пожаловать : ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()"> Logout</a>
    </h2>
</c:if>

<div align="left">
    <input value="Client Database" type="button" size="100" height="40"/>
    <input value="Bank Database" type="button" size="100"/>
    <input value="Order Database" type="button" size="100"/>
</div>
<script>
    function toggle() {
        if (document.getElementById("hidethis").style.display == 'none') {
            document.getElementById("hidethis").style.display = '';
        } else {
            document.getElementById("hidethis").style.display = 'none';
        }
    }
    function toggleToo() {
        if (document.getElementById("hidethistoo").style.display == 'none') {
            document.getElementById("hidethistoo").style.display = '';
        } else {
            document.getElementById("hidethistoo").style.display = 'none';
        }
    }
</script>


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
                    <sf:form>
                        <input type="hidden" value="${c.id}" name="deleteByID">
                        <input type="submit" value="Удалить" align="center"/>
                    </sf:form>
                </td>
                <td>
                    <sf:form>
                        <input onclick="toggle(), toggleToo()" type="button" value="изменить/добавить"/>
                    </sf:form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div align="center">
    <c:if test="${not empty allfields}">
        <div class="allfields" align="center">${allfields}</div>
    </c:if>

    <sf:form id="hidethis" method="post" style="display: none" name="setForm">

        <td>ID:</td>
        <td><input name="id" type="text" width="5" required/>

        </td>
        <td>Name:</td>
        <td><input name="name" type="text" width="5" required/></td>

        <td>Login:</td>
        <td><input name="login" type="text" width="15" required/></td>

        <td>Password:</td>
        <td><input name="password" type="text" width="10" formenctype="application/x-www-form-urlencoded" required/></td>

        <td>E-mail:</td>
        <td><input name="email" type="email" width="20" required/></td>

        <td>Type:</td>
        <td>
            <select>
                <option>REAL_FACE</option>
                <option>COMPANY</option>
            </select>
        </td>

        <td><input type="submit" value="Сохранить изменения"/></td>

    </sf:form>
</div>


<div align="center">
    <sf:form id="hidethistoo" method="post" name="addForm">
        <div class="allfields" align="center">Введите данные нового клиента:</div>
        <tr>
            <td>Name:</td>
            <td><input name="name" type="text" width="5" required/></td>
        </tr>
        <tr>
            <td>Login:</td>
            <td><input name="login" type="text" width="15" required/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input name="password" type="text" width="10" formenctype="application/x-www-form-urlencoded" required/></td>
        </tr>
        <tr>
            <td>E-mail:</td>
            <td><input name="email" type="email" width="20" required/></td>
        </tr>
        <tr>
            <td>Type:</td>
            <td>
                <select>
                    <option>REAL_FACE</option>
                    <option>COMPANY</option>
                </select>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Добавить клиента"/></td>
        </tr>
    </sf:form>
</div>
<div align="center">
    <td>Скачать таблицу в формате:</td>
    <td><input type="button" value="PDF"/></td>
    <td><input type="button" value="Exel"/></td>
</div>
</body>
</html>