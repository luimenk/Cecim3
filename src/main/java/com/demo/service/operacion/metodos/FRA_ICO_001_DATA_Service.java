package com.demo.service.operacion.metodos;

import com.demo.model.operacion.metodos.*;
import com.demo.repository.operacion.metodos.*;
import com.demo.utils.EstructuraNombres;
import com.demo.model.operacion.metodos.FRA_AT_001;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

@Service
public class FRA_ICO_001_DATA_Service {

    @Autowired
    private FRA_ICO_001_DATA_Repository fra_ico_001_data_repository;

    EstructuraNombres estructuraNombres = new EstructuraNombres();

    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    private static final Logger APP = LoggerFactory.getLogger("info");

    public FRA_ICO_001_DATA save(FRA_ICO_001_DATA fra_ico_001_data) {
        return fra_ico_001_data_repository.save(fra_ico_001_data);
    }

    public List<FRA_ICO_001_DATA> findAll() {
        return fra_ico_001_data_repository.findAll();
    }

//    public List<FRA_ICO_001_DATA> findAllBy(FRA_EAT_001 fra_eat_001) {
//        return fra_ico_001_data_repository.findByFra_eat_001(fra_eat_001);
//    }

    public List<FRA_ICO_001_DATA> findRandPreguntas(Long id){
        return fra_ico_001_data_repository.buscarTodosPorEnsayo(id);
    }

    public FRA_ICO_001_DATA findById(Long id) {
        return fra_ico_001_data_repository.findByIdFRAICODATA(id);
    }

    public void delete(Long id) {
        fra_ico_001_data_repository.deleteById(id);
    }

    public long contar() {
        return fra_ico_001_data_repository.count();
    }
}
