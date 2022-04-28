package com.ias.handyman.repositories;

import com.ias.handyman.entities.ServiceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceEntityRepository extends CrudRepository<ServiceEntity, String> {

    public ServiceEntity findByIdTechnician(@Param("idTechnician") String idTechnician);
}
