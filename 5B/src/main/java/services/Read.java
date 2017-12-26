package services;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.*;
import org.hibernate.cfg.*;
import entities.*;

public class Read {

    private static Set<String> tableNames = new HashSet<String>(){
        {
            add("available");
            add("gender");
            add("messanger");
            add("person");
        }
    };

    public static void readTable(Session session){
        Scanner input = new Scanner(System.in);
        System.out.println("Read data from table: ");
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
                Query query = session.createQuery("from " + "Available");
                for (Object obj : query.list()) {
                    Available available = (Available) obj;
                    System.out.format("%3d %3d \n", available.getPerson(), available.getMessanger());
                }
                return;
            }
            if  (tableName.equals("gender")){
                Query query = session.createQuery("from " + "Gender");
                for (Object obj : query.list()) {
                    Gender gender = (Gender) obj;
                    System.out.format("%3d %-12s \n", gender.getId_gender(), gender.getGender_name());
                }
                return;
            }
            if  (tableName.equals("messanger")) {
                Query query = session.createQuery("from " + "Messanger");
                for (Object obj : query.list()) {
                    Messanger messanger = (Messanger) obj;
                    System.out.format("%3d %-12s \n", messanger.getId_messanger(), messanger.getMessanger_name());
                }
                return;
            }
            if  (tableName.equals("person")) {
                Query query = session.createQuery("from " + "Person");
                for (Object obj : query.list()) {
                    Person person = (Person) obj;
                    System.out.format("%3d %-12s %-12s %-12s\n", person.getId_person(), person.getName(), person.getSurname(),
                            person.getGender().getGender_name());
                }
                return;
            }
        } catch(Exception e){
            System.out.println("Exception in read table");
        }
    }
}
