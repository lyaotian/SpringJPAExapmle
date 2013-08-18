package com.lyaotian.example;

import com.lyaotian.example.pojo.Email;
import com.lyaotian.example.pojo.Person;
import com.lyaotian.example.pojo.Privilege;
import com.lyaotian.example.pojo.Role;
import com.lyaotian.example.repository.EmailRepository;
import com.lyaotian.example.repository.PersonRepository;
import com.lyaotian.example.repository.PrivilegeRepository;
import com.lyaotian.example.repository.RoleRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * User: Yaotian Leung
 * Date: 7/19/13
 * Time: 10:55 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration("classpath:spring-context.xml")
public class ORMTestAdd {
    Random rdm = new Random();
    int code = rdm.nextInt(100);

    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Autowired
    EmailRepository emailRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PrivilegeRepository privilegeRepository;
    @Autowired
    RoleRepository roleRepository;

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
        personRepository.save(new Person("Yaotian", "Leung", false, new Date()));
    }

    @Test
    public void testAddEmail(){
        Email email1 = new Email("lyaotian@live.com");
        Email email2 = new Email("lyaotian@gmail.com");
        emailRepository.save(email1);
        emailRepository.save(email2);
    }

    @Test
    public void addRole(){
        Role role = new Role();
        role.setName("role" + code);
        role = roleRepository.save(role);
        assertTrue(role.getId() > 0);
    }

    @Test
    public void addPrivilege(){
        Privilege privilege = new Privilege();
        privilege.setName("privilege" + code);
        privilege = privilegeRepository.save(privilege);
        assertTrue(privilege.getId() > 0);
    }
}
