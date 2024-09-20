package com.serviciosAdministrativos.servicios.infrastructure.abstract_services.registerToken;

public interface TokenRegisterServiceImpl {
    void generarTokenInicioGestorCursos(String id_emp, String cadena_llave, String cadena_encriptada, String flag_sesion) throws Exception;
}
