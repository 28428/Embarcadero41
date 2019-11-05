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

import pe.edu.upn.demo.entity.Pedidos;
import pe.edu.upn.demo.entity.Platos;
import pe.edu.upn.demo.service.PedidosService;
import pe.edu.upn.demo.service.PlatosService;

@Controller
@RequestMapping("/platos")
@SessionAttributes( {"plato", "pedidos" } )
public class PlatosController {

@Autowired
private PlatosService platosService;


@Autowired
private PedidosService pedidosService;

@GetMapping
public String inicio(Model model) {
 try {
  List<Platos> platos = platosService.findAll();
  model.addAttribute("platos", platos);
 } catch (Exception e) {
  // TODO: handle exception
 }
 return "/platos/inicio";
}

@GetMapping("/edit/{id}")
public String editar(@PathVariable("id") Integer id, Model model) {
 try {
  Optional<Platos> optional = platosService.findById(id);
  if (optional.isPresent()) {
   
   model.addAttribute("plato", optional.get());

  } else {
   return "redirect:/plato";
  }
 } catch (Exception e) {
  // TODO: handle exception
 }
 
 return "/platos/edit";
}

@PostMapping("/save")
public String save(@ModelAttribute("plato") Platos plato,
  Model model, SessionStatus status) {
 try {
	 System.out.println(plato.getDescripcion());
  platosService.save(plato);
  status.setComplete();
 
 } catch (Exception e) {
  System.out.println(e.getMessage());
 }
 return "redirect:/platos";
}




@GetMapping("/nuevo")
public String nuevo(Model model) {
 Platos plato= new Platos();
 model.addAttribute("plato", plato);
 try {

 } catch (Exception e) {
  // TODO: handle exception
 }
 return "/platos/nuevo";
}

@GetMapping("/del/{id}")
public String eliminar(@PathVariable("id") Integer id, Model model) {
 try {
  Optional<Platos> plato = platosService.findById(id);
  if(plato.isPresent()) {
   platosService.deleteById(id);
  }
 } catch (Exception e) {
 
  model.addAttribute("dangerDel", "ERROR - Violación contra el principio de Integridad referencia");
  try {
   List<Platos> platos = platosService.findAll();
   model.addAttribute("platos", platos);
  } catch (Exception e2) {
   // TODO: handle exception
  }
  return "/platos/inicio";
 }
 return "redirect:/platos";
}



@GetMapping("/{id}/nuevopedido")
public String nuevoPedido(@PathVariable("id") String id, Model model) {
 Pedidos pedidos = new Pedidos();
 try {
	 List<Platos> platos = platosService.findAll();
	 
	 model.addAttribute("platos", platos);
      model.addAttribute("pedido", pedidos);
   
   

 } catch (Exception e) {
  // TODO: handle exception
 }
 return "/pedido/nuevopedido";
}

@PostMapping("/savepedido")
public String savePedido(@ModelAttribute("pedido") Pedidos pedidos,
  Model model, SessionStatus status) {
 try {
  pedidosService.save(pedidos);
  status.setComplete();
 
 } catch (Exception e) {
  // TODO: handle exception
 }
 return "redirect:/plato/info/";
}

}
