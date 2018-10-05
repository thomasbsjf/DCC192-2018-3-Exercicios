package UFJF;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarUsuarioCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Usuario> usuarios = jdbcUsuario.getInstance().listAllUsuarios();
        request.setAttribute("usuarios", usuarios);
        RequestDispatcher dispacher = request.getRequestDispatcher("/lista-usuario.jsp");
        dispacher.forward(request, response);
    }
}
