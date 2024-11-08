package com.sicredi.desafiovotacao.repository;

import com.sicredi.desafiovotacao.model.PautaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepositoryImpl extends JpaRepository<PautaEntity, Long> {
}
