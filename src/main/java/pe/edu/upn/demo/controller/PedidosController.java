package pe.edu.upn.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import pe.edu.upn.demo.service.ClienteService;
import pe.edu.upn.demo.service.PedidosService;
import pe.edu.upn.demo.service.PersonalService;
import pe.edu.upn.demo.service.PlatosService;

@Controller
@RequestMapping("/pedidos")
@SessionAttributes( {"plato", "pedido" , "cliente" , "personal" , } )
public class PedidosController {

@Autowired
private PlatosService platosService;


@Autowired
private PedidosService pedidosService;

@Autowired
private ClienteService clienteService;

@Autowired
private PersonalService personalService;


@GetMapping
public String inicio(Model model) {
 try {
  List<Pedidos> pedidos = pedidosService.findAll();
  model.addAttribute("pedidos", pedidos);
 } catch (Exception e) {
  // TODO: handle exception
 }
 return "/pedido/inicio";
}

@GetMapping("/edit/{id}")
public String editar(@PathVariable("id") String id, Model model) {
	try {
		Optional<Pedidos> optional = pedidosService.findById(id);
		if (optional.isPresent()) {
			
			List<Platos> platos
				= platosService.findAll(); 
			List<Cliente> clientes = clienteService.findAll();
			List<Personal> personales = personalService.findAll();
			
			model.addAttribute("pedido", optional.get());
			model.addAttribute("platos", platos);
			model.addAttribute("clientes", clientes);
			model.addAttribute("personales", personales);
			
		} else {
			return "redirect:/pedido";
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	return "/pedido/edit";
}




@PostMapping("/save")
public String save(@ModelAttribute("pedido") Pedidos pedido,
  Model model, SessionStatus status) {
 try {
  //System.out.println(pedido.getDescripcion());
  pedidosService.save(pedido);
  status.setComplete();
 
 } catch (Exception e) {
  System.out.println(e.getMessage());
 }
 return "redirect:/pedido";
}

@GetMapping("/nuevo")
public String nuevo(Model model) {
 Pedidos pedido= new Pedidos();
 model.addAttribute("pedido", pedido);
 try {
	 List<Platos> platos = platosService.findAll();
	 model.addAttribute("platos", platos);
	 List<Cliente> clientes= clienteService.findAll();
	 model.addAttribute("clientes", clientes);
	 List<Personal> personales=personalService.findAll();
	 model.addAttribute("personales", personales);
	 
 } catch (Exception e) {
  // TODO: handle exception
 }
 return "/pedido/nuevo";
}

@GetMapping("/del/{id}")
public String eliminar(@PathVariable("id") String id, Model model) {
 try {
  Optional<Pedidos> pedido = pedidosService.findById(id);
  if(pedido.isPresent()) {
   pedidosService.deleteById(id);
  }
 } catch (Exception e) {
 
  model.addAttribute("dangerDel", "ERROR - Violación contra el principio de Integridad referencia");
  try {
   List<Pedidos> pedidos =pedidosService.findAll();
   model.addAttribute("pedidos", pedidos);

  } catch (Exception e2) {
   // TODO: handle exception
  }
  return "/pedido/inicio";
 }
 return "redirect:/pedido";
}

@GetMapping("/info/{id}")
public String info(@PathVariable("id") String id, Model model) {
	try {
		Optional<Pedidos> pedido = pedidosService.findById(id);
		if(pedido.isPresent()) {
			model.addAttribute("pedido", pedido.get());
		} else {
			return "redirect:/pedido";
		}
	} catch (Exception e) {

	}	
	
	return "/pedido/info";
}



}
