package com.serviciosAdministrativos.servicios.api.controllers.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.ParticipantResponse;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/infoAspirant")
@CrossOrigin(origins = "*")
public class ParticipantController {
    public final IParticipantService participantService;

    public ParticipantController(IParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping(path = "{document}")
    public ResponseEntity<ParticipantResponse> getInfoAspirant(@PathVariable String document) {
        return ResponseEntity.ok(participantService.getInfoAspirant(document));
    }

}
