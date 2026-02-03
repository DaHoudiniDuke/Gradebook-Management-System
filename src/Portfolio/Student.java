package Portfolio;

/*
 * Represents one student.
 * Delegates grading behavior to Grade.
 */
public class Student {

    private String firstName;
    private String lastName;
    private int pid;
    private Grade grade;

    public Student(String firstName, String lastName, int pid, int score) {
        this.firstName = firstName;
        this .lastName = lastName;
        this.pid = pid;

        this.grade = new Grade(score);
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getPid() {
        return pid;
    }
    public Grade getGrade() {
        return grade;
    }

    // Updates score via Grade to preserve grading rules
    public void setScore(int newScore) {
        grade.setScore(newScore);
    }
}
