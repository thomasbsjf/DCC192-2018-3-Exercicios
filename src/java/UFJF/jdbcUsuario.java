package UFJF;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class jdbcUsuario implements UsuarioDAO {

    private static Connection conexao;
    private static jdbcUsuario instancia;

    public static jdbcUsuario getInstance() {
        if (instancia == null) {
            instancia = new jdbcUsuario();
        }
        return instancia;
    }

    public jdbcUsuario() {
        try {
            if (conexao == null) {
                conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/usuarioDB", "root", "senha");
            }

        } catch (SQLException ex) {
            Logger.getLogger(jdbcUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Usuario findByUsuarioNome(String nome) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = new ConnectionFactory().getConnection();
            stmt = conn.prepareStatement("SELECT login FROM login");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                return usuario;
            } else {
                return null;
            }

        } catch (SQLException e) {

        }
        return null;
    }

    @Override
    public List<Usuario> listAllUsuarios() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        PreparedStatement comando = conexao.prepareStatement("SELECT login FROM login");
        ResultSet rs = comando.executeQuery();
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setNome(rs.getString("login"));
            usuarios.add(usuario);
        }
        rs.close();
        comando.close();

        return usuarios;
    }

    @Override
    public boolean validaLogin(String nome, String senha) {

        String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
        Connection conn = null;
        PreparedStatement stmt = null;
        String resp = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = new ConnectionFactory().getConnection();
            stmt = conn.prepareStatement("SELECT login,senha FROM login WHERE upper(login)= ? AND senha = ?");
            stmt.setString(1, nome.toUpperCase());
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {

                return rs.next();
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
            try {
                throw new ServletException(e);
            } catch (ServletException ex) {
                Logger.getLogger(jdbcUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            try {
                //Handle errors for Class.forName
                //throw new ServletException(e);
                //resp = e.getMessage();
                throw new ServletException(e);
            } catch (ServletException ex) {
                Logger.getLogger(jdbcUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            //System.out.printf(resp);
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                try {
                    throw new ServletException(e);
                } catch (ServletException ex) {
                    Logger.getLogger(jdbcUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                try {
                    throw new ServletException(e);
                } catch (ServletException ex) {
                    Logger.getLogger(jdbcUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }//end finally try
        }//end try
        return false;
    }
}
