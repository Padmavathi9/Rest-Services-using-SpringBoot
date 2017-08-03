package com.example.service;

import com.example.model.Person;
import com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class PersonServiceBean implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Override
    public Collection<Person> findAll(){
        Collection<Person> p = personRepository.findAll();
        return p;
    }

    @Override
    public Person findOne(BigInteger id){
        Person p= personRepository.findOne(id);
        return p;
    }

    @Override
    public Person create(Person person){
        if(person.getId()!= null){
            //Cannot create Greeting with specified ID value
            return  null;
        }
        Person savePerson=personRepository.save(person);
        return savePerson;
    }

    @Override
    public Person update(Person person){
        Person personPersisted = findOne(person.getId());
        if(personPersisted == null){
            //cannot update person that hasn't been persisted
            return null;
        }
        Person updatePerson = personRepository.save(person);
        return updatePerson;
    }

    @Override
    public void delete(BigInteger id){
        personRepository.delete(id);

    }

}
