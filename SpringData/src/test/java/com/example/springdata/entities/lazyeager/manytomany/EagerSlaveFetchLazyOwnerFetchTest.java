package com.example.springdata.entities.lazyeager.manytomany;

import com.example.springdata.AbstractUnitTest;
import com.example.springdata.repositories.lazyeager.manytomany.OwnerELRepository;
import com.example.springdata.repositories.lazyeager.manytomany.SlaveELRepository;
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
     *
     * The owner fetches the slaves in one eager request.
     */
    @Test
    public void whenReadFromOwnerRepository_givenLazyOwnerFetchAndEagerSlaveFetch_shouldExecuteOneSqlRequest() {
        printMessage("Owner Call");
        OwnerEL owner = ownerRepository.findById(1L).get();
        printMessage("Slave Call");
        Set<SlaveEL> slaves = owner.getSlaves();
        assertEquals(2, slaves.size());
        printMessage("End of Calls");
    }

    /**
     * The slaves are fetched in the 1st request. Then the owners are loaded lazily in the 2nd. In the
     * 3rd the slaves of the owner are fetched eagerly.
     */
    @Test
    @Transactional
    public void whenReadFromSlaveRepository_givenLazyOwnerFetchAndEagerSlaveFetch_shouldExecuteThreeSqlRequests() {
        printMessage("Slave Call");
        SlaveEL slave = slaveRepository.findById(1L).get();
        printMessage("Owner Call");
        Set<OwnerEL> owners = slave.getOwners();
        assertEquals(1, owners.size());
        printMessage("End of Calls");
    }
}
