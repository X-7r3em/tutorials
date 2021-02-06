package com.example.springdata.lazyeager.manytomany;

import com.example.springdata.AbstractUnitTest;
import com.example.springdata.repositories.lazyeager.manytomany.OwnerLLRepository;
import com.example.springdata.repositories.lazyeager.manytomany.SlaveLLRepository;
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

    /**
     * First the owner is fetched. Then in the 2nd request his slaves are fetched lazily.
     */
    @Test
    @Transactional
    public void whenReadFromOwnerRepository_givenLazyOwnerFetchAndLazySlaveFetch_shouldExecuteTwoSqlRequests() {
        printMessage("Owner Call");
        OwnerLL owner = ownerRepository.findById(1L).get();
        printMessage("Slave Call");
        Set<SlaveLL> slaves = owner.getSlaves();
        assertEquals(3, slaves.size());
        printMessage("End of Calls");
    }

    /**
     * The first request fetches the slaves. The 2nd fetches lazily the owners of the slave.
     */
    @Test
    @Transactional
    public void whenReadFromSlaveRepository_givenLazyOwnerFetchAndLazySlaveFetch_shouldExecuteTwoSqlRequests() {
        printMessage("Slave Call");
        SlaveLL slave = slaveRepository.findById(1L).get();
        printMessage("Owner Call");
        Set<OwnerLL> owners = slave.getOwners();
        assertEquals(1, owners.size());
        printMessage("End of Calls");
    }

}
