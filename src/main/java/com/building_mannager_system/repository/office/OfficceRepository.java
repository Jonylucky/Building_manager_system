package com.building_mannager_system.repository.office;

import com.building_mannager_system.entity.customer_service.contact_manager.Office;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficceRepository extends JpaRepository<Office,Integer> {


}
