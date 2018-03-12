import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Midterm {

    static Connection conn;
    static Statement stmt;
    static ResultSet resultSet;

    public static void main(String[] args) throws SQLException {
        String website = "https://aps2.missouriwestern.edu/schedule/Default.asp?tck=201910";
        String connectStmt = "jdbc:sqlite:Schedule.db";

        /*ArrayList<Schedule> mySubjects = new ArrayList<>();
        ArrayList<Schedule> myDepartments = new ArrayList<>();*/

        try {
            conn = DriverManager.getConnection(connectStmt);
            stmt = conn.createStatement();

            menu(website);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void menu(String website){
        char ch;
        do{
            System.out.println("\nMENU");
            System.out.print("A = Erase and build subject table.\n" +
                    "B = Erase and build department table.\n" +
                    "C = Print subject table.\n" +
                    "D = Print department table.\n" +
                    "Q = Quit.\n");
            System.out.println("Please type in your choice:");
            Scanner input = new Scanner(System.in);
            String choice = input.next().toUpperCase().trim();
            ch = (choice.length() > 0)? choice.charAt(0): 'x';
            switch(ch){
                case 'A':
                    eraseAndBuildSubjects(website);
                    break;
                case 'B':
                    eraseAndBuildDepartments(website);
                    break;
                case 'C':
                    printSubjectTable();
                    break;
                case 'D':
                    printDepartmentTable();
                    break;
                case 'Q':
                    break;
                default:
                    System.out.println("You still have to type a letter from the menu!!");
            }
        }while(ch != 'Q');
    }

    public static void eraseAndBuildSubjects(String website){
        try {
            stmt.execute("delete from subjects");

            Document doc = Jsoup.connect(website).get();
            Elements subject = doc.select("select#subject > option");

            for(Element option: subject){
                String sub_abbrev = option.attr("value");
                String sub_name = option.select("[value]").text();
                stmt.executeUpdate("insert into subjects(sub_abbrev, sub_name) values('" + sub_abbrev + "', '"  + sub_name +"')");
            }

            stmt.execute("delete from subjects where sub_abbrev like 'ALL'");
            stmt.execute("delete from subjects where sub_name like 'ALL Subjects'");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("Erasing and building subjects table successful.");
    }

    public static void eraseAndBuildDepartments(String website){
        try {
            stmt.execute("delete from departments");

            Document doc = Jsoup.connect(website).get();
            Elements department = doc.select("select#department > option");

            for(Element option: department){
                String dep_abbrev = option.attr("value");
                String dep_name = option.select("[value]").text();
                stmt.executeUpdate("insert into departments(dep_abbrev, dep_name) values('" + dep_abbrev + "', '"  + dep_name +"')");
            }

            stmt.execute("delete from departments where dep_abbrev like 'ALL'");
            stmt.execute("delete from departments where dep_name like 'ALL Departments'");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("Erasing and building departments table successful.");
    }

    public static void printSubjectTable(){
        try {
            stmt.execute("select * from subjects");
            resultSet = stmt.getResultSet();

            System.out.println("\nSUBJECT TABLE");
            System.out.println("SUB_ABBREV      SUB_NAME");

            while(resultSet.next()) {
                String abbrev = resultSet.getString("sub_abbrev");
                String name = resultSet.getString("sub_name");
                System.out.printf("%-15s %s\n", abbrev, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Printing subjects table complete.");
    }

    public static void printDepartmentTable(){
        try {
            stmt.execute("select * from departments");
            resultSet = stmt.getResultSet();

            System.out.println("\nDEPARTMENT TABLE");
            System.out.println("DEP_ABBREV      DEP_NAME");

            while(resultSet.next()) {
                String abbrev = resultSet.getString("dep_abbrev");
                String name = resultSet.getString("dep_name");
                System.out.printf("%-15s %s\n", abbrev, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Printing departments table complete.");
    }
}

/*abstract class College{
    public abstract String getAbbrev();
    public abstract String getName();
}

class Schedule extends College{
    protected String abbrev;
    protected String name;

    public Schedule(String abbrev, String name){
        this.abbrev = abbrev;
        this.name = name;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public String getName() {
        return name;
    }
}*/
