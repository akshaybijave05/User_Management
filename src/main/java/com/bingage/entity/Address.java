package com.bingage.entity;


import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@NoArgsConstructor
public class Address {

    private String address;

    private String city;

    private String zip;

    private String country;

}