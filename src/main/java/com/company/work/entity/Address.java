package com.company.work.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@JmixEntity
@Embeddable
public class Address {
    @Column(name = "COUNTRY", nullable = false, length = 100)
    @NotNull
    private String country;

    @Column(name = "REGION", nullable = false, length = 100)
    @NotNull
    private String region;

    @Column(name = "LOCALITY", nullable = false, length = 100)
    @NotNull
    private String locality;

    @Column(name = "STREET", nullable = false, length = 100)
    @NotNull
    private String street;

    @Column(name = "HOUSE_NUM", nullable = false)
    @NotNull
    private Integer houseNum;

    @Column(name = "BLOCK")
    private Integer block;

    @Column(name = "APARTMENT_NUMBER")
    private String apartmentNumber;

    @Column(name = "POSTAL_CODE", nullable = false, length = 20)
    @NotNull
    private String postalCode;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public Integer getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(Integer houseNum) {
        this.houseNum = houseNum;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}