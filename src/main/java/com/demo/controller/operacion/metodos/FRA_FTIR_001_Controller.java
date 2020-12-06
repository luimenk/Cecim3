package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.FRA_CST_001;
import com.demo.model.operacion.metodos.FRA_DI_001;
import com.demo.model.operacion.metodos.FRA_FTIR_001;
import com.demo.service.formatos.metodos.IMPRIMIR_FRA_CST_001_Service;
import com.demo.service.formatos.metodos.IMPRIMIR_FRA_DI_001_Service;
import com.demo.service.formatos.metodos.IMPRIMIR_FRA_FTIR_001_Service;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.metodos.FRA_CST_001_Service;
import com.demo.service.operacion.metodos.FRA_DI_001_Service;
import com.demo.service.operacion.metodos.FRA_FTIR_001_Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping(path = "/espectrometriaInfrarroja")
public class FRA_FTIR_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_FTIR_001_Service fra_ftir_001_service;

    @Autowired
    private IMPRIMIR_FRA_FTIR_001_Service imprimir_fra_ftir_001_service;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_FTIR_001> getAll() {
        return fra_ftir_001_service.findAll();
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_FTIR_001 get(@PathVariable("id") Long id) {
        FRA_FTIR_001 fra_ftir_001 = fra_ftir_001_service.findById(id);

        if (fra_ftir_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_ftir_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Map<String, String> request) throws Exception {
        APP.debug("Apartado de registro nuevo" + calendario.getTime());
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        FRA_FTIR_001 fra_ftir_001 = new FRA_FTIR_001();

        fra_ftir_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
        fra_ftir_001.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_ftir_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_ftir_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
        fra_ftir_001.setTemperatura(request.get("temperatura"));
        fra_ftir_001.setHumedadRelativa(request.get("humedadRelativa"));
        fra_ftir_001.setCodigoEspectrometro(request.get("codigoEspectrometro"));
        fra_ftir_001.setCompuesto1(request.get("compuesto1"));
        fra_ftir_001.setIdentidad1(request.get("identidad1"));
        fra_ftir_001.setCompuesto2(request.get("compuesto2"));
        fra_ftir_001.setIdentidad2(request.get("identidad2"));
        fra_ftir_001.setObservaciones(request.get("observaciones"));
        fra_ftir_001.setRealizo(request.get("realizo"));
        fra_ftir_001.setSupervisor(request.get("supervisor"));
        fra_ftir_001.setMetodoMuestra(metodoMuestra);

        fra_ftir_001_service.save(fra_ftir_001);

        metodoMuestra.setEstatus("OK");
        metodoMuestraService.save(metodoMuestra);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimirEspectrometriaInfrarroja/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-FTIR-001");
        System.out.println(LocalTime.now());

        return imprimir_fra_ftir_001_service.crearFormato(id,1);
    }

    @RequestMapping(value = "/imprimirEspectrometriaInfrarroja2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        System.out.println("Se generó FRA-FTIR-001");
        System.out.println(LocalTime.now());

        return imprimir_fra_ftir_001_service.crearFormato(id,2);
    }
}
