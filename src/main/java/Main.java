import models.Car;
import models.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

//Створити клас Car з полями:
//id
//model,
//Type (ENUM)
//power,
//price,
//year.

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry serviceRegistry =   new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metadata =  new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Car.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Car car1 = new Car(
                "BMW M5",
                Type.SEDAN,
                600,
                85000,
                2023
        );
        Car car2 = new Car(
                "Audi RS7",
                Type.SEDAN,
                630,
                115000,
                2023
        );

        Car car3 = new Car(
                "Porsche Cayenne",
                Type.SUV,
                500,
                98000,
                2022
        );

        session.save(car1);
        session.save(car2);
        session.save(car3);

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();




    }
}