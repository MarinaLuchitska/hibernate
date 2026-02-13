package models;

//додаючи до моделі клас Course,
//який містить поля: id, name, description. Створіть
//зв’язок між студентами і курсами через відповідні колекції
//(один до багатьох або багато до багатьох).

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
 @NoArgsConstructor
 @Getter
 @Setter
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "course_id" ),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> studentList;

    public Course(String name, String description) {
         this.name = name;
         this.description = description;
     }



}
