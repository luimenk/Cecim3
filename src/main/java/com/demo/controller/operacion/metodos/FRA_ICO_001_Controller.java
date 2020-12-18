package com.demo.controller.operacion.metodos;

import com.demo.model.operacion.MetodoMuestra;
import com.demo.model.operacion.metodos.*;
import com.demo.service.operacion.MetodoMuestraService;
import com.demo.service.operacion.metodos.*;
import com.demo.utils.SaveInServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.jni.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping(path = "/FRAICO")
public class FRA_ICO_001_Controller {

    private static final Logger APP = LoggerFactory.getLogger("info");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    Calendar calendario = new GregorianCalendar();

    @Autowired
    private FRA_ICO_001_Service fra_ico_001_service;

    @Autowired
    private FRA_ICO_001_DATA_Service fra_ico_001_data_service;

    @Autowired
    private MetodoMuestraService metodoMuestraService;

    //ListarTodo
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_ICO_001> getAll() {
        return fra_ico_001_service.findAll();
    }

    //ListarTodoPorData
    @RequestMapping(method = RequestMethod.GET, value = "/getAllBy/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public List<FRA_ICO_001_DATA> getAllBy(@PathVariable("id") Long id) {
        return fra_ico_001_data_service.findRandPreguntas(id);
    }

    //ListarUnElemento
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET})
    public FRA_ICO_001 get(@PathVariable("id") Long id) {
        FRA_ICO_001 fra_ico_001 = fra_ico_001_service.findById(id);

        if (fra_ico_001 == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return fra_ico_001;
    }

    //GuardarElemento
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create1(@RequestBody Map<String, String> request) throws Exception {
        APP.debug("Registro FRA_ICO a las: " + calendario.getTime());
        FRA_ICO_001 fra_ico_001 = new FRA_ICO_001();

        fra_ico_001.setFolioSolicitudServicioInterno(request.get("folioSolicitudServicioInterno"));
        fra_ico_001.setFechaInicioAnalisis(request.get("fechaInicioAnalisis"));
        fra_ico_001.setFechaFinalAnalisis(request.get("fechaFinalAnalisis"));
        fra_ico_001.setIdInternoMuestra(request.get("idInternoMuestra"));
        fra_ico_001.setTemperatura(request.get("temperatura"));
        fra_ico_001.setHumedadRelativa(request.get("humedadRelativa"));
        fra_ico_001.setCodigoEspectrometro(request.get("codigoEspectrometro"));
        fra_ico_001.setParrillaCalentamiento(request.get("parrillaCalentamiento"));
        fra_ico_001.setEstatus("inicio");
        MetodoMuestra metodoMuestra = metodoMuestraService.findById(Long.parseLong(request.get("id")));
        fra_ico_001.setMetodoMuestra(metodoMuestra);

        fra_ico_001_service.save(fra_ico_001);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/iniciar/{id}", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    public ResponseEntity<?> iniciar(@PathVariable("id") Long id) throws Exception {
        APP.debug("Se inició FRA_ICO a las: " + calendario.getTime());

        FRA_ICO_001 fra_ico_001 = fra_ico_001_service.findById(id);
        fra_ico_001.setEstatus("progreso");

        fra_ico_001_service.save(fra_ico_001);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //GuardarElemento
    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> agrega(@RequestPart("imagen") MultipartFile file,
                                    @RequestPart("datos") Map<String, String> request,
                                    //@RequestPart("tiempoExposicion") String tiempoExposicion,
                                    //@RequestBody Map<String, String> request,
                                    Principal principal) {

        String filePath = "";

        if ("Linux".equals(System.getProperty("os.name"))){
            filePath = "/home/adpmx/java/laboratorio.cecim.com.mx/QR/";
        } else {
            filePath = System.getProperty("user.home") + "/";
        }

        FRA_ICO_001 fra_ico_001 = fra_ico_001_service.findById(Long.parseLong(request.get("id")));
        FRA_ICO_001_DATA fra_ico_001_data = new FRA_ICO_001_DATA();
        //Timestamp ts = new Timestamp()

        SaveInServer saveInServer = new SaveInServer();
        try {
            fra_ico_001_data.setFra_ico_001(fra_ico_001);
            fra_ico_001_data.setTiempoExposicion(request.get("tiempoExposicion"));
            fra_ico_001_data.setIco(request.get("icocod"));
            fra_ico_001_data.setPathImage(filePath+saveInServer.SaveInServer(file, filePath));

            fra_ico_001_data_service.save(fra_ico_001_data);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //GuardarElemento
    @RequestMapping(value = "/terminar", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create2(@RequestBody Map<String, String> request) throws Exception {
        APP.debug("Se finalizó FRA_ICO a las: " + calendario.getTime());

        FRA_ICO_001 fra_ico_001 = fra_ico_001_service.findById(Long.parseLong(request.get("id")));

        fra_ico_001.setTiempoTotalExposicion(request.get("tiempoTotalExposicion"));
        fra_ico_001.setValorFinalICO(request.get("valorFinalICO"));
        fra_ico_001.setObservaciones(request.get("observaciones"));
        fra_ico_001.setRealizo(request.get("realizo"));
        fra_ico_001.setSupervisor(request.get("supervisor"));

        fra_ico_001.setEstatus("terminado");

        fra_ico_001_service.save(fra_ico_001);

        MetodoMuestra metodoMuestra = fra_ico_001.getMetodoMuestra();
        metodoMuestra.setEstatus("OK");
        metodoMuestraService.save(metodoMuestra);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/imprimir/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir1(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_ICO a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_ico_001_service.crearFormato(id, 1);
    }

    @RequestMapping(value = "/imprimir2/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> imprimir2(@PathVariable("id") Long id) throws Exception {
        APP.debug("Impresion de FRA_ICO a las: " + calendario.getTime() + calendario.getTimeZone());

        return fra_ico_001_service.crearFormato(id, 2);
    }
}