import models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import java.util.Scanner;

//
//Створіть клас Student з полями: id, name, age, course.
//Налаштуйте Hibernate для збереження цього класу в базу даних (використовуючи анотації).
//Напишіть програму для збереження кількох студентів
//у базу даних та отримання їх за допомогою Session.get() по ID.
public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Student.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(new Student("Nina",36,1));
        session.save(new Student("Dima",19, 3));
        session.save(new Student("Lina",80, 2));
        session.getTransaction().commit();
        session.close();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть іd:  ");
        int id = scanner.nextInt();

        Session session1 = sessionFactory.openSession();
        Student student = session1.get(Student.class, id);
         if (student != null){
             System.out.println("Знайдено:" + student);
         }
         else {
             System.out.println("???");
         }

        session1.close();
        sessionFactory.close();












    }
}