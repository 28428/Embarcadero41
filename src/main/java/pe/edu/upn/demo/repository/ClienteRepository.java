package pe.edu.upn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upn.demo.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{

}
