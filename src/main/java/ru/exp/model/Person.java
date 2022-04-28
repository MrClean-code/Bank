package ru.exp.model;

import javax.validation.constraints.*;

public class Person {
    private int id;

    @Size(max = 50, message = "name no valid size")
    @NotBlank(message = "name need valid")
    private String name;

    @Min(0)
    private long number;

    @Email(message = "email need right")
    @Size(max = 50, message = "email no valid size")
    @NotBlank(message = "email need valid")
    private String mail;

    @Size(max = 50, message = "passport no valid size")
    @NotBlank(message = "passport need valid")
    private String passport;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}
