package com.serviciosAdministrativos.servicios.api.controllers.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.ExperienceResponse;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IExperienceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/experience")
@CrossOrigin(origins = "*")
public class ExperiencieController {
    public final IExperienceService experienceService;

    public ExperiencieController(IExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Set<ExperienceResponse>> getExperiencia(@PathVariable Integer id) {
        return ResponseEntity.ok(this.experienceService.getExperiencia(id));
    }
}
