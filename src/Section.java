import org.jsoup.select.Evaluator;

public class Section {

    private Evaluator.Id courseID;
    private String department;
    //private Professor instructor;
    private String term;
    private String crn;
    private String discipline;
    private String courseNumber;
    private String section;
    private String type;
    private String title;
    private String hours;
    private String days;
    private String time;
    private String room;
    private String instructor;
    private String endDate;
    private String messages;
    private int maximumEnrollment;
    private int seatsAvailable;


    public Section(){}//Constructor

    @Override
    public String toString(){
        return "["+"courseID='"+courseID+"', department='"+department+"', term='"+term+"', crn='"+crn+
                "', discipline='"+discipline+"', courseNumber='"+courseNumber+"', section='"+section+"', type='"+type+
                "', title='"+title+"', hours='"+hours+"', days='"+days+"', time='"+time+"' room='"+room+
                "', Instructor='"+instructor+"', endDate='"+endDate+"', messages='"+messages+
                "', maxEnrollment='"+maximumEnrollment+"', seatsAvailable='"+seatsAvailable+"', " +"]";
    }


    //Utility Function
    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getMaximumEnrollment() {
        return maximumEnrollment;
    }

    public void setMaximumEnrollment(int maximumEnrollment) {
        this.maximumEnrollment = maximumEnrollment;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCourseID(Evaluator.Id courseID) {
        this.courseID = courseID;
    }
}//end Section Object
