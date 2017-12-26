import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.*;
import org.hibernate.cfg.*;
import entities.*;
import services.*;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.Scanner;

public class Main {

    /*private static SessionFactory ourSessionFactory;
    static {
        try { // Create the SessionFactory from hibernate.cfg.xml
            ourSessionFactory =  new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) { throw new ExceptionInInitializerError(ex); }
    }
    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession(); //return opened session
    }*/
    //---------------------------------------------------------------------------
    public static void main(final String[] args) throws Exception {
        // get opened session
        SessionFactory ourSessionFactory = null;
        Session session = null;
        try {
            ourSessionFactory =  new Configuration().configure().buildSessionFactory();
            session = ourSessionFactory.openSession();
            //Read.readTable(session);
            //Insert.insert(session);
            //Delete.delete(session);
            //Update.update(session);
            //ReadAllTable(session);
            waitForInput(session);
        } catch (Exception e){
            System.out.println(e);
            System.out.println("Exception");
        } finally{
            if(session != null) {
                session.close();
            }
            if(ourSessionFactory != null) {
                ourSessionFactory.close();
            }
        }
    }

    private static void waitForInput(Session session){
        Scanner input = new Scanner(System.in);
        System.out.println("---------------------------------");
        System.out.println("Available operations are: ");
        System.out.format("%-25s %1s %2d\n", "Read data from table", ":", 1);
        //System.out.println("Read data from table - input 1");
        System.out.format("%-25s %1s %2d\n", "Insert data into table", ":", 2);
        //System.out.println("Insert data into table - input 2");
        System.out.format("%-25s %1s %2d\n", "Delete data from table", ":", 3);
        //System.out.println("Delete data from table - input 3");
        System.out.format("%-25s %1s %2d\n", "Update data in table", ":", 4);
        //System.out.println("Update data in table - input 4");

        System.out.format("%-25s %1s %2d\n", "Show available table", ":", 5);

        System.out.format("%-25s %1s %2d\n", "Exit", ":", 0);
        //System.out.println("Exit - input 0");
        System.out.print("Your input: ");
        int operation = input.nextInt();
        System.out.println("---------------------------------");
        if(operation == 0){
            return;
        }
        switch (operation){
            case 1:Read.readTable(session);break;
            case 2:Insert.insert(session);break;
            case 3:Delete.delete(session);break;
            case 4:Update.update(session);break;
            case 5:showAvailableTable(session);break;
            default: System.out.println("No such operation, try again");
        }
        waitForInput(session);
    }

    private static void showAvailableTable(Session session) {
        Query query = session.createQuery("from " + "Person");
        for (Object obj : query.list()){
            String result = "";
            Person person = (Person) obj;
            result += person.getName() + " " + person.getSurname() + ": ";
            Query available = session.createQuery("from " + "Available" + " where id_person=" + person.getId_person());
            for(Object o: available.list()){
                Messanger messanger = session.get(Messanger.class, ((Available)o).getMessanger());
                result += messanger.getMessanger_name() + " ";
            }
            System.out.println(result);
        }
    }

    private static void ReadAllTable(Session session){
        Query query = session.createQuery("from " + "Gender");
        for (Object obj : query.list()) {
            Gender gender = (Gender) obj;
            System.out.format("%3d %-12s \n", gender.getId_gender(), gender.getGender_name());
        }

        String name = "Available";
        query = session.createQuery("from " + name);
        for (Object obj : query.list()) {
            Available available = (Available) obj;
            System.out.format("%3d %3d \n", available.getPerson(), available.getMessanger());
        }

        query = session.createQuery("from " + "Person");
        for (Object obj : query.list()) {
            Person person = (Person) obj;
            System.out.format("%3d %-12s %-12s %-12s\n", person.getId_person(), person.getName(), person.getSurname(),
                    person.getGender().getGender_name());
        }

        query = session.createQuery("from " + "Messanger");
        for (Object obj : query.list()) {
            Messanger messanger = (Messanger) obj;
            System.out.format("%3d %-12s \n", messanger.getId_messanger(), messanger.getMessanger_name());
        }
    }

}
