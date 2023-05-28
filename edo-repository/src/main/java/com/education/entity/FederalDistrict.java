package com.education.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "federal_district")
public class FederalDistrict extends BaseEntity {

    /**
     * Связь федерального округа и региона
     */
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Region> region;
    /**
     * Название федерального округа
     */
    @Column(name = "federal_district_name")
    private String federalDistrictName;

    /**
     * Сайт федерального округа
     */
    @Column(name = "website")
    private String website;

}
