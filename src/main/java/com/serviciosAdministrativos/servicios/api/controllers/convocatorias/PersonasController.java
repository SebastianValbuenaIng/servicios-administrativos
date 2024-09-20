package com.serviciosAdministrativos.servicios.api.controllers.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.PersonaResponse;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IPersonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/people")
@CrossOrigin(origins = "*")
public class PersonasController {
    private final IPersonaService personaService;

    public PersonasController(IPersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public ResponseEntity<Set<PersonaResponse>> getPersonas() {
        return ResponseEntity.ok(this.personaService.getPersonas());
    }
}
