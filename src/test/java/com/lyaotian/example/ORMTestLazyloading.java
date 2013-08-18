package com.lyaotian.example;

import com.lyaotian.example.pojo.Person;
import com.lyaotian.example.repository.EmailRepository;
import com.lyaotian.example.repository.PersonRepository;
import org.hibernate.collection.internal.PersistentSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * User: Yaotian Leung
 * Date: 7/19/13
 * Time: 10:55 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-context.xml")
public class ORMTestLazyloading {
    Random rdm = new Random();
    int code = rdm.nextInt(100);

    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Autowired
    EmailRepository emailRepository;
    @Autowired
    PersonRepository personRepository;

    @Test
    public void testAddPerson(){
        personRepository.save(new Person("Yaotian", "Leung", false, new Date()));
    }

    @Test
    public void test1Find(){
        long personId = 9L;
        Person person = personRepository.findOne(personId);
        assertTrue(person.getEmails() instanceof PersistentSet);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        person = entityManager.merge(person);
        person.getEmails().size();
        entityManager.close();
        assertFalse(person.getEmails() instanceof PersistentSet);
    }
}
