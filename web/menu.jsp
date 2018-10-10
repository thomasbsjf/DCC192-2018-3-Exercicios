<%@page import="UFJF.SessionListener"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="cabecalho.jspf" %>

<jsp:useBean id = "rotator" scope = "session"      
             class = "com.deitel.jhtp6.jsp.beans.Rotator" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="refresh" content="15">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
                                 property = "image" />" class="img-responsive" alt = "Chania" />
            </a>
        </p>
        <a href="lista-usuario.jsp" class="btn btn-primary" role="button">Cadastro de Usuários</a>        
        <a href="index.jsp" class="btn btn-primary" role="button">Sair</a>

        <%
            out.println("</br>" + "Seu endereço IP: " + request.getRemoteAddr() + "</br>");
            SessionListener sessionListener = new SessionListener();
            out.println("</br>" + "Usuários Online: " + sessionListener.getTotalActiveSession() + "</br>");
        %>
    </body>
</html>
<%@include file="rodape.jspf" %>