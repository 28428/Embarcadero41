package pe.edu.upn.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.demo.entity.Pedidos;
import pe.edu.upn.demo.repository.PedidosRepository;
import pe.edu.upn.demo.service.PedidosService;

@Service
public class PedidosServiceimpl implements PedidosService{

	@Autowired
	private PedidosRepository pedidosRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Pedidos> findAll() throws Exception {
		return pedidosRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Pedidos> findById(String id) throws Exception {
		return pedidosRepository.findById(id);
	}

	@Transactional
	@Override
	public Pedidos save(Pedidos entity) throws Exception {
		return pedidosRepository.save(entity);
	}

	@Transactional
	@Override
	public Pedidos update(Pedidos entity) throws Exception {
		return pedidosRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(String id) throws Exception {
		pedidosRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		pedidosRepository.deleteAll();
	}

}
