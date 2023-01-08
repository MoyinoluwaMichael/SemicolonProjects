package christmassSnacks.ccValidator;

import java.util.concurrent.TimeUnit;

public class CreditCardValidator {

    String creditCardType;
    private int[] creditCardNumber;
    private String creditCardValidityStatus;

    public void confirmValidity(String cardNumber) throws InterruptedException {
        setCreditCardNumber(cardNumber);
        setCreditCardType();
        verifyCreditCard();
        load("Validating card");
        addNewLine();
        System.out.printf("""
                *******************************************
                **Credit Card Type: %s
                **Credit Card Number: %s
                **Credit Card Digit Length: %d
                **Credit Card Validity Status: %s
                *******************************************
                """, creditCardType, cardNumber, creditCardNumber.length, creditCardValidityStatus);
    }


    private void setCreditCardNumber(String cardNumber) {
        creditCardNumber = new int[cardNumber.length()];
        for (int count = 0; count < cardNumber.length(); count++) {
                creditCardNumber[count] = Integer.parseInt(String.valueOf(cardNumber.charAt(count)));
        }
    }

    private void setCreditCardType() {
        if (creditCardNumber.length >= 13 && creditCardNumber.length <= 16) {
            switch (creditCardNumber[0]) {
                case 4 -> {
                    creditCardType = "Visa Card";
                }
                case 5 -> {
                    creditCardType = "Master Card";
                }
                case 3 -> {
                    creditCardType = "American Express Card";
                }
                case 6 -> {
                    creditCardType = "Discover Card";
                }
                default -> {
                    creditCardType = "Invalid Card";
                }
            }
        } else {
            creditCardType = "Invalid Card";
        }
    }

    private void verifyCreditCard() {
        int sum = doubleSecondDigit() + addDigitsInOddIndex();
        if (sum % 10 == 0) {
            creditCardValidityStatus = "Valid";
        } else {
            creditCardValidityStatus = "Invalid";
        }
    }

    private int doubleSecondDigit() {
        int total = 0;
        for (int count = creditCardNumber.length - 2; count >= 0; count -= 2) {
            int doubleDigit = creditCardNumber[count] * 2;
            if (doubleDigit > 9) {
                doubleDigit =(doubleDigit/10) + (doubleDigit%10);
            }
            total += doubleDigit;
        }
        return total;
    }

    private int addDigitsInOddIndex() {
        int total = 0;
        for (int count = creditCardNumber.length - 1; count >= 0; count -= 2) {
            total += creditCardNumber[count];
        }
        return total;
    }

    private static void load(String message) throws InterruptedException {
        System.out.print(message);
        for (int count = 1; count <= 3; count++) {
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
        }
    }

    private static void addNewLine() {
        System.out.println("""
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                """);
    }


}
