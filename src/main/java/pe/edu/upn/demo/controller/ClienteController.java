package pe.edu.upn.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upn.demo.entity.Cliente;
import pe.edu.upn.demo.entity.Pedidos;
import pe.edu.upn.demo.entity.Personal;
import pe.edu.upn.demo.entity.Platos;
import pe.edu.upn.demo.entity.Usuario;
import pe.edu.upn.demo.service.ClienteService;
import pe.edu.upn.demo.service.PedidosService;
import pe.edu.upn.demo.service.PersonalService;
import pe.edu.upn.demo.service.PlatosService;
import pe.edu.upn.demo.service.UsuarioService;


@Controller
@RequestMapping("/cliente")
@SessionAttributes({"cliente","usuario"})

public class ClienteController {

@Autowired
private ClienteService clienteService;

@Autowired
private PedidosService pedidoService;
@Autowired
private PlatosService platosService;

@Autowired
private PersonalService personalService;

@Autowired
private UsuarioService usuarioService;

@Autowired
    private PasswordEncoder passwordEncoder;

@GetMapping
public String inicio(Model model) {
 try {
  List<Cliente> clientes= clienteService.findAll();
  model.addAttribute("clientes", clientes);
 } catch (Exception e) {
  // TODO: handle exception
 }
 return "/cliente/inicio";
}

@GetMapping("/nuevo")
public String nuevo(Model model) {
 Cliente cliente= new Cliente();
 model.addAttribute("cliente", cliente);
 try {

 } catch (Exception e) {
  // TODO: handle exception
 }
 return "/cliente/nuevo";
}

@GetMapping("/edit/{id}")
public String editar(@PathVariable("id") String id, Model model) {
 try {
  Optional<Cliente> optional = clienteService.findById(id);
  if (optional.isPresent()) {
   
   model.addAttribute("cliente", optional.get());
   
  } else {
   return "redirect:/cliente";
  }
 } catch (Exception e) {
  // TODO: handle exception
 }
 return "/cliente/edit";
}

@GetMapping("/del/{id}")
public String eliminar(@PathVariable("id") String id, Model model) {
 try {
  Optional<Cliente> cliente = clienteService.findById(id);
  if (cliente.isPresent()) {
   clienteService.deleteById(id);
  }
 } catch (Exception e) {
  model.addAttribute("dangerDel", "ERROR");
  try {
   List<Cliente> cliente = clienteService.findAll();
   model.addAttribute("cliente", cliente);
  } catch (Exception e2) {
   // TODO: handle exception
  }
  return "/cliente/inicio";
 }
 return "redirect:/cliente";
}

@GetMapping("/info/{id}")
public String info(@PathVariable("id")  String id, Model model) {
 try {
  Optional<Cliente> cliente = clienteService.findById(id);
  //Optional<Platos> platos =platosService.findById(id1);
  if (cliente.isPresent()) {
   model.addAttribute("cliente", cliente.get());
  } else {
   return "redirect:/cliente";
  }
 } catch (Exception e) {
  // TODO: handle exception
 }
 return "/cliente/info";
}


@PostMapping("/save")
public String save(@ModelAttribute("cliente") Cliente cliente, Model model, SessionStatus status,
		@Valid Cliente clien, BindingResult result) {
	if (result.hasErrors()) {
		return "/cliente/nuevo";
	}
	
 try {
  clienteService.save(cliente);
  status.setComplete();
 } catch (Exception e) {
  // TODO: handle exception
 }
 return "redirect:/cliente";
}

@GetMapping("/{id}/nuevopedido")
public String nuevoPedido(@PathVariable("id") String id, Model model) {
	Pedidos pedido= new Pedidos();
	try {
		Optional<Cliente> cliente = clienteService.findById(id);
		List<Platos> platos = platosService.findAll();
		List<Personal> personales = personalService.findAll();
		model.addAttribute("platos", platos);
		model.addAttribute("personales", personales);
		if (cliente.isPresent()) {
			pedido.setCliente(cliente.get());
			model.addAttribute("pedido", pedido);
		} else {
			return "redirect:/cliente";
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	return "/cliente/nuevopedido";
}
@PostMapping("/savepedido")
public String savePedido(@ModelAttribute("pedido")Pedidos pedido, Model model, SessionStatus status) {
	try {
		pedidoService.save(pedido);
		status.setComplete();
	} catch (Exception e) {
		System.out.println("Ocurrio un error");
	}
	return "redirect:/cliente/info/" + pedido.getCliente().getId();
}



@GetMapping("/{id}/nuevousuario")
public String nuevoUsuario(@PathVariable("id") String id, Model model) {
	Usuario usuario= new Usuario();
	try {
		Optional<Cliente> cliente = clienteService.findById(id);
		if (cliente.isPresent()) {
			usuario.setCliente(cliente.get());
			model.addAttribute("usuario", usuario);
		} else {
			return "redirect:/cliente";
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	return "/cliente/nuevousuario";
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
			return "/cliente";
		} else {
			usuario.setPassword(passwordEncoder
					.encode( usuario.getPassword() ));
			usuario.addAuthority("ROLE_CLIENTE");
			usuarioService.save(usuario);
			status.setComplete();
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	return "redirect:/cliente";
}
}
