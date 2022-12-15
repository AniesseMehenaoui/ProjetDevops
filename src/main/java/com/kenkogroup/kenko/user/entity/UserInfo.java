package com.kenkogroup.kenko.user.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "userInfo")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    @Size(min = 2, max = 25, message = "Firstname Entre 2 et 25 caracteres SVP")
    private String lastName;

    @Size(min = 2, max = 25, message = "Lastname Entre 2 et 25 caracteres SVP")
    private String firstName;

    private String password;

    private String sexe;

    private float age;
    private float weight;
    private float size;

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public UserInfo() {
    }

    public UserInfo(String firstName, String lastName,String email, int age, String sexe, float weight, float size) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.sexe = sexe;
        this.weight = weight;
        this.size = size;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}