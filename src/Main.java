import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Department>departments;

        //TODO: Create Database Table to hold all of the sections listed in the Schedule
        //TODO: Wipeout and restore data for one department. Checkpoint/Modify/Restart
        //TODO: Generate a report that accepts either a discipline or a department BIO-Biology || CSMP - ACT, CSC, MAT, PHY
        //TODO: Generate a report that prints a list of instructors by department along with their schedule of classes.

        //TODO: Control Break report that breaks on every course.
        // todo: Shows a title for the course and the sections should appear under it.
        // todo: For each course, print the sum of the people taking the course and a total of the number of seats still available in the course.


        //Create All Departments and Add Professors




    }


}

//Department Class - Parent of Teacher and Subject objects
class Department {
    String name;
    //list of Subjects in Department
    ArrayList<Subject> subjects;
    ArrayList<Instructor> instructors;

    //constructor
    public Department(String name, ArrayList<Instructor> instructors, ArrayList<Subject> subjects) {
        this.name = name;
        this.instructors = instructors;
        this.subjects = subjects;
    }//end Constructor

    //subclass of Subjects
    private class Subject {
        String name;
        //list of classes in Subject
        ArrayList<Course> classes;

        //Constructor
        public Subject(String name, ArrayList<Course> classes) {
            this.name = name;
            this.classes = classes;
        }//end Constructor

        //Subclass Classes in Subject
        private class Course {
            String crn, name, title, time;
            Instructor professor;

            //Constructor
            public Course(String crn, String name, String title, String time ,Instructor professor ){
                this.crn = crn;
                this.name = name;
                this.title = title;
                this.time = time;
                this.professor = professor;
            }//end Constructor
        }

    }
}

class Instructor{
    String name;

    //Constructor
    public Instructor(String name){
        this.name = name;
    }
}

//Class for keeping totals for each object
class CStats{
    int maxSeats;
    int students;
    int totalStudents;
    int seatsAvailable;

    //Constructor
    public CStats(){
        students=totalStudents=seatsAvailable=0;
    }//end constructor

    public void runStats(){
        seatsAvailable = maxSeats-=students;
        totalStudents += students;
    }
}








































