package com.building_mannager_system.repository.Contract;


import com.building_mannager_system.entity.customer_service.contact_manager.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
}

