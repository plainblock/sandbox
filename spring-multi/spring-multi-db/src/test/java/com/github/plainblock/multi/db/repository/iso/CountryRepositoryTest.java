package com.github.plainblock.multi.db.repository.iso;

import com.github.plainblock.multi.db.table.iso.CountryTable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CountryRepositoryTest {

    private final CountryRepository repository;

    @Autowired
    CountryRepositoryTest(final CountryRepository repository) {
        this.repository = repository;
    }

    @Test
    void testFindById() {
        String targetId = "JP";
        CountryTable table = repository.findById(targetId).orElse(null);
        Assertions.assertNotNull(table);
        Assertions.assertEquals(targetId, table.getTwoLetter());
    }

}
