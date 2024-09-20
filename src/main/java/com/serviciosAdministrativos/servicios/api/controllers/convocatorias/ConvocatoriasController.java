package com.serviciosAdministrativos.servicios.api.controllers.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.request.RequestNewConvocatoria;
import com.serviciosAdministrativos.servicios.api.models.convocatorias.request.RequestUpdateConvocatoria;
import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.ConvocatoriaResponse;
import com.serviciosAdministrativos.servicios.domain.entities.DBConvocatorias.ConvocatoriasEntity;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IConvocatoriaServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/announcement")
@CrossOrigin(origins = "*")
public class ConvocatoriasController {
    private final IConvocatoriaServices convocatoriaServices;

    public ConvocatoriasController(IConvocatoriaServices convocatoriaServices) {
        this.convocatoriaServices = convocatoriaServices;
    }

    @GetMapping
    public ResponseEntity<Set<ConvocatoriaResponse>> getConvocatorias() {
        return ResponseEntity.ok(this.convocatoriaServices.getConvocatorias());
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> postConvocatoria(@RequestBody RequestNewConvocatoria request) {
        return ResponseEntity.ok(this.convocatoriaServices.postConvocatoria(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<ConvocatoriasEntity> putConvocatoria(@PathVariable Integer id, @RequestBody RequestUpdateConvocatoria requestUpdate) {
        return ResponseEntity.ok(this.convocatoriaServices.putConvocatoria(id, requestUpdate));
    }
}
