package com.example.service;

import com.example.model.Person;
import java.math.BigInteger;
import java.util.Collection;

public interface PersonService{

    Collection<Person> findAll();
    Person findOne(BigInteger id);
    Person create(Person person);
    Person update(Person person);
    void delete(BigInteger id);

}
