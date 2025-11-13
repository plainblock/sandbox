package com.github.plainblock.multi.db.table.iso;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "country", schema = "iso")
public class CountryTable {

    @Id
    @Column(name = "two_letter")
    private String twoLetter;

    @Column(name = "country_id")
    private int countryId;

    @Column(name = "name")
    private String name;

}
