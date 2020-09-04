package com.example.springdata.lazyeager.manytomany;

import com.example.springdata.AbstractUnitTest;
import com.example.springdata.db.entities.lazyeager.manytomany.OwnerEE;
import com.example.springdata.db.entities.lazyeager.manytomany.SlaveEE;
import com.example.springdata.db.entities.lazyeager.manytoone.ParentEE;
import com.example.springdata.db.repositories.lazyeager.manytomany.OwnerEERepository;
import com.example.springdata.db.repositories.lazyeager.manytomany.SlaveEERepository;
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
        OwnerEE parent = ownerRepository.findById(1L).get();
        printMessage("Slave Call");
        Set<SlaveEE> slaves = parent.getSlaves();
        assertEquals(2, slaves.size());
        printMessage("End of Calls");
    }

    @Test
    public void whenReadFromSlaveRepository_givenEagerOwnerFetchAndEagerSlaveFetch_shouldExecuteTwoSqlRequests() {
        printMessage("Slave Call");
        SlaveEE child = slaveRepository.findById(1L).get();
        printMessage("Owner Call");
        Set<OwnerEE> owners = child.getOwners();
        assertEquals(1, owners.size());
        printMessage("End of Calls");
    }
}
