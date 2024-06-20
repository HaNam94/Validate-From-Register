package org.example.validateformregister.model;

import org.hibernate.validator.constraints.Range;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class User implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private int age;
    private String phoneNumber;
    private String email;

    public User() {
    }

    public User(String name, int age, String phoneNumber, String email) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(user.getName().isEmpty()){
            errors.rejectValue("name","name.notEmpty");

        }
        if(user.getAge() < 18){
            errors.rejectValue("age","age.notQualified");
        }

        if(!user.getPhoneNumber().matches("^[0][\\d]{9,10}$")){
            errors.rejectValue("phoneNumber", "phoneNumber.notValid");
        }

        if(!user.getEmail().matches("^([a-z]+[a-zA-Z0-9_.]*@[a-z]{2,}[\\.][\\w]{2,})$")){
            errors.rejectValue("email", "email.notValid");
        }
    }
}
