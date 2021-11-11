package api.spring.bluebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

import api.spring.bluebank.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

}
