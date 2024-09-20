package com.serviciosAdministrativos.servicios.infrastructure.services.votaciones.DBTwo;

import com.serviciosAdministrativos.servicios.domain.entities.DBOne.PersonaActivaEntity;
import com.serviciosAdministrativos.servicios.domain.entities.DBTwo.VotacionActivaEntity;
import com.serviciosAdministrativos.servicios.domain.entities.DBTwo.VotanteEntity;
import com.serviciosAdministrativos.servicios.domain.repositories.DBOne.PersonaActivaRepository;
import com.serviciosAdministrativos.servicios.domain.repositories.DBTwo.VotacionActivaRepository;
import com.serviciosAdministrativos.servicios.domain.repositories.DBTwo.VotanteRepository;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.votaciones.IVotacionActivaService;
import com.serviciosAdministrativos.servicios.util.errors.NotFoundError;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(value = "db2TransactionManager")
public class VotacionActivaService implements IVotacionActivaService {
    private final VotacionActivaRepository votacionActivaRepository;
    private final VotanteRepository votanteRepository;
    private final PersonaActivaRepository personaActivaRepository;

    public VotacionActivaService(VotanteRepository votanteRepository,
                                 VotacionActivaRepository votacionActivaRepository,
                                 PersonaActivaRepository personaActivaRepository) {
        this.votacionActivaRepository = votacionActivaRepository;
        this.personaActivaRepository = personaActivaRepository;
        this.votanteRepository = votanteRepository;
    }

    public List<Map<String, String>> buscarVotaciones(Integer estado, String email) {
        PersonaActivaEntity personaActivaFind = personaActivaRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundError("email no encontrado"));

        VotanteEntity votanteFind = votanteRepository.findByIdentificacion(personaActivaFind.getNroDocumento())
                .orElseThrow(() -> new NotFoundError("No existe en votante"));

        List<VotacionActivaEntity> votacionesActivas = votacionActivaRepository.findAllByEstado(estado);

        if (votacionesActivas.isEmpty()) throw new NotFoundError("No se han encontrado Votaciones");

        List<Map<String, String>> votacionesPersona = new ArrayList<>();

        for (VotacionActivaEntity votacion : votacionesActivas) {
            Map<String, String> nuevaVotacion = getVotacionesActivasEmpleado(votacion, votanteFind);

            votacionesPersona.add(nuevaVotacion);
        }

        return votacionesPersona.stream().filter(stringStringMap -> !stringStringMap.get("ruta").isEmpty()).toList();
    }

    private Map<String, String> getVotacionesActivasEmpleado(VotacionActivaEntity votacion, VotanteEntity votanteFind) {
        Map<String, String> nuevaVotacion = new HashMap<>();
        nuevaVotacion.put("id", votacion.getId().toString());
        nuevaVotacion.put("nombre", votacion.getNombre());

        if (votacion.getId() == 1 && votanteFind.getCopasst() != null) {
            nuevaVotacion.put("estado_voto", votanteFind.getCopasst().toString());
            nuevaVotacion.put("ruta", "/votcopasst");
            nuevaVotacion.put("idcrp", "120");
        } else if (votacion.getId() == 3 && votanteFind.getCcl() != null) {
            nuevaVotacion.put("estado_voto", votanteFind.getCcl().toString());
            nuevaVotacion.put("ruta", "/ccl");
            nuevaVotacion.put("idcrp", "121");
        } else if (votacion.getId() == 17 && votanteFind.getVca_prof() != null) {
            nuevaVotacion.put("estado_voto", votanteFind.getVca_prof().toString());
            nuevaVotacion.put("ruta", "/vcaprof");
            nuevaVotacion.put("idcrp", "118");
        } else if (votacion.getId() == 18 && votanteFind.getVcd_prof() != null) {
            nuevaVotacion.put("estado_voto", votanteFind.getVcd_prof().toString());
            nuevaVotacion.put("ruta", "/vcdprof");
            nuevaVotacion.put("idcrp", "119");
        } else {
            nuevaVotacion.put("ruta", "");
        }

        return nuevaVotacion;
    }
}