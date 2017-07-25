package com.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;


@Data
public class Person {
    private BigInteger id;
    private String name;
}
