package com.demo.repository.operacion.metodos;

import com.demo.model.operacion.metodos.FRA_EAUV_001;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FRA_EAUV_001_Repository extends JpaRepository<FRA_EAUV_001, Long>{
    FRA_EAUV_001 findByIdFRAEAUV(Long id);
    FRA_EAUV_001 findByMetodoMuestra_MetodoMuestraId(Long id);
}
