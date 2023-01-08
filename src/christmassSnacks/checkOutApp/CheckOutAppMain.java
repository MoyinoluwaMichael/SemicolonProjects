package christmassSnacks.checkOutApp;


public class CheckOutAppMain {
    public static void main(String[] args) throws InterruptedException {

        CheckOutApp newInvoice = new CheckOutApp();

        newInvoice.registerCustomer();
        do {
            newInvoice.computeSales();
        }
        while (newInvoice.confirmMoreSales().equalsIgnoreCase("yes"));
        newInvoice.registerCashier();
        newInvoice.prepareInvoice();
        newInvoice.calculateCustomerBalance();


    }
}
