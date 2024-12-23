package com.building_mannager_system.repository.system_manager;

import com.building_mannager_system.entity.property_manager.Systems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemsRepository extends JpaRepository<Systems,Long> {

}
