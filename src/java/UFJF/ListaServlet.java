package UFJF;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

        String usu = request.getParameter("usuario");
        String psw = request.getParameter("senha");

        String logado = (String) request.getSession().getAttribute("logado");

        if (logado == null) {
            response.sendRedirect("index.jsp");
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        String resp = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = new ConnectionFactory().getConnection();
            stmt = conn.prepareStatement("SELECT usuario FROM login");
            ResultSet rs = stmt.executeQuery();

            try (PrintWriter out = response.getWriter()) {
                out.println("<title>Usuários Cadastrados</title>");
                out.println("<h1>Usuários Cadastrados</h1>");
                while (rs.next()) {
                    out.println(rs.getString("usuario") + "<br>");
                }
                out.println("<a href='menu.jsp'>Voltar</a>");
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            //Handle errors for JDBC
            //throw new ServletException(e);
            //response.sendRedirect("erro.html");           
        } catch (Exception e) {
            //Handle errors for Class.forName
            //throw new ServletException(e);
            resp = e.getMessage();
            throw new ServletException(e);
        } finally {
            //System.out.printf(resp);
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                //response.sendRedirect("erro.html"); 
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                //response.sendRedirect("erro.html"); 
            }//end finally try
        } //end try

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
