package christmassSnacks.myersBriggsQuestionnaire;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MyersBriggsQuestionnaire {
    static Scanner scanner = new Scanner(System.in);
    private static String candidateName;
    private static String candidatePersonalityTrait = "";
    private static final String[][] questions = {
            {"expend energy, enjoy groups", "conserve energy, enjoy one-on-one"},
            {"interpret literally", "look for meaning and possibilities"},
            {"logical, thinking, questioning", "empathetic, feeling, accommodating"},
            {"organized, orderly", "flexible, adaptable"},
            {"more outgoing, think out loud", "more reserved, think to yourself"},
            {"practical, realistic, experiential", "imaginative, innovative, theoretical"},
            {"candid, straight forward, frank", "tactful, kind, encouraging"},
            {"plan, schedule", "unplanned, spontaneous"},
            {"seek many tasks, public activities, interaction with others", "seek private, solitary activities with quiet to concentrate"},
            {"standard, usual, conventional", "different, novel, unique"},
            {"firm, tend to criticize, hold the line", "gentle, tend to appreciate, conciliate"},
            {"regulated, structured", "easygoing, \"live\" and \"let live\""},
            {"external, communicative, express yourself", "internal, reticent, keep to yourself"},
            {"focus on here and now", "look to the future, global perspective, \"big picture\""},
            {"tough minded, just", "tender-hearted, merciful"},
            {"preparation, plan ahead", "go with the flow, adapt as you go"},
            {"active, initiate", "reflective, deliberate"},
            {"facts, things, \"what is\"", "ideas, dreams, \"what could be\", philosophical"},
            {"matter of fact, issue oriented", "sensitive, people-oriented, compassionate"},
            {"control, govern", "latitude, freedom"}};
    private static final String[][] personalityTraits = {
            {"Extroverted", "Introverted"},
            {"Sensing", "Intuitive"},
            {"Thinking", "Feeling"},
            {"Judging", "Perceptive"}};
    private static final String[] responses = new String[20];

    public void setCandidateName() {
        System.out.println("What is your name?");
        candidateName = scanner.nextLine();
    }

    public static void displayQuestions() {
        String[] questionIndex = {"A", "B"};
        for (int row = 0; row < 20; row++) {
            addNewLine();
            System.out.print(row + 1 + ".\t");
            for (int column = 0; column < 2; column++) {
                System.out.printf(questionIndex[column] + ". %1s%20s", questions[row][column], "");
            }
            System.out.println();
            takeResponse(row);
        }
    }


    private static void takeResponse(int questionNumber) {
        String response = scanner.nextLine();
        while (!response.equalsIgnoreCase("A") && !response.equalsIgnoreCase("B")) {
            displayErrorMessage();
            response = scanner.nextLine();
        }
        responses[questionNumber] = response;
    }

    public static void analyzeResponses() throws InterruptedException {
        load("Analyzing Responses");
        int numberOfAs = 0;
        int numberOfBs = 0;
        int questionIndex;
        System.out.println("Dear " + candidateName + ", you selected: ");
        for (int counter = 0; counter < 4; counter++) {
            load("Part "+(counter+1)+" loading");
            addNewLine();
            for (int row = counter; row < 20; row += 4) {
                System.out.print(row + 1 + ".\t");
                if (responses[row].equalsIgnoreCase("A")) {
                    numberOfAs++;
                    questionIndex = 0;
                } else {
                    numberOfBs++;
                    questionIndex = 1;
                }
                System.out.println(responses[row].toUpperCase() + ". " + questions[row][questionIndex]);
            }
            calculateNumberOfAsAndBs(numberOfAs, numberOfBs);
            if (numberOfAs > numberOfBs) {
                displayResponseConnotation("A", counter);
            } else {
                displayResponseConnotation("B", counter);
            }
            numberOfAs = 0;
            numberOfBs = 0;
        }
        System.out.println();
    }

    private static void calculateNumberOfAsAndBs(int numberOfAs, int numberOfBs) {
        System.out.printf("""
                Number of A selected: [%d] | Number of B selected: [%d]               
                """, numberOfAs, numberOfBs);
    }

    private static void displayResponseConnotation(String maxResponse, int row) {
        for (String each:personalityTraits[row]){
            System.out.print("\t\t"+each+"["+each.charAt(0)+"]"+"\t\t");
        }
        int personalityTraitIndex;
        if (maxResponse.equalsIgnoreCase("A")){
            personalityTraitIndex = 0;
        }else {
            personalityTraitIndex = 1;
        }
        if (personalityTraits[row][personalityTraitIndex].equalsIgnoreCase("Intuitive")){
            System.out.println("\nTrait is "+"\""+"N"+"\"");
            candidatePersonalityTrait += "N";
        }
        else {
            System.out.println("\nTrait is "+"\""+personalityTraits[row][personalityTraitIndex].charAt(0)+"\"");
            candidatePersonalityTrait += (personalityTraits[row][personalityTraitIndex].charAt(0));
        }
    }
    public void printPersonalityTrait() throws InterruptedException {
        load("Retrieving personality trait");
        System.out.println();
        System.out.println("Your personality trait is " + candidatePersonalityTrait);
        switch (candidatePersonalityTrait){
            case "INTJ" -> {
                PersonalityTypes.getINTJ();
            }
            case "INTP" -> {
                PersonalityTypes.getINTP();
            }
            case "ENTJ" -> {
                PersonalityTypes.getENTJ();
            }
            case "ENTP" -> {
                PersonalityTypes.getENTP();
            }
            case "INFJ" -> {
                PersonalityTypes.getINFJ();
            }
            case "INFP" -> {
                PersonalityTypes.getINFP();
            }
            case "ENFJ" -> {
                PersonalityTypes.getENFJ();
            }
            case "ENFP" -> {
                PersonalityTypes.getENFP();
            }
            case "ISTJ" -> {
                PersonalityTypes.getISTJ();
            }
            case "ISFJ" -> {
                PersonalityTypes.getISFJ();
            }
            case "ESTJ" -> {
                PersonalityTypes.getESTJ();
            }
            case "ESFJ" -> {
                PersonalityTypes.getESFJ();
            }
            case "ISTP" -> {
                PersonalityTypes.getISTP();
            }
            case "ISFP" -> {
                PersonalityTypes.getISFP();
            }
            case "ESTP" -> {
                PersonalityTypes.getESTP();
            }
            case "ESFP" -> {
                PersonalityTypes.getESFP();
            }
        }
    }


    private static void displayErrorMessage() {
        System.out.println("""                
                Expected A or B as Response
                I know this is an error. Please try again.""");
    }
    private static void load(String message) throws InterruptedException {
        System.out.print(message);
        for (int count = 1; count <= 5; count++){
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
        }
    }
    private static void addNewLine() {
        System.out.println("""
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                """);
    }
}

