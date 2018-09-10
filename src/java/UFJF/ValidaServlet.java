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
            stmt = conn.prepareStatement("SELECT usuario,senha FROM login WHERE "
                    + "upper(usuario)= " + usu.toUpperCase() + " AND " + "senha = " + psw + "");
            ResultSet rs = stmt.executeQuery();
            RequestDispatcher rd;
            if (rs.next()) {
                System.out.println("Batata");
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
