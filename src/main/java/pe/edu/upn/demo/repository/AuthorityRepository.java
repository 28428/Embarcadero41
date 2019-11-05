package pe.edu.upn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upn.demo.entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
