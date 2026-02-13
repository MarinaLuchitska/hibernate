package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
//Створіть клас Student з полями: id, name, age, course.

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private int course;

    public Student( String name, int age, int course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }
}
