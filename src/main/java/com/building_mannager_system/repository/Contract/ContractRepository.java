package com.building_mannager_system.repository.Contract;

import com.building_mannager_system.entity.customer_service.contact_manager.Contract;
import com.building_mannager_system.entity.customer_service.customer_manager.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {

    Contract findByOfficeID_Id(Integer officeId);

    Contract findByCustomerID_Id(Integer customerID);

}
