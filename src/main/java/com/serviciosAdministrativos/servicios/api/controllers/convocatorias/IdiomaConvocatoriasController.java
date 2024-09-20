package com.serviciosAdministrativos.servicios.api.controllers.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.IdiomaResponse;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IIdiomaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/language")
@CrossOrigin(origins = "*")
public class IdiomaConvocatoriasController {

    public IdiomaConvocatoriasController(IIdiomaService idiomaService) {
        this.idiomaService = idiomaService;
    }

    public final IIdiomaService idiomaService;

    @GetMapping(path = "{cedula}")
    public ResponseEntity<Set<IdiomaResponse>> findAllByCedula(@PathVariable String cedula) {
        return ResponseEntity.ok(this.idiomaService.findAllByCedula(cedula));
    }
}
