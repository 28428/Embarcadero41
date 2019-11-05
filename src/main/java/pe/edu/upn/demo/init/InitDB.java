package pe.edu.upn.demo.init;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.upn.demo.entity.Usuario;
import pe.edu.upn.demo.repository.AuthorityRepository;
import pe.edu.upn.demo.repository.UsuarioRepository;

@Service
public class InitDB implements CommandLineRunner{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		
		Usuario admin = new Usuario();
		admin.setUsername("admin");
		admin.setPassword(passwordEncoder.encode("admin"));
		admin.setEnable(true);
		
		Usuario diego = new Usuario();
		diego.setUsername("diego");
		diego.setPassword(passwordEncoder.encode("123456"));
		diego.setEnable(true);
		
		
		Usuario gerente = new Usuario();
		gerente.setUsername("gerente");
		gerente.setPassword(passwordEncoder.encode("gerente"));
		gerente.setEnable(true);
		/*
        
        juan.addAuthority("ROLE_USER");
        juan.addAuthority("ACCESS_MEDICO_READ");
        */
        admin.addAuthority("ROLE_ADMIN");
        admin.addAuthority("ACCESS_REST1");
        admin.addAuthority("ACCESS_REST2");
        
        
        List<Usuario> usuarios = Arrays.asList(admin,diego,gerente);        
        this.usuarioRepository.saveAll(usuarios);
	}
}
