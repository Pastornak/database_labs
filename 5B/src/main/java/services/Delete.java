package services;

import entities.*;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Delete {

    private static Set<String> tableNames = new HashSet<String>(){
        {
            add("available");
            add("gender");
            add("messanger");
            add("person");
        }
    };

    public static void delete(Session session){
        Scanner input = new Scanner(System.in);
        System.out.println("Delete data from table: ");
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
                Available available = session.get(Available.class, availablePK);
                String deletedAvailable = available.getPerson() + " " + available.getMessanger();
                session.delete(available);
                session.getTransaction().commit();

                System.out.println("Deleted available " + deletedAvailable + ".");
                return;
            }
            if  (tableName.equals("gender")){
                System.out.println("Input gender id: ");
                int genderId = input.nextInt();

                session.beginTransaction();
                Gender gender = session.get(Gender.class, genderId);
                String deletedGender = gender.getGender_name();
                session.delete(gender);
                session.getTransaction().commit();

                System.out.println("Deleted gender " + deletedGender + ".");
                return;
            }
            if  (tableName.equals("messanger")) {
                System.out.println("Input messanger id: ");
                int messangerId = input.nextInt();

                session.beginTransaction();
                Messanger messanger = session.get(Messanger.class, messangerId);
                String deletedMessanger = messanger.getMessanger_name();
                session.delete(messanger);
                session.getTransaction().commit();

                System.out.println("Deleted messanger " + deletedMessanger + ".");
                return;
            }
            if  (tableName.equals("person")) {
                System.out.println("Input person id: ");
                int personId = input.nextInt();

                session.beginTransaction();
                Person person = session.get(Person.class, personId);
                String deletedPerson = person.getName() + " " + person.getSurname();
                session.delete(person);
                session.getTransaction().commit();

                System.out.println("Deleted person " + deletedPerson + ".");
                return;
            }
        } catch(Exception e){
            System.out.println("Exception in read table");
        }
    }
}
