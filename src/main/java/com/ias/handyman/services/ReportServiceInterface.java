package com.ias.handyman.services;

import com.ias.handyman.entities.ReportEntity;

import java.util.List;

public interface ReportServiceInterface {
    ReportEntity addReport(ReportEntity report);
    List<ReportEntity> showReports();
    List<ReportEntity> getReportByIdTechnician(String idTechnician);
    List<ReportEntity> getReportByIdTechnicianAndWeek(String idTechnician, int week);

}
