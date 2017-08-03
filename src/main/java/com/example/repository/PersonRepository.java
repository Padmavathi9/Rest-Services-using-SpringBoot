package com.example.repository;


import com.example.model.Person;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
@Repository
public interface PersonRepository extends JpaRepository<Person, BigInteger>{

}
