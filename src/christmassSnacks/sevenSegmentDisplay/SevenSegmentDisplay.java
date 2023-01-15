package christmassSnacks.sevenSegmentDisplay;

import com.sun.source.tree.PatternTree;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SevenSegmentDisplay {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String[] light = new String[7];

    public static void display() {
        System.out.println("Enter binary numbers:");
        String binaryNumbers = scanner.nextLine();
        SevenSegmentValidation.validateArg(binaryNumbers);
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
                """, light[0], light[5], light[1], light[5], light[1], light[6], light[4], light[2], light[4], light[2], light[3]);
    }

    private static void setLight(String binaryNumbers) {
        boolean isOn = String.valueOf(binaryNumbers.charAt(7)).equals("1");
        if (isOn) {
            setOn(binaryNumbers);
        } else {
            Arrays.fill(light, "");
        }
    }

    private static void setOn(String binaryNumbers) {
        for (int count = 0; count < light.length; count++) {
            String index = String.valueOf(binaryNumbers.charAt(count));
            if (index.equals("1")) {
                light[count] = count % 3 == 0 ? "# # # #" : "#";
            } else {
                light[count] = "";
            }
        }
    }
}
