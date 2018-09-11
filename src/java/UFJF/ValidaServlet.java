/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UFJF;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ValidaServlet", urlPatterns = {"/ValidaServlet"})
public class ValidaServlet extends HttpServlet {

    String usuario, senha;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        //String usu = (String) getServletContext().getInitParameter("usuario");
        //String psw = (String) getServletContext().getInitParameter("senha");
        String usu = request.getParameter("usuario");
        String psw = request.getParameter("senha");

        String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

        String DB_URL = "jdbc:derby://localhost:1527/usuarioBD";
        Connection conn = null;
        PreparedStatement stmt = null;
        String resp = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = new ConnectionFactory().getConnection();            
            stmt = conn.prepareStatement("SELECT usuario,senha FROM login WHERE upper(usuario)= ? AND senha = ?");
            stmt.setString(1, usu.toUpperCase());
            stmt.setString(2, psw);
            //TESTE
            try (ResultSet rs = stmt.executeQuery()) {
                //TESTE
                if (rs.next()) {
                    response.sendRedirect("menu.jsp");
                }else{
                    response.sendRedirect("erro.html");
                }
            }catch (Exception e){
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
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
