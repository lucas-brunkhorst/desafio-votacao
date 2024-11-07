package com.example.desafiovotacao.sicredi.repository;

import com.example.desafiovotacao.sicredi.model.AssociadoEntity;
import com.example.desafiovotacao.sicredi.model.PautaEntity;
import com.example.desafiovotacao.sicredi.model.VotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepositoryImpl extends JpaRepository<VotoEntity, Long> {
    boolean existsByPautaAndAssociado(PautaEntity pauta, AssociadoEntity associado);

    List<VotoEntity> findByPauta(PautaEntity pauta);
}
