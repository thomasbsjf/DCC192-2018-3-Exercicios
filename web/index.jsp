<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tela de Login</title>
    </head>
    <body>
        <form method="post" role="form" action="ValidaServlet">
            <div class="row">
                <div class="form-group col-lg-2">  
                    <label for="usuario_input">Login</label>
                    <input type="text" class="form-control" id="usuario_input" name="usuario"><br>
                </div>
            </div>
            
            <div class="row">
                <div class="form-group col-lg-2">
                    <label for="senha_input">Senha</label>              
                    <input type="password" class="form-control input normal" id="senha_input" name="senha">
                </div>
            </div>                       
            <button type="submit" class="btn btn-default">Confirmar</button>

        </form>
    </body>
</html>
