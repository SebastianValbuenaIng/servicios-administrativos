package com.serviciosAdministrativos.servicios.infrastructure.services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.EstudiosResponse;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IEstudioService;
import com.serviciosAdministrativos.servicios.util.errors.ServerError;
import com.serviciosAdministrativos.servicios.util.helpers.NovasoftConnection;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EstudioService implements IEstudioService {
    private final NovasoftConnection novasoftConnection;

    public EstudioService(NovasoftConnection novasoftConnection) {
        this.novasoftConnection = novasoftConnection;
    }

    public Set<EstudiosResponse> getEstudios(String id) {
        try {
            String findEstudios = String.format("""
                    select idestud, nom_est, nom_ins, convert(varchar(10), fec_gra, 103) fgra, des_est, blq, nro_tar from novasoft.dbo.rhh_tbclaest, [10.1.5.40\\GOBIERNO_DATOS].[Convocatorias].[conv].estudasp, novasoft.dbo.rhh_tbinsti where novasoft.dbo.rhh_tbclaest.tip_est = estudasp.tip_est collate Modern_Spanish_CI_AS and estudasp.cod_ins collate Modern_Spanish_CI_AS = novasoft.dbo.rhh_tbinsti.cod_ins and cod_emp = '%s' order by fec_gra DESC
                    """, id);

            List<Map<String, Object>> estudios = novasoftConnection.executeSelectSql(findEstudios);

            return estudios.stream()
                    .map(this::entityResponse)
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ServerError("Ha ocurrido un error interno");
        }
    }

    private EstudiosResponse entityResponse(Map<String, Object> estudio) {
        EstudiosResponse response = new EstudiosResponse();

        response.setIdestud(Integer.valueOf(String.valueOf(estudio.get("idestud"))));
        response.setNom_est((String) estudio.get("nom_est"));
        response.setNom_ins(estudio.get("nom_ins").toString().trim());
        response.setFgra((String) estudio.get("fgra"));
        response.setDes_est(estudio.get("des_est").toString().trim());
        response.setBlq(String.valueOf(estudio.get("blq")));
        response.setNro_tar((String) estudio.get("nro_tar"));
        return response;
    }
}
