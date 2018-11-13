<%@include file="cabecalho.jspf" %>

<jsp:useBean id = "rotator" scope = "session"      
             class = "com.deitel.jhtp6.jsp.beans.Rotator" />

<!DOCTYPE html>
<html>
    <head>
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
                                 property = "image" />" class="img-responsive" alt = "Chania" />
            </a>
        </p>
        <form>
            <button name="opcao" class="btn btn-success" value="listar" formaction="ValidaServlet">Listar Usuários</button>
        </form>      
        <a href="index.jsp" class="btn btn-primary" role="button">Sair</a>

        <%
            out.println("</br>" + "Seu endereço IP: " + request.getRemoteAddr() + "</br>");
            SessionListener sessionListener = new SessionListener();
            out.println("</br>" + "Usuários Online: " + sessionListener.getTotalActiveSession() + "</br>");
        %>
    </body>
</html>
<%@include file="rodape.jspf" %>