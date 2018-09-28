package UFJF;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jpaUsuario implements UsuarioDAO {

    @Override
    public Usuario create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
