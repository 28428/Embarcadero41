package pe.edu.upn.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.demo.entity.Personal;
import pe.edu.upn.demo.repository.PersonalRepository;
import pe.edu.upn.demo.service.PersonalService;

@Service
public class PersonalServiceimpl implements PersonalService{

	@Autowired
	private PersonalRepository personalRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Personal> findAll() throws Exception {
		return personalRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Personal> findById(String id) throws Exception {
		return personalRepository.findById(id);
	}

	@Transactional
	@Override
	public Personal save(Personal entity) throws Exception {
		return personalRepository.save(entity);
	}

	@Transactional
	@Override
	public Personal update(Personal entity) throws Exception {
		return personalRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(String id) throws Exception {
		personalRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		personalRepository.deleteAll();
	}

	
}
