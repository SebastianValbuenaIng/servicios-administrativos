package com.serviciosAdministrativos.servicios.infrastructure.abstract_services.fondoSolidaridad;

import com.serviciosAdministrativos.servicios.api.models.fondoSolidaridad.request.FondoSolidaridadRequest;

import java.util.Map;

public interface IFondoSolidaridadService {
    Map<String, String> postFondoSolidaridad(FondoSolidaridadRequest fondoSolidaridadRequest);
}
