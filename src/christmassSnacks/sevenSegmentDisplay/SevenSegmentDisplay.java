package christmassSnacks.sevenSegmentDisplay;

import java.util.Arrays;
import java.util.Scanner;

public class SevenSegmentDisplay {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String[] light = new String[7];

    public static void display(){
        System.out.println("Enter binary numbers:");
        String binaryNumbers = scanner.nextLine();
        validateArg(binaryNumbers);
        setLight(binaryNumbers);
        displayLight();
    }

    private static void displayLight() {
        System.out.printf("""
                %10s
                %2s%10s
                %2s%10s
                %10s
                %2s%10s
                %2s%10s
                %10s               
                """, light[0], light[5],light[1],light[5],light[1],light[6],light[4],light[2],light[4],light[2],
                light[3]);
    }

    private static void setLight(String binaryNumbers) {
        if (String.valueOf(binaryNumbers.charAt(7)).equals("1")){
            for (int count = 0; count < light.length; count++){
                String index = String.valueOf(binaryNumbers.charAt(count));
                if (index.equals("1")){
                    light[count] = count%3==0 ? "# # # #" : "#";
                }
                else {
                    light[count] = "";
                }
            }
        }
        else {
            Arrays.fill(light, "");
        }
    }

    private static void validateArg(String binaryNumbers) {
        while (binaryNumbers.length() != 8){
            System.out.println("Binary numbers must be of length 8. Please try again.");
            binaryNumbers = scanner.nextLine();
        }
        String indexContainingError = "";
        for (int count = 0; count < binaryNumbers.length(); count++){
            String index = String.valueOf(binaryNumbers.charAt(count));
            if (!index.equals("1") && !index.equals("0")){
                indexContainingError += count;
            }
        }
        while (!indexContainingError.equals("")){
            System.out.printf("%d errors found. Binary numbers must be 0's and 1's.", indexContainingError.length());
            binaryNumbers = scanner.nextLine();
            validateArg(binaryNumbers);
        }
    }

}
