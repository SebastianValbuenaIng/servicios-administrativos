package com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias;

import jakarta.servlet.http.HttpServletResponse;

public interface IGenerarPDFAspiranteService {
    void generatePDF(HttpServletResponse response, Integer id);
    void getFileSupport(HttpServletResponse response, String id);
}
