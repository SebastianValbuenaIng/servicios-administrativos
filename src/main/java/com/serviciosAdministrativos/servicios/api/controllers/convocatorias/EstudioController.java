package com.serviciosAdministrativos.servicios.api.controllers.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.EstudiosResponse;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IEstudioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/study")
@CrossOrigin(origins = "*")
public class EstudioController {
    public final IEstudioService estudioService;

    public EstudioController(IEstudioService estudioService) {
        this.estudioService = estudioService;
    }


    @GetMapping(path = "{id}")
    public ResponseEntity<Set<EstudiosResponse>> getEstudios(@PathVariable String id) {
        return ResponseEntity.ok(estudioService.getEstudios(id));
    }
}
