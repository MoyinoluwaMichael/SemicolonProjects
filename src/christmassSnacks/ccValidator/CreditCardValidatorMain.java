package christmassSnacks.ccValidator;

import java.util.Scanner;

public class CreditCardValidatorMain {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        CreditCardValidator ccValidator = new CreditCardValidator();
        System.out.println("Hello, kindly enter card details to verify");
        String cardNumber = scanner.nextLine();

        ccValidator.confirmValidity(cardNumber);
    }
}
