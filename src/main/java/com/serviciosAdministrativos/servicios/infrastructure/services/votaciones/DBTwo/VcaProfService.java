package com.serviciosAdministrativos.servicios.infrastructure.services.votaciones.DBTwo;

import com.serviciosAdministrativos.servicios.domain.entities.DBOne.PersonaActivaEntity;
import com.serviciosAdministrativos.servicios.domain.entities.DBTwo.CandidatosEntity;
import com.serviciosAdministrativos.servicios.domain.entities.DBTwo.VotanteEntity;
import com.serviciosAdministrativos.servicios.domain.repositories.DBOne.PersonaActivaRepository;
import com.serviciosAdministrativos.servicios.domain.repositories.DBTwo.CandidatosRepository;
import com.serviciosAdministrativos.servicios.domain.repositories.DBTwo.VotanteRepository;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.votaciones.IVcaProfService;
import com.serviciosAdministrativos.servicios.util.errors.NotFoundError;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(value = "db2TransactionManager", readOnly = true)
public class VcaProfService implements IVcaProfService {
    private final CandidatosRepository candidatosRepository;
    private final VotanteRepository votanteRepository;
    private final PersonaActivaRepository personaActivaRepository;

    public VcaProfService(CandidatosRepository candidatosRepository,
                          VotanteRepository votanteRepository,
                          PersonaActivaRepository personaActivaRepository) {
        this.candidatosRepository = candidatosRepository;
        this.votanteRepository = votanteRepository;
        this.personaActivaRepository = personaActivaRepository;
    }

    @Override
    public Map<String, String> verificarEstadoVoto(String email) {
        PersonaActivaEntity personaActivaFind = personaActivaRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundError("Email no encontrado"));

        VotanteEntity votanteFind = votanteRepository.findByIdentificacion(personaActivaFind.getNroDocumento())
                .orElseThrow(() -> new NotFoundError("No se han encontrado votaciones"));

        Map<String, String> estado = new HashMap<>();
        estado.put("estado", votanteFind.getVca_prof().toString());
        return estado;
    }

    @Override
    public List<CandidatosEntity> buscarCandidatos() {
        List<CandidatosEntity> buscarCandidatos = candidatosRepository.findAllByIdcrp(118);

        if (buscarCandidatos.isEmpty()) throw new NotFoundError("No se encontraron votaciones con ese código");

        return buscarCandidatos;
    }
}
