package com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.ParticipantResponse;

public interface IParticipantService {
    ParticipantResponse getInfoAspirant(String document);

}
