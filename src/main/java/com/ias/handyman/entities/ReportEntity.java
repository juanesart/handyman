package com.ias.handyman.entities;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reporte")
public class ReportEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "week")
    private int week;
    @Column(name = "id_technician")
    private String idTechnician;
    @Column(name = "startDate")
    private LocalDateTime startDate;
    @Column(name = "finishDate")
    private LocalDateTime finishDate;
    @Column(name = "hours")
    private int hours;
    @Column(name = "dayOfWeek")
    private int dayOfWeek;


    public ReportEntity(int id, int week, String idTechnician, LocalDateTime startDate, LocalDateTime finishDate, int hours, int dayOfWeek) {
    }

    public ReportEntity(int week, String idTechnician, LocalDateTime startDate, LocalDateTime finishDate, int hours, int dayOfWeek) {
        this.week = week;
        this.idTechnician = idTechnician;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.hours = hours;
        this.dayOfWeek = dayOfWeek;
    }

    public ReportEntity() {

    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getIdTechnician() {
        return idTechnician;
    }

    public void setIdTechnician(String idTechnician) {
        this.idTechnician = idTechnician;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }
}
