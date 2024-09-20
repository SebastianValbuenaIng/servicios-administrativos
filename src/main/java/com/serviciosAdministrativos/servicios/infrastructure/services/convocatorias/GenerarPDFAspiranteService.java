package com.serviciosAdministrativos.servicios.infrastructure.services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.*;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.*;
import com.serviciosAdministrativos.servicios.util.errors.NotFoundError;
import com.serviciosAdministrativos.servicios.util.utils.convocatorias.GeneratePDF;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
public class GenerarPDFAspiranteService implements IGenerarPDFAspiranteService {
    private final IParticipantService participantService;
    private final IEstudioService estudioService;
    private final IIdiomaService idiomaService;
    private final IExperienceService experienceService;
    private final IPublicacionesService publicacionesService;
    private final IMembresiaService membresiaService;
    private final IDistincionesServices distincionesServices;
    private final IProyectoService proyectoService;
    private final IDocenciaService docenciaService;
    private final Environment environment;

    public GenerarPDFAspiranteService(IParticipantService participantService,
                                      IEstudioService estudioService,
                                      IIdiomaService idiomaService,
                                      IExperienceService experienceService,
                                      IPublicacionesService publicacionesService,
                                      IMembresiaService membresiaService,
                                      IDistincionesServices distincionesServices,
                                      IProyectoService proyectoService,
                                      IDocenciaService docenciaService,
                                      Environment environment) {
        this.experienceService = experienceService;
        this.participantService = participantService;
        this.estudioService = estudioService;
        this.idiomaService = idiomaService;
        this.publicacionesService = publicacionesService;
        this.membresiaService = membresiaService;
        this.distincionesServices = distincionesServices;
        this.proyectoService = proyectoService;
        this.docenciaService = docenciaService;
        this.environment = environment;
    }

    @Override
    public void generatePDF(HttpServletResponse response, Integer id) {
        // Experiencias
        List<ExperienceResponse> experiencias = experienceService.getExperiencia(id).stream().toList();

        // Informacion General
        ParticipantResponse infoAspirant = participantService.getInfoAspirant(String.valueOf(id));

        // Estudios
        List<EstudiosResponse> estudios = estudioService.getEstudios(String.valueOf(id)).stream().toList();

        // Idiomas
        List<IdiomaResponse> idiomas = idiomaService.findAllByCedula(String.valueOf(id)).stream().toList();

        // Publicaciones
        List<PublicacionesResponse> publicaciones = publicacionesService.getPublicaciones(id).stream().toList();

        // Membresias
        List<MembresiaResponse> membresias = membresiaService.findAllByIdemp(String.valueOf(id)).stream().toList();

        // Disticiones
        List<DistincionResponse> distinciones = distincionesServices.getDistinciones(id).stream().toList();

        // Proyectos
        List<ProyectoResponse> proyectos = proyectoService.getProyectos(id).stream().toList();

        // Docencia
        List<DocenciaResponse> docencia = docenciaService.getDocencia(id).stream().toList().stream().toList();

        try {
            GeneratePDF.generatePDF(response, String.valueOf(id), idiomas, experiencias, infoAspirant, estudios, publicaciones, membresias, distinciones, proyectos, docencia);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getFileSupport(HttpServletResponse response, String id) {
        try {
            String path = Objects.requireNonNull(
                    environment.getProperty("route.documents.support")).concat("\\") + id + ".pdf";

            byte[] bytes = Files.readAllBytes(Paths.get(path));

            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.close();

            response.setHeader("Content-Disposition", "attachment; filename=" + id);
        } catch (Exception e) {
            throw new NotFoundError("No existe un documento con ese identificador");
        }
    }
}