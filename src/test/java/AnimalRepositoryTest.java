import com.vet.VetCenter.Config.PostgreSQLContainerTest;
import com.vet.VetCenter.application.ports.in.AnimalRepository;
import com.vet.VetCenter.domain.entity.Animal;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.junit.jupiter.Testcontainers;


//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest()
@SpringBootConfiguration
@AutoConfigureMockMvc
//@Testcontainers
//@ActiveProfiles("test-containers")
//@RunWith(SpringRunner.class)
public class AnimalRepositoryTest extends PostgreSQLContainerTest {

//    private static final Logger log = LoggerFactory.getLogger(AnimalRepositoryTest.class);

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private AnimalRepository repository;

//    @Test
//    void shouldCreateOnePerson() {
//        final var people = personCreateService.createFamily(
//                List.of("Simon"),
//                "Kirekov"
//        );
//        assertEquals(1, people.size());
//        final var person = people.get(0);
//        assertEquals("Simon", person.getFirstName());
//        assertEquals("Kirekov", person.getLastName());
//        assertTrue(person.getDateCreated().isBefore(ZonedDateTime.now()));
//    }

    @Test
    public void runInsert() {
//        log.info("Start Test");
        Animal animal = new Animal();
        animal.setName("Marie");
        animal.setAge(11);
        animal.setType("felino");
        animal.setRace("Tomba Lata");

        int status = repository.save(animal);
        Assert.assertNotEquals(0, status);


    }
}
