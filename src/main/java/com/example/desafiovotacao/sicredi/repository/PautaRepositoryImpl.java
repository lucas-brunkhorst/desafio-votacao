package com.example.desafiovotacao.sicredi.repository;

import com.example.desafiovotacao.sicredi.model.PautaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepositoryImpl extends JpaRepository<PautaEntity, Long> {
}
