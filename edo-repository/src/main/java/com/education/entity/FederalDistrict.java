package com.education.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
