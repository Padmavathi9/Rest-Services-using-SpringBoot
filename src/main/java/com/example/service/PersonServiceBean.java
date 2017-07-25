package com.example.service;

import com.example.model.Person;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class PersonServiceBean implements PersonService {

    private static BigInteger nextId;
    private static Map<BigInteger, Person> persons;

    private static Person save(Person person){
        if(persons==null)
        {
            persons= new HashMap<BigInteger, Person>();
            nextId=BigInteger.ONE;
        }
        //if there..
        if(person.getId()!= null){
            Person oldPerson=persons.get(person.getId());
            if(oldPerson == null){
                return  null;
            }
            persons.remove(person.getId());
            persons.put(person.getId(), person);
            return person;
        }

        //If Create
        person.setId(nextId);
        nextId=nextId.add(BigInteger.ONE);
        persons.put(person.getId(),person);
        return person;

    }
    private static boolean remove(BigInteger id){
        Person deletePerson=persons.remove(id);
        if(deletePerson == null){
            return false;
        }
        return true;
    }

    static {
        Person p1 = new Person();
        p1.setName("padmavathi");
        save(p1);

        Person p2 = new Person();
        p2.setName("Bindu");
        save(p2);
    }

    @Override
    public Collection<Person> findAll(){
        Collection<Person> p = persons.values();
        return p;
    }

    @Override
    public Person findOne(BigInteger id){
        Person p= persons.get(id);
        return p;
    }

    @Override
    public Person create(Person person){
        Person savePerson=save(person);
        return savePerson;
    }

    @Override
    public Person update(Person person){
        Person updatePerson = save(person);
        return updatePerson;
    }

    @Override
    public void delete(BigInteger id){
        remove(id);
    }

}
