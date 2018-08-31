<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" contentType="text/html; charset=utf-8" %>

<%--<jsp:include page="../../css/hello.css" />--%>
<%--<jsp:include page="/img/background.jpg" />--%>

<html>
<head>
    <title>MANAGER</title>

    <link href="css/hello.css" rel="stylesheet">

</head>

<body>
<h1>Title : ${title}<></h1>
<h1>Message : ${message} </h1>
<h6>Time: <%= getFormattedDate () %></h6>

<%!
    String getFormattedDate ()
    {
        SimpleDateFormat sdf = new SimpleDateFormat ("dd.MM.yyyy hh:mm:ss");
        return sdf.format (new Date());
    }
%>

<p>
    Для редактирования базы перейдите на <a href="client">/Client</a>, <a href="bank">/Bank</a>, <a href="orders">/Orders</a>
</p>

<sec:authorize access="hasRole('ROLE_USER')">

    <c:url value="/j_spring_security_logout" var="logoutUrl"/>
    <form action="${logoutUrl}" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>

    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
    </script>

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h2>
            User : ${pageContext.request.userPrincipal.name} | <a
                href="javascript:formSubmit()"> Logout</a>
        </h2>
    </c:if>

</sec:authorize>
</body>
</html>
