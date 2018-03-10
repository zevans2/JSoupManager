import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Midterm {

    Statement statement;
    ResultSet resultSet;

    public static void main(String[] args) {
        String website = "https://aps2.missouriwestern.edu/schedule/Default.asp?tck=201910";

        Sqlite db = new Sqlite();
        db.connectDb("Schedule.db");
        db.clear("subjects");
        db.clear("departments");

        try {
            Document doc = Jsoup.connect(website).get();
            Elements subject = doc.select("select#subject > option");
            Elements department = doc.select("select#department > option");

            for(Element option: subject){
                String sub_abbrev = option.attr("value");
                String sub_name = option.select("[value]").text();
                db.insert("subjects (sub_abbrev, sub_name)", sub_abbrev, sub_name);
            }

            for(Element option: department){
                String dep_abbrev = option.attr("value");
                String dep_name = option.select("[value]").text();
                db.insert("departments (dep_abbrev, dep_name)", dep_abbrev, dep_name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Sqlite {

    Connection conn;
    Statement stmt;

    public void connectDb(String fileName){
        String connectStmt = "jdbc:sqlite:" + fileName;

        try {
            conn = DriverManager.getConnection(connectStmt);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(String table, String abbrev, String name){
        String query = "INSERT into " + table + " VALUES (" + abbrev + ", " + name + ");";
        try {
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clear(String table){
        String query = "DELETE * FROM " + table;
    }
}

