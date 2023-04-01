<%-- 
    Document   : layout-dispatcher
    Created on : Apr 1, 2023, 4:05:41 PM
    Author     : dinhp
--%>

<%
    String view = request.getParameter("view");
    if(view.startsWith("home/")){
        pageContext.forward("main-layout.jsp");
    }
    else{
        pageContext.forward("empty-layout.jsp");
    }
%>

