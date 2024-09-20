package com.serviciosAdministrativos.servicios.infrastructure.services.votaciones.DBTwo;

import com.serviciosAdministrativos.servicios.domain.entities.DBTwo.CandidatosEntity;
import com.serviciosAdministrativos.servicios.domain.repositories.DBTwo.CandidatosRepository;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.votaciones.ICandidatosCopasstService;
import com.serviciosAdministrativos.servicios.util.errors.NotFoundError;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(value = "db2TransactionManager", readOnly = true)
public class CandidatosCopasstService implements ICandidatosCopasstService {
    private final CandidatosRepository candidatosRepository;

    public CandidatosCopasstService(CandidatosRepository candidatosRepository) {
        this.candidatosRepository = candidatosRepository;
    }

    public List<CandidatosEntity> buscarCandidatos() {
        List<CandidatosEntity> buscarCandidatos = candidatosRepository.findAllByIdcrp(120);

        if (buscarCandidatos.isEmpty()) throw new NotFoundError("No se encontraron votaciones con ese código");

        return buscarCandidatos;
    }
}