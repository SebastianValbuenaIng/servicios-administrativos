package com.serviciosAdministrativos.servicios.api.controllers.votaciones;


import com.serviciosAdministrativos.servicios.api.models.votaciones.request.VotoRequest;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.votaciones.IVcaProfService;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.votaciones.IVotacionesSecurity;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.votaciones.IVotoActionsService;
import com.serviciosAdministrativos.servicios.util.errors.NotFoundError;
import com.serviciosAdministrativos.servicios.util.errors.SecurityError;
import com.serviciosAdministrativos.servicios.util.errors.ValidationErrorHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vcaprof")
public class VcaProfController {
    private final IVcaProfService iVcaProfService;
    private final IVotacionesSecurity iVotacionesSecurity;
    private final IVotoActionsService iVotoActionsService;

    public VcaProfController(IVcaProfService iVcaProfService,
                             IVotacionesSecurity iVotacionesSecurity,
                             IVotoActionsService iVotoActionsService) {
        this.iVcaProfService = iVcaProfService;
        this.iVotacionesSecurity = iVotacionesSecurity;
        this.iVotoActionsService = iVotoActionsService;
    }

    @GetMapping("/estado_voto")
    public ResponseEntity<?> get(@RequestParam String email) {
        try {
            return ResponseEntity.ok(iVcaProfService.verificarEstadoVoto(email));
        } catch (NotFoundError e) {
            return ValidationErrorHandler.handleNotFound(e);
        }
    }

    @GetMapping("/candidatos")
    public ResponseEntity<?> get() {
        try {
            return ResponseEntity.ok(iVcaProfService.buscarCandidatos());
        } catch (SecurityError e) {
            return ValidationErrorHandler.handleValidation(e);
        } catch (NotFoundError e) {
            return ValidationErrorHandler.handleNotFound(e);
        }
    }

    @PostMapping("/votar")
    public ResponseEntity<?> post(@RequestBody VotoRequest votacionesRequest, @RequestParam String email) {
        try {
            iVotacionesSecurity.userAuthorized(email);
            return ResponseEntity.ok(iVotoActionsService.save(votacionesRequest, email));
        } catch (SecurityError e) {
            return ValidationErrorHandler.handleValidation(e);
        } catch (NotFoundError e) {
            return ValidationErrorHandler.handleNotFound(e);
        }
    }
}
