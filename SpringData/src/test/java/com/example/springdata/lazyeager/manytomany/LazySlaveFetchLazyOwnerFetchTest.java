package com.example.springdata.lazyeager.manytomany;

import com.example.springdata.AbstractUnitTest;
import com.example.springdata.db.entities.lazyeager.manytomany.OwnerLL;
import com.example.springdata.db.entities.lazyeager.manytomany.SlaveLL;
import com.example.springdata.db.repositories.lazyeager.manytomany.OwnerLLRepository;
import com.example.springdata.db.repositories.lazyeager.manytomany.SlaveLLRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LazySlaveFetchLazyOwnerFetchTest extends AbstractUnitTest {

    // Owning Entity
    @Autowired
    private OwnerLLRepository ownerRepository;

    // Slave Entity
    @Autowired
    private SlaveLLRepository slaveRepository;

    /*
     * After the slaves are fetched for the owner, for each slave, there is another request to see his owners.
     * Meaning that we have 3 slaves, and they will need 3 requests to the database.
     */
    @Test
    @Transactional
    public void whenReadFromOwnerRepository_givenLazyOwnerFetchAndLazySlaveFetch_shouldExecuteFiveSqlRequests() {
        printMessage("Owner Call");
        OwnerLL parent = ownerRepository.findById(1L).get();
        printMessage("Slave Call");
        Set<SlaveLL> slaves = parent.getSlaves();
        assertEquals(3, slaves.size());
        printMessage("End of Calls");
    }

    @Test
    public void whenReadFromSlaveRepository_givenLazyOwnerFetchAndLazySlaveFetch_shouldExecuteOneSqlRequest() {
        printMessage("Slave Call");
        SlaveLL child = slaveRepository.findById(1L).get();
        printMessage("Owner Call");
        Set<OwnerLL> owners = child.getOwners();
        assertEquals(1, owners.size());
        printMessage("End of Calls");
    }

}
