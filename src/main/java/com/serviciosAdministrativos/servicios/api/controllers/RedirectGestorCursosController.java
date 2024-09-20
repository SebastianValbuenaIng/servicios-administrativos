package com.serviciosAdministrativos.servicios.api.controllers;

import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.registerToken.TokenRegisterServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.logging.Logger;

import static com.serviciosAdministrativos.servicios.util.utils.redirectGestorCursos.Encriptar.EncriptarCadena;
import static com.serviciosAdministrativos.servicios.util.utils.redirectGestorCursos.Encriptar.randomAlphaNumeric;

@RestController
@RequestMapping("/redirectGestorCursos")
@CrossOrigin(origins = "*")
public class RedirectGestorCursosController {
    private final TokenRegisterServiceImpl tokenRegistroService;

    public RedirectGestorCursosController(TokenRegisterServiceImpl tokenRegistroService) {
        this.tokenRegistroService = tokenRegistroService;
    }

    @GetMapping
    public ResponseEntity<?> redirectGestorCursos(@RequestParam String id_emp) {
        String cadena = randomAlphaNumeric(20);
        String llave = randomAlphaNumeric(20);
        String cadena_encriptada = EncriptarCadena(cadena, llave);

        try {
            tokenRegistroService.generarTokenInicioGestorCursos(id_emp, llave, cadena_encriptada, "1");
        } catch (Exception ex) {
            Logger.getLogger("Ha ocurrido un error");
        }

        String url = "https://tycho.escuelaing.edu.co/GestionDeCursos/Services/Login?id_emp="+id_emp.trim()+"&cadena="+cadena;

        return ResponseEntity.ok(Map.of("url", url));
    }
}
