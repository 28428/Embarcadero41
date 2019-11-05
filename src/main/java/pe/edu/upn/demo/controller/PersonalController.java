package pe.edu.upn.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upn.demo.entity.Pedidos;
import pe.edu.upn.demo.entity.Personal;
import pe.edu.upn.demo.entity.Platos;
import pe.edu.upn.demo.entity.Usuario;
import pe.edu.upn.demo.service.PedidosService;
import pe.edu.upn.demo.service.PersonalService;
import pe.edu.upn.demo.service.PlatosService;
import pe.edu.upn.demo.service.UsuarioService;

@Controller
@RequestMapping("/personal")
@SessionAttributes({"personal","usuario"})
public class PersonalController {
	@Autowired
	private PersonalService trabajadorService;


	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Personal> personales= trabajadorService.findAll();
			model.addAttribute("personales", personales);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/personal/inicio";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") String id, Model model) {
		try {
			Optional<Personal> optional = trabajadorService.findById(id);
			if (optional.isPresent()) {
				
				model.addAttribute("personal", optional.get());
				
			} else {
				return "redirect:/personal";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/personal/edit";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("personal") Personal personal, Model model, SessionStatus status) {
		try {
			trabajadorService.save(personal);
			status.setComplete();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/personal";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Personal personal= new Personal();
		model.addAttribute("personal", personal);
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/personal/nuevo";
	}

	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id") String id, Model model) {
		try {
			Optional<Personal> personal = trabajadorService.findById(id);
			if (personal.isPresent()) {
				trabajadorService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel", "ERROR");
			try {
				List<Personal> personal = trabajadorService.findAll();
				model.addAttribute("personal", personal);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			return "/personal/inicio";
		}
		return "redirect:/personal";
	}

	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") String id, Model model) {
		try {
			Optional<Personal> personal = trabajadorService.findById(id);
			if (personal.isPresent()) {
				model.addAttribute("personal", personal.get());
			} else {
				return "redirect:/personal";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/personal/info";
	}
	
	@GetMapping("/{id}/nuevousuario")
	public String nuevoUsuario(@PathVariable("id") String id, Model model) {
		Usuario usuario= new Usuario();
		try {
			Optional<Personal> personal= trabajadorService.findById(id);
			if (personal.isPresent()) {
				usuario.setPersonal(personal.get());
				model.addAttribute("usuario", usuario);
			} else {
				return "redirect:/personal";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/personal/nuevousuario";
	}
	
	@PostMapping("/saveusuario")
	public String saveUsuario(@ModelAttribute("usuario") Usuario usuario, 
			Model model, SessionStatus status) {
		
		try {
			// Verificar que el username ya exista.
			Optional<Usuario> optional 
				= usuarioService.findByUsername(usuario.getUsername());
			if(optional.isPresent()) {
				model.addAttribute("dangerRegister"
						, "ERROR - El username " 
							+ usuario.getUsername() 
							+ " ya existe ");
				return "/personal";
			} else {
				usuario.setPassword(passwordEncoder
						.encode( usuario.getPassword() ));
				usuario.addAuthority("ROLE_TRABAJADOR");
				usuarioService.save(usuario);
				status.setComplete();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/personal";
	}
}