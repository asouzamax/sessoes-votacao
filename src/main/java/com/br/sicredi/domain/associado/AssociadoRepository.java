package com.br.sicredi.domain.associado;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssociadoRepository extends JpaRepository<Associado, String> {

	Optional<Associado> findByNome(String ementa);
}