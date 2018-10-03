package UFJF;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaUsuarioCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("titulo", "Lista de Usuarios");
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/lista-usuario.jsp");
        dispacher.forward(request, response);
    }
}
