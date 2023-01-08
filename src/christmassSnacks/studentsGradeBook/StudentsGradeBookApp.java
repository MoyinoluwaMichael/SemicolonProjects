package christmassSnacks.studentsGradeBook;

public class StudentsGradeBookApp {
    public static void main(String[] args) throws InterruptedException {

        StudentsGradeBook.collectData();
        StudentsGradeBook.processStudentGrades();
        StudentsGradeBook.printStudentResult();
        StudentsGradeBook.printSubjectSummary();
        StudentsGradeBook.printClassSummary();
    }
}
