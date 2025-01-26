package com.building_mannager_system.repository.system_manager;

import com.building_mannager_system.entity.property_manager.ItemCheckResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckResultRepository extends JpaRepository<ItemCheckResult, Long> {

    Page<ItemCheckResult> findByItemCheckId(Long checkItemId, Pageable pageable);

    void deleteByItemCheckId(Long checkItemId);
    boolean existsByItemCheckId(Long checkItemId);
}
