<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="cabecalho.jspf" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Principal</title>
    </head>
    <body>
        <%
        String logado = (String) request.getSession().getAttribute("logado");

        if (logado == null) {
            response.sendRedirect("index.jsp");
        }
        %>
        <h1>Menu Principal</h1>
        <a href="ListaServlet">Cadastro de Usuários</a><br>
        <a href="index.jsp">Sair</a>
    </body>
</html>
<%@include file="rodape.jspf" %>