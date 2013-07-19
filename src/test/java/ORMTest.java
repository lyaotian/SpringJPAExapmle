import com.lyaotian.example.pojo.Email;
import com.lyaotian.example.pojo.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: Yaotian Leung
 * Date: 7/19/13
 * Time: 10:55 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration("classpath:spring-context.xml")
public class ORMTest {
    @Autowired
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    @Before
    public void setup(){
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @After
    public void teardown(){
        entityManager.getTransaction().commit();
    }

    @Test
    public void testAddPerson(){
        entityManager.persist(new Person("Yaotian2", "Leung", false, new Date()));
    }

    @Test
    public void testAddEmail(){
        Email email1 = new Email("lyaotian@live.com");
        Email email2 = new Email("lyaotian@gmail.com");
        entityManager.persist(email1);
        entityManager.persist(email2);
    }

    @Test
    public void testAssociate(){
        long email1Id = 1l;
        long email2Id = 2l;
        Email email1 = entityManager.find(Email.class, new Long(email1Id));
        Email email2 = entityManager.find(Email.class, new Long(email2Id));
        assertTrue(email1.getId() == email1Id);
        assertTrue(email2.getId() == email2Id);

//        long personId = 1l;
//        Person person1 = entityManager.find(Person.class, new Long(personId));
//        assertTrue(person1.getId() == personId);
//
//        email1.getPersons().add(person1);
//        email2.getPersons().add(person1);
//        person1.getEmails().add(email1);
//        person1.getEmails().add(email2);

        long personId = 2l;
        Person person2 = entityManager.find(Person.class, new Long(personId));
        person2.getEmails().add(email1);
        person2.getEmails().add(email2);
        entityManager.flush();
    }

    @Test
    public void testFind(){
        long personId = 2l;
        Person person = entityManager.find(Person.class, new Long(personId));
        Set<Email> emails = person.getEmails();
        assertTrue(emails.size() == 2);

        long email1Id = 1l;
        long email2Id = 2l;
        Email email1 = entityManager.find(Email.class, new Long(email1Id));
        Email email2 = entityManager.find(Email.class, new Long(email2Id));
        assertTrue(email1.getPersons().contains(person));
        assertTrue(email2.getPersons().contains(person));
    }
}
