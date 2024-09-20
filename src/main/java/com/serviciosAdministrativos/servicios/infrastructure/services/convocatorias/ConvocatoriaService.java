package com.serviciosAdministrativos.servicios.infrastructure.services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.request.RequestNewConvocatoria;
import com.serviciosAdministrativos.servicios.api.models.convocatorias.request.RequestUpdateConvocatoria;
import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.ConvocatoriaResponse;
import com.serviciosAdministrativos.servicios.domain.entities.DBConvocatorias.ConvocatoriasEntity;
import com.serviciosAdministrativos.servicios.domain.entities.DBConvocatorias.UsuariosEntity;
import com.serviciosAdministrativos.servicios.domain.repositories.DBConvocatorias.ConvocatoriaRepository;
import com.serviciosAdministrativos.servicios.domain.repositories.DBConvocatorias.UsuariosRepository;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IConvocatoriaServices;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConvocatoriaService implements IConvocatoriaServices {
    private final ConvocatoriaRepository convocatoriaRepository;
    private final UsuariosRepository usuariosRepository;

    public ConvocatoriaService(ConvocatoriaRepository convocatoriaRepository,
                               UsuariosRepository usuariosRepository) {
        this.convocatoriaRepository = convocatoriaRepository;
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public Set<ConvocatoriaResponse> getConvocatorias() {
        List<ConvocatoriasEntity> convocatorias = convocatoriaRepository.findAll();
        return convocatorias
                .stream()
                .map(this::entityToResponse)
                .sorted(Comparator.comparing(ConvocatoriaResponse::getFfin).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Map<String, String> postConvocatoria(RequestNewConvocatoria requestNewConvocatoria) {
        Integer getIdSecuencia = convocatoriaRepository.getIdSecuencia();

        Optional<UsuariosEntity> usuarioAdmFindByCodEmp = usuariosRepository.findByCodEmp(requestNewConvocatoria.getIdadm());
        Optional<UsuariosEntity> usuarioDirUnidad = usuariosRepository.findByCodEmp(requestNewConvocatoria.getDirunidad());

        String Idadm = getIdadm(requestNewConvocatoria.getIdadm(), requestNewConvocatoria.getCorreo_dadm(), usuarioAdmFindByCodEmp);

        String Idirunidad = getIdirunidad(requestNewConvocatoria.getDirunidad(), requestNewConvocatoria.getCorreo_dirunidad(), usuarioDirUnidad);

        DateTimeFormatter formateador = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .toFormatter();

        ConvocatoriasEntity convocatoriaToPersist = new ConvocatoriasEntity();
        convocatoriaToPersist.setIdcon(getIdSecuencia);
        convocatoriaToPersist.setUnidad(requestNewConvocatoria.getUnidad());
        convocatoriaToPersist.setNombre(requestNewConvocatoria.getNombre());
        convocatoriaToPersist.setDescon(requestNewConvocatoria.getDescon());
        convocatoriaToPersist.setEstado("Abierta");
        convocatoriaToPersist.setFini(LocalDateTime.parse(requestNewConvocatoria.getFini() + " 00:00:00", formateador));
        convocatoriaToPersist.setFfin(LocalDateTime.parse(requestNewConvocatoria.getFfin() + " 00:00:00", formateador));
        convocatoriaToPersist.setTipo(requestNewConvocatoria.getTipo());
        convocatoriaToPersist.setAsig(0);
        convocatoriaToPersist.setPeracad(requestNewConvocatoria.getPeriodo());
        convocatoriaToPersist.setIdadm(Integer.valueOf(Idadm));
        convocatoriaToPersist.setDirunidad(Integer.valueOf(Idirunidad));

        convocatoriaRepository.save(convocatoriaToPersist);

        return Collections.singletonMap("message", "Convocatoria creada correctamente");
    }

    private String getIdirunidad(String dir_unidad, String correo_dir_unidad, Optional<UsuariosEntity> usuarioDirUnidad) {
        String Idirunidad;
        if (usuarioDirUnidad.isEmpty()) {
            UsuariosEntity usuariosEntityToSave = new UsuariosEntity(
                    dir_unidad,
                    correo_dir_unidad,
                    "decano",
                    "1"
            );

            UsuariosEntity usuariosEntitySaved = usuariosRepository.save(usuariosEntityToSave);
            Idirunidad = usuariosEntitySaved.getCodEmp();
        }
        else {
            Idirunidad = usuarioDirUnidad.get().getCodEmp();
        }
        return Idirunidad;
    }

    private String getIdadm(String idadm, String correo_idadm, Optional<UsuariosEntity> usuarioAdmFindByCodEmp) {
        String Idadm;

        if (usuarioAdmFindByCodEmp.isEmpty()) {
            UsuariosEntity usuariosEntityToSave = new UsuariosEntity(
                    idadm,
                    correo_idadm,
                    "decano",
                    "1"
            );

            UsuariosEntity usuariosEntitySaved = usuariosRepository.save(usuariosEntityToSave);
            Idadm = usuariosEntitySaved.getCodEmp();
        } 
        else {
            Idadm = usuarioAdmFindByCodEmp.get().getCodEmp();
        }
        return Idadm;
    }

    @Override
    public ConvocatoriasEntity putConvocatoria(Integer id, RequestUpdateConvocatoria requestUpdate) {
//        try {
            List<ConvocatoriasEntity> convocatorias = convocatoriaRepository.findAllByIdcon(id);

            Optional<UsuariosEntity> usuarioAdmFindByCodEmp = usuariosRepository.findByCodEmp(requestUpdate.getIdadm());
            Optional<UsuariosEntity> usuarioDirUnidad = usuariosRepository.findByCodEmp(requestUpdate.getDirunidad());

            String Idadm = getIdadm(requestUpdate.getIdadm(), requestUpdate.getCorreo_dadm(), usuarioAdmFindByCodEmp);

            String Idirunidad = getIdirunidad(requestUpdate.getDirunidad(), requestUpdate.getCorreo_dirunidad(), usuarioDirUnidad);

            if (convocatorias.isEmpty()) throw new RuntimeException();

            ConvocatoriasEntity convocatoriaToUpdate = convocatorias.get(0);

            convocatoriaToUpdate.setNombre(requestUpdate.getNombre());
            convocatoriaToUpdate.setDescon(requestUpdate.getDescon());
            convocatoriaToUpdate.setUnidad(requestUpdate.getUnidad());
            convocatoriaToUpdate.setFini(requestUpdate.getFini().atStartOfDay());
            convocatoriaToUpdate.setFfin(requestUpdate.getFfin().atStartOfDay());
            convocatoriaToUpdate.setEstado("Abierta");
            convocatoriaToUpdate.setTipo(requestUpdate.getTipo());
            convocatoriaToUpdate.setPeracad(requestUpdate.getPeracad());
            convocatoriaToUpdate.setIdadm(Integer.valueOf(Idadm));
            convocatoriaToUpdate.setDirunidad(Integer.valueOf(Idirunidad));

            convocatoriaRepository.save(convocatoriaToUpdate);
            return convocatoriaToUpdate;
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//            throw new RuntimeException();
//        }
    }

    private ConvocatoriaResponse entityToResponse(ConvocatoriasEntity convocatoriasEntity) {
        ConvocatoriaResponse response = new ConvocatoriaResponse();

        response.setIdcon(convocatoriasEntity.getIdcon());
        response.setNombre(convocatoriasEntity.getNombre());
        response.setDescon(convocatoriasEntity.getDescon());
        response.setUnidad(convocatoriasEntity.getUnidad());
        response.setFini(String.valueOf(convocatoriasEntity.getFini()));
        response.setFfin(String.valueOf(convocatoriasEntity.getFfin()));
        response.setTipo(convocatoriasEntity.getTipo());
        response.setAsig(convocatoriasEntity.getAsig());
        response.setPeracad(convocatoriasEntity.getPeracad());
        response.setEstado(convocatoriasEntity.getEstado());
        response.setIdadm(convocatoriasEntity.getIdadm());
        response.setDirunidad(convocatoriasEntity.getDirunidad());
        return response;
    }
}
