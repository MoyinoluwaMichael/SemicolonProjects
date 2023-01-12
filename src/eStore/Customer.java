package eStore;

import java.util.List;

public class Customer extends User {

    private String receiverPhoneNumber;
    private String receiverName;
    private Address deliveryAddress;
    private CreditCard creditCard;
    private List <Item> shoppingCart;
}
