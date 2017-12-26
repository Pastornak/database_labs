import java.sql.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import services.Deletion;
import services.InsertionProcedures;
import services.Update;

public class lab5A {
    private static final String url = "jdbc:mysql://localhost:3306/pasternak_5?serverTimezone=UTC&useSSL=false";
    private static final String user = "root";
    private static final String password = "1234";

    private static Connection connection=null;
    private static Statement statement=null;
    private static ResultSet rs=null;

    private static Set<String> tableNames = new HashSet<String>(){
        {
            add("available");
            add("gender");
            add("messanger");
            add("person");
        }
    };

    public static void main(String args[]){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            //readData();
            //InsertionProcedures.callInsertProcedure(connection);
            //Deletion.delete(connection);
            //Update.update(connection);
            waitForInput();
        } catch(Exception E){

        } finally{
            if (rs != null) try { rs.close(); } catch (SQLException e) { } // ignore
            if (statement != null) try { statement.close(); } catch (SQLException e) { }
            if (connection != null) try { connection.close(); } catch (SQLException e) { }
        }
    }

    private static void waitForInput(){
        Scanner input = new Scanner(System.in);
        System.out.println("---------------------------------");
        System.out.println("Available operations are: ");
        System.out.println("Read data from table - input 1");
        System.out.println("Insert data into table - input 2");
        System.out.println("Delete data from table - input 3");
        System.out.println("Update data in table - input 4");
        System.out.println("Exit - input 0");
        System.out.print("Your input: ");
        int operation = input.nextInt();
        System.out.println("---------------------------------");
        if(operation == 0){
            return;
        }
        switch (operation){
            case 1:readData();break;
            case 2:InsertionProcedures.callInsertProcedure(connection);break;
            case 3:Deletion.delete(connection);break;
            case 4:Update.update(connection);break;
            default: System.out.println("No such operation, try again");
        }
        //input.close();
        waitForInput();
    }

    private static void readData(){
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
            rs = statement.executeQuery("SELECT * FROM " + tableName + ";");
            if  (tableName.equals("available")) {
                while (rs.next()) {
                    System.out.println(rs.getString(1) + " " + rs.getString(2));
                }
                return;
            }
            if  (tableName.equals("gender")){
                while (rs.next()) {
                    System.out.println(rs.getString(1) + " " + rs.getString(2));
                }
                return;
            }
            if  (tableName.equals("messanger")) {
                while (rs.next()) {
                    System.out.println(rs.getString(1) + " " + rs.getString(2));
                }
                return;
            }
            if  (tableName.equals("person")) {
                while (rs.next()) {
                    System.out.println(rs.getString(1) + " " + rs.getString(2)
                            + " " + rs.getString(3) + " " + rs.getString(4));
                }
                return;
            }
            connection.close();
        } catch(Exception e){}
    }


}
