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
                conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/usuarioBD", "root", "senha");
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
            //Class.forName(JDBC_DRIVER);
            conn = new ConnectionFactory().getConnection();
            stmt = conn.prepareStatement("SELECT usuario FROM login");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("usuario"));
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
    public List<Usuario> listAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            PreparedStatement comando = conexao.prepareStatement("SELECT usuario FROM login");
            ResultSet rs = comando.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("usuario"));
                usuarios.add(usuario);
            }
            rs.close();
            comando.close();
        } catch (SQLException ex) {
            Logger.getLogger(jdbcUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
