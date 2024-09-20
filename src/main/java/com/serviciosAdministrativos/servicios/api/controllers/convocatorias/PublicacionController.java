package com.serviciosAdministrativos.servicios.api.controllers.convocatorias;


import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.PublicacionesResponse;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IPublicacionesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/publication")
@CrossOrigin(origins = "*")
public class PublicacionController {
    private final IPublicacionesService publicacionesService;

    public PublicacionController(IPublicacionesService publicacionesService) {
        this.publicacionesService = publicacionesService;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Set<PublicacionesResponse>> getPublicacion(@PathVariable Integer id) {
        return ResponseEntity.ok(this.publicacionesService.getPublicaciones(id));
    }
}
