package com.building_mannager_system.repository.system_manager;

import com.building_mannager_system.entity.customer_service.system_manger.ElectricityRate;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface ElectricityRateRepository  extends JpaRepository<ElectricityRate, Integer> {
}
