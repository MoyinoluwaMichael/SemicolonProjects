package christmassSnacks.checkOutApp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CheckOutApp {
    Scanner scanner = new Scanner(System.in);
    private String customerName;
    private String cashierName;
    private double discount;
    private double subTotal;
    private ArrayList<String> items = new ArrayList<>();
    private ArrayList<Integer> quantity = new ArrayList<>();
    private ArrayList<Double> price = new ArrayList<>();
    private double vat;
    private double billTotaL;
    private double discountRate;


    public void registerCustomer() {
        System.out.println("What is the customer's name?");
        customerName = scanner.nextLine();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void registerCashier() {
        System.out.println("What is your name?");
        cashierName = scanner.next();
    }

    public String getCashierName() {
        return cashierName;
    }

    public void computeSales() {
        System.out.println("What did the customer buy?");
        String newItem = scanner.next();
        items.add(newItem);
        System.out.println("How many pieces?");
        int newQuantity = scanner.nextInt();
        quantity.add(newQuantity);
        System.out.println("How much per unit?");
        double newPrice = scanner.nextDouble();
        price.add(newPrice);
    }

    public String confirmMoreSales() {
        System.out.println("Add more items?");
        return scanner.next();
    }

    public void prepareInvoice() throws InterruptedException {
        System.out.println("How much discount will he get?");
        discountRate = scanner.nextDouble();
        load("Preparing invoice");
        addNewLine();
        calculateSales();
        System.out.printf("""
                ========================================================
                THIS IS NOT A RECEIPT KINDLY PAY %.2f
                ========================================================
                """, billTotaL);
    }

    private void analyzeSales() {
        int subTotal = 0;
        for (int count = 0; count < items.size(); count++) {
            double total = quantity.get(count) * price.get(count);
            subTotal += total;
            System.out.printf("%20s%8d%12.2f%15.2f%n", items.get(count), quantity.get(count), price.get(count), total);
        }
        this.subTotal = subTotal;
        calculateDiscount(discountRate);
        calculateVat();
        calculateBillTotal();
    }

    private void calculateBillTotal() {
        billTotaL = subTotal - discount + vat;
    }

    public void calculateDiscount(double discountRate) {
        discount = (discountRate / 100) * subTotal;
    }

    private void calculateVat() {
        vat = (7.5 / 100) * subTotal;
    }


    public void calculateCustomerBalance() throws InterruptedException {
        System.out.println("How much did the customer pay?");
        double amountPaid = scanner.nextDouble();
        load("Preparing Invoice");
        addNewLine();
        calculateSales();
        System.out.printf("""
                                                   Amount paid: %15.2f
                                                       Balance: %15.2f
                        ========================================================
                                       THANK YOU FOR YOUR PATRONAGE
                        ========================================================""",
                amountPaid, amountPaid - billTotaL);
    }

    private static void load(String message) throws InterruptedException {
        System.out.print(message);
        for (int count = 1; count <= 3; count++) {
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
        }
    }

    private void calculateSales() {
        System.out.printf("""
                SEMICOLON STORES
                MAIN BRANCH
                LOCATION: 312, HEBERT MACAULAY WAY, SABO YABA, LAGOS.
                TEL: 03293828343
                Date: %s
                Cashier Name: %s
                Customer Name: %s
                ========================================================
                                  ITEM     QTY    PRICE       TOTAL(NGN)
                --------------------------------------------------------            
                               """, LocalDateTime.now(), getCashierName(), getCustomerName());
        analyzeSales();
        System.out.printf("""
                --------------------------------------------------------
                                             Sub Total: %15.2f
                                              Discount: %15.2f
                                           VAT @ 7.50%s: %15.2f
                ========================================================
                                            Bill Total: %15.2f
                """, subTotal, discount, "%", vat, billTotaL);
    }

    private static void addNewLine() {
        System.out.println("""
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                """);
    }
}