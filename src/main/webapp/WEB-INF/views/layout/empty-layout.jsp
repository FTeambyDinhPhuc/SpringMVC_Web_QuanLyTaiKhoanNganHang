<%-- 
    Document   : empty-layout
    Created on : Apr 1, 2023, 4:12:23 PM
    Author     : dinhp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.min.css" />"/>
        <link rel="stylesheet" href="<c:url value="/assets/fontawesome-free-6.3.0-web/css/all.min.css" />"/>
        <link rel="stylesheet" href="<c:url value="/assets/css/main.css" />"/>
        <link rel="stylesheet" href="<c:url value="/assets/css/base.css" />"/>

        <title>${pageTitle}</title>
    </head>
    <body>
        <div>
             <jsp:include page="../${param.view}"/>
        </div>

        <script src="<c:url value="/assets/js/jquery-3.5.1.min.js" />"></script>
        <script src="<c:url value="/assets/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/assets/js/main.js" />"></script>
    </body>
</html>

