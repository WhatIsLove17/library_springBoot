package org.whatIsLove.dao;


import org.springframework.stereotype.Component;


@Component
public class PersonDAO {

    /*private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1(){
        Session session = entityManager.unwrap(Session.class);

        Set<Person> people = new HashSet<>(
                session.createQuery("SELECT p FROM Person p LEFT JOIN FETCH p.personBooks").getResultList());

        for(Person person : people)
            System.out.println(person.getName() + ": " + person.getPersonBooks());
    }*/
}
