package com.serviciosAdministrativos.servicios.api.controllers.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.ProyectoResponse;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IProyectoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/proyects")
@CrossOrigin(origins =  "*")
public class ProyectoController {
    private final IProyectoService proyectoService;

    public ProyectoController(IProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Set<ProyectoResponse>> getProyectos(@PathVariable Integer id){
        return ResponseEntity.ok(this.proyectoService.getProyectos(id));
    }
}
