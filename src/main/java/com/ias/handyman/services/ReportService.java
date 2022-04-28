package com.ias.handyman.services;

import com.ias.handyman.entities.ReportEntity;
import com.ias.handyman.entities.ServiceEntity;
import com.ias.handyman.model.HoursReport;
import com.ias.handyman.repositories.ReportEntityRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

@Service
public class ReportService implements ReportServiceInterface{

    private final ReportEntityRepository data;
    HoursReport hoursReport;

    @Autowired
    public ReportService(ReportEntityRepository data) {
        this.data = data;
    }


    @Override
    public ReportEntity addReport(ReportEntity report) {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_WEEK_DATE;
        report.setWeek(this.getNumberWeek(report.getStartDate()));
        report.setHours(this.getHourWorked(report.getStartDate(), report.getFinishDate()));
        report.setDayOfWeek(this.getDayOfWeek(report.getStartDate()));
        return data.save(report);

    }

    @Override
    public List<ReportEntity> showReports() {
        return (List<ReportEntity>) data.findAll();
    }

    @Override
    public List<ReportEntity> getReportByIdTechnician(String idTechnician) {
        return data.findByIdTechnician(idTechnician);
    }

    @Override
    public List<ReportEntity> getReportByIdTechnicianAndWeek(String idTechnician, int week) {
        return data.findByIdTechnicianAndWeek(idTechnician, week);
    }


    public Integer getNumberWeek(@NotNull LocalDateTime date){
        int week = date.get(WeekFields.of(Locale.getDefault()).weekOfYear());
        return week;
    }

    public Integer getHourWorked(LocalDateTime start,LocalDateTime end){
        Duration duration = Duration.between(start, end);
        int hours = (int) duration.toMinutes();

        return hours;
    }

    public Integer getDayOfWeek(LocalDateTime start){
        int dayOfWeekValue;
        DayOfWeek dayOfWeek = DayOfWeek.from(start);
        dayOfWeekValue = dayOfWeek.getValue();
        return dayOfWeekValue;
    }

}
