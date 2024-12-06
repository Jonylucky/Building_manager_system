package com.building_mannager_system.entity.customer_service.officeSpaceAllcation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "locations")
public class Location {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LocationId", nullable = false)
    private Integer id;

    @Column(name = "Floor")
    private String floor;

}