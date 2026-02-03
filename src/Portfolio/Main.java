package Portfolio;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Gradebook gb = new Gradebook();

        // INPUT HANDLING PHASE
        System.out.println("Welcome to my grade book!");
        System.out.println("Please enter the information of the first student using the following format:");
        System.out.println("\"firstName lastName PID grade\".");
        System.out.println("Press Enter when you are done.");
        System.out.println();

        // Read first student until valid
        while (true) {
            String line = in.nextLine().trim();
            Student s = parseStudentLine(line);
            if (s != null) {
                gb.addStudent(s);
                break;
            }
            System.out.println("Invalid input. Please try again.");
        }

        // Read remaining students until DONE
        while (true) {
            System.out.println("Please enter the information of the next student using the same format.");
            System.out.println("If there is no more students, please enter the keyword \"DONE\".");
            System.out.println("Press Enter when you are done.");
            System.out.println();

            String line = in.nextLine().trim();
            if (line.equals("DONE")) break;

            Student s = parseStudentLine(line);
            if (s != null) {
                gb.addStudent(s);
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }


        // COMMAND HANDLING PHASE
        while (true) {
            System.out.println("Please enter a new command");
            String cmdLine = in.nextLine().trim();

            if (cmdLine.equals("quit")) {
                break;
            }

            if (!handleCommand(cmdLine, gb)) {
                System.out.println("Invalid command.");
            }
        }

        in.close();
    }


    // Command Parser/Dispatcher
    private static boolean handleCommand(String cmdLine, Gradebook gb) {
        String[] t = cmdLine.split("\\s+");

        // 2-word commands
        if (t.length == 2) {
            String a = t[0];
            String b = t[1];

            if (a.equals("min") && b.equals("score"))   { System.out.println(gb.minScore()); return true; }
            if (a.equals("min") && b.equals("letter"))  { System.out.println(gb.minLetter()); return true; }
            if (a.equals("max") && b.equals("score"))   { System.out.println(gb.maxScore()); return true; }
            if (a.equals("max") && b.equals("letter"))  { System.out.println(gb.maxLetter()); return true; }

            if (a.equals("average") && b.equals("score"))  { System.out.println(gb.calculateAvg()); return true; }
            if (a.equals("average") && b.equals("letter")) { System.out.println(gb.averageLetter()); return true; }

            if (a.equals("median") && b.equals("score"))   { System.out.println(gb.calculateMedian()); return true; }
            if (a.equals("median") && b.equals("letter"))  { System.out.println(gb.medianLetter()); return true; }

            if (a.equals("tab") && b.equals("scores")) {
                System.out.println("first name\tlast name\tPID\tscore");
                gb.printAllStudents(); // prints scores table rows
                return true;
            }

            if (a.equals("tab") && b.equals("letters")) {
                System.out.println("first name\tlast name\tPID\tletter-grades");
                // You need a method that prints rows with letter grades.
                // Recommended: implement gb.printAllStudentsLetters() in Gradebook.
                gb.printAllStudentsLetters();
                return true;
            }

            // PID lookup commands: letter XXXXXXX, name XXXXXXX
            if (a.equals("letter") && isValidPid(t[1])) {
                int pid = Integer.parseInt(t[1]);
                Student s = gb.findByPid(pid);
                if (s == null) System.out.println("Student not found.");
                else System.out.println(s.getGrade().getLetterGrade());
                return true;
            }

            if (a.equals("name") && isValidPid(t[1])) {
                int pid = Integer.parseInt(t[1]);
                Student s = gb.findByPid(pid);
                if (s == null) System.out.println("Student not found.");
                else System.out.println(s.getFirstName() + " " + s.getLastName());
                return true;
            }

            return false;
        }

        // 3-word command: change XXXXXXX YY
        if (t.length == 3 && t[0].equals("change")) {
            String pidStr = t[1];
            String scoreStr = t[2];

            if (!isValidPid(pidStr) || !isValidScore(scoreStr)) return false;

            int pid = Integer.parseInt(pidStr);
            int newScore = Integer.parseInt(scoreStr);

            Student s = gb.findByPid(pid);
            if (s == null) {
                System.out.println("Student not found.");
            } else {
                s.setScore(newScore);
                System.out.println("Grade updated.");
            }
            return true;
        }

        return false;
    }

    // Student Line Parser
    // Expected: firstName lastName PID grade
    private static Student parseStudentLine(String line) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length != 4) return null;

        String first = parts[0];
        String last  = parts[1];
        String pidS  = parts[2];
        String scS   = parts[3];

        if (!isValidFirstName(first)) return null;
        if (!isValidLastName(last)) return null;
        if (!isValidPid(pidS)) return null;
        if (!isValidScore(scS)) return null;

        int pid = Integer.parseInt(pidS);
        int score = Integer.parseInt(scS);

        return new Student(first, last, pid, score);
    }


    // Validation Helpers
    private static boolean isValidFirstName(String s) {
        if (s == null || s.isEmpty()) return false;
        if (!Character.isUpperCase(s.charAt(0))) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    private static boolean isValidLastName(String s) {
        if (s == null || s.isEmpty()) return false;
        if (!Character.isUpperCase(s.charAt(0))) return false;

        int dots = 0;
        for (char c : s.toCharArray()) {
            if (c == '.') {
                dots++;
                if (dots > 1) return false;
            } else if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidPid(String s) {
        if (s == null || s.length() != 7) return false;
        if (s.charAt(0) == '0') return false;
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private static boolean isValidScore(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        int val = Integer.parseInt(s);
        return val >= 0 && val <= 100;



    }

}
