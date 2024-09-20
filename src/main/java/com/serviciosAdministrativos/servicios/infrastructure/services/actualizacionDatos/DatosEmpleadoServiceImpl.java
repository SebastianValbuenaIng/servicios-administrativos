package com.serviciosAdministrativos.servicios.infrastructure.services.actualizacionDatos;

import com.serviciosAdministrativos.servicios.api.models.actualizacionDatos.request.InformacionEmpleadoRequest;
import com.serviciosAdministrativos.servicios.api.models.actualizacionDatos.response.DatosEmpleadoRedesSocialesResponse;
import com.serviciosAdministrativos.servicios.domain.entities.DBActualizaDatosPers.*;
import com.serviciosAdministrativos.servicios.domain.entities.DBActualizacionDatos.UsrRhhActualizaDatosEntity;
import com.serviciosAdministrativos.servicios.domain.repositories.DBActualizaDatosPers.*;
import com.serviciosAdministrativos.servicios.domain.repositories.DBActualizacionDatos.UsrRhhActualizaDatosRepository;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.actualizacionDatos.DatosEmpleadoService;
import com.serviciosAdministrativos.servicios.util.errors.NotFoundError;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class DatosEmpleadoServiceImpl implements DatosEmpleadoService {
    private final DatosEmpleadoRepository datosEmpleadoRepository;
    private final RedesSocialesRepository redesSocialesRepository;
    private final PublicacionesRepository publicacionesRepository;
    private final ExperienciaRepository experienciaRepository;
    private final AreasInteresRepository areasInteresRepository;
    private final IdiomasRepository idiomasRepository;
    private final FamiliaEmpleadoRepository familiaEmpleadoRepository;
    private final TitulosEmpleadoRepository titulosEmpleadoRepository;
    private final PerfilWebRepository perfilWebRepository;
    private final UsrRhhActualizaDatosRepository usrRhhActualizaDatosRepository;
    private final FechasRepository fechasRepository;

    public DatosEmpleadoServiceImpl(DatosEmpleadoRepository datosEmpleadoRepository,
                                    RedesSocialesRepository redesSocialesRepository,
                                    PublicacionesRepository publicacionesRepository,
                                    ExperienciaRepository experienciaRepository,
                                    AreasInteresRepository areasInteresRepository,
                                    IdiomasRepository idiomasRepository,
                                    FamiliaEmpleadoRepository familiaEmpleadoRepository,
                                    TitulosEmpleadoRepository titulosEmpleadoRepository,
                                    PerfilWebRepository perfilWebRepository,
                                    UsrRhhActualizaDatosRepository usrRhhActualizaDatosRepository,
                                    FechasRepository fechasRepository) {
        this.datosEmpleadoRepository = datosEmpleadoRepository;
        this.redesSocialesRepository = redesSocialesRepository;
        this.publicacionesRepository = publicacionesRepository;
        this.experienciaRepository = experienciaRepository;
        this.areasInteresRepository = areasInteresRepository;
        this.idiomasRepository = idiomasRepository;
        this.familiaEmpleadoRepository = familiaEmpleadoRepository;
        this.titulosEmpleadoRepository = titulosEmpleadoRepository;
        this.perfilWebRepository = perfilWebRepository;
        this.usrRhhActualizaDatosRepository = usrRhhActualizaDatosRepository;
        this.fechasRepository = fechasRepository;
    }

    @Override
    public DatosEmpleadoRedesSocialesResponse findDatosEmpleadoByCoEmp(String documento) {
        Optional<DatosEmpleadoEntity> findDatosEmpleado = datosEmpleadoRepository.findByCodEmp(documento);

        if (findDatosEmpleado.isEmpty()) throw new NotFoundError("El empleado no existe");

        List<RedesSocialesEntity> findRedesSocialesByEmpleado = redesSocialesRepository.findAllByCodEmp(documento);

        return entityToResponse(findDatosEmpleado.get(), findRedesSocialesByEmpleado);
    }

    private DatosEmpleadoRedesSocialesResponse entityToResponse(DatosEmpleadoEntity datosEmpleadoEntity,
                                                                List<RedesSocialesEntity> redesSocialesByEmpleado) {
        List<Map<String, Object>> redes_sociales = redesSocialesByEmpleado.stream().map(redesSocialesEntity -> {
            Map<String, Object> red_social = new LinkedHashMap<>();
            red_social.put("cod_red_soc", redesSocialesEntity.getCodRedsoc());
            red_social.put("usuario_red", redesSocialesEntity.getUsuario_red());

            return red_social;
        }).toList();

        return new DatosEmpleadoRedesSocialesResponse(
                datosEmpleadoEntity.getPais_residencia(),
                datosEmpleadoEntity.getDepto_res(),
                datosEmpleadoEntity.getNom_ciu(),
                datosEmpleadoEntity.getTel_celular(),
                datosEmpleadoEntity.getE_mail_alt(),
                datosEmpleadoEntity.getUsr_num_ext(),
                datosEmpleadoEntity.getUbicacion(),
                datosEmpleadoEntity.getEst_civ_emp(),
                datosEmpleadoEntity.getDir_res(),
                redes_sociales
        );
    }

    @Override
    public DatosEmpleadoEntity newDatosEmpleadoToSave(String documento, InformacionEmpleadoRequest informacionEmpleadoRequest) {
        Optional<DatosEmpleadoEntity> findDatosEmpleado = datosEmpleadoRepository.findByCodEmp(documento);

        if (findDatosEmpleado.isPresent()) {
            findDatosEmpleado.get().setDir_res(informacionEmpleadoRequest.getDireccion_residencia());
            findDatosEmpleado.get().setTel_celular(informacionEmpleadoRequest.getTel_celular().toString());
            findDatosEmpleado.get().setPais_residencia(informacionEmpleadoRequest.getCod_pais());
            findDatosEmpleado.get().setDepto_res(informacionEmpleadoRequest.getCod_departamento());
            findDatosEmpleado.get().setNom_ciu(informacionEmpleadoRequest.getCod_ciudad());
            findDatosEmpleado.get().setE_mail_alt(informacionEmpleadoRequest.getEmail_alterno());
            findDatosEmpleado.get().setEst_civ_emp(informacionEmpleadoRequest.getCod_estado_civil());
            findDatosEmpleado.get().setUbicacion(informacionEmpleadoRequest.getUbicacion());
            findDatosEmpleado.get().setUsr_num_ext(informacionEmpleadoRequest.getUsr_num_ext());
            findDatosEmpleado.get().setInf_veridica(informacionEmpleadoRequest.getConfirmarVericidad());
            findDatosEmpleado.get().setInd_acepta(informacionEmpleadoRequest.getAceptarTratamiento());
            findDatosEmpleado.get().setInd_modifica(informacionEmpleadoRequest.getAdicionoEstudios());

            datosEmpleadoRepository.save(findDatosEmpleado.get());

            if (!informacionEmpleadoRequest.getRed_social().isEmpty()) {

                informacionEmpleadoRequest.getRed_social().forEach(stringObjectMap -> {
                    Optional<RedesSocialesEntity> findByCodRedSoc = redesSocialesRepository
                            .findByCodRedsocAndCodEmp((Integer) stringObjectMap.get("cod_red_soc"), documento);

                    if (findByCodRedSoc.isPresent()) {
                        if (stringObjectMap.get("usuario_red") != null && !stringObjectMap.get("usuario_red").toString().trim().isEmpty()) {
                            findByCodRedSoc.get().setUsuario_red(stringObjectMap.get("usuario_red").toString());
                            redesSocialesRepository.save(findByCodRedSoc.get());
                        }
                    } else {
                        if (!stringObjectMap.get("usuario_red").toString().trim().isEmpty()) {
                            RedesSocialesEntity redesSocialesEntity = new RedesSocialesEntity(
                                    documento,
                                    (Integer) stringObjectMap.get("cod_red_soc"),
                                    stringObjectMap.get("usuario_red").toString()
                            );

                            redesSocialesRepository.save(redesSocialesEntity);
                        }
                    }
                });
            }

            return findDatosEmpleado.get();
        }

        DatosEmpleadoEntity datosEmpleadoEntityToSave = new DatosEmpleadoEntity(
                documento,
                informacionEmpleadoRequest.getDireccion_residencia(),
                informacionEmpleadoRequest.getTel_celular().toString(),
                informacionEmpleadoRequest.getCod_pais(),
                informacionEmpleadoRequest.getCod_departamento(),
                informacionEmpleadoRequest.getCod_ciudad(),
                informacionEmpleadoRequest.getEmail_alterno(),
                informacionEmpleadoRequest.getCod_estado_civil(),
                informacionEmpleadoRequest.getUbicacion(),
                informacionEmpleadoRequest.getUsr_num_ext(),
                informacionEmpleadoRequest.getAdicionoEstudios(),
                informacionEmpleadoRequest.getConfirmarVericidad(),
                informacionEmpleadoRequest.getAceptarTratamiento()
        );

        DatosEmpleadoEntity datosEmpleadoSaved = datosEmpleadoRepository.save(datosEmpleadoEntityToSave);

        if (!informacionEmpleadoRequest.getRed_social().isEmpty()) {
            informacionEmpleadoRequest.getRed_social().forEach(stringObjectMap -> {
                if (!stringObjectMap.get("usuario_red").toString().trim().isEmpty()) {
                    RedesSocialesEntity redesSocialesEntity = new RedesSocialesEntity(
                            documento,
                            (Integer) stringObjectMap.get("cod_red_soc"),
                            stringObjectMap.get("usuario_red").toString()
                    );

                    redesSocialesRepository.save(redesSocialesEntity);
                }
            });
        }

        return datosEmpleadoSaved;
    }

    @Override
    @Transactional(value = "db3TransactionManager")
    public void insertActualizaDatos() {
        List<DatosEmpleadoEntity> findAllEmpleados = (List<DatosEmpleadoEntity>) datosEmpleadoRepository.findAll();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

        findAllEmpleados.forEach(datosEmpleadoEntity -> {
            UsrRhhActualizaDatosEntity usrRhhActualizaDatosToSave = new UsrRhhActualizaDatosEntity();

            usrRhhActualizaDatosToSave.setCod_emp(datosEmpleadoEntity.getCodEmp());
            usrRhhActualizaDatosToSave.setDir_res(datosEmpleadoEntity.getDir_res() != null ? datosEmpleadoEntity.getDir_res() : null);
            usrRhhActualizaDatosToSave.setPai_res(datosEmpleadoEntity.getPais_residencia() != null ? datosEmpleadoEntity.getPais_residencia() : null);
            usrRhhActualizaDatosToSave.setDpt_res(datosEmpleadoEntity.getDepto_res() != null ? datosEmpleadoEntity.getDepto_res() : null);
            usrRhhActualizaDatosToSave.setCiu_res(datosEmpleadoEntity.getNom_ciu());
            usrRhhActualizaDatosToSave.setE_mail_alt(datosEmpleadoEntity.getE_mail_alt());
            usrRhhActualizaDatosToSave.setCelular(datosEmpleadoEntity.getTel_celular());

            // Redes sociales
            List<RedesSocialesEntity> findAllRedesSocialesByCodEmp = redesSocialesRepository.findAllByCodEmp(datosEmpleadoEntity.getCodEmp());

            if (!findAllRedesSocialesByCodEmp.isEmpty()) {
                findAllRedesSocialesByCodEmp.forEach(redesSocialesEntity -> {
                    UsrRhhActualizaDatosEntity usrRhhActualizaDatosWithRedesSociales = new UsrRhhActualizaDatosEntity();
                    usrRhhActualizaDatosWithRedesSociales.setCod_emp(datosEmpleadoEntity.getCodEmp());
                    usrRhhActualizaDatosWithRedesSociales.setFec_registro(LocalDateTime.now());
                    usrRhhActualizaDatosWithRedesSociales.setCod_redsoc(redesSocialesEntity.getCodRedsoc());
                    usrRhhActualizaDatosWithRedesSociales.setUsuario_red(redesSocialesEntity.getUsuario_red());
                    usrRhhActualizaDatosRepository.save(usrRhhActualizaDatosWithRedesSociales);
                });
            }

            // Publicaciones
            List<PublicacionesEntity> findAllPublicacionesByCodEmp = publicacionesRepository.findAllByCodEmp(datosEmpleadoEntity.getCodEmp());

            if (!findAllPublicacionesByCodEmp.isEmpty()) {
                findAllPublicacionesByCodEmp.forEach(publicacionesEntity -> {
                    UsrRhhActualizaDatosEntity usrRhhActualizaDatosPublicaciones = new UsrRhhActualizaDatosEntity();
                    usrRhhActualizaDatosPublicaciones.setFec_registro(LocalDateTime.now());
                    usrRhhActualizaDatosPublicaciones.setCod_emp(datosEmpleadoEntity.getCodEmp());

                    String resultPublicacion = publicacionesEntity.getTitulo_public() != null ? publicacionesEntity.getTitulo_public() + " En: " + publicacionesEntity.getFecha_public().getYear() + " ed: " + publicacionesEntity.getEditorial() + " ISBN: " + publicacionesEntity.getIsbn() : null;

                    usrRhhActualizaDatosPublicaciones.setDescrip_publicacion(resultPublicacion);
                    usrRhhActualizaDatosRepository.save(usrRhhActualizaDatosPublicaciones);
                });
            }

            // Ubicacion
            usrRhhActualizaDatosToSave.setUbicacion(datosEmpleadoEntity.getUbicacion() != null
                    ? datosEmpleadoEntity.getUbicacion().trim()
                    : null
            );

            // Extension
            usrRhhActualizaDatosToSave.setUsr_num_ext(datosEmpleadoEntity.getUsr_num_ext() != null
                    ? datosEmpleadoEntity.getUsr_num_ext().trim()
                    : null
            );

            usrRhhActualizaDatosToSave.setEst_civ_emp(datosEmpleadoEntity.getEst_civ_emp());

            // Area Interes
            List<AreasInteresEntity> findAllAreasInteresByCodEmp = areasInteresRepository.findAllByCodEmp(datosEmpleadoEntity.getCodEmp());

            if (!findAllAreasInteresByCodEmp.isEmpty()) {
                findAllAreasInteresByCodEmp.forEach(areasInteresEntity -> {
                    UsrRhhActualizaDatosEntity usrRhhActualizaAreaInteres = new UsrRhhActualizaDatosEntity();
                    usrRhhActualizaAreaInteres.setFec_registro(LocalDateTime.now());
                    usrRhhActualizaAreaInteres.setCod_emp(datosEmpleadoEntity.getCodEmp());

                    usrRhhActualizaAreaInteres.setId_interes(areasInteresEntity.getId());

                    usrRhhActualizaDatosRepository.save(usrRhhActualizaAreaInteres);
                });
            }

            // Experiencia
            List<ExperienciaEntity> findAllExperienciaByCodEmp = experienciaRepository.findAllByCodEmp(datosEmpleadoEntity.getCodEmp());

            if (!findAllExperienciaByCodEmp.isEmpty()) {
                findAllExperienciaByCodEmp.forEach(experienciaEntity -> {
                    UsrRhhActualizaDatosEntity usrRhhActualizaDatosExperiencia = new UsrRhhActualizaDatosEntity();
                    usrRhhActualizaDatosExperiencia.setFec_registro(LocalDateTime.now());
                    usrRhhActualizaDatosExperiencia.setCod_emp(datosEmpleadoEntity.getCodEmp());

                    usrRhhActualizaDatosExperiencia.setNom_empr(experienciaEntity.getNomEmpr());
                    usrRhhActualizaDatosExperiencia.setNom_car(experienciaEntity.getNom_car());
                    usrRhhActualizaDatosExperiencia.setArea_exp(experienciaEntity.getArea_exp());
                    usrRhhActualizaDatosExperiencia.setDes_fun(experienciaEntity.getDes_fun());
                    usrRhhActualizaDatosExperiencia.setTpo_exp(experienciaEntity.getTpo_exp());
                    usrRhhActualizaDatosExperiencia.setMot_ret(experienciaEntity.getMot_ret());
                    usrRhhActualizaDatosExperiencia.setJefe_inm(experienciaEntity.getJefe_inm());
                    usrRhhActualizaDatosExperiencia.setNum_tel(experienciaEntity.getNum_tel());
                    usrRhhActualizaDatosExperiencia.setFec_ing(experienciaEntity.getFec_ing());
                    usrRhhActualizaDatosExperiencia.setFec_ter(experienciaEntity.getFec_ter());

                    usrRhhActualizaDatosRepository.save(usrRhhActualizaDatosExperiencia);
                });
            }

            // Titulos
            List<TitulosEmpleadoEntity> findAllTitulosByCodEmp = titulosEmpleadoRepository.findAllByCodEmp(datosEmpleadoEntity.getCodEmp());

            if (!findAllTitulosByCodEmp.isEmpty()) {
                findAllTitulosByCodEmp.forEach(titulosEmpleadoEntity -> {
                    UsrRhhActualizaDatosEntity usrRhhActualizaDatosTitulo = new UsrRhhActualizaDatosEntity();
                    usrRhhActualizaDatosTitulo.setFec_registro(LocalDateTime.now());
                    usrRhhActualizaDatosTitulo.setCod_emp(datosEmpleadoEntity.getCodEmp());

                    usrRhhActualizaDatosTitulo.setCod_estudio(titulosEmpleadoEntity.getCod_estudio() != null ? titulosEmpleadoEntity.getCod_estudio() : null);
                    usrRhhActualizaDatosTitulo.setCod_ins(titulosEmpleadoEntity.getCod_ins() != null ? titulosEmpleadoEntity.getCod_ins() : null);
                    usrRhhActualizaDatosTitulo.setAno_est(titulosEmpleadoEntity.getAno_est() != null ? titulosEmpleadoEntity.getAno_est() : null);
                    usrRhhActualizaDatosTitulo.setFec_gra(LocalDateTime.parse(titulosEmpleadoEntity.getFec_gra(), formatter));
                    usrRhhActualizaDatosTitulo.setNro_tar(titulosEmpleadoEntity.getNro_tar());

                    usrRhhActualizaDatosRepository.save(usrRhhActualizaDatosTitulo);
                });
            }

            // Idiomas
            List<IdiomasEntity> findAllIdiomasByCodEmp = idiomasRepository.findAllByCodEmp(datosEmpleadoEntity.getCodEmp());

            if (!findAllIdiomasByCodEmp.isEmpty()) {
                findAllIdiomasByCodEmp.forEach(idiomasEntity -> {
                    UsrRhhActualizaDatosEntity usrRhhActualizaDatosIdioma = new UsrRhhActualizaDatosEntity();
                    usrRhhActualizaDatosIdioma.setFec_registro(LocalDateTime.now());
                    usrRhhActualizaDatosIdioma.setCod_emp(datosEmpleadoEntity.getCodEmp());

                    usrRhhActualizaDatosIdioma.setCod_idi(idiomasEntity.getCodIdi());
                    usrRhhActualizaDatosIdioma.setCod_calif(idiomasEntity.getCod_calif());
                    usrRhhActualizaDatosIdioma.setCod_hab(idiomasEntity.getCod_hab());

                    usrRhhActualizaDatosRepository.save(usrRhhActualizaDatosIdioma);
                });
            }

            // Familiares
            List<FamiliaEmpleadoEntity> findAllFamiliaresByCodEmp = familiaEmpleadoRepository.findByCodEmp(datosEmpleadoEntity.getCodEmp());

            if (!findAllFamiliaresByCodEmp.isEmpty()) {
                findAllFamiliaresByCodEmp.forEach(familiaEmpleadoEntity -> {
                    UsrRhhActualizaDatosEntity usrRhhActualizaDatosFamiliar = new UsrRhhActualizaDatosEntity();
                    usrRhhActualizaDatosFamiliar.setFec_registro(LocalDateTime.now());
                    usrRhhActualizaDatosFamiliar.setCod_emp(datosEmpleadoEntity.getCodEmp());

                    usrRhhActualizaDatosFamiliar.setAp1_fam(familiaEmpleadoEntity.getAp1Fam());
                    usrRhhActualizaDatosFamiliar.setAp2_fam(familiaEmpleadoEntity.getAp2_fam());
                    usrRhhActualizaDatosFamiliar.setNom_fam(familiaEmpleadoEntity.getNom_fam());
                    usrRhhActualizaDatosFamiliar.setTip_fam(familiaEmpleadoEntity.getTip_fam());
                    usrRhhActualizaDatosFamiliar.setTip_ide(familiaEmpleadoEntity.getTip_ide());
                    usrRhhActualizaDatosFamiliar.setNum_ced(familiaEmpleadoEntity.getNum_ced());
                    usrRhhActualizaDatosFamiliar.setFec_nac(familiaEmpleadoEntity.getFec_nac());
                    usrRhhActualizaDatosFamiliar.setSex_fam(familiaEmpleadoEntity.getSex_fam());
                    usrRhhActualizaDatosFamiliar.setEst_civ_fam(familiaEmpleadoEntity.getEst_civ_fam());
                    usrRhhActualizaDatosFamiliar.setNiv_est(familiaEmpleadoEntity.getNiv_est());
                    usrRhhActualizaDatosFamiliar.setOcu_fam(familiaEmpleadoEntity.getOcu_fam());
                    usrRhhActualizaDatosFamiliar.setInd_comp(familiaEmpleadoEntity.getInd_comp());
                    usrRhhActualizaDatosFamiliar.setInd_sub(familiaEmpleadoEntity.getInd_sub());
                    usrRhhActualizaDatosFamiliar.setInd_pro(familiaEmpleadoEntity.getInd_pro());
                    usrRhhActualizaDatosFamiliar.setInd_conv(familiaEmpleadoEntity.getInd_conv());

                    usrRhhActualizaDatosRepository.save(usrRhhActualizaDatosFamiliar);
                });
            }

            // Perfil Web
            Optional<PerfilWebEntity> findPerfilWebByCodEmp = perfilWebRepository.findByCodEmp(datosEmpleadoEntity.getCodEmp());

            if (findPerfilWebByCodEmp.isPresent()) {
                UsrRhhActualizaDatosEntity usrRhhActualizaDatosPerfilWeb = new UsrRhhActualizaDatosEntity();
                usrRhhActualizaDatosPerfilWeb.setFec_registro(LocalDateTime.now());
                usrRhhActualizaDatosPerfilWeb.setCod_emp(datosEmpleadoEntity.getCodEmp());

                usrRhhActualizaDatosPerfilWeb.setPerfil(
                        "Párrafo 1: " + (findPerfilWebByCodEmp.get().getParrafo1() != null ? findPerfilWebByCodEmp.get().getParrafo1() : "") + "\n"
                        + "Párrafo 2: " + (findPerfilWebByCodEmp.get().getParrafo2() != null ? findPerfilWebByCodEmp.get().getParrafo2() : "") + "\n"
                        + "DISTINCIONES: " + (findPerfilWebByCodEmp.get().getReconocimientos() != null ? findPerfilWebByCodEmp.get().getReconocimientos() : "") + "\n"
                        + "Membresias: " + (findPerfilWebByCodEmp.get().getMembresias() != null ? findPerfilWebByCodEmp.get().getMembresias() : "") + "\n"
                        + "Cargos: " + (findPerfilWebByCodEmp.get().getCargo_direc() != null ? findPerfilWebByCodEmp.get().getCargo_direc() : "")
                );

                usrRhhActualizaDatosRepository.save(usrRhhActualizaDatosPerfilWeb);
            }

            usrRhhActualizaDatosToSave.setInd_modifica(datosEmpleadoEntity.getInd_modifica());
            usrRhhActualizaDatosToSave.setInd_acepta(datosEmpleadoEntity.getInd_acepta());
            usrRhhActualizaDatosToSave.setInf_veridica(datosEmpleadoEntity.getInf_veridica());
            usrRhhActualizaDatosToSave.setFec_registro(LocalDateTime.now());
            usrRhhActualizaDatosRepository.save(usrRhhActualizaDatosToSave);
        });
    }

    @Override
    public Map<String, LocalDateTime> getFechaFin() {
        return Map.of("fecha", fechasRepository.findFirstFecha()
                .orElseThrow(() -> new RuntimeException("No existe una fecha fin")).getFecha_fin());
    }
}