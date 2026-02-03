package Portfolio;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Manages a collection of students and class statistics.
 */
public class Gradebook {
    private ArrayList<Student> listOfStudents;

    public Gradebook() {
        listOfStudents = new ArrayList<>();
    }

    public void addStudent(Student s) {
        listOfStudents.add(s);
    }

    // Linear search by PID
    public Student findByPid(int pid) {
        for (Student s : listOfStudents) {
            if (s.getPid() == pid) {
                return s;
            }
        }
        return null;
    }

    public double calculateAvg() {
        int sum = 0;

        for (Student s : listOfStudents) {
            sum += s.getGrade().getScore();
        }
        return sum / listOfStudents.size();
    }

    // Median requires sorting a copy of scores
    public double calculateMedian() {
        int i = 0, n = listOfStudents.size();
        int[] scores = new int[n];
        for (Student s : listOfStudents) {
            scores[i++] = s.getGrade().getScore();
        }
        Arrays.sort(scores);

        if (n % 2 == 0) {
            return (scores[n / 2] + scores[n / 2 - 1]) / 2.0f;
        } else {
            return scores[n / 2];
        }
    }

    public String averageLetter() {
        double avg = calculateAvg();

        int rounded = (int) Math.round(avg);

        Grade temp = new Grade(rounded);
        return temp.getLetterGrade();
    }

    public String medianLetter() {
        double med = calculateMedian();

        int rounded = (int) Math.round(med);

        Grade temp = new Grade(rounded);
        return temp.getLetterGrade();
    }

    public int minScore() {
        if (listOfStudents.isEmpty()) {
            return 0;
        }
        int min = listOfStudents.get(0).getGrade().getScore();

        for (Student s : listOfStudents) {
            int sc = s.getGrade().getScore();
            if (sc < min) {
                min = sc;
            }
        }
        return min;
    }

    public int maxScore() {
        if (listOfStudents.isEmpty()) {
            return 0;
        }
        int max = listOfStudents.get(0).getGrade().getScore();

        for (Student s : listOfStudents) {
            int sc = s.getGrade().getScore();
            if (sc > max) {
                max = sc;
            }
        }
        return max;
    }

    public String minLetter() {
        return new Grade(minScore()).getLetterGrade();
    }

    public String maxLetter() {
        return new Grade(maxScore()).getLetterGrade();
    }

    public void printAllStudents() {
        for (Student s : listOfStudents) {
            System.out.printf("%s\t%s\t%d\t%d\n", s.getFirstName(), s.getLastName(), s.getPid(), s.getGrade().getScore());


        }
    }

    public void printAllStudentsLetters() {
        for (Student s : listOfStudents) {
            System.out.printf("%s\t%s\t%d\t%s%n", s.getFirstName(), s.getLastName(), s.getPid(), s.getGrade().getLetterGrade());
        }
    }

}

