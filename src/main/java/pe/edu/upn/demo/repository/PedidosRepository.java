package pe.edu.upn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upn.demo.entity.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos,String> {

}
