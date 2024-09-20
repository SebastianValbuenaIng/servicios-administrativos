package com.serviciosAdministrativos.servicios.infrastructure.services.votaciones.DBTwo;

import com.serviciosAdministrativos.servicios.api.models.votaciones.request.VotoRequest;
import com.serviciosAdministrativos.servicios.domain.entities.DBOne.PersonaActivaEntity;
import com.serviciosAdministrativos.servicios.domain.entities.DBTwo.VotacionesEntity;
import com.serviciosAdministrativos.servicios.domain.entities.DBTwo.VotanteEntity;
import com.serviciosAdministrativos.servicios.domain.repositories.DBOne.PersonaActivaRepository;
import com.serviciosAdministrativos.servicios.domain.repositories.DBTwo.VotacionesRepository;
import com.serviciosAdministrativos.servicios.domain.repositories.DBTwo.VotanteRepository;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.votaciones.IVotoActionsService;
import com.serviciosAdministrativos.servicios.util.errors.NotFoundError;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@Service
@Transactional("db2TransactionManager")
public class VotoActionsService implements IVotoActionsService {
    private final VotanteRepository votanteRepository;
    private final VotacionesRepository votacionesRepository;
    private final PersonaActivaRepository personaActivaRepository;

    public VotoActionsService(VotanteRepository votanteRepository,
                              VotacionesRepository votacionesRepository,
                              PersonaActivaRepository personaActivaRepository) {
        this.votanteRepository = votanteRepository;
        this.votacionesRepository = votacionesRepository;
        this.personaActivaRepository = personaActivaRepository;
    }

    @Override
    public Map<String, String> save(VotoRequest votoRequest, String email) {
        PersonaActivaEntity votanteFindEmail = personaActivaRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundError("No existe en personas activas"));

        VotanteEntity votanteFind = votanteRepository.findByIdentificacion(votanteFindEmail.getNroDocumento())
                .orElseThrow(() -> new NotFoundError("No existe en votante"));

        // Conocer el periodo actual
        String periodo;

        if (LocalDate.now().getMonthValue() < 7)
            periodo = LocalDate.now().getYear() + "-1";
        else
            periodo = LocalDate.now().getYear() + "-2";


        for (Integer nroton : votoRequest.getCandidatos()) {
            VotacionesEntity saveVotaciones = new VotacionesEntity(
                    votoRequest.getIdcrp(),
                    nroton,
                    LocalDateTime.now(),
                    periodo
            );

            votacionesRepository.save(saveVotaciones);

            if (votanteFind.getCcl() != null && votanteFind.getCcl() == 0 && votoRequest.getIdcrp() == 121) {
                votanteFind.setCcl(1);
                votanteFind.setFecha_ccl(LocalDate.now());
            } else if ((votanteFind.getCopasst() != null && votanteFind.getCopasst() == 0) && votoRequest.getIdcrp() == 120) {
                votanteFind.setCopasst(1);
                votanteFind.setFecha_copasst(LocalDate.now());
            } else if ((votanteFind.getVca_prof() != null && votanteFind.getVca_prof() == 0) && votoRequest.getIdcrp() == 118) {
                votanteFind.setVca_prof(1);
                votanteFind.setFecha_vcaprof(LocalDate.now());
            } else if ((votanteFind.getVcd_prof() != null && votanteFind.getVcd_prof() == 0) && votoRequest.getIdcrp() == 119) {
                votanteFind.setVcd_prof(1);
                votanteFind.setFecha_vcdprof(LocalDate.now());
            }

            votanteRepository.save(votanteFind);
        }

        return Collections.singletonMap("message", "Se guardó correctamente");
    }
}
