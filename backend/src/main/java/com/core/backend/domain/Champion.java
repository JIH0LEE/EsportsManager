package com.core.backend.domain;

import com.core.backend.domain.enums.Feature;
import com.core.backend.domain.enums.Position;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Champion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    private Feature feature;

    private Position position1;

    private Position position2;

    private Position position3;

    private Integer tier;

}
