package pe.edu.upn.demo.service;

import java.util.Optional;

import pe.edu.upn.demo.entity.Usuario;

public interface UsuarioService extends CrudService<Usuario, Long>{
	Optional<Usuario> findByUsername(String username) throws Exception;
}
