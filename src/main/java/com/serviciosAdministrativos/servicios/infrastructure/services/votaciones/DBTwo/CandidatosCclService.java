package com.serviciosAdministrativos.servicios.infrastructure.services.votaciones.DBTwo;

import com.serviciosAdministrativos.servicios.domain.entities.DBTwo.CandidatosEntity;
import com.serviciosAdministrativos.servicios.domain.repositories.DBTwo.CandidatosRepository;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.votaciones.ICandidatosCclService;
import com.serviciosAdministrativos.servicios.util.errors.NotFoundError;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(value = "db2TransactionManager", readOnly = true)
public class CandidatosCclService implements ICandidatosCclService {
    private final CandidatosRepository candidatosRepository;

    public CandidatosCclService(CandidatosRepository candidatosRepository) {
        this.candidatosRepository = candidatosRepository;
    }

    public List<CandidatosEntity> buscarCandidatosCcl(Integer idcrp) {
        List<CandidatosEntity> buscarCandidatosCcl = candidatosRepository.findAllByIdcrp(idcrp);

        if (buscarCandidatosCcl.isEmpty())
            throw new NotFoundError("No se encontraron votaciones con ese código");

        return buscarCandidatosCcl;
    }
}
