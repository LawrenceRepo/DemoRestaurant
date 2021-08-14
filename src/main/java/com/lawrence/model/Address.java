package com.lawrence.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable  //declare that a class will be embedded by other entities without the need of seperate table
public class Address {
    private String street;
    private String city;
    private String state;
    private String pincode;

}
