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

    String usuario, senha;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String usu = request.getParameter("usuario");
        String psw = request.getParameter("senha");

        String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
        Connection conn = null;
        PreparedStatement stmt = null;
        String resp = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = new ConnectionFactory().getConnection();
            stmt = conn.prepareStatement("SELECT usuario,senha FROM login WHERE upper(usuario)= ? AND senha = ?");
            stmt.setString(1, usu.toUpperCase());
            stmt.setString(2, psw);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("logado", new String("true"));
                    response.sendRedirect("menu.jsp");
                } else {
                    response.sendRedirect("erro.html");
                }
            } catch (Exception e) {
                System.out.printf("Problema com o Statement");
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            //Handle errors for JDBC
            //throw new ServletException(e);
            resp = e.getMessage();
            resp += "SQLException: " + e.getMessage();
            resp += "SQLState: " + e.getSQLState();
            resp += "VendorError: " + e.getErrorCode();
            throw new ServletException(e);
        } catch (Exception e) {
            //Handle errors for Class.forName
            //throw new ServletException(e);
            //resp = e.getMessage();
            throw new ServletException(e);
        } finally {
            //System.out.printf(resp);
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                throw new ServletException(e);
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new ServletException(e);
            }//end finally try
        } //end try
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> rotas = new HashMap<>();
        rotas.put("/index.jsp", "UFJF.IndexCommand");
        rotas.put("/lista-usuario", "UFJF.ListaUsuarioCommand");

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
