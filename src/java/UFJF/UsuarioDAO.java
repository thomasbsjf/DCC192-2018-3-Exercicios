package UFJF;

public interface UsuarioDAO {
    Usuario create();
    public Usuario findByUsuarioNome(String nome);
}
