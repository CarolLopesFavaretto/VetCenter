import com.vet.VetCenter.Config.PostgreSQLContainerTest;
import com.vet.VetCenter.application.ports.in.AnimalRepository;
import com.vet.VetCenter.domain.entity.Animal;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class AnimalRepositoryTest extends PostgreSQLContainerTest {


    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private AnimalRepository repository;


    @Test
    public void runInsert() {
        Animal animal = new Animal();
        animal.setName("Marie");
        animal.setAge(11);
        animal.setType("felino");
        animal.setRace("Tomba Lata");

        int status = repository.save(animal);
        Assert.assertNotEquals(0, status);


    }
}
