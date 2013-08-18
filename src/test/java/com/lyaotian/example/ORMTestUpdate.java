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
import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * User: Yaotian Leung
 * Date: 7/19/13
 * Time: 10:55 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration("classpath:spring-context.xml")
public class ORMTestUpdate {
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
    public void associate(){
        List<Role> roleList = roleRepository.findAll();
        List<Privilege> privilegeList = privilegeRepository.findAll();

        assertFalse(roleList.isEmpty());
        assertFalse(privilegeList.isEmpty());

        Role targetRole = roleList.get(0);
        if(targetRole.getPrivileges().isEmpty()){
            targetRole.getPrivileges().addAll(privilegeList);
            for(Privilege p : privilegeList){
                p.getRoles().add(targetRole);
            }
        }
    }

    @Test
    public void cascadeAdd(){
        List<Email> emailList = emailRepository.findAll();
        assertTrue(emailList.size() > 0);

        Person person = new Person();
        person.setFirstName("Yaotian");
        person.setLastName("Leung");
        person.getEmails().addAll(emailList);

        personRepository.save(person);
    }

    @Test
    public void cascadeRemove(){
        long pId = 9L;
        long eId = 5L;
        Person person = personRepository.findOne(pId);
        Email email = emailRepository.findOne(eId);
        assertTrue(person.getId() == pId);
        assertTrue(email.getId() == eId);
        person.getEmails().remove(email);
    }

    @Test
    public void checkResult(){
        List<Role> roleList = roleRepository.findAll();
        assertFalse(roleList.isEmpty());
        Role role = roleList.get(0);
        assertTrue(role.getPrivileges().isEmpty());
    }
}
