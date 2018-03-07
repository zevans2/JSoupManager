import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Department> departments;

        //TODO: Create Database Table to hold all of the sections listed in the Schedule
        //TODO: Wipeout and restore data for one department. Checkpoint/Modify/Restart
        //TODO: Generate a report that accepts either a discipline or a department BIO-Biology || CSMP - ACT, CSC, MAT, PHY
        //TODO: Generate a report that prints a list of professors by department along with their schedule of classes.

        //TODO: Control Break report that breaks on every course.
        // todo: Shows a title for the course and the sections should appear under it.
        // todo: For each course, print the sum of the people taking the course and a total of the number of seats still available in the course.


        //Create All Departments and Add Professors
        makeMenu();
    }//end main


    private static void makeMenu(){
        char ch = 'x';
        do{
            System.out.println("\t\tA. Erase and Build the Subjects (Discipline) table");
            System.out.println("\t\tB. Erase and Build the Department table");
            //...
            System.out.println("\t\tQ. Quit\n");

            Scanner input = new Scanner(System.in);
            System.out.println("Type a letter. ");

            String selection = input.next().toUpperCase().trim();
            ch = (selection.length()>0) ? selection.charAt(0) : 'x';

            switch(ch){
                case 'A':
                    eraseAndBuildSubjectsTable();
                    break;
                case'B':
                    eraseAndBuildDepartmentTable();
                    break;
                case 'Q':
                    break;
                default:
                    System.out.println("Error, please pick A throug K or Q to quit");

            }


        }while(ch != 'Q');
        System.out.println("Closing App....");
        System.exit(0);
    }

    private static void eraseAndBuildDepartmentTable() {
        System.out.println("Erasing Department Table...");
        //Drop Table
        System.out.println("Building Department Table...");
        //Create Table
        System.out.println("Table Created Successfully...\nJK. It's gone forevever.");
    }

    private static void eraseAndBuildSubjectsTable() {
        System.out.println("Erasing Subjects Table...");
        //Drop Table
        System.out.println("Building Subjects Table...");
        //Create Table
        System.out.println("Table Created Successfully...\n..WHAT DID YOU DO?!\nIT's Gone Forever!!");
    }


}//end Main

//Department Class - Parent of Teacher and Subject objects
class Department {
    String name;
    //list of Subjects in Department
    ArrayList<Subject> subjects;
    ArrayList<Professor> professors;
    Cstats statics;

    //constructor
    public Department(String name, ArrayList<Professor> professors, ArrayList<Subject> subjects) {
        this.name = name;
        this.professors = professors;
        this.subjects = subjects;
        statics = new Cstats(subjects);//TODO: setup statics at department level
    }//end Constructor

    //subclass of Subjects
    protected class Subject {
        String name;
        //list of classes in Subject
        ArrayList<Course> classes;
        Cstats statics;

        //Constructor
        public Subject(String name, ArrayList<Course> classes) {
            this.name = name;
            this.classes = classes;
            statics = new Cstats("Subject",classes);
        }//end Constructor

        //Subclass Classes in Subject
        protected class Course {
            String crn, name, title, time;
            Professor professor;

            //Constructor
            public Course(String crn, String name, String title, String time, Professor professor) {
                this.crn = crn;
                this.name = name;
                this.title = title;
                this.time = time;
                this.professor = professor;
                Cstats statics = new Cstats();
            }//end Constructor

            public void setProfessor(Professor newProfessor){
                professor = newProfessor;
            }
        }//end Course
    }//end Subject
}//end Department

//Professor object. Holds Schedule, and is assigned to both department and course objects.
class Professor {
    String name;
    //Constructor
    public Professor(String name) {
        this.name = name;
    }//end Constructor
}//end Professor





//Class for keeping totals for each object
class Cstats {
    int maxSeats;
    int students;
    int totalStudents;
    int seatsAvailable;
    ArrayList<Department.Subject.Course> courses;
    ArrayList<Department.Subject> subjects;

    //Constructor for Course Statics
    public Cstats() {
        students = totalStudents = seatsAvailable = 0;
        int flag = 0;//flag for Course stats
    }//end constructor

    //Constructor for Department Statics
    public Cstats(String name, ArrayList<Department.Subject.Course> courses){
        students = totalStudents = 0;
        this.courses = courses;
        int flag = 1; //flag for Subject Stats
    }//end constructor

    public Cstats(ArrayList<Department.Subject> subjects) {
        students = totalStudents = 0;
        this.subjects = subjects;
        int flag = 2;

    }

/*
    public Cstats(ArrayList<Department.Subject> subjects) {
        int flag = 2; //flag for Department stats
    }
*/

    public void runStats() {
        seatsAvailable = maxSeats -= students;
        totalStudents += students;
    }
}








































