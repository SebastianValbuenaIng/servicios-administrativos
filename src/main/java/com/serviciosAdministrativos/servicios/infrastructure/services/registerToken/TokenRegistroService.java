package com.serviciosAdministrativos.servicios.infrastructure.services.registerToken;

import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.registerToken.TokenRegisterServiceImpl;
import com.serviciosAdministrativos.servicios.util.helpers.RegistroConnection;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class TokenRegistroService implements TokenRegisterServiceImpl {
    private final RegistroConnection registroConnection;

    public TokenRegistroService(RegistroConnection registroConnection) {
        this.registroConnection = registroConnection;
    }

    @Override
    public void generarTokenInicioGestorCursos(String id_emp, String cadena_llave, String cadena_encriptada, String flag_sesion) throws Exception {
        try {
            registroConnection.executeSql("delete from registro.token_gestor_cursos where id_persona='" + id_emp + "'");
        } catch (SQLException ex) {
            if (!ex.getMessage().equals("Error no Definido. Cero filas afectadas.")) {
                throw new Exception(ex.getMessage());
            }
        }

        registroConnection.executeSql("insert into registro.token_gestor_cursos (id_persona, cadena_llave, cadena_encriptada, flag_sesion) values ('" + id_emp + "', '" + cadena_llave + "', '" + cadena_encriptada + "', '" + flag_sesion + "')");
    }
}
