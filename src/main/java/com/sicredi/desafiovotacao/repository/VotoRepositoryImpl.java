package com.sicredi.desafiovotacao.repository;

import com.sicredi.desafiovotacao.model.AssociadoEntity;
import com.sicredi.desafiovotacao.model.PautaEntity;
import com.sicredi.desafiovotacao.model.VotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepositoryImpl extends JpaRepository<VotoEntity, Long> {
    boolean existsByPautaAndAssociado(PautaEntity pauta, AssociadoEntity associado);

    List<VotoEntity> findByPauta(PautaEntity pauta);
}
