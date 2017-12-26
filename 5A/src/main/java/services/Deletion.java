package services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Deletion {

    private static Connection connection=null;

    public static void delete(Connection connectionInput){
        Scanner input = new Scanner(System.in);
        System.out.println("Delete data from table: ");
        String tableName = input.next();
        tableName = tableName.toLowerCase();
        connection = connectionInput;
        if  (tableName.equals("available")) {
            deleteAvailable();
            return;
        }
        if  (tableName.equals("gender")){
            deleteGender();
            return;
        }
        if  (tableName.equals("messanger")) {
            deleteMessanger();
            return;
        }
        if  (tableName.equals("person")) {
            deletePerson();
        }
    }

    private static void deleteAvailable(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Delete user from Available(int): ");
            int user = input.nextInt();

            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("DELETE FROM available WHERE id_person=?;");
            preparedStatement.setInt(1, user);
            int n = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + n);
        }catch (Exception E){
            System.out.println("Exception");
        }
    }

    private static void deleteGender(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Delete name from Gender: ");
            String gender = input.next();

            PreparedStatement preparedStatement;
            /*preparedStatement = connection.prepareStatement("SET SQL_SAFE_UPDATES = 0;" +
                    "DELETE FROM gender WHERE gender_name ='" + gender + "';" +
                    "SET SQL_SAFE_UPDATES = 1;");*/
            preparedStatement = connection.prepareStatement("DELETE FROM gender WHERE gender_name=?;");
            preparedStatement.setString(1, gender);
            int n = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + n);
        }catch (Exception E){
            System.out.println("Exception");
        }
    }

    private static void deleteMessanger(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Delete name from Messanger: ");
            String messanger = input.next();

            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("DELETE FROM messanger WHERE messanger_name=?;");
            preparedStatement.setString(1, messanger);
            int n = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + n);
        }catch (Exception E){
            System.out.println("Exception");
        }
    }

    private static void deletePerson(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Input name for Person: ");
            String name = input.next();
            System.out.println("Input surname for Person: ");
            String surname = input.next();

            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("DELETE FROM person WHERE name=? AND surname=?;");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            int n = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + n);
        }catch (Exception E){
            System.out.println("Exception");
        }
    }
}
