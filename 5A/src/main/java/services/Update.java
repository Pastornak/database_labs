package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class Update {

    private static Connection connection=null;

    public static void update(Connection connectionInput){
        Scanner input = new Scanner(System.in);
        System.out.println("Update data in table: ");
        String tableName = input.next();
        tableName = tableName.toLowerCase();
        connection = connectionInput;
        if  (tableName.equals("available")) {
            updateAvailable();
            return;
        }
        if  (tableName.equals("gender")){
            updateGender();
            return;
        }
        if  (tableName.equals("messanger")) {
            updateMessanger();
            return;
        }
        if  (tableName.equals("person")) {
            updatePerson();
        }
    }

    private static void updateAvailable(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Update user in Available(int): ");
            int user = input.nextInt();
            System.out.println("Where user is(int): ");
            int condition = input.nextInt();

            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("UPDATE available SET id_user=? WHERE id_user=?;");
            preparedStatement.setInt(1, user);
            preparedStatement.setInt(2, condition);
            int n = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + n);
        }catch (Exception E){
            System.out.println("Exception");
        }
    }

    private static void updateGender(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Update name in Gender: ");
            String gender = input.next();
            System.out.println("Where name is: ");
            String condition = input.next();
            /*Statement statement = connection.createStatement();
            statement.execute("SET SQL_SAFE_UPDATES = 0;" +
                    "UPDATE gender SET gender_name='" + gender + "' WHERE gender_name='" + condition + "';" +
                    "SET SQL_SAFE_UPDATES = 1;");
            statement.close();*/

            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("UPDATE gender SET gender_name=? WHERE gender_name=?;");
            preparedStatement.setString(1, gender);
            preparedStatement.setString(2, condition);
            int n = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + n);
        }catch (Exception E){
            System.out.println("Exception");
        }
    }

    private static void updateMessanger(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Update name in Messanger: ");
            String messanger = input.next();
            System.out.println("Where name is: ");
            String condition = input.next();

            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("UPDATE messanger SET messanger_name=? WHERE messanger_name=?;");
            preparedStatement.setString(1, messanger);
            preparedStatement.setString(2, condition);
            int n = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + n);
        }catch (Exception E){
            System.out.println("Exception");
        }
    }

    private static void updatePerson(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Update name in Person: ");
            String name = input.next();
            System.out.println("Where name is: ");
            String condition = input.next();

            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("UPDATE person SET name=? WHERE name=?;");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, condition);
            int n = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + n);
        }catch (Exception E){
            System.out.println("Exception");
        }
    }
}
