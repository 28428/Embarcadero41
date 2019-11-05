package pe.edu.upn.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upn.demo.entity.Cliente;
import pe.edu.upn.demo.service.ClienteService;


@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public String inicio(Model model) {
		return "/menu";
	}
	
	@GetMapping("/clientes")
	public String alumnos() {
		return "/cliente/inicio";
	}
	
	@GetMapping("/menuestudiante/{id}")
	public String menuEstudiante(@PathVariable("id") String id, Model model) {
		
		try {
			Optional<Cliente> clientes= clienteService.findById(id);

			if (clientes.isPresent()) {
				model.addAttribute("clientes", clientes.get());

			} else {
				return "redirect:/pedido";
			}
		} catch (Exception e) {
		}
		
		return "/menucliente";
	}
}