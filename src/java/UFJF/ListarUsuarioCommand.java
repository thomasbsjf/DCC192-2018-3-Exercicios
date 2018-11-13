package UFJF;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarUsuarioCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Usuario> usuarios;
        try {
            usuarios = jdbcUsuario.getInstance().listAllUsuarios();
            request.setAttribute("usuarios", usuarios);
            RequestDispatcher dispacher = request.getRequestDispatcher("/lista-usuario.jsp");
            dispacher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ListarUsuarioCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

        //response.sendRedirect("lista-usuario.jsp");
        return;
    }
}
