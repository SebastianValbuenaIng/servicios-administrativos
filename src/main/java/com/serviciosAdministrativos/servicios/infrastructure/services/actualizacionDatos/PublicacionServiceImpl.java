package com.serviciosAdministrativos.servicios.infrastructure.services.actualizacionDatos;

import com.serviciosAdministrativos.servicios.api.models.actualizacionDatos.request.AgregarPublicacionRequest;
import com.serviciosAdministrativos.servicios.domain.entities.DBActualizaDatosPers.PublicacionesEntity;
import com.serviciosAdministrativos.servicios.domain.repositories.DBActualizaDatosPers.PublicacionesRepository;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.actualizacionDatos.PublicacionService;
import com.serviciosAdministrativos.servicios.util.errors.NotFoundError;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PublicacionServiceImpl implements PublicacionService {
    private final PublicacionesRepository publicacionesRepository;

    public PublicacionServiceImpl(PublicacionesRepository publicacionesRepository) {
        this.publicacionesRepository = publicacionesRepository;
    }

    @Override
    public List<PublicacionesEntity> findAllPublicacionByEmpleado(String documento) {
        return publicacionesRepository.findAllByCodEmp(documento);
    }

    @Override
    public PublicacionesEntity saveNewPublicacionEmpleado(String documento, AgregarPublicacionRequest agregarPublicacionRequest) {
        PublicacionesEntity publicacionToSaved = new PublicacionesEntity(
                documento,
                agregarPublicacionRequest.getTitulo_public(),
                agregarPublicacionRequest.getFecha_public(),
                agregarPublicacionRequest.getEditorial(),
                agregarPublicacionRequest.getIsbn()
        );

        return publicacionesRepository.save(publicacionToSaved);
    }

    @Override
    public PublicacionesEntity updateNewPublicacionEmpleado(Integer id_publicacion, AgregarPublicacionRequest agregarPublicacionRequest) {
        Optional<PublicacionesEntity> findPublicacionById = publicacionesRepository.findById(id_publicacion);

        if (findPublicacionById.isEmpty()) throw new NotFoundError("Publicacion no encontrada.");

        findPublicacionById.get().setEditorial(agregarPublicacionRequest.getEditorial());
        findPublicacionById.get().setIsbn(agregarPublicacionRequest.getIsbn());
        findPublicacionById.get().setTitulo_public(agregarPublicacionRequest.getTitulo_public());
        findPublicacionById.get().setFecha_public(agregarPublicacionRequest.getFecha_public());

        return publicacionesRepository.save(findPublicacionById.get());
    }

    @Override
    public Map<String, String> deleteNewPublicacionEmpleado(Integer id_publicacion) {
        Optional<PublicacionesEntity> findPublicacionById = publicacionesRepository.findById(id_publicacion);

        if (findPublicacionById.isEmpty()) throw new NotFoundError("Publicación no encontrada.");

        publicacionesRepository.delete(findPublicacionById.get());

        return Map.of("message", "Publicación eliminada correctamente.");
    }
}
