package com.sicredi.desafiovotacao.repository;

import com.sicredi.desafiovotacao.model.AssociadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociadoRepositoryImpl extends JpaRepository<AssociadoEntity, Long> {
    Optional<AssociadoEntity> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
