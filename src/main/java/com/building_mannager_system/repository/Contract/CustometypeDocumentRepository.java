package com.building_mannager_system.repository.Contract;

import com.building_mannager_system.entity.customer_service.customer_manager.CustomertypeDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustometypeDocumentRepository extends JpaRepository<CustomertypeDocument, Integer> {

   List<CustomertypeDocument> findByCustomerTypeIdAndStatus(Integer customerTypeId, boolean status);
}
