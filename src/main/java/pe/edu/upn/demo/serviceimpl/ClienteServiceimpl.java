package pe.edu.upn.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.demo.entity.Cliente;
import pe.edu.upn.demo.repository.ClienteRepository;
import pe.edu.upn.demo.service.ClienteService;

@Service
public class ClienteServiceimpl implements ClienteService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Cliente> findAll() throws Exception {
		return clienteRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Cliente> findById(String id) throws Exception {
		return clienteRepository.findById(id);
	}

	@Transactional
	@Override
	public Cliente save(Cliente entity) throws Exception {
		return clienteRepository.save(entity);
	}

	@Transactional
	@Override
	public Cliente update(Cliente entity) throws Exception {
		return clienteRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(String id) throws Exception {
		clienteRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		clienteRepository.deleteAll();
	}

}
