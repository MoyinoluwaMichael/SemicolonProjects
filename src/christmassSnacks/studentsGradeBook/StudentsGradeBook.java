package christmassSnacks.studentsGradeBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class StudentsGradeBook {
    private static final Scanner scanner = new Scanner(System.in);
    private static int[][] studentGradeBook;
    private static int[] studentPosition;
    private static int[] highestScores;
    private static int[] lowestScores;

    public static void collectData() throws InterruptedException {
        System.out.println("How many students do you have?");
        int numberOfStudents = scanner.nextInt();
        System.out.println("How many subjects do they offer?");
        int numberOfSubjects = scanner.nextInt();
        load("Saving");
        System.out.println("Saved successfully");
        studentGradeBook = new int[numberOfStudents][numberOfSubjects];
        studentPosition = new int[numberOfStudents];
        highestScores = new int[numberOfSubjects];
        lowestScores = new int[numberOfSubjects];
    }

    public static void processStudentGrades() throws InterruptedException {
        for (int student = 0; student < studentGradeBook.length; student++) {
            int total = 0;
            for (int subject = 0; subject < studentGradeBook[student].length; subject++) {
                addNewLine();
                System.out.printf("""
                        Entering score for student %d
                        Enter score for subject %d
                        """, student + 1, subject + 1);
                int score = scanner.nextInt();
                studentGradeBook[student][subject] = score;
                total += score;
                load("Saving");
                System.out.println("Saved successfully");
            }
            studentPosition[student] = total;
        }
        setStudentPosition();
    }

    private static void setStudentPosition() {
        Arrays.sort(studentPosition);
        int[] studentPositionCopy = Arrays.copyOf(studentPosition, studentPosition.length);
        int index = 0;
        for (int count = studentPositionCopy.length - 1; count >= 0; count--) {
            studentPosition[index] = studentPositionCopy[count];
            index++;
        }
    }

    public static void printStudentResult() {
        addNewLine();
        printSeparatorLine();
        printHeading();
        printSeparatorLine();
        computeStudentGrade();
        printSeparatorLine();
        printSeparatorLine();
    }

    private static void computeStudentGrade() {
        for (int student = 0; student < studentGradeBook.length; student++) {
            int totalScore = 0;
            System.out.printf("%-15s", "Student " + (student + 1));
            for (int score = 0; score < studentGradeBook[student].length; score++) {
                System.out.printf("%-8d", studentGradeBook[student][score]);
                totalScore += studentGradeBook[student][score];
            }
            System.out.printf("%-6d", totalScore);
            System.out.printf("%-8.2f", totalScore * 1.0 / studentGradeBook[student].length);
            System.out.printf("%-6d", getStudentPosition(totalScore));
            System.out.println();
        }
    }

    private static int getStudentPosition(int totalScore) {
        for (int count = 0; count < studentPosition.length; count++) {
            if (studentPosition[count] == totalScore) {
                return count + 1;
            }
        }
        return 0;
    }

    private static void printSeparatorLine() {
        System.out.print("================================");
        for (int count = 0; count < studentGradeBook[0].length; count++) {
            System.out.print("========");
        }
        System.out.println();
    }

    private static void printHeading() {
        System.out.printf("%-14s", "STUDENT");
        for (int count = 0; count < studentGradeBook[0].length; count++) {
            System.out.printf("%-8s", "SUB" + (count + 1));
        }
        System.out.printf("%4s%7s%6s%n", "TOT", "AVE", "POS");
    }

    public static void printSubjectSummary() {
        addNewLine();
        int hardestSubject = 0;
        int easiestSubject = 0;
        int numberOfPasses = 0;
        int numberOfFails = 0;
        System.out.println("SUBJECT SUMMARY");
        for (int subject = 0; subject < studentGradeBook[0].length; subject++) {
            System.out.printf("""
                            Subject %d
                            Highest scoring student is: Student %d scoring %d
                            Lowest scoring student is: Student %d scoring %d
                            Total score is: %d
                            Average score is: %.2f
                            Number of passes: %d
                            Number of fails: %d
                            """, subject + 1, getHighestScoringStudent(subject), highestScores[subject], getLowestScoringStudent(subject),
                    lowestScores[subject], getTotalSubjectScore(subject), getTotalSubjectScore(subject) * 1.0 / studentGradeBook.length,
                    getNumberOfPasses(subject), getNumberOfFails(subject));
            if (getNumberOfPasses(subject) >= numberOfPasses) {
                if (getTotalSubjectScore(subject) > getTotalSubjectScore(easiestSubject)) {
                    numberOfPasses = getNumberOfPasses(subject);
                    easiestSubject = subject;
                }
            }
            if (getNumberOfFails(subject) >= numberOfFails) {
                if (getTotalSubjectScore(subject) < getTotalSubjectScore(easiestSubject)) {
                    numberOfFails = getNumberOfFails(subject);
                    hardestSubject = subject;
                }
            }
            System.out.println();
        }
        printGeneralSubjectSummary(easiestSubject, hardestSubject);
    }

    private static void printGeneralSubjectSummary(int easiestSubject, int hardestSubject) {

        System.out.printf("""
                        =============================================================================
                        The hardest subject is Subject %d with %d failure(s)
                        The easiest subject is Subject %d with %d passes(s)
                        The overall highest score is scored by Student %d in subject %d scoring %d
                        The overall lowest score is scored by Student %d in subject %d scoring %d
                        =============================================================================
                        """, hardestSubject + 1, getNumberOfFails(hardestSubject), easiestSubject + 1, getNumberOfPasses(easiestSubject),
                getOverallHighestScoringStudent(), getSubjectWithOverallHighestScore(getOverallHighestScore()), getOverallHighestScore(),
                getOverallLowestScoringStudent(), getSubjectWithOverallLowestScore(getOverallLowestScore()), getOverallLowestScore());
    }

    private static int getOverallHighestScoringStudent() {
        int overallHighestScore = 0;
        int overallHighestScoringStudent = 0;
        for (int studentNumber = 0; studentNumber < studentGradeBook.length; studentNumber++) {
            for (int score = 0; score < studentGradeBook[0].length; score++) {
                if (studentGradeBook[studentNumber][score] > overallHighestScore) {
                    overallHighestScore = studentGradeBook[studentNumber][score];
                    overallHighestScoringStudent = studentNumber;
                }
            }
        }
        return overallHighestScoringStudent + 1;
    }

    private static int getOverallLowestScoringStudent() {
        int overallLowestScore = 100;
        int overallLowestScoringStudent = 0;
        for (int studentNumber = 0; studentNumber < studentGradeBook.length; studentNumber++) {
            for (int score = 0; score < studentGradeBook[0].length; score++) {
                if (studentGradeBook[studentNumber][score] < overallLowestScore) {
                    overallLowestScore = studentGradeBook[studentNumber][score];
                    overallLowestScoringStudent = studentNumber;
                }
            }
        }
        return overallLowestScoringStudent + 1;
    }

    private static int getSubjectWithOverallHighestScore(int overallHighestScore) {
        int subjectWithOverallHighestScore = 0;
        for (int[] student : studentGradeBook) {
            for (int scoreCount = 0; scoreCount < student.length; scoreCount++) {
                if (overallHighestScore == student[scoreCount]) {
                    subjectWithOverallHighestScore = scoreCount;
                }
            }
        }
        return subjectWithOverallHighestScore + 1;
    }

    private static int getSubjectWithOverallLowestScore(int overallHighestScore) {
        int subjectWithOverallLowestScore = 0;
        for (int[] student : studentGradeBook) {
            for (int scoreCount = 0; scoreCount < student.length; scoreCount++) {
                if (overallHighestScore == student[scoreCount]) {
                    subjectWithOverallLowestScore = scoreCount;
                }
            }
        }
        return subjectWithOverallLowestScore + 1;
    }

    private static int getOverallHighestScore() {
        int overallHighestScore = 0;
        for (int[] student : studentGradeBook) {
            for (int score = 0; score < student.length; score++) {
                if (student[score] > overallHighestScore) {
                    overallHighestScore = student[score];
                }
            }
        }
        return overallHighestScore;
    }

    private static int getOverallLowestScore() {
        int overallLowestScore = 100;
        for (int[] student : studentGradeBook) {
            for (int count = 0; count < student.length; count++) {
                if (student[count] < overallLowestScore) {
                    overallLowestScore = student[count];
                }
            }
        }
        return overallLowestScore;
    }

    private static int getHighestScoringStudent(int subjectNumber) {
        int highestScore = 0;
        for (int[] student : studentGradeBook) {
            if (student[subjectNumber] > highestScore) {
                highestScore = student[subjectNumber];
                highestScores[subjectNumber] = student[subjectNumber];
            }
        }
        for (int count = 0; count < studentGradeBook.length; count++) {
            if (studentGradeBook[count][subjectNumber] == highestScore) {
                return count + 1;
            }
        }
        return 0;
    }

    private static int getLowestScoringStudent(int subjectNumber) {
        int lowestScore = 100;
        for (int[] student : studentGradeBook) {
            if (student[subjectNumber] < lowestScore) {
                lowestScore = student[subjectNumber];
                lowestScores[subjectNumber] = student[subjectNumber];
            }
        }
        for (int count = 0; count < studentGradeBook.length; count++) {
            if (studentGradeBook[count][subjectNumber] == lowestScore) {
                return count + 1;
            }
        }
        return 0;
    }

    private static int getTotalSubjectScore(int subjectNumber) {
        int total = 0;
        for (int[] student : studentGradeBook) {
            total += student[subjectNumber];
        }
        return total;
    }

    private static int getNumberOfPasses(int subjectNumber) {
        int numberOfPasses = 0;
        for (int[] student : studentGradeBook) {
            if (student[subjectNumber] >= 50) {
                numberOfPasses++;
            }
        }
        return numberOfPasses;
    }

    private static int getNumberOfFails(int subjectNumber) {
        int numberOfFails = 0;
        for (int[] student : studentGradeBook) {
            if (student[subjectNumber] < 50) {
                numberOfFails++;
            }
        }
        return numberOfFails;
    }
    public static void printClassSummary(){
        int classTotlScore = 0;
        int bestStudentTotalScore = 0;
        int bestGraduatingStudentIndex = 0;
        int worstStudentTotalScore = 0;
        int worstGraduatingStudentIndex = 0;
        for (int studentIndex = 0; studentIndex < studentGradeBook.length; studentIndex++){
            int totalStudentScore = 0;
            for (int subjectScoreCount = 0; subjectScoreCount < studentGradeBook[studentIndex].length; subjectScoreCount++){
                totalStudentScore+= studentGradeBook[studentIndex][subjectScoreCount];
                classTotlScore+= studentGradeBook[studentIndex][subjectScoreCount];
            }
            if (totalStudentScore > bestStudentTotalScore){
                bestStudentTotalScore = totalStudentScore;
                bestGraduatingStudentIndex = studentIndex;
            }
            else {
                worstStudentTotalScore = totalStudentScore;
                worstGraduatingStudentIndex = studentIndex;
            }
        }
        System.out.printf("""
                CLASS SUMMARY
                ====================================================
                Best Graduating Student is: Student %d scoring %d
                ====================================================
                
                !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                Worst Graduating Student is: Student %d scoring %d
                !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                
                ====================================================
                Class total score is: %d
                Class average score is: %.2f
                """, bestGraduatingStudentIndex, bestStudentTotalScore, worstGraduatingStudentIndex, worstStudentTotalScore,
                classTotlScore, classTotlScore*1.0/studentGradeBook.length);
    }

    private static void load(String message) throws InterruptedException {
        System.out.print(message);
        for (int count = 1; count <= 5; count++) {
            System.out.print(">");
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println();
    }

    private static void addNewLine() {
        System.out.println("""          
                                
                        
                        
                        
                        
                                
                                
                                
                                
                                
                """);
    }
}
