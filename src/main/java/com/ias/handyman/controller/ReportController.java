package com.ias.handyman.controller;

import com.ias.handyman.entities.ReportEntity;
import com.ias.handyman.services.ReportServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ReportController {
    private final ReportServiceInterface report;

    @Autowired
    public ReportController(ReportServiceInterface report) {
        this.report = report;
    }

    @PostMapping(value = "/addReport")
    public ResponseEntity<ReportEntity> addReport(@RequestBody ReportEntity reportEntity){
        ReportEntity reporte = report.addReport(reportEntity);
        return new ResponseEntity<>(reporte, HttpStatus.CREATED);
    }

    @GetMapping(value = "/listReport")
    public ResponseEntity<Iterable<ReportEntity>> showReports(){
        try {
            return new ResponseEntity<>(report.showReports(), HttpStatus.OK);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Sin resultados",emptyResultDataAccessException);
        }
    }

    @GetMapping(value = "/technician/{idTechnician}")
    public ResponseEntity<Iterable<ReportEntity>> getReportByIdTechnician(@PathVariable("idTechnician") String idTechnician){
        Iterable<ReportEntity> reports = report.getReportByIdTechnician(idTechnician);
        List<ReportEntity> reportsEmtpy = new ArrayList<>();
        if (reports.equals(reportsEmtpy)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Sin resultados");
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @GetMapping("/technician/{idTechnician}/week/{week}")
    public ResponseEntity<Iterable<ReportEntity>> getReportByIdTechnicianAndWeek(
            @PathVariable("idTechnician") String idTechnician,
            @PathVariable("week") int week
    ){
        Iterable<ReportEntity> reports = report.getReportByIdTechnicianAndWeek(idTechnician, week);
        List<ReportEntity> reportsEmtpy = new ArrayList<>();
        if (reports.equals(reportsEmtpy)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Sin resultados");
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

}
