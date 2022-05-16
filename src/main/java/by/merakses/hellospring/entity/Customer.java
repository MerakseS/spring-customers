package by.merakses.hellospring.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import by.merakses.hellospring.validation.Adult;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Имя должно быть заполнено.")
    @NotNull(message = "Имя должно быть заполнено.")
    @Length(message = "Длина имени должна быть от 2 до 30 символов.", min = 2, max = 30)
    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @NotBlank(message = "Фамилия должна быть заполнена.")
    @NotNull(message = "Фамилия должна быть заполнена.")
    @Length(message = "Длина фамилии должна быть от 2 до 30 символов.", min = 2, max = 30)
    @Column(name = "surname", nullable = false, length = 30)
    private String surname;

    @NotNull(message = "Дата рождения должна быть заполнена.")
    @Adult(message = "Клиент должен быть совершеннолетним.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Positive(message = "Рост должен быть больше нуля.")
    @Column(name = "height")
    private Integer height;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer weight) {
        this.height = weight;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}