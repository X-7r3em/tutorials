package com.example.springdata.lazyeager.manytomany;

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
     *
     * First we fetch the owner and his slaves eagerly. Then for the 3 slaves, we need to check their
     * owners in 3 requests. Then as one of the slaves has multiple owners, we need to check the slaves
     * of that owner as well with another query.
     */
    @Test
    public void whenReadFromOwnerRepository_givenEagerOwnerFetchAndEagerSlaveFetch_shouldExecuteFiveSqlRequest() {
        printMessage("Owner Call");
        OwnerEE owner = ownerRepository.findById(1L).get();
        printMessage("Slave Call");
        Set<SlaveEE> slaves = owner.getSlaves();
        assertEquals(2, slaves.size());
        printMessage("End of Calls");
    }

    /**
     * First we take the slave with all of its owners in one request. However, the owner has 2 slaves and
     * they are fetched with 2nd query. The other slave also has 2 owners, so his other owner is fetched
     * with 3rd query. The new owner has 2 slaves, so the other slave is fetched with the 4th query.
     * The last slave is checked for multiple owners with the 5th query.
     */
    @Test
    public void whenReadFromSlaveRepository_givenEagerOwnerFetchAndEagerSlaveFetch_shouldExecuteFiveSqlRequests() {
        printMessage("Slave Call");
        SlaveEE slave = slaveRepository.findById(1L).get();
        printMessage("Owner Call");
        Set<OwnerEE> owners = slave.getOwners();
        assertEquals(1, owners.size());
        printMessage("End of Calls");
    }
}
