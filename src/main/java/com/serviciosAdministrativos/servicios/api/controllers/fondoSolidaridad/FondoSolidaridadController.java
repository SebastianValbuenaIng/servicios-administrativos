package com.serviciosAdministrativos.servicios.api.controllers.fondoSolidaridad;

import com.serviciosAdministrativos.servicios.api.models.fondoSolidaridad.request.FondoSolidaridadRequest;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.fondoSolidaridad.IFondoSolidaridadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fondoSolidaridad")
@CrossOrigin(origins = "*")
public class FondoSolidaridadController {
    private final IFondoSolidaridadService iFondoSolidaridadService;

    public FondoSolidaridadController(IFondoSolidaridadService iFondoSolidaridadService) {
        this.iFondoSolidaridadService = iFondoSolidaridadService;
    }

    @PostMapping
    public ResponseEntity<?> postFondoSolidaridad(@RequestBody FondoSolidaridadRequest fondoSolidaridadRequest) {
        return ResponseEntity.ok(iFondoSolidaridadService.postFondoSolidaridad(fondoSolidaridadRequest));
    }
}
