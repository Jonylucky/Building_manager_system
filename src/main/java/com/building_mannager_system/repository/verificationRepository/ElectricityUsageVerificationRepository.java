package com.building_mannager_system.repository.verificationRepository;

import com.building_mannager_system.entity.verification.ElectricityUsageVerification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectricityUsageVerificationRepository extends JpaRepository<ElectricityUsageVerification, Integer> {


    List<ElectricityUsageVerification> findByStatus(String status);


    //Truy vấn với phân trang
    Page<ElectricityUsageVerification> findByMeterId(int meterId, Pageable pageable);
}
