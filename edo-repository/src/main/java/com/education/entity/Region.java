package com.education.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "region")
public class Region extends BaseEntity {

    /**
     * Связь обращения и региона
     */
    @OneToMany(fetch = FetchType.LAZY)
    private List<Appeal> appeal;

    /**
     * Идентификатор региона из внешних систем
     */
    @Column(name = "external_id")
    private String externalId;
    /**
     * Название субъекта РФ
     */
    @Column(name = "region_name")
    private String regionName;
    /**
     * Дата архивации
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;
    /**
     * Количество
     */
    @Column(name = "quantity")
    private String quantity;

    /**
     * Федеральный округ(связанная сущность)
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "federal_district_id")
    private FederalDistrict federalDistrict;

    /**
     * Количество первичных отделений в регионе
     */
    @Column(name = "number_of_primary_branches")
    private String numberOfPrimaryBranches;
    /**
     * Количество местных отделений в регионе
     */
    @Column(name = "number_of_local_branches")
    private String numberOfLocalBranches;

}
