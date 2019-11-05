package pe.edu.upn.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upn.demo.entity.Usuario;
import pe.edu.upn.demo.service.UsuarioService;




@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public String index() {
		return "index";
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@GetMapping("loginestudiante")
	public String loginEstudiante() {
		return "loginestudiante";
	}
	
	@GetMapping("verid")
	public String verId(Model model) {
		// Para obtener el username
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		try {
			Optional<Usuario> optional = usuarioService.findByUsername(username);
			if(optional.isPresent()) {
				model.addAttribute("usuario", optional.get());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "/verid";
		
	}
}
