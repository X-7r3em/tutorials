package com.example.springdata.lazyeager.manytomany;

import com.example.springdata.AbstractUnitTest;
import com.example.springdata.db.entities.lazyeager.manytomany.OwnerEL;
import com.example.springdata.db.entities.lazyeager.manytomany.SlaveEL;
import com.example.springdata.db.repositories.lazyeager.manytomany.OwnerELRepository;
import com.example.springdata.db.repositories.lazyeager.manytomany.SlaveELRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EagerSlaveFetchLazyOwnerFetchTest extends AbstractUnitTest {

    // Owning Entity
    @Autowired
    private OwnerELRepository ownerRepository;

    // Slave Entity
    @Autowired
    private SlaveELRepository slaveRepository;

    /**
     * {@link Transactional} is not needed as all the entities are eagerly loaded in the same session.
     */
    @Test
    public void whenReadFromOwnerRepository_givenLazyOwnerFetchAndEagerSlaveFetch_shouldExecuteThreeSqlRequest() {
        printMessage("Owner Call");
        OwnerEL parent = ownerRepository.findById(1L).get();
        printMessage("Slave Call");
        Set<SlaveEL> slaves = parent.getSlaves();
        assertEquals(2, slaves.size());
        printMessage("End of Calls");
    }

    @Test
    @Transactional
    public void whenReadFromSlaveRepository_givenLazyOwnerFetchAndEagerSlaveFetch_shouldExecuteThreeSqlRequests() {
        printMessage("Slave Call");
        SlaveEL child = slaveRepository.findById(1L).get();
        printMessage("Owner Call");
        Set<OwnerEL> owners = child.getOwners();
        assertEquals(1, owners.size());
        printMessage("End of Calls");
    }
}
