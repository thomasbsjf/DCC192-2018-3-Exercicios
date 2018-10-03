<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuários Cadastrados</title>
    </head>
    <body>
        <h1>Usuários Cadastrados</h1>        
        <c:forEach var="usuario" items="${usuarios}">      
            <p>${usuario.nome}</p>       
        </c:forEach>

        <a href="menu.jsp">Voltar</a>
    </body>
</html>
