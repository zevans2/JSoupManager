import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Midterm {

    public static void main(String[] args) throws SQLException {
        String website = "https://aps2.missouriwestern.edu/schedule/Default.asp?tck=201910";
        String connectStmt = "jdbc:sqlite:Schedule.db";

        ArrayList<Schedule> mySubjects = new ArrayList<>();
        ArrayList<Schedule> myDepartments = new ArrayList<>();

        scrapeIntoArrayList(mySubjects, myDepartments, website);

        Connection conn;
        Statement stmt;
        ResultSet resultSet;

        try {
            conn = DriverManager.getConnection(connectStmt);
            stmt = conn.createStatement();
            /*String createSubjects = "create table if not exists subjects(sub_abbrev varchar(3), sub_name varchar(50))";
            String createDepartments = "create table if not exists departments(dep_abbrev varchar(4), dep_name varchar(50))";
            stmt.execute(createSubjects);
            stmt.execute(createDepartments);*/
            stmt.execute("delete from Departments");
            stmt.execute("delete from Subjects");

            for(int i=1; i<mySubjects.size(); i++){
                insertSubjects(mySubjects.get(i), stmt);
            }

            for(int j=1; j<myDepartments.size(); j++){
                insertDepartments(myDepartments.get(j), stmt);
            }

            resultSet = stmt.getResultSet();

            while(resultSet.next()) {
                String result = resultSet.getString("sub_abbrev");
                String result2 = resultSet.getString("sub_name");
                System.out.println(result);
                System.out.println(result2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void scrapeIntoArrayList(ArrayList mySubjects, ArrayList myDepartments, String website){
        try {
            Document doc = Jsoup.connect(website).get();
            Elements subject = doc.select("select#subject > option");
            Elements department = doc.select("select#department > option");

            for(Element option: subject){
                String sub_abbrev = option.attr("value");
                String sub_name = option.select("[value]").text();
                mySubjects.add(new Schedule(sub_abbrev, sub_name));
            }

            for(Element option: department){
                String dep_abbrev = option.attr("value");
                String dep_name = option.select("[value]").text();
                myDepartments.add(new Schedule(dep_abbrev, dep_name));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertSubjects(Schedule mySubject, Statement stmt){
        String abbrev = mySubject.getAbbrev();
        String name = mySubject.getName();
        try {
            stmt.execute("insert into subjects(sub_abbrev, sub_name) values('" + abbrev + "', '"  + name +"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insertDepartments(Schedule myDepartments, Statement stmt){
        String abbrev = myDepartments.getAbbrev();
        String name = myDepartments.getName();
        try {
            stmt.execute("insert into departments(dep_abbrev, dep_name) values('" + abbrev + "', '"  + name +"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

abstract class College{
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
}
