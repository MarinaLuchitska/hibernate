import models.Course;
import models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import java.util.Arrays;


//
//Створіть клас Student з полями: id, name, age, course.
//Налаштуйте Hibernate для збереження цього класу в базу даних (використовуючи анотації).
//Напишіть програму для збереження кількох студентів
//у базу даних та отримання їх за допомогою Session.get() по ID.

//Розширте попереднє завдання, додаючи до моделі клас Course,
//який містить поля: id, name, description. Створіть
//зв’язок між студентами і курсами через відповідні колекції
//(один до багатьох або багато до багатьох).
//Напишіть програму, яка зберігає дані студентів і курсів,
//а також отримує список студентів, які записані на певний курс,
// використовуючи Session.createQuery() для фільтрації.
public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .getMetadataBuilder()
                .build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Course qa = new Course("QA","Testing");
        Student s1 = new Student("Nina",18, Arrays.asList(qa));
        Student s2 = new Student("Dima",50, Arrays.asList(qa));
        session.save(s1);
        session.save(s2);
        session.getTransaction().commit();
        session.close();

        Session session2 = sessionFactory.openSession();

        String courseName = "QA";

        session2.createQuery(
                        "select distinct s from Student s join s.courseList c where c.name = :name",
                        Student.class
                ).setParameter("name", courseName)
                .getResultList()
                .forEach(System.out::println);

        session2.close();
        sessionFactory.close();
    }
}