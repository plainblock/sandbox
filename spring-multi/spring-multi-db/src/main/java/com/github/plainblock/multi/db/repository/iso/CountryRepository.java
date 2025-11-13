package com.github.plainblock.multi.db.repository.iso;

import com.github.plainblock.multi.db.table.iso.CountryTable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryTable, String> {
    // pass
}
