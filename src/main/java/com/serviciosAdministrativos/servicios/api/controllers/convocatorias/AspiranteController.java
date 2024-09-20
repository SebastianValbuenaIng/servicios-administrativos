package com.serviciosAdministrativos.servicios.api.controllers.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.AspiranteResponse;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IAspiranteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/participant")
@CrossOrigin(origins = "*")
public class AspiranteController {
    public final IAspiranteService aspiranteService;

    public AspiranteController(IAspiranteService aspiranteService) {
        this.aspiranteService = aspiranteService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Set<AspiranteResponse>> getAspirantes(@PathVariable Integer id) {
        return ResponseEntity.ok(this.aspiranteService.getAspirantes(id));
    }
}
