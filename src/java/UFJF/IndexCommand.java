package UFJF;

import Modelo.Login;
import Modelo.LoginJpaController;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usu = request.getParameter("usuario");
        String psw = request.getParameter("senha");

        /*---------------JDBC LOGIN--------------------
        jdbcUsuario jdbcUser = new jdbcUsuario();
        if (jdbcUser.validaLogin(usu, psw)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("logado", new String("true"));
            response.sendRedirect("menu.jsp");
        } else {
            response.sendRedirect("erro.html");
        }
         */
 /*

         */
        LoginJpaController jpaControl = new LoginJpaController();
        if (jpaControl.findLogin(usu) != null) {
            //HttpSession session = request.getSession(true);
            //session.setAttribute("logado", new String("true"));
            request.getSession().setAttribute("user", usu);
            response.sendRedirect("menu.jsp");
            return;
        } else {
            response.sendRedirect("erro.html");
        }
    }

}
