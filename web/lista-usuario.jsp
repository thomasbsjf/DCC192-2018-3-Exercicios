<%@include file="cabecalho.jspf" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usu�rios Cadastrados</title>
    </head>
    <body>
        <h1>Usu�rios Cadastrados</h1>        
        <c:forEach var="usuario" items="${usuarios}">      
            <p>${usuario.login}</p>
        </c:forEach>   
            
        <a href="menu.jsp" class="btn btn-primary" role="button">Voltar</a>     
    </body>
</html>
