import models.Department;
import models.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

//Створіть клас Employee з полями: id, name, salary, department.
//Використовуйте анотації для створення відповідних таблиць у базі даних і зв’язку
//з іншими класами (наприклад, Department). Напишіть програму для:
//Збереження даних про співробітників і департаменти в базу даних.
//Отримання списку співробітників з певного департаменту.
//Фільтрації співробітників за зарплатою та сортування їх за іменем
//в порядку спадання за допомогою JPQL або Criteria API.


public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        //JPQL
        Department it = new Department("IT");
        it.addEmployee(new Employee("Ivan", 5000));
        it.addEmployee(new Employee("Maria", 7000));
        it.addEmployee(new Employee("Petro", 6000));

        Department hr = new Department("HR");
        hr.addEmployee(new Employee("Olena", 4500));
        hr.addEmployee(new Employee("Andrii", 5500));

        session.save(it);
        session.save(hr);

        session.getTransaction().commit();
        String depName = "IT";
        session.beginTransaction();
        List<Employee> fromDep = session.createQuery(
                "select e from Employee e where e.department.name = :depName",
                Employee.class
                ).setParameter("depName",depName)
                 .getResultList();
        session.getTransaction().commit();

        System.out.println("Empioyee in " + depName + ":");
        fromDep.forEach(System.out::println);
        //Фільтр
         int minSalary = 5000;
         session.beginTransaction();
         List<Employee> filtered = session.createQuery(
               "select e from Employee e where e.salary >= :minSalary order by e.name desc",
                 Employee.class).setParameter("minSalary",minSalary).getResultList();

        session.getTransaction().commit();
        System.out.println("Employee with salary >= " + minSalary + " sorted by name:");
        filtered.forEach(System.out::println);
        session.close();
        sessionFactory.close();


    }
}