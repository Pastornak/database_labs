package services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

public class InsertionProcedures {

    private static Connection connection=null;

    public static void callInsertProcedure(Connection connectionInput){
        Scanner input = new Scanner(System.in);
        System.out.println("Insert data into table: ");
        String tableName = input.next();
        tableName = tableName.toLowerCase();
        connection = connectionInput;
        if  (tableName.equals("available")) {
            insertAvailable();
            return;
        }
        if  (tableName.equals("gender")){
            insertGender();
            return;
        }
        if  (tableName.equals("messanger")) {
            insertMessanger();
            return;
        }
        if  (tableName.equals("person")) {
            insertPerson();
        }
    }

    private static void insertAvailable(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Input user for Available(int): ");
            int user = input.nextInt();
            System.out.println("Input messanger for Available(int): ");
            int messanger = input.nextInt();
            CallableStatement callableStatement;
            callableStatement = connection.prepareCall("call InsertAvailable(" + user + ", " + messanger + ")");
            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
        }catch (Exception E){
            System.out.println("Exception");
        }
    }

    private static void insertGender(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Input name for Gender: ");
            String gender = input.next();
            CallableStatement callableStatement;
            callableStatement = connection.prepareCall("call InsertGender('" + gender + "')");
            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }catch (Exception E){
            System.out.println("Exception");
        }
    }

    private static void insertMessanger(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Input name for Messanger: ");
            String messanger = input.next();
            CallableStatement callableStatement;
            callableStatement = connection.prepareCall("call InsertMessanger('" + messanger + "')");
            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }catch (Exception E){
            System.out.println("Exception");
        }
    }

    private static void insertPerson(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Input name for Person: ");
            String name = input.next();
            System.out.println("Input surname for Person: ");
            String surname = input.next();
            System.out.println("Input gender for Person (int): ");
            int gender = input.nextInt();
            CallableStatement callableStatement;
            callableStatement = connection.prepareCall("call InsertPerson('" + name + "', '" + surname + "', " +
                    gender + ")");
            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }catch (Exception E){
            System.out.println("Exception");
        }
    }
}
