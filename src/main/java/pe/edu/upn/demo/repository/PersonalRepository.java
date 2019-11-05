package pe.edu.upn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upn.demo.entity.Personal;

public interface PersonalRepository extends JpaRepository<Personal, String>{

}
