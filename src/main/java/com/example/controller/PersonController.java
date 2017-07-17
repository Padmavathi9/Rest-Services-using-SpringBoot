package com.example.controller;

import com.example.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PersonController {

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

    static {
        Person p1 = new Person();
        p1.setName("padmavathi");
        save(p1);

        Person p2 = new Person();
        p2.setName("Madhu");
        save(p2);
    }

    @RequestMapping(value = "/api/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Collection<Person>> getAll(){
        Collection<Person> p=persons.values();
        return new ResponseEntity<Collection<Person>>(p, HttpStatus.OK);

    }

    @RequestMapping(value = "/api/all/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPerson(@PathVariable("id") BigInteger id){
        Person p= persons.get(id);
        if(p == null)
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        return  new ResponseEntity<Person>(p,HttpStatus.OK);
    }

    @RequestMapping(value = "api/all",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
            Person savePerson=save(person);
            return new ResponseEntity<Person>(savePerson, HttpStatus.CREATED);

    }

    @RequestMapping(value="/api/all/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> updatePerson(@RequestBody Person person){
        Person updatePerson = save(person);
        if(updatePerson == null){
            return new ResponseEntity<Person>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Person>(updatePerson, HttpStatus.OK);
    }

    private static boolean delete(BigInteger id){
        Person deletePerson=persons.remove(id);
        if(deletePerson == null){
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/api/all/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> deletePerson(@PathVariable("id") BigInteger id, @RequestBody Person person){
        boolean deleted = delete(id);
        if(!deleted)
            return new ResponseEntity<Person>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }
}