package UFJF;

import java.util.List;

public interface UsuarioDAO {
    Usuario create();
    public UsuarioDAO getInstance();
    public Usuario findByUsuarioNome(String nome);
    public List<Usuario> listaUsuarios();
    
}
