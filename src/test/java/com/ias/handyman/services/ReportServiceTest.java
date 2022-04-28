package com.ias.handyman.services;

import com.ias.handyman.entities.ReportEntity;
import com.ias.handyman.repositories.ReportEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportServiceTest {

    ReportEntityRepository mockRepository;
    ReportService service;

    ReportEntity report;

    @BeforeEach
    void init(){
         int id = 25;
         int week = 18;
         String idTechnician = "212";
         LocalDateTime startDate = LocalDateTime.of(2022, Month.APRIL, 25, 12, 0);;
         LocalDateTime finishDate = LocalDateTime.of(2022, Month.APRIL, 25, 15, 0);;
         int hours = 3;
         int dayOfWeek = 1;

         report = new ReportEntity(
                 id,
                 week,
                 idTechnician,
                 startDate,
                 finishDate,
                 hours,
                 dayOfWeek
         );

         mockRepository = mock(ReportEntityRepository.class);
         service = new ReportService(mockRepository);
    }

    @Test
    void showReports() {
        List<ReportEntity> reports = new ArrayList<>();
        reports.add(report);
        when(mockRepository.findAll()).thenReturn(reports);
        assertEquals(reports, service.showReports());
    }
}