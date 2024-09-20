package com.serviciosAdministrativos.servicios.api.controllers.convocatorias;

import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IGenerarPDFAspiranteService;
import com.serviciosAdministrativos.servicios.util.errors.NotFoundError;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/generatePDF")
@CrossOrigin(origins = "*")
public class GenerarPDFDatosAspiranteController {
    private final IGenerarPDFAspiranteService generarPDFAspiranteService;

    public GenerarPDFDatosAspiranteController(IGenerarPDFAspiranteService generarPDFAspiranteService) {
        this.generarPDFAspiranteService = generarPDFAspiranteService;
    }

    @GetMapping
    public ResponseEntity<?> exportPDF(HttpServletResponse response, @RequestParam String id) {
        try {
            generarPDFAspiranteService.generatePDF(response, Integer.valueOf(id));
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            Map<String, String> message = new HashMap<>();
            message.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(message);
        }
    }

    @GetMapping("/support")
    public ResponseEntity<?> getSupportDocument(HttpServletResponse response, @RequestParam String id) {
        try {
            generarPDFAspiranteService.getFileSupport(response, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundError e) {
            Map<String, String> message = new HashMap<>();
            message.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(message);
        }
    }
}
