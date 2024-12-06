package com.building_mannager_system.entity.customer_service.customer_manager;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customertype")
public class Customertype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerTypeId", nullable = false)
    private Integer id;

    @Column(name = "TypeName")
    private String typeName;

}