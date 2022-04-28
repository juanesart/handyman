package com.ias.handyman.services;

import com.ias.handyman.entities.ReportEntity;
import com.ias.handyman.model.HoursReport;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HoursCalculatorService {

    private final ReportService reportService;

    public HoursCalculatorService(ReportService reportService) {
        this.reportService = reportService;
    }

    public HoursReport getHoursWorked(String idTechnician, int week){

        HoursReport hourReport = new HoursReport();

        hourReport.setIdTechnician("");
        hourReport.setTotal(0);
        hourReport.setDiurna(0);
        hourReport.setDiurnaExtra(0);
        hourReport.setNocturna(0);
        hourReport.setNocturnaExtra(0);
        hourReport.setDominical(0);
        hourReport.setDominicalExtra(0);

        List<ReportEntity> reports = reportService.getReportByIdTechnicianAndWeek(idTechnician, week);

        reports.forEach(report -> {
            int timeWorked = report.getHours();
            LocalDateTime startDate = report.getStartDate();
            LocalDateTime finishDate = report.getFinishDate();
            Boolean extra = false;
            int acum = 0;
            int enter = 0;
            int normalHours = 48 * 60;

            while (timeWorked > 0){
                //hourReport.setTotal(timeWorked);

                if (hourReport.getTotal() >= normalHours && extra == false){
                    extra = true;
                }
                if ((hourReport.getTotal() + timeWorked) > normalHours && extra == false){
                    enter = normalHours - hourReport.getTotal();
                    acum = timeWorked - enter;
                    finishDate = finishDate.minusMinutes(acum);
                }
                if (isSunday(startDate)) {
                    if (extra) {
                        hourReport.setDominicalExtra(hourReport.getDominicalExtra() + timeWorked);
                        hourReport.setTotal(hourReport.getTotal() + timeWorked);
                    }else if (enter != 0){
                        hourReport.setDominical(hourReport.getDominical() + enter);
                        hourReport.setTotal(hourReport.getTotal() + enter);
                    }else {
                        hourReport.setDominical(hourReport.getDominical() + timeWorked);
                        hourReport.setTotal(hourReport.getTotal() + timeWorked);
                    }
                    timeWorked -= (int) Duration.between(startDate, finishDate).toMinutes();
                }else if (isSameTime(startDate, finishDate)){
                    if (extra){
                        if (isNormal(startDate)){
                            hourReport.setDiurnaExtra(hourReport.getDiurnaExtra() + timeWorked);
                        }else {
                            hourReport.setNocturnaExtra(hourReport.getNocturnaExtra() + timeWorked);
                        }
                        hourReport.setTotal(hourReport.getTotal() + timeWorked);
                    }else if (enter != 0){
                        if (isNormal(startDate)){
                            hourReport.setDiurna(hourReport.getDiurna() + enter);
                        }else {
                            hourReport.setNocturna(hourReport.getNocturna() + enter);
                        }
                        hourReport.setTotal(hourReport.getTotal() + enter);
                    }else {
                        if (isNormal(startDate)) {
                            hourReport.setDiurna(hourReport.getDiurna() + timeWorked);
                        } else {
                            hourReport.setNocturna(hourReport.getNocturna() + timeWorked);
                        }
                        hourReport.setTotal(hourReport.getTotal() + timeWorked);
                    }
                    timeWorked -= (int) Duration.between(startDate, finishDate).toMinutes();
                }else {
                    if (isNormal(startDate)){
                        int timePart = (int) Duration.between(
                                startDate, startDate.toLocalDate().atTime(20,0)
                        ).toMinutes();
                        timeWorked -= timePart;
                        startDate = startDate.toLocalDate().atTime(20,0).plusNanos(1);
                        if (extra){
                            hourReport.setDiurnaExtra(hourReport.getDiurnaExtra() + timePart);
                            hourReport.setTotal(hourReport.getTotal() + timePart);
                        }else if (enter != 0){
                            enter -= timePart;
                            hourReport.setDiurna(hourReport.getDiurna() + enter);
                            hourReport.setTotal(hourReport.getTotal() + enter);
                        }else {
                            hourReport.setDiurna(hourReport.getDiurna() + timePart);
                            hourReport.setTotal(hourReport.getTotal() + timePart);
                        }
                    }else {
                        if (startDate.isBefore(startDate.toLocalDate().atTime(7,0))){
                            int timePart = (int) Duration.between(
                                    startDate, startDate.toLocalDate().atTime(7,0)
                            ).toMinutes();
                            timeWorked -= timePart;
                            startDate = startDate.toLocalDate().atTime(7,0);
                            if (extra) {
                                hourReport.setNocturnaExtra(hourReport.getNocturnaExtra() + timePart);
                                hourReport.setTotal(hourReport.getTotal() + timePart);
                            }else if (enter != 0){
                                enter -= timePart;
                                hourReport.setNocturna(hourReport.getNocturna() + enter);
                                hourReport.setTotal(hourReport.getTotal() + enter);
                            }else {
                                hourReport.setNocturna(hourReport.getNocturna() + timePart);
                                hourReport.setTotal(hourReport.getTotal() + timePart);
                            }
                        }else if (startDate.isBefore(startDate.toLocalDate().atTime(20,0))){
                            int timePart = (int) Duration.between(
                                    startDate, startDate.toLocalDate().atTime(20,0)
                            ).toMinutes();
                            timeWorked -= timePart;
                            startDate = startDate.toLocalDate().atTime(20,0);
                            if (extra) {
                                hourReport.setNocturnaExtra(hourReport.getNocturnaExtra() + timePart);
                                hourReport.setTotal(hourReport.getTotal() + timePart);
                            }else if (enter != 0){
                                enter -= timePart;
                                hourReport.setNocturna(hourReport.getNocturna() + enter);
                                hourReport.setTotal(hourReport.getTotal() + enter);
                            }else {
                                hourReport.setNocturna(hourReport.getNocturna() + timePart);
                                hourReport.setTotal(hourReport.getTotal() + timePart);
                            }
                        }else {
                            int timePart = (int) Duration.between(
                                    startDate, startDate.toLocalDate().atTime(0,0).plusDays(1)
                            ).toMinutes();
                            timeWorked -= timePart;
                            startDate = startDate.toLocalDate().atTime(0,0).plusDays(1);
                            if (extra) {
                                hourReport.setNocturnaExtra(hourReport.getNocturnaExtra() + timePart);
                                hourReport.setTotal(hourReport.getTotal() + timePart);
                            }else if (enter != 0){
                                enter -= timePart;
                                hourReport.setNocturna(hourReport.getNocturna() + enter);
                                hourReport.setTotal(hourReport.getTotal() + enter);
                            }else {
                                hourReport.setNocturna(hourReport.getNocturna() + timePart);
                                hourReport.setTotal(hourReport.getTotal() + timePart);
                            }
                        }
                    }
               }
                if (finishDate != report.getFinishDate())
                    finishDate = report.getFinishDate();
            }
            hourReport.setIdTechnician(report.getIdTechnician());
        });
        return hourReport;
    }

    public Boolean isSunday(LocalDateTime start){
        int dayOfWeekValue;
        DayOfWeek dayOfWeek = DayOfWeek.from(start);
        dayOfWeekValue = dayOfWeek.getValue();
        return (dayOfWeekValue == 7)?true:false;
    }

    public Boolean isSameTime(LocalDateTime start, LocalDateTime end){

        if (start.plusNanos(1).isAfter((start.toLocalDate().atTime(0,0)))
                && end.plusNanos(1).isAfter(end.toLocalDate().atTime(0,0))
                && start.isBefore(start.toLocalDate().atTime(7,0))
                && end.isBefore(end.toLocalDate().atTime(7,0))){
            return true;
        }

        if (start.isAfter((start.toLocalDate().atTime(7,0)))
        && end.isAfter(end.toLocalDate().atTime(7,0))
        && start.isBefore(start.toLocalDate().atTime(20,0))
        && end.isBefore(end.toLocalDate().atTime(20,0))){
            return true;
        }
        return  (start.isAfter(start.toLocalDate().atTime(20,0))
                && end.isAfter(end.toLocalDate().atTime(20,0))
                && start.isBefore(start.toLocalDate().atTime(0,0).plusDays(1))
                && end.isBefore(end.toLocalDate().atTime(0,0).plusDays(1)));
    }

    public Boolean isNormal(LocalDateTime start){
        if (start.plusNanos(1).isAfter(start.toLocalDate().atTime(7,0))
        && start.minusNanos(1).isBefore(start.toLocalDate().atTime(20,0))){
            return true;
        }
        return false;
    }
}
