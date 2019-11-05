package pe.edu.upn.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.demo.entity.Platos;
import pe.edu.upn.demo.repository.PlatosRepository;
import pe.edu.upn.demo.service.PlatosService;

@Service
public class PlatosServiceimpl implements PlatosService{

	@Autowired
	private PlatosRepository platosRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Platos> findAll() throws Exception {
		return platosRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Platos> findById(Integer id) throws Exception {
		return platosRepository.findById(id);
	}

	@Transactional
	@Override
	public Platos save(Platos entity) throws Exception {
		return platosRepository.save(entity);
	}

	@Transactional
	@Override
	public Platos update(Platos entity) throws Exception {
		return platosRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		platosRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		platosRepository.deleteAll();
	}


}

