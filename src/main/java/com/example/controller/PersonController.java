package com.example.controller;

import com.example.model.Person;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Collection;


@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/search",
            method = RequestMethod.GET,
            produces = { "application/json", "text/json" })
    public ResponseEntity<Collection<Person>> getAll(){
        Collection<Person> p=personService.findAll();
        return new ResponseEntity<Collection<Person>>(p, HttpStatus.OK);

    }

    @RequestMapping(value = "/search/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPerson(@PathVariable("id") BigInteger id){
        Person p= personService.findOne(id);
        if(p == null)
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        return  new ResponseEntity<Person>(p,HttpStatus.OK);
    }

    @RequestMapping(value = "/search",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
            Person savePerson=personService.create(person);
            return new ResponseEntity<Person>(savePerson, HttpStatus.CREATED);

    }

    @RequestMapping(value="/search/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> updatePerson(@RequestBody Person person){
        Person updatePerson = personService.update(person);
        if(updatePerson == null){
            return new ResponseEntity<Person>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Person>(updatePerson, HttpStatus.OK);
    }



    @RequestMapping(value = "/search/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> deletePerson(@PathVariable("id") BigInteger id, @RequestBody Person person){
        personService.delete(id);
        return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
    }
}