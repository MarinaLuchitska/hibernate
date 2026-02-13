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
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns =  @JoinColumn(name = "course_id")
    )
    private List<Course> courseList;

    public Student( String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age, List<Course> courseList) {

        this.name = name;
        this.age = age;
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", courseList=" + courseList +
                '}';
    }
}
