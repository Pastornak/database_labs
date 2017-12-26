package services;

import entities.*;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Insert {

    private static Set<String> tableNames = new HashSet<String>(){
        {
            add("available");
            add("gender");
            add("messanger");
            add("person");
        }
    };

    public static void insert(Session session){
        Scanner input = new Scanner(System.in);
        System.out.println("Insert data into table: ");
        String tableName = input.next();
        tableName = tableName.toLowerCase();
        //String tableName = inputWord.toLowerCase();
        if(!tableNames.contains(tableName)){
            String names = "";
            for (String name: tableNames){
                names = names + name + " ";
            }
            System.out.println("No such table. Available tables are: " + names);
        }
        try {
            if  (tableName.equals("available")) {
                System.out.println("Input person id: ");
                int personId = input.nextInt();
                System.out.println("Input messanger id: ");
                int messangerId = input.nextInt();

                session.beginTransaction();
                AvailablePK availablePK = new AvailablePK();
                availablePK.setId_person(personId);
                availablePK.setId_messanger(messangerId);
                Available available = new Available();
                available.setAvailablePK(availablePK);
                session.save(available);
                session.getTransaction().commit();

                System.out.println("Inserted new available.");
                return;
            }
            if  (tableName.equals("gender")){
                System.out.println("Input a new gender name: ");
                String genderName = input.next();

                session.beginTransaction();
                Gender gender = new Gender();
                gender.setGender_name(genderName);
                session.save(gender);
                session.getTransaction().commit();

                System.out.println("Inserted new gender.");
                return;
            }
            if  (tableName.equals("messanger")) {
                System.out.println("Input a new messanger name: ");
                String messangerName = input.next();

                session.beginTransaction();
                Messanger messanger = new Messanger();
                messanger.setMessanger_name(messangerName);
                session.save(messanger);
                session.getTransaction().commit();

                System.out.println("Inserted new messanger.");
                return;
            }
            if  (tableName.equals("person")) {
                System.out.println("Input a new person name: ");
                String name = input.next();

                System.out.println("Input a new person surname: ");
                String surname = input.next();

                System.out.println("Input a new person gender id: ");
                int genderId = input.nextInt();

                session.beginTransaction();
                Gender gender = session.byId(Gender.class).getReference(genderId);
                Person person = new Person();
                person.setName(name);
                person.setSurname(surname);
                person.setGender(gender);
                session.save(person);
                session.getTransaction().commit();

                System.out.println("Inserted new person.");
                return;
            }
        } catch(Exception e){
            System.out.println("Exception in insert");
        }
    }
}
