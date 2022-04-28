package com.ias.handyman.model;

public class HoursReport {
    private String idTechnician;
    private int total;
    private int diurna;
    private int diurnaExtra;
    private int nocturna;
    private int nocturnaExtra;
    private int dominical;
    private int dominicalExtra;

    public HoursReport() {
    }

    public HoursReport(String idTechnician, int total, int diurna, int diurnaExtra, int nocturna, int nocturnaExtra, int dominical, int dominicalExtra) {
        this.idTechnician = idTechnician;
        this.total = total;
        this.diurna = diurna;
        this.diurnaExtra = diurnaExtra;
        this.nocturna = nocturna;
        this.nocturnaExtra = nocturnaExtra;
        this.dominical = dominical;
        this.dominicalExtra = dominicalExtra;
    }

    public String getIdTechnician() {
        return idTechnician;
    }

    public void setIdTechnician(String idTechnician) {
        this.idTechnician = idTechnician;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDiurna() {
        return diurna;
    }

    public void setDiurna(int diurna) {
        this.diurna = diurna;
    }

    public int getDiurnaExtra() {
        return diurnaExtra;
    }

    public void setDiurnaExtra(int diurnaExtra) {
        this.diurnaExtra = diurnaExtra;
    }

    public int getNocturna() {
        return nocturna;
    }

    public void setNocturna(int nocturna) {
        this.nocturna = nocturna;
    }

    public int getNocturnaExtra() {
        return nocturnaExtra;
    }

    public void setNocturnaExtra(int nocturnaExtra) {
        this.nocturnaExtra = nocturnaExtra;
    }

    public int getDominical() {
        return dominical;
    }

    public void setDominical(int dominical) {
        this.dominical = dominical;
    }

    public int getDominicalExtra() {
        return dominicalExtra;
    }

    public void setDominicalExtra(int dominicalExtra) {
        this.dominicalExtra = dominicalExtra;
    }
}
