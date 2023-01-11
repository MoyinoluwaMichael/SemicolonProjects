package christmassSnacks.myersBriggsQuestionnaire;

public class MyersBriggsQuestionnaireMain {
    public static void main(String[] args) throws InterruptedException {
        MyersBriggsQuestionnaire newPersonality = new MyersBriggsQuestionnaire();

        newPersonality.setCandidateName();
        MyersBriggsQuestionnaire.displayQuestions();
        MyersBriggsQuestionnaire.analyzeResponses();
        newPersonality.printPersonalityTrait();
    }
}
