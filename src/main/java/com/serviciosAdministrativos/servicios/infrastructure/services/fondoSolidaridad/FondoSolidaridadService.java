package com.serviciosAdministrativos.servicios.infrastructure.services.fondoSolidaridad;

import com.serviciosAdministrativos.servicios.api.models.fondoSolidaridad.request.FondoSolidaridadRequest;
import com.serviciosAdministrativos.servicios.domain.entities.DBActualizacionDatos.FondoSolidaridadEntity;
import com.serviciosAdministrativos.servicios.domain.repositories.DBActualizacionDatos.FondoSolidaridadRepository;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.fondoSolidaridad.IFondoSolidaridadService;
import com.serviciosAdministrativos.servicios.util.errors.ServerError;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class FondoSolidaridadService implements IFondoSolidaridadService {
    private final FondoSolidaridadRepository fondoSolidaridadRepository;

    public FondoSolidaridadService(FondoSolidaridadRepository fondoSolidaridadRepository) {
        this.fondoSolidaridadRepository = fondoSolidaridadRepository;
    }

    @Override
    public Map<String, String> postFondoSolidaridad(FondoSolidaridadRequest fondoSolidaridadRequest) {
        try {
            if(fondoSolidaridadRequest.getValor() < 0) throw new ServerError("El valor debe ser mayor a 0");

            FondoSolidaridadEntity fondoSolidaridadEntity = new FondoSolidaridadEntity();
            fondoSolidaridadEntity.setFec_graba(LocalDateTime.now());
            fondoSolidaridadEntity.setValor(fondoSolidaridadRequest.getValor());
            fondoSolidaridadEntity.setCod_emp(fondoSolidaridadRequest.getCod_emp());

            if (fondoSolidaridadRequest.getMes_donacion_indefinida() == null || fondoSolidaridadRequest.getMes_donacion_indefinida().isEmpty()) {
                fondoSolidaridadEntity.setDonacion_indefinida(false);

                donationWithMoreMonths(fondoSolidaridadRequest, fondoSolidaridadEntity);
            }
            else {
                fondoSolidaridadEntity.setDonacion_indefinida(true);

                donationUndefined(fondoSolidaridadRequest, fondoSolidaridadEntity);
            }

            fondoSolidaridadRepository.save(fondoSolidaridadEntity);

            return Map.of("message", "se realizó la operación correctamente");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Ha ocurrido un error interno");
        }
    }

    private static void donationWithMoreMonths(FondoSolidaridadRequest fondoSolidaridadRequest, FondoSolidaridadEntity fondoSolidaridadEntity) {
        Optional<String> enero = fondoSolidaridadRequest.getMeses()
                .stream().filter(s -> s.equalsIgnoreCase("enero")).findFirst();

        fondoSolidaridadEntity.setEnero(enero.isPresent());

        Optional<String> febrero = fondoSolidaridadRequest.getMeses()
                .stream().filter(s -> s.equalsIgnoreCase("febrero")).findFirst();

        fondoSolidaridadEntity.setFebrero(febrero.isPresent());

        Optional<String> marzo = fondoSolidaridadRequest.getMeses()
                .stream().filter(s -> s.equalsIgnoreCase("marzo")).findFirst();

        fondoSolidaridadEntity.setMarzo(marzo.isPresent());

        Optional<String> abril = fondoSolidaridadRequest.getMeses()
                .stream().filter(s -> s.equalsIgnoreCase("abril")).findFirst();

        fondoSolidaridadEntity.setAbril(abril.isPresent());

        Optional<String> mayo = fondoSolidaridadRequest.getMeses()
                .stream().filter(s -> s.equalsIgnoreCase("mayo")).findFirst();

        fondoSolidaridadEntity.setMayo(mayo.isPresent());

        Optional<String> junio = fondoSolidaridadRequest.getMeses()
                .stream().filter(s -> s.equalsIgnoreCase("junio")).findFirst();

        fondoSolidaridadEntity.setJunio(junio.isPresent());

        Optional<String> julio = fondoSolidaridadRequest.getMeses()
                .stream().filter(s -> s.equalsIgnoreCase("julio")).findFirst();

        fondoSolidaridadEntity.setJulio(julio.isPresent());

        Optional<String> agosto = fondoSolidaridadRequest.getMeses()
                .stream().filter(s -> s.equalsIgnoreCase("agosto")).findFirst();

        fondoSolidaridadEntity.setAgosto(agosto.isPresent());

        Optional<String> septiembre = fondoSolidaridadRequest.getMeses()
                .stream().filter(s -> s.equalsIgnoreCase("septiembre")).findFirst();

        fondoSolidaridadEntity.setSeptiembre(septiembre.isPresent());

        Optional<String> octubre = fondoSolidaridadRequest.getMeses()
                .stream().filter(s -> s.equalsIgnoreCase("octubre")).findFirst();

        fondoSolidaridadEntity.setOctubre(octubre.isPresent());

        Optional<String> noviembre = fondoSolidaridadRequest.getMeses()
                .stream().filter(s -> s.equalsIgnoreCase("noviembre")).findFirst();

        fondoSolidaridadEntity.setNoviembre(noviembre.isPresent());

        Optional<String> diciembre = fondoSolidaridadRequest.getMeses()
                .stream().filter(s -> s.equalsIgnoreCase("diciembre")).findFirst();

        fondoSolidaridadEntity.setDiciembre(diciembre.isPresent());
    }

    private static void donationUndefined(FondoSolidaridadRequest fondoSolidaridadRequest, FondoSolidaridadEntity fondoSolidaridadEntity) {
        String mes = fondoSolidaridadRequest.getMes_donacion_indefinida();

        fondoSolidaridadEntity.setEnero(mes.equalsIgnoreCase("enero"));

        fondoSolidaridadEntity.setFebrero(mes.equalsIgnoreCase("febrero"));

        fondoSolidaridadEntity.setMarzo(mes.equalsIgnoreCase("marzo"));

        fondoSolidaridadEntity.setAbril(mes.equalsIgnoreCase("abril"));

        fondoSolidaridadEntity.setMayo(mes.equalsIgnoreCase("mayo"));

        fondoSolidaridadEntity.setJunio(mes.equalsIgnoreCase("junio"));

        fondoSolidaridadEntity.setJulio(mes.equalsIgnoreCase("julio"));

        fondoSolidaridadEntity.setAgosto(mes.equalsIgnoreCase("agosto"));

        fondoSolidaridadEntity.setSeptiembre(mes.equalsIgnoreCase("septiembre"));

        fondoSolidaridadEntity.setOctubre(mes.equalsIgnoreCase("octubre"));

        fondoSolidaridadEntity.setNoviembre(mes.equalsIgnoreCase("noviembre"));

        fondoSolidaridadEntity.setDiciembre(mes.equalsIgnoreCase("diciembre"));
    }
}
