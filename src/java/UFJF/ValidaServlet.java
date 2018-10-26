package UFJF;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ValidaServlet", urlPatterns = {"/ValidaServlet", "index.jsp"})
public class ValidaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String usu = request.getParameter("usuario");
        String psw = request.getParameter("senha");

        jdbcUsuario jdbcUser = new jdbcUsuario();
        if (jdbcUser.validaLogin(usu, psw)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("logado", new String("true"));
            response.sendRedirect("menu.jsp");
        } else {
            response.sendRedirect("erro.html");
        }
    }
    @Override
    public void init() throws ServletException{
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> rotas = new HashMap<>();
        rotas.put("/index.jsp", "UFJF.IndexCommand");
        rotas.put("/lista-usuario.jsp", "UFJF.ListarUsuarioCommand");

        String clazzName = rotas.get(request.getServletPath());
        try {
            Comando comando = (Comando) Class.forName(clazzName).newInstance();
            comando.exec(request, response);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            response.sendError(500, "Erro: " + ex);
            Logger.getLogger(ValidaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
