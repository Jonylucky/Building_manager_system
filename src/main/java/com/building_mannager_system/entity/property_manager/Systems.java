package com.building_mannager_system.entity.property_manager;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter

@Table(name="systems")

public class Systems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Or Integer, depending on your database

    private String systemName;
    private String description;
    private int maintenanceCycle;

    @OneToMany(mappedBy = "system", cascade = CascadeType.ALL)
    private List<Subcontractor> subcontractors;



}
