package org.medium.examples.memcached.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
public class Person {

    @Id
    private String id;
    private String name;
    private int age;

    @Transient
    private double salary;

    public Person() {

    }
}
