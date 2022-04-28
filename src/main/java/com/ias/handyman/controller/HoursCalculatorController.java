package com.ias.handyman.controller;

import com.ias.handyman.model.HoursReport;
import com.ias.handyman.services.HoursCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HoursCalculatorController {

    private final HoursCalculatorService service;

    @Autowired
    public HoursCalculatorController(HoursCalculatorService service) {
        this.service = service;
    }

    @GetMapping("/hours/technician/{idTechnician}/week/{week}")
    public ResponseEntity<HoursReport> getHoursWorked(
            @PathVariable("idTechnician") String idTechnician,
            @PathVariable("week") int week
    ) {
        HoursReport hoursReport = service.getHoursWorked(idTechnician, week);
        if (hoursReport.getIdTechnician() == ""){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No existe el reporte");
        }
        return new ResponseEntity<>(hoursReport, HttpStatus.OK);
    }
}
