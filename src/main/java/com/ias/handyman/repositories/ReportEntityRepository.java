package com.ias.handyman.repositories;

import com.ias.handyman.entities.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReportEntityRepository extends JpaRepository<ReportEntity, Long> {

    List<ReportEntity> findByIdTechnician(String idTechnician);
    List<ReportEntity> findByIdTechnicianAndWeek(String idTechnician, int week);
}
