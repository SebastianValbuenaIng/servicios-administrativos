package com.serviciosAdministrativos.servicios.infrastructure.services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.PersonaResponse;
import com.serviciosAdministrativos.servicios.domain.repositories.DBActualizacionDatos.GTHInfoEmpleadoRepository;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IPersonaService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonaService implements IPersonaService {
    private final GTHInfoEmpleadoRepository gthInfoEmpleadoRepository;

    public PersonaService(GTHInfoEmpleadoRepository gthInfoEmpleadoRepository) {
        this.gthInfoEmpleadoRepository = gthInfoEmpleadoRepository;
    }

    public Set<PersonaResponse> getPersonas() {
        List<Map<String, String>> people = gthInfoEmpleadoRepository.people();
        return people.stream().map(this::entityToResponse).sorted(Comparator.comparing(PersonaResponse::getNombre)).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private PersonaResponse entityToResponse(Map<String, String> person) {
        PersonaResponse response = new PersonaResponse();

        response.setCod_emp(Integer.parseInt(person.get("cod_emp").trim()));
        response.setNombre(person.get("nombre"));
        response.setCod_cl1(Integer.parseInt(person.get("cod_cl1").trim()));
        response.setArea(person.get("area"));
        response.setCorreo(person.get("e_mail"));

        return response;
    }
}
