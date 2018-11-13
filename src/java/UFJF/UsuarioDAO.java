package UFJF;

import java.util.List;

public interface UsuarioDAO {
    
    public Usuario findByUsuarioNome(String nome);
    public List<Usuario> listAllUsuarios() throws Exception;
    public boolean validaLogin(String nome, String senha);
    
}
