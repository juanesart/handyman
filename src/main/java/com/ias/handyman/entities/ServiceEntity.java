package com.ias.handyman.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="servicio")
public class ServiceEntity {
    @Id
    @Column(name = "id_service")
    private Long idService;
    @Column(name = "id_technician")
    private String idTechnician;
    @Column(name = "startDate")
    private LocalDateTime startDate;
    @Column(name = "finishDate")
    private LocalDateTime finishDate;

    public ServiceEntity() {
    }

    public ServiceEntity(Long idService, String idTechnician, LocalDateTime startDate, LocalDateTime finishDate) {
        this.idService = idService;
        this.idTechnician = idTechnician;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public Long getIdService() {
        return idService;
    }

    public void setIdService(Long idService) {
        this.idService = idService;
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
