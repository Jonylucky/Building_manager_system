package com.building_mannager_system.entity.property_manager;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "device_type")
public class DeviceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_type_id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "type_name", nullable = false)
    private String typeName;

}
