package models;

//Створіть клас Employee з полями: id, name, salary, department.

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
@ToString
@Table(name = "employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int salary;
    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;
    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

}
