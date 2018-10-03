package UFJF;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class jdbcUsuario implements UsuarioDAO {

    private static Connection conexao;
    private static jdbcUsuario instancia;

    @Override
    public Usuario create() {
        return null; // MUDAR
    }

    @Override
    public UsuarioDAO getInstance() {
        if(instancia == null){
        //    instancia = new UsuarioDAO();
        }
        return instancia;
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
    public List<Usuario> listaUsuarios() {
        return null;
    }

}
