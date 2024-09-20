package com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.request.RequestNewConvocatoria;
import com.serviciosAdministrativos.servicios.api.models.convocatorias.request.RequestUpdateConvocatoria;
import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.ConvocatoriaResponse;
import com.serviciosAdministrativos.servicios.domain.entities.DBConvocatorias.ConvocatoriasEntity;

import java.util.Map;
import java.util.Set;

public interface IConvocatoriaServices {
    Set<ConvocatoriaResponse> getConvocatorias();
    Map<String, String> postConvocatoria(RequestNewConvocatoria requestNewConvocatoria);

    ConvocatoriasEntity putConvocatoria(Integer id, RequestUpdateConvocatoria requestUpdate);
}
