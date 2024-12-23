package com.building_mannager_system.repository.system_manager;

import com.building_mannager_system.entity.property_manager.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
}
