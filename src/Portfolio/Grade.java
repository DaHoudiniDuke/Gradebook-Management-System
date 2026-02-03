package Portfolio;
/*
 * Encapsulates grading logic.
 * Keeps numeric → letter conversion in one place.
 */
public class Grade {
    private int score;
    private String letterGrade;

    // Initialize grade using score (letter is derived)
    public Grade(int score) {
        setScore(score);
    }

    public int getScore() {
        return score;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    // Ensures score and letterGrade stay consistent
    public void setScore(int score) {
        this.score = score;
        this.letterGrade = computeLetterGrade(score);
    }

    // Internal grading rules (not exposed outside Grade)
    private String computeLetterGrade(int score) {
        if (score >= 95)
            return "A";
        else if (score >= 90)
            return "A-";
        else if (score >= 87)
            return "B+";
        else if (score >= 83)
            return "B";
        else if (score >= 80)
            return "B-";
        else if (score >= 77)
            return "C+";
        else if (score >= 73)
            return "C";
        else if (score >= 70)
            return "C-";
        else if (score >= 60)
            return "D";
        else {
            return "F";
        }
    }
}
