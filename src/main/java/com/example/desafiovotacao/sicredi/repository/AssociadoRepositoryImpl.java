package com.example.desafiovotacao.sicredi.repository;

import com.example.desafiovotacao.sicredi.model.AssociadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociadoRepositoryImpl extends JpaRepository<AssociadoEntity, Long> {
    Optional<AssociadoEntity> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
