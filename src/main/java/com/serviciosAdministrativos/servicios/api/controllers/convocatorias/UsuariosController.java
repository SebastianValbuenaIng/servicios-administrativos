package com.serviciosAdministrativos.servicios.api.controllers.convocatorias;

import com.serviciosAdministrativos.servicios.domain.entities.DBConvocatorias.UsuariosEntity;
import com.serviciosAdministrativos.servicios.domain.repositories.DBConvocatorias.UsuariosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuariosConvocatorias")
@CrossOrigin(origins = "*")
public class UsuariosController {
    private final UsuariosRepository usuariosRepository;

    public UsuariosController(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @GetMapping
    public ResponseEntity<?> getUsuarios(@RequestParam String email) {
        Optional<UsuariosEntity> findUsuarioByEmail = usuariosRepository.findByCorreo(email);

        if (findUsuarioByEmail.isPresent()) {
            Map<String, String> responseUsuario = new HashMap<>();
            responseUsuario.put("correo", findUsuarioByEmail.get().getCorreo());
            responseUsuario.put("rol", findUsuarioByEmail.get().getRol());

            return ResponseEntity.ok(responseUsuario);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Correo no encontrado"));
    }
}
