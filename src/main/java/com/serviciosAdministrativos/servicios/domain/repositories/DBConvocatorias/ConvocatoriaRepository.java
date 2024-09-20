package com.serviciosAdministrativos.servicios.domain.repositories.DBConvocatorias;

import com.serviciosAdministrativos.servicios.domain.entities.DBConvocatorias.ConvocatoriasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ConvocatoriaRepository extends JpaRepository<ConvocatoriasEntity, Integer> {
    List<ConvocatoriasEntity> findAllByIdcon(Integer id);

    @Query(value = "select iddis, nombre, instit, pais, anio, razon, blq from conv.distinciones where idemp = :id order by anio", nativeQuery = true)
    List<Map<String, Object>> distincion(String id);
    @Query(value = "select * FROM conv.histprof where docprof = :id", nativeQuery = true)
    List<Map<String, Object>> docencia(String id);

    @Query(value = "select idpub, tipo, titulo, titmay, area, fec, edic, edit, ciudad, pais from conv.publicaciones where idemp = :id order by fec DESC", nativeQuery = true)
    List<Map<String, Object>> publicacion(String id);

    @Query(value = "select idpro, tipp, titp, convert(varchar(10),fini,103) fini, convert(varchar(10),ffin,103) ffin, blq from conv.productos where idemp = :id order by fini", nativeQuery = true)
    List<Map<String, Object>> proyecto(String id);

    @Query(value = "select idhis, nomorg, convert(varchar(10), fini, 103) + ' - ' + convert(varchar(10), ffin, 103) as periodo, cargo as nombre_cargo, tpcar as tipo_cargo, convert(varchar(10), fini, 103) fini, convert(varchar(10), ffin, 103) ffin from conv.histlab where idemp = :id order by conv.histlab.fini DESC", nativeQuery = true)
    List<Map<String, Object>> experience(String id);

    @Query(value = "select LTRIM(RTRIM(b.ap1)) + ' ' + LTRIM(RTRIM(b.ap2)) + ' ' + LTRIM(RTRIM(b.nom)) as nombre,  a.estado, a.obs, a.fecha, a.conv, b.docid from conv.partconvoca a inner join conv.aspirante b on (a.cod_emp = b.docid) where a.conv = :id", nativeQuery = true)
    List<Map<String, String>> aspirant(String id);

    @Query(value = "SELECT * FROM conv.aspirante where docid = :docid", nativeQuery = true)
    List<Map<String, String>> validarAspiranteExistente(String docid);

    @Query(value = "SELECT max(idcon) + 1 FROM conv.convoca", nativeQuery = true)
    Integer getIdSecuencia();
}