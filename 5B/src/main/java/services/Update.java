package services;

import entities.*;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Update {

    private static Set<String> tableNames = new HashSet<String>(){
        {
            add("available");
            add("gender");
            add("messanger");
            add("person");
        }
    };

    public static void update(Session session){
        Scanner input = new Scanner(System.in);
        System.out.println("Update data in table: ");
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
                System.out.println("Update unavailable for table available.");
                return;
            }
            if  (tableName.equals("gender")){
                System.out.println("Update gender where id: ");
                int genderId = input.nextInt();
                System.out.println("New gender name: ");
                String genderName = input.next();

                session.beginTransaction();
                Gender gender = session.get(Gender.class, genderId);
                String beforeUpdate = gender.getGender_name();
                gender.setGender_name(genderName);
                session.update(gender);
                session.getTransaction().commit();

                System.out.println("Updated gender " + beforeUpdate + " to " + genderName + ".");
                return;
            }
            if  (tableName.equals("messanger")) {
                System.out.println("Update messanger where id: ");
                int messangerId = input.nextInt();
                System.out.println("New messanger name: ");
                String messangerName = input.next();

                session.beginTransaction();
                Messanger messanger = session.get(Messanger.class, messangerId);
                String beforeUpdate = messanger.getMessanger_name();
                messanger.setMessanger_name(messangerName);
                session.update(messanger);
                session.getTransaction().commit();

                System.out.println("Updated messanger " + beforeUpdate + " to " + messangerName + ".");
                return;
            }
            if  (tableName.equals("person")) {
                System.out.println("Input person id: ");
                int personId = input.nextInt();
                System.out.println("New person name: ");
                String personName = input.next();

                session.beginTransaction();
                Person person = session.get(Person.class, personId);
                String beforeUpdate = person.getName() + " " + person.getSurname();
                person.setName(personName);
                session.update(person);
                session.getTransaction().commit();

                System.out.println("Updated person " + beforeUpdate + " to " + personName + ".");
                return;
            }
        } catch(Exception e){
            System.out.println("Exception in read table");
        }
    }
}
