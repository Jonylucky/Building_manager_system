package com.building_mannager_system.repository.Contract;

import com.building_mannager_system.entity.customer_service.customer_manager.Customertype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomertypeRepository extends JpaRepository<Customertype, Integer> {
}
