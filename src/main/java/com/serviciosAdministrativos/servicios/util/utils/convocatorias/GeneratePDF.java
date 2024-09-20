package com.serviciosAdministrativos.servicios.util.utils.convocatorias;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.*;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.List;

public class GeneratePDF {
    public static void generatePDF(
            HttpServletResponse response,
            String id,
            List<IdiomaResponse> idiomas,
            List<ExperienceResponse> experiencias,
            ParticipantResponse infoAspirant,
            List<EstudiosResponse> estudios,
            List<PublicacionesResponse> publicaciones,
            List<MembresiaResponse> membresias,
            List<DistincionResponse> distinciones,
            List<ProyectoResponse> proyectos,
            List<DocenciaResponse> docencia
    ) throws Exception {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", String.format("attachment; filename=%s.pdf", id));

        Font coloredFont = new Font(Font.NORMAL, 15, Font.BOLD, RGBColor.RED);

        // Titulos
        Paragraph tituloDocumento = new Paragraph(String.format("Hoja de Vida %s %S", infoAspirant.getNom(), infoAspirant.getAp1()), coloredFont);
        tituloDocumento.setAlignment(Element.ALIGN_CENTER);
        tituloDocumento.setSpacingAfter(14);

        Paragraph nombreAspirante = new Paragraph(infoAspirant.getNom() + " " + infoAspirant.getAp1() + " " + infoAspirant.getAp2());
        nombreAspirante.setAlignment(Element.ALIGN_LEFT);
        nombreAspirante.setIndentationLeft(10);
        nombreAspirante.setSpacingAfter(20);

        // Información General
        Paragraph documento = new Paragraph("Documento: " + infoAspirant.getDocid());
        documento.setAlignment(Element.ALIGN_LEFT);
        documento.setIndentationLeft(20);
        documento.setSpacingAfter(10);

        Paragraph nacimiento = new Paragraph("Nacimiento: " + Arrays.stream(infoAspirant.getFnac().toString().split("T")).toList().get(0));
        nacimiento.setAlignment(Element.ALIGN_LEFT);
        nacimiento.setIndentationLeft(20);
        nacimiento.setSpacingAfter(10);

        String generoDesc = infoAspirant.getGen().equals("M") ? "Masculino" : "Femenino";

        Paragraph genero = new Paragraph("Género: " + generoDesc);
        genero.setAlignment(Element.ALIGN_LEFT);
        genero.setIndentationLeft(20);
        genero.setSpacingAfter(10);

        Paragraph direccion = new Paragraph("Dirección: " + infoAspirant.getDirres());
        direccion.setAlignment(Element.ALIGN_LEFT);
        direccion.setIndentationLeft(20);
        direccion.setSpacingAfter(10);

        Paragraph telefono = new Paragraph("Teléfono: " + infoAspirant.getTelres());
        telefono.setAlignment(Element.ALIGN_LEFT);
        telefono.setIndentationLeft(20);
        telefono.setSpacingAfter(10);

        Paragraph celular = new Paragraph("Celular: " + infoAspirant.getCel());
        celular.setAlignment(Element.ALIGN_LEFT);
        celular.setIndentationLeft(20);
        celular.setSpacingAfter(10);

        Paragraph correo = new Paragraph("Correo electrónico: " + infoAspirant.getEmail());
        correo.setAlignment(Element.ALIGN_LEFT);
        correo.setIndentationLeft(20);
        correo.setSpacingAfter(20);

        // Perfil Profesional
        Paragraph perfilProfesionalTitulo = new Paragraph("Perfil Profesional");
        perfilProfesionalTitulo.setAlignment(Element.ALIGN_LEFT);
        perfilProfesionalTitulo.setIndentationLeft(10);
        perfilProfesionalTitulo.setSpacingAfter(20);

        Paragraph perfilProfesional = new Paragraph(infoAspirant.getPerfil());
        perfilProfesional.setAlignment(Element.ALIGN_LEFT);
        perfilProfesional.setIndentationLeft(20);
        perfilProfesional.setSpacingAfter(10);

        // Estudios
        Paragraph estudiosTitulo = new Paragraph("Estudios");
        estudiosTitulo.setAlignment(Element.ALIGN_LEFT);
        estudiosTitulo.setIndentationLeft(10);
        estudiosTitulo.setSpacingAfter(20);

        PdfPTable tableEstudios = new PdfPTable(5);
        tableEstudios.setWidthPercentage(90);

        PdfPCell headerTitulo = new PdfPCell();
        Paragraph tituloText = new Paragraph("Título");
        tituloText.setAlignment(Element.ALIGN_CENTER);
        headerTitulo.addElement(tituloText);

        PdfPCell headerNivel = new PdfPCell();
        Paragraph nivelText = new Paragraph("Nivel");
        nivelText.setAlignment(Element.ALIGN_CENTER);
        headerNivel.addElement(nivelText);

        PdfPCell headerInstitucion = new PdfPCell();
        Paragraph institucionText = new Paragraph("Institución");
        institucionText.setAlignment(Element.ALIGN_CENTER);
        headerInstitucion.addElement(institucionText);

        PdfPCell headerFechaGrado = new PdfPCell();
        Paragraph fechaGradoText = new Paragraph("Fecha de Grado");
        fechaGradoText.setAlignment(Element.ALIGN_CENTER);
        headerFechaGrado.addElement(fechaGradoText);

        PdfPCell headerTarjetaProfesional = new PdfPCell();
        Paragraph tarjetaProfesionalText = new Paragraph("Tarjeta Profesional");
        tarjetaProfesionalText.setAlignment(Element.ALIGN_CENTER);
        headerTarjetaProfesional.addElement(tarjetaProfesionalText);

        tableEstudios.addCell(headerTitulo);
        tableEstudios.addCell(headerNivel);
        tableEstudios.addCell(headerInstitucion);
        tableEstudios.addCell(headerFechaGrado);
        tableEstudios.addCell(headerTarjetaProfesional);

        estudios.forEach(estudiosResponse -> {
            PdfPCell bodyTitulo = new PdfPCell();
            Paragraph bodyTituloText = new Paragraph(estudiosResponse.getNom_est());
            bodyTituloText.setAlignment(Element.ALIGN_CENTER);
            bodyTitulo.addElement(bodyTituloText);
            tableEstudios.addCell(bodyTitulo);

            PdfPCell bodyNivel = new PdfPCell();
            Paragraph bodyNivelText = new Paragraph(estudiosResponse.getDes_est());
            bodyNivelText.setAlignment(Element.ALIGN_CENTER);
            bodyNivel.addElement(bodyNivelText);
            tableEstudios.addCell(bodyNivel);

            PdfPCell bodyInstitucion = new PdfPCell();
            Paragraph bodyInstitucionText = new Paragraph(estudiosResponse.getNom_ins());
            bodyInstitucionText.setAlignment(Element.ALIGN_CENTER);
            bodyInstitucion.addElement(bodyInstitucionText);
            tableEstudios.addCell(bodyInstitucion);

            PdfPCell bodyFechaGrado = new PdfPCell();
            Paragraph bodyFechaGradoText = new Paragraph(estudiosResponse.getFgra());
            bodyFechaGradoText.setAlignment(Element.ALIGN_CENTER);
            bodyFechaGrado.addElement(bodyFechaGradoText);
            tableEstudios.addCell(bodyFechaGrado);

            PdfPCell bodyTarjetaProfesional = new PdfPCell();
            Paragraph bodyTarjetaProfesionalText = new Paragraph(estudiosResponse.getNro_tar());
            bodyTarjetaProfesionalText.setAlignment(Element.ALIGN_CENTER);
            bodyTarjetaProfesional.addElement(bodyTarjetaProfesionalText);
            tableEstudios.addCell(bodyTarjetaProfesional);
        });

        // Idiomas
        Paragraph idiomasTitulo = new Paragraph("Idiomas");
        idiomasTitulo.setAlignment(Element.ALIGN_LEFT);
        idiomasTitulo.setIndentationLeft(10);
        idiomasTitulo.setSpacingAfter(20);
        idiomasTitulo.setSpacingBefore(20);

        PdfPTable tableIdiomas = new PdfPTable(4);
        tableEstudios.setWidthPercentage(90);

        PdfPCell headerLengua = new PdfPCell();
        Paragraph lenguaText = new Paragraph("Lengua");
        lenguaText.setAlignment(Element.ALIGN_CENTER);
        headerLengua.addElement(lenguaText);

        PdfPCell headerLectura = new PdfPCell();
        Paragraph lecturaText = new Paragraph("Lectura");
        lecturaText.setAlignment(Element.ALIGN_CENTER);
        headerLectura.addElement(lecturaText);

        PdfPCell headerEscritura = new PdfPCell();
        Paragraph escrituraText = new Paragraph("Escritura");
        escrituraText.setAlignment(Element.ALIGN_CENTER);
        headerEscritura.addElement(escrituraText);

        PdfPCell headerHabla = new PdfPCell();
        Paragraph hablaText = new Paragraph("Habla");
        hablaText.setAlignment(Element.ALIGN_CENTER);
        headerHabla.addElement(hablaText);

        tableIdiomas.addCell(headerLengua);
        tableIdiomas.addCell(headerLectura);
        tableIdiomas.addCell(headerEscritura);
        tableIdiomas.addCell(headerHabla);

        idiomas.forEach(idiomaResponse -> {
            PdfPCell bodyLengua = new PdfPCell();
            Paragraph bodyLenguaText = new Paragraph(idiomaResponse.getLengua());
            bodyLenguaText.setAlignment(Element.ALIGN_CENTER);
            bodyLengua.addElement(bodyLenguaText);
            tableIdiomas.addCell(bodyLengua);

            // Bueno - B // Regular - R / Malo - M
            PdfPCell bodyLectura = new PdfPCell();

            String nivelLectura = idiomaResponse.getNivlec().equals("B")
                    ? "Bueno" : idiomaResponse.getNivlec().equals("R")
                    ? "Regular" : "Malo";

            Paragraph bodyLecturaText = new Paragraph(nivelLectura);
            bodyLecturaText.setAlignment(Element.ALIGN_CENTER);
            bodyLectura.addElement(bodyLecturaText);
            tableIdiomas.addCell(bodyLectura);

            PdfPCell bodyEscribe = new PdfPCell();

            String nivelEscritura = idiomaResponse.getNivesc().equals("B")
                    ? "Bueno" : idiomaResponse.getNivesc().equals("R")
                    ? "Regular" : "Malo";

            Paragraph bodyEscrituraText = new Paragraph(nivelEscritura);
            bodyEscrituraText.setAlignment(Element.ALIGN_CENTER);
            bodyEscribe.addElement(bodyEscrituraText);
            tableIdiomas.addCell(bodyEscribe);

            PdfPCell bodyHabla = new PdfPCell();

            String nivelHablar = idiomaResponse.getNivesc().equals("B")
                    ? "Bueno" : idiomaResponse.getNivesc().equals("R")
                    ? "Regular" : "Malo";

            Paragraph bodyHablarText = new Paragraph(nivelHablar);
            bodyHablarText.setAlignment(Element.ALIGN_CENTER);
            bodyHabla.addElement(bodyHablarText);
            tableIdiomas.addCell(bodyHabla);
        });

        // Experiencias Laborales
        Paragraph experienciasTitulo = new Paragraph("Experiencias Laborales");
        experienciasTitulo.setAlignment(Element.ALIGN_LEFT);
        experienciasTitulo.setIndentationLeft(10);
        experienciasTitulo.setSpacingAfter(20);
        experienciasTitulo.setSpacingBefore(20);

        PdfPTable tableExperiencias = new PdfPTable(4);
        tableEstudios.setWidthPercentage(90);

        PdfPCell headerEmpresa = new PdfPCell();
        Paragraph empresaText = new Paragraph("Empresa");
        empresaText.setAlignment(Element.ALIGN_CENTER);
        headerEmpresa.addElement(empresaText);

        PdfPCell headerNombreCargo = new PdfPCell();
        Paragraph nombreCargoText = new Paragraph("Cargo");
        nombreCargoText.setAlignment(Element.ALIGN_CENTER);
        headerNombreCargo.addElement(nombreCargoText);

        PdfPCell headerTipoCargo = new PdfPCell();
        Paragraph tipoCargoText = new Paragraph("Tipo Cargo");
        tipoCargoText.setAlignment(Element.ALIGN_CENTER);
        headerTipoCargo.addElement(tipoCargoText);

        PdfPCell headerPeriodo = new PdfPCell();
        Paragraph periodoText = new Paragraph("Periodo");
        periodoText.setAlignment(Element.ALIGN_CENTER);
        headerPeriodo.addElement(periodoText);

        tableExperiencias.addCell(headerEmpresa);
        tableExperiencias.addCell(headerNombreCargo);
        tableExperiencias.addCell(headerTipoCargo);
        tableExperiencias.addCell(headerPeriodo);

        experiencias.forEach(experienceMapper -> {
            PdfPCell bodyEmpresa = new PdfPCell();
            Paragraph bodyEmpresaText = new Paragraph(experienceMapper.getNomorg());
            bodyEmpresaText.setAlignment(Element.ALIGN_CENTER);
            bodyEmpresa.addElement(bodyEmpresaText);
            tableExperiencias.addCell(bodyEmpresa);

            PdfPCell bodyCargo = new PdfPCell();
            Paragraph bodyCargoText = new Paragraph(experienceMapper.getNombre_cargo());
            bodyCargoText.setAlignment(Element.ALIGN_CENTER);
            bodyCargo.addElement(bodyCargoText);
            tableExperiencias.addCell(bodyCargo);

            PdfPCell bodyTipoCargo = new PdfPCell();
            Paragraph bodyTipoCargoText = new Paragraph(experienceMapper.getTipo_cargo());
            bodyTipoCargoText.setAlignment(Element.ALIGN_CENTER);
            bodyTipoCargo.addElement(bodyTipoCargoText);
            tableExperiencias.addCell(bodyTipoCargo);

            PdfPCell bodyPeriodo = new PdfPCell();
            Paragraph bodyPeriodoText = new Paragraph(experienceMapper.getPeriodo());
            bodyPeriodoText.setAlignment(Element.ALIGN_CENTER);
            bodyPeriodo.addElement(bodyPeriodoText);
            tableExperiencias.addCell(bodyPeriodo);
        });

        // Publicaciones
        Paragraph publicacionesTitulo = new Paragraph("Publicaciones");
        publicacionesTitulo.setAlignment(Element.ALIGN_LEFT);
        publicacionesTitulo.setIndentationLeft(10);
        publicacionesTitulo.setSpacingAfter(20);
        publicacionesTitulo.setSpacingBefore(20);

        PdfPTable tablePublicaciones = new PdfPTable(9);
        tablePublicaciones.setWidthPercentage(98);

        PdfPCell headerTituloPublicacion = new PdfPCell();
        Paragraph tituloPublicacionText = new Paragraph("Titulo");
        tituloPublicacionText.setAlignment(Element.ALIGN_CENTER);
        headerTituloPublicacion.addElement(tituloPublicacionText);
        tablePublicaciones.addCell(headerTituloPublicacion);

        PdfPCell headerTituloMayorPublicacion = new PdfPCell();
        Paragraph tituloMayorPublicacionText = new Paragraph("Titulo Mayor");
        tituloMayorPublicacionText.setAlignment(Element.ALIGN_CENTER);
        headerTituloMayorPublicacion.addElement(tituloMayorPublicacionText);
        tablePublicaciones.addCell(headerTituloMayorPublicacion);

        PdfPCell headerTipoPublicacion = new PdfPCell();
        Paragraph tipoPublicacionText = new Paragraph("Tipo");
        tipoPublicacionText.setAlignment(Element.ALIGN_CENTER);
        headerTipoPublicacion.addElement(tipoPublicacionText);
        tablePublicaciones.addCell(headerTipoPublicacion);

        PdfPCell headerAreaPublicacion = new PdfPCell();
        Paragraph areaPublicacionText = new Paragraph("Area");
        areaPublicacionText.setAlignment(Element.ALIGN_CENTER);
        headerAreaPublicacion.addElement(areaPublicacionText);
        tablePublicaciones.addCell(headerAreaPublicacion);

        PdfPCell headerEditorialPublicacion = new PdfPCell();
        Paragraph editorialPublicacionText = new Paragraph("Edición/Editorial");
        editorialPublicacionText.setAlignment(Element.ALIGN_CENTER);
        headerEditorialPublicacion.addElement(editorialPublicacionText);
        tablePublicaciones.addCell(headerEditorialPublicacion);

        PdfPCell headerISBNPublicacion = new PdfPCell();
        Paragraph editorialISBNText = new Paragraph("ISBN/ISSN");
        editorialISBNText.setAlignment(Element.ALIGN_CENTER);
        headerISBNPublicacion.addElement(editorialISBNText);
        tablePublicaciones.addCell(headerISBNPublicacion);

        PdfPCell headerFechaPublicacion = new PdfPCell();
        Paragraph fechaPublicacionText = new Paragraph("Fecha");
        fechaPublicacionText.setAlignment(Element.ALIGN_CENTER);
        headerFechaPublicacion.addElement(fechaPublicacionText);
        tablePublicaciones.addCell(headerFechaPublicacion);

        PdfPCell headerPaisPublicacion = new PdfPCell();
        Paragraph paisPublicacionText = new Paragraph("País");
        paisPublicacionText.setAlignment(Element.ALIGN_CENTER);
        headerPaisPublicacion.addElement(paisPublicacionText);
        tablePublicaciones.addCell(headerPaisPublicacion);

        PdfPCell headerCiudadPublicacion = new PdfPCell();
        Paragraph ciudadPublicacionText = new Paragraph("Ciudad");
        ciudadPublicacionText.setAlignment(Element.ALIGN_CENTER);
        headerCiudadPublicacion.addElement(ciudadPublicacionText);
        tablePublicaciones.addCell(headerCiudadPublicacion);

        publicaciones.forEach(publicacionesResponse -> {
            PdfPCell bodyTituloPublicacion = new PdfPCell();
            Paragraph bodyTituloPublicacionText = new Paragraph(publicacionesResponse.getTitulo());
            bodyTituloPublicacionText.setAlignment(Element.ALIGN_CENTER);
            bodyTituloPublicacion.addElement(bodyTituloPublicacionText);
            tablePublicaciones.addCell(bodyTituloPublicacion);

            PdfPCell bodyTituloMayorPublicacion = new PdfPCell();
            Paragraph bodyTituloMayorPublicacionText = new Paragraph(publicacionesResponse.getTitmay());
            bodyTituloMayorPublicacionText.setAlignment(Element.ALIGN_CENTER);
            bodyTituloMayorPublicacion.addElement(bodyTituloMayorPublicacionText);
            tablePublicaciones.addCell(bodyTituloMayorPublicacion);

            PdfPCell bodyTipoPublicacion = new PdfPCell();
            Paragraph bodyTipoPublicacionText = new Paragraph(publicacionesResponse.getTipo());
            bodyTipoPublicacionText.setAlignment(Element.ALIGN_CENTER);
            bodyTipoPublicacion.addElement(bodyTipoPublicacionText);
            tablePublicaciones.addCell(bodyTipoPublicacion);

            PdfPCell bodyAreaPublicacion = new PdfPCell();
            Paragraph bodyAreaPublicacionText = new Paragraph(publicacionesResponse.getArea());
            bodyAreaPublicacionText.setAlignment(Element.ALIGN_CENTER);
            bodyAreaPublicacion.addElement(bodyAreaPublicacionText);
            tablePublicaciones.addCell(bodyAreaPublicacion);

            PdfPCell bodyEditorialPublicacion = new PdfPCell();
            Paragraph bodyEditoralPublicacionText = new Paragraph(publicacionesResponse.getEdic());
            bodyEditoralPublicacionText.setAlignment(Element.ALIGN_CENTER);
            bodyEditorialPublicacion.addElement(bodyEditoralPublicacionText);
            tablePublicaciones.addCell(bodyEditorialPublicacion);

            PdfPCell bodyISBNPublicacion = new PdfPCell();
            Paragraph bodyISBNPublicacionText = new Paragraph(publicacionesResponse.getEdit());
            bodyISBNPublicacionText.setAlignment(Element.ALIGN_CENTER);
            bodyISBNPublicacion.addElement(bodyISBNPublicacionText);
            tablePublicaciones.addCell(bodyISBNPublicacion);

            PdfPCell bodyFechaPublicacion = new PdfPCell();
            Paragraph bodyFechaPublicacionText = new Paragraph(publicacionesResponse.getFec());
            bodyFechaPublicacionText.setAlignment(Element.ALIGN_CENTER);
            bodyFechaPublicacion.addElement(bodyFechaPublicacionText);
            tablePublicaciones.addCell(bodyFechaPublicacion);

            PdfPCell bodyPaisPublicacion = new PdfPCell();
            Paragraph bodyPaisPublicacionText = new Paragraph(publicacionesResponse.getPais());
            bodyPaisPublicacionText.setAlignment(Element.ALIGN_CENTER);
            bodyPaisPublicacion.addElement(bodyPaisPublicacionText);
            tablePublicaciones.addCell(bodyPaisPublicacion);

            PdfPCell bodyCiudadPublicacion = new PdfPCell();
            Paragraph bodyCiudadPublicacionText = new Paragraph(publicacionesResponse.getCiudad());
            bodyCiudadPublicacionText.setAlignment(Element.ALIGN_CENTER);
            bodyCiudadPublicacion.addElement(bodyCiudadPublicacionText);
            tablePublicaciones.addCell(bodyCiudadPublicacion);
        });

        // Membresías
        PdfPTable tableMembresias = new PdfPTable(6);
        tableMembresias.setWidthPercentage(90);

        Paragraph membresiasTitulo = new Paragraph("Membresías");
        membresiasTitulo.setAlignment(Element.ALIGN_LEFT);
        membresiasTitulo.setIndentationLeft(10);
        membresiasTitulo.setSpacingAfter(20);
        membresiasTitulo.setSpacingBefore(20);

        PdfPCell headerOrganizacionMembresia = new PdfPCell();
        Paragraph OrganizacionMembresiaText = new Paragraph("Organización");
        OrganizacionMembresiaText.setAlignment(Element.ALIGN_CENTER);
        headerOrganizacionMembresia.addElement(OrganizacionMembresiaText);
        tableMembresias.addCell(headerOrganizacionMembresia);

        PdfPCell headertipoMembresia = new PdfPCell();
        Paragraph tipoMembresiaText = new Paragraph("Tipo");
        tipoMembresiaText.setAlignment(Element.ALIGN_CENTER);
        headertipoMembresia.addElement(tipoMembresiaText);
        tableMembresias.addCell(headertipoMembresia);

        PdfPCell headerFechaIngreso = new PdfPCell();
        Paragraph fechaIngresoText = new Paragraph("Fecha ingreso");
        fechaIngresoText.setAlignment(Element.ALIGN_CENTER);
        headerFechaIngreso.addElement(fechaIngresoText);
        tableMembresias.addCell(headerFechaIngreso);

        PdfPCell headerFechaRetiro = new PdfPCell();
        Paragraph fechaRetiroText = new Paragraph("Fecha retiro");
        fechaRetiroText.setAlignment(Element.ALIGN_CENTER);
        headerFechaRetiro.addElement(fechaRetiroText);
        tableMembresias.addCell(headerFechaRetiro);

        PdfPCell headerCategoriaMembresia = new PdfPCell();
        Paragraph categoriaMembresiaText = new Paragraph("Categoria");
        categoriaMembresiaText.setAlignment(Element.ALIGN_CENTER);
        headerCategoriaMembresia.addElement(categoriaMembresiaText);
        tableMembresias.addCell(headerCategoriaMembresia);

        PdfPCell headerPaisMembresia = new PdfPCell();
        Paragraph paisMembresiaText = new Paragraph("Pais");
        paisMembresiaText.setAlignment(Element.ALIGN_CENTER);
        headerPaisMembresia.addElement(paisMembresiaText);
        tableMembresias.addCell(headerPaisMembresia);

        membresias.forEach(membresiaResponse -> {
            PdfPCell organizacionMembresia = new PdfPCell();
            Paragraph membresiaText = new Paragraph(membresiaResponse.getOrg());
            membresiaText.setAlignment(Element.ALIGN_CENTER);
            organizacionMembresia.addElement(membresiaText);
            tableMembresias.addCell(organizacionMembresia);

            PdfPCell tipoMembresia = new PdfPCell();
            Paragraph tipoText = new Paragraph(membresiaResponse.getTipm());
            tipoText.setAlignment(Element.ALIGN_CENTER);
            tipoMembresia.addElement(tipoText);
            tableMembresias.addCell(tipoMembresia);

            PdfPCell fechaIngresoMembresia = new PdfPCell();
            Paragraph bodyfechaIngresoText = new Paragraph(String.valueOf(membresiaResponse.getFing()));
            bodyfechaIngresoText.setAlignment(Element.ALIGN_CENTER);
            fechaIngresoMembresia.addElement(bodyfechaIngresoText);
            tableMembresias.addCell(fechaIngresoMembresia);

            PdfPCell fechaRetiroMembresia = new PdfPCell();
            Paragraph bodyFechaRetiroText = new Paragraph(String.valueOf(membresiaResponse.getFret()));
            bodyFechaRetiroText.setAlignment(Element.ALIGN_CENTER);
            fechaRetiroMembresia.addElement(bodyFechaRetiroText);
            tableMembresias.addCell(fechaRetiroMembresia);

            PdfPCell categoriaMembresia = new PdfPCell();
            Paragraph bodyCategoriaMembresiaText = new Paragraph(membresiaResponse.getCat());
            bodyCategoriaMembresiaText.setAlignment(Element.ALIGN_CENTER);
            categoriaMembresia.addElement(bodyCategoriaMembresiaText);
            tableMembresias.addCell(categoriaMembresia);

            PdfPCell paisMembresia = new PdfPCell();
            Paragraph bodyPaisMembresiaText = new Paragraph(membresiaResponse.getPais());
            bodyPaisMembresiaText.setAlignment(Element.ALIGN_CENTER);
            paisMembresia.addElement(bodyPaisMembresiaText);
            tableMembresias.addCell(paisMembresia);
        });

        // Distinciones
        PdfPTable tableDistinciones = new PdfPTable(5);
        tableDistinciones.setWidthPercentage(90);

        Paragraph distincionesTitulo = new Paragraph("Distinciones");
        distincionesTitulo.setAlignment(Element.ALIGN_LEFT);
        distincionesTitulo.setIndentationLeft(10);
        distincionesTitulo.setSpacingAfter(20);
        distincionesTitulo.setSpacingBefore(20);

        PdfPCell headerNombreDistincion = new PdfPCell();
        Paragraph nombreDistincionText = new Paragraph("Nombre");
        nombreDistincionText.setAlignment(Element.ALIGN_CENTER);
        headerNombreDistincion.addElement(nombreDistincionText);
        tableDistinciones.addCell(headerNombreDistincion);

        PdfPCell headerInstitucionDistincion = new PdfPCell();
        Paragraph institucionDistincionText = new Paragraph("Institución");
        institucionDistincionText.setAlignment(Element.ALIGN_CENTER);
        headerInstitucionDistincion.addElement(institucionDistincionText);
        tableDistinciones.addCell(headerInstitucionDistincion);

        PdfPCell headerPaisDistincion = new PdfPCell();
        Paragraph paisDistincionText = new Paragraph("País");
        paisDistincionText.setAlignment(Element.ALIGN_CENTER);
        headerPaisDistincion.addElement(paisDistincionText);
        tableDistinciones.addCell(headerPaisDistincion);

        PdfPCell headerYearDistincion = new PdfPCell();
        Paragraph yearDistincionText = new Paragraph("Año");
        yearDistincionText.setAlignment(Element.ALIGN_CENTER);
        headerYearDistincion.addElement(yearDistincionText);
        tableDistinciones.addCell(headerYearDistincion);

        PdfPCell headerRazonDistincion = new PdfPCell();
        Paragraph razonDistincionText = new Paragraph("Razón");
        razonDistincionText.setAlignment(Element.ALIGN_CENTER);
        headerRazonDistincion.addElement(razonDistincionText);
        tableDistinciones.addCell(headerRazonDistincion);

        distinciones.forEach(distincionResponse -> {
            PdfPCell bodyNombreDistincion = new PdfPCell();
            Paragraph bodyNombreDistincionText = new Paragraph(distincionResponse.getNombre());
            bodyNombreDistincionText.setAlignment(Element.ALIGN_CENTER);
            bodyNombreDistincion.addElement(bodyNombreDistincionText);
            tableDistinciones.addCell(bodyNombreDistincion);

            PdfPCell bodyInstitucionDistincion = new PdfPCell();
            Paragraph bodyInstitucionDistincionText = new Paragraph(distincionResponse.getInstit());
            bodyInstitucionDistincionText.setAlignment(Element.ALIGN_CENTER);
            bodyInstitucionDistincion.addElement(bodyInstitucionDistincionText);
            tableDistinciones.addCell(bodyInstitucionDistincion);

            PdfPCell bodyPaisDistincion = new PdfPCell();
            Paragraph bodyPaisDistincionText = new Paragraph(distincionResponse.getPais());
            bodyPaisDistincionText.setAlignment(Element.ALIGN_CENTER);
            bodyPaisDistincion.addElement(bodyPaisDistincionText);
            tableDistinciones.addCell(bodyPaisDistincion);

            PdfPCell bodyYearDistincion = new PdfPCell();
            Paragraph bodyYearDistincionText = new Paragraph(distincionResponse.getAnio());
            bodyYearDistincionText.setAlignment(Element.ALIGN_CENTER);
            bodyYearDistincion.addElement(bodyYearDistincionText);
            tableDistinciones.addCell(bodyYearDistincion);

            PdfPCell bodyRazonDistincion = new PdfPCell();
            Paragraph bodyRazonDistincionText = new Paragraph(distincionResponse.getRazon());
            bodyRazonDistincionText.setAlignment(Element.ALIGN_CENTER);
            bodyRazonDistincion.addElement(bodyRazonDistincionText);
            tableDistinciones.addCell(bodyRazonDistincion);
        });

        // Proyectos
        PdfPTable tableProyectos = new PdfPTable(3);
        tableProyectos.setWidthPercentage(90);

        Paragraph proyectosTitulo = new Paragraph("Proyectos");
        proyectosTitulo.setAlignment(Element.ALIGN_LEFT);
        proyectosTitulo.setIndentationLeft(10);
        proyectosTitulo.setSpacingAfter(20);
        proyectosTitulo.setSpacingBefore(20);

        PdfPCell headerNombreProyecto = new PdfPCell();
        Paragraph nombreProyectoText = new Paragraph("Nombre");
        nombreProyectoText.setAlignment(Element.ALIGN_CENTER);
        headerNombreProyecto.addElement(nombreProyectoText);
        tableProyectos.addCell(headerNombreProyecto);

        PdfPCell headerTipoProyecto = new PdfPCell();
        Paragraph tipoProyectoText = new Paragraph("Tipo");
        tipoProyectoText.setAlignment(Element.ALIGN_CENTER);
        headerTipoProyecto.addElement(tipoProyectoText);
        tableProyectos.addCell(headerTipoProyecto);

        PdfPCell headerPeriodoProyecto = new PdfPCell();
        Paragraph periodoProyectoText = new Paragraph("Periodo");
        periodoProyectoText.setAlignment(Element.ALIGN_CENTER);
        headerPeriodoProyecto.addElement(periodoProyectoText);
        tableProyectos.addCell(headerPeriodoProyecto);

        proyectos.forEach(proyectoResponse -> {
            PdfPCell bodyNombreProyecto = new PdfPCell();
            Paragraph bodyNombreProyectoText = new Paragraph(proyectoResponse.getTitp());
            bodyNombreProyectoText.setAlignment(Element.ALIGN_CENTER);
            bodyNombreProyecto.addElement(bodyNombreProyectoText);
            tableProyectos.addCell(bodyNombreProyecto);

            PdfPCell bodyTipoProyecto = new PdfPCell();
            Paragraph bodyTipoProyectoText = new Paragraph(proyectoResponse.getTipp());
            bodyTipoProyectoText.setAlignment(Element.ALIGN_CENTER);
            bodyTipoProyecto.addElement(bodyTipoProyectoText);
            tableProyectos.addCell(bodyTipoProyecto);

            PdfPCell bodyPeriodoProyecto = new PdfPCell();
            Paragraph bodyperiodoProyectoText = new Paragraph(proyectoResponse.getFini() + " - " + proyectoResponse.getFfin());
            bodyperiodoProyectoText.setAlignment(Element.ALIGN_CENTER);
            bodyPeriodoProyecto.addElement(bodyperiodoProyectoText);
            tableProyectos.addCell(bodyPeriodoProyecto);
        });

        // Docencia
        PdfPTable tableDocencia = new PdfPTable(7);
        tableProyectos.setWidthPercentage(90);

        Paragraph docenciaTitulo = new Paragraph("Docencia");
        docenciaTitulo.setAlignment(Element.ALIGN_LEFT);
        docenciaTitulo.setIndentationLeft(10);
        docenciaTitulo.setSpacingAfter(20);
        docenciaTitulo.setSpacingBefore(20);

        PdfPCell headerAsignaturaDocencia = new PdfPCell();
        Paragraph bodyAsignaturaDocenciaText = new Paragraph("Asignatura");
        bodyAsignaturaDocenciaText.setAlignment(Element.ALIGN_CENTER);
        headerAsignaturaDocencia.addElement(bodyAsignaturaDocenciaText);
        tableDocencia.addCell(headerAsignaturaDocencia);

        PdfPCell headerPeriodoDocencia = new PdfPCell();
        Paragraph periodoDocenciaText = new Paragraph("Periodo");
        periodoDocenciaText.setAlignment(Element.ALIGN_CENTER);
        headerPeriodoDocencia.addElement(periodoDocenciaText);
        tableDocencia.addCell(headerPeriodoDocencia);

        PdfPCell headerGrupoDocencia = new PdfPCell();
        Paragraph grupoDocenciaText = new Paragraph("Grupo");
        grupoDocenciaText.setAlignment(Element.ALIGN_CENTER);
        headerGrupoDocencia.addElement(grupoDocenciaText);
        tableDocencia.addCell(headerGrupoDocencia);

        PdfPCell headerCantidadEstudiantes = new PdfPCell();
        Paragraph cantidadEstudiantesText = new Paragraph("# Estudiantes");
        cantidadEstudiantesText.setAlignment(Element.ALIGN_CENTER);
        headerCantidadEstudiantes.addElement(cantidadEstudiantesText);
        tableDocencia.addCell(headerCantidadEstudiantes);

        PdfPCell headerGanaron = new PdfPCell();
        Paragraph ganaronText = new Paragraph("Ganaron");
        ganaronText.setAlignment(Element.ALIGN_CENTER);
        headerGanaron.addElement(ganaronText);
        tableDocencia.addCell(headerGanaron);

        PdfPCell headerPerdieron = new PdfPCell();
        Paragraph perdieronText = new Paragraph("Perdieron");
        perdieronText.setAlignment(Element.ALIGN_CENTER);
        headerPerdieron.addElement(perdieronText);
        tableDocencia.addCell(headerPerdieron);

        PdfPCell headerCancelaron = new PdfPCell();
        Paragraph cancelaronText = new Paragraph("Cancelaron");
        cancelaronText.setAlignment(Element.ALIGN_CENTER);
        headerCancelaron.addElement(cancelaronText);
        tableDocencia.addCell(headerCancelaron);

        docencia.forEach(docenciaResponse -> {
            PdfPCell bodyAsignatura = new PdfPCell();
            Paragraph bodyAsignaturaText = new Paragraph(docenciaResponse.getNom_asig());
            bodyAsignaturaText.setAlignment(Element.ALIGN_CENTER);
            bodyAsignatura.addElement(bodyAsignaturaText);
            tableDocencia.addCell(bodyAsignatura);

            PdfPCell bodyPeriodo = new PdfPCell();
            Paragraph bodyPeriodoDocenciaText = new Paragraph(docenciaResponse.getPeriodo());
            bodyPeriodoDocenciaText.setAlignment(Element.ALIGN_CENTER);
            bodyPeriodo.addElement(bodyPeriodoDocenciaText);
            tableDocencia.addCell(bodyPeriodo);

            PdfPCell bodyGrupo = new PdfPCell();
            Paragraph bodyGrupoDocenciaText = new Paragraph(String.valueOf(docenciaResponse.getGrupo()));
            bodyGrupoDocenciaText.setAlignment(Element.ALIGN_CENTER);
            bodyGrupo.addElement(bodyGrupoDocenciaText);
            tableDocencia.addCell(bodyGrupo);

            PdfPCell cantidadAlumnos = new PdfPCell();
            Paragraph cantidadAlumnosText = new Paragraph(String.valueOf(docenciaResponse.getCan_alum()));
            cantidadAlumnosText.setAlignment(Element.ALIGN_CENTER);
            cantidadAlumnos.addElement(cantidadAlumnosText);
            tableDocencia.addCell(cantidadAlumnos);

            PdfPCell ganaronDocencia = new PdfPCell();
            Paragraph bodyGanaronText = new Paragraph(String.valueOf(docenciaResponse.getCan_gan()));
            bodyGanaronText.setAlignment(Element.ALIGN_CENTER);
            ganaronDocencia.addElement(bodyGanaronText);
            tableDocencia.addCell(ganaronDocencia);

            PdfPCell perdieronDocencia = new PdfPCell();
            Paragraph perdieronDocenciaText = new Paragraph(String.valueOf(docenciaResponse.getPerdida()));
            perdieronDocenciaText.setAlignment(Element.ALIGN_CENTER);
            perdieronDocencia.addElement(perdieronDocenciaText);
            tableDocencia.addCell(perdieronDocencia);

            PdfPCell cancelaronDociencia = new PdfPCell();
            Paragraph cancelaronDocenciaText = new Paragraph(String.valueOf(docenciaResponse.getCancelaron()));
            cancelaronDocenciaText.setAlignment(Element.ALIGN_CENTER);
            cancelaronDociencia.addElement(cancelaronDocenciaText);
            tableDocencia.addCell(cancelaronDociencia);
        });

        // Generar documento
        Document document = new Document(PageSize.LETTER, 4, 4, 20, 20);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        document.add(tituloDocumento);
        document.add(nombreAspirante);
        document.add(documento);
        document.add(nacimiento);
        document.add(genero);
        document.add(direccion);
        document.add(telefono);
        document.add(celular);
        document.add(correo);
        document.add(perfilProfesionalTitulo);
        document.add(perfilProfesional);
        document.add(estudiosTitulo);
        document.add(tableEstudios);
        document.add(idiomasTitulo);
        document.add(tableIdiomas);
        document.add(experienciasTitulo);
        document.add(tableExperiencias);
        document.add(publicacionesTitulo);
        document.add(tablePublicaciones);
        document.add(membresiasTitulo);
        document.add(tableMembresias);
        document.add(distincionesTitulo);
        document.add(tableDistinciones);
        document.add(proyectosTitulo);
        document.add(tableProyectos);
        document.add(docenciaTitulo);
        document.add(tableDocencia);
        document.close();
    }
}
