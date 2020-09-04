package com.example.springdata.entities.lazyeager.manytomany;

import com.example.springdata.AbstractUnitTest;
import com.example.springdata.repositories.lazyeager.manytomany.OwnerEERepository;
import com.example.springdata.repositories.lazyeager.manytomany.SlaveEERepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EagerSlaveFetchEagerOwnerFetchTest extends AbstractUnitTest {

    // Owning Entity
    @Autowired
    private OwnerEERepository ownerRepository;

    // Slave Entity
    @Autowired
    private SlaveEERepository slaveRepository;

    /**
     * {@link Transactional} is not needed as all the entities are eagerly loaded in the same session.
     */
    @Test
    public void whenReadFromOwnerRepository_givenEagerOwnerFetchAndEagerSlaveFetch_shouldExecuteThreeSqlRequest() {
        printMessage("Owner Call");
        OwnerEE owner = ownerRepository.findById(1L).get();
        printMessage("Slave Call");
        Set<SlaveEE> slaves = owner.getSlaves();
        assertEquals(2, slaves.size());
        printMessage("End of Calls");
    }

    @Test
    public void whenReadFromSlaveRepository_givenEagerOwnerFetchAndEagerSlaveFetch_shouldExecuteTwoSqlRequests() {
        printMessage("Slave Call");
        SlaveEE slave = slaveRepository.findById(1L).get();
        printMessage("Owner Call");
        Set<OwnerEE> owners = slave.getOwners();
        assertEquals(1, owners.size());
        printMessage("End of Calls");
    }
}
