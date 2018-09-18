<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="cabecalho.jspf" %>

<jsp:useBean id = "rotator" scope = "session"      
             class = "com.deitel.jhtp6.jsp.beans.Rotator" />

<!DOCTYPE html>
<html>
    <head>
        <!meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="15">
        <title>Menu Principal</title>
        <% rotator.nextAd(); %>
    </head>
    <body>
        <%
            String logado = (String) request.getSession().getAttribute("logado");

            if (logado == null) {
                response.sendRedirect("index.jsp");
            }
        %>
        <h1>Menu Principal</h1>

        <p> 
            <a href = "<jsp:getProperty name = "rotator" 
                             property = "link" />">

                <img src = "<jsp:getProperty name = "rotator"
                                 property = "image" />" alt = "advertisement" />
            </a>
        </p>

        <a href="ListaServlet">Cadastro de Usuários</a></br>
        <a href="index.jsp">Sair</a></br>

        <%
            out.println("Seu endereço IP: " + request.getRemoteAddr() + "</br>");
        %>
    </body>
</html>
<%@include file="rodape.jspf" %>