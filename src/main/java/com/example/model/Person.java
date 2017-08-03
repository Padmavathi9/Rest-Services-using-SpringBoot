package com.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue
    private BigInteger id;
    @Column(name="`name`")
    private String name;
}
