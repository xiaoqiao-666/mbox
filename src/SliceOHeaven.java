import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

public class SliceOHeaven {
    public String storeName = "Slice-o-Heaven";
    public String storeAddress = "666 Pizza Avenue, Cheese City";
    public String storeEmail = "order@sliceoheaven.com";
    public String storePhone = "(666) 888-9999";

    private String DEF_ORDER_ID = "DEF-SOH-099";
    private String DEF_PIZZA_INGREDIENTS = "Mozzarella Cheese";
    private double DEF_ORDER_TOTAL = 15.00;

    private String orderID;
    private String orderDetails;
    private double orderTotal;
    private double calculateTotal(String orderDetails) {
        return 15.00;
    }

    public SliceOHeaven() {
        this.orderID = DEF_ORDER_ID;
        this.orderDetails = DEF_PIZZA_INGREDIENTS;
        this.orderTotal = DEF_ORDER_TOTAL;

        System.out.println("Welcome to " + storeName + "!");
    }

    public SliceOHeaven(String orderID, String orderDetails, double orderTotal) {
        this.orderID = orderID;
        this.orderDetails = orderDetails;
        this.orderTotal = orderTotal;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public void takeOrder() {
        try (Scanner scanner = new Scanner(System.in)) {

        System.out.println("Enter three ingredients for your pizza (use spaces to separate ingredients):");
        String ing1 = scanner.next();
        String ing2 = scanner.next();
        String ing3 = scanner.next();
        String orderDetails = ing1 + ", " + ing2 + ", " + ing3;

        System.out.println("Enter size of pizza (Small, Medium, Large):");
        String pizzaSize = scanner.next();

        System.out.println("Do you want extra cheese (Y/N):");
        String extraCheese = scanner.next();

        System.out.println("Enter one side dish (Calzone, Garlic bread, None):");
        String sideDish = scanner.next();

        System.out.println("Enter drinks (Cold Coffee, Cocoa drink, Coke, None):");
        String drinks = scanner.next();

        System.out.println("Would you like the chance to pay only half for your order? (Y/N):");
        String wantDiscount = scanner.next();

        this.orderDetails = orderDetails + ", " + pizzaSize + ", " + extraCheese + ", " + sideDish + ", " + drinks;
        this.orderTotal = calculateTotal(orderDetails);

        if (wantDiscount.equalsIgnoreCase("Y")) {
            isItYourBirthday();
        } else {
            makeCardPayment();
        }

        System.out.println("Order accepted for: \"" + orderDetails + "\"");
        makePizza();
        printReceipt();
    }
}
    private void isItYourBirthday() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter your birthday (yyyy-MM-dd):");
            String birthdateStr = scanner.next();
            LocalDate birthdate = LocalDate.parse(birthdateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate today = LocalDate.now();
            int age = Period.between(birthdate, today).getYears();

            if (age < 18 && birthdate.getMonth() == today.getMonth() && birthdate.getDayOfMonth() == today.getDayOfMonth()) {
                System.out.println("Congratulations! You pay only half the price for your order");
                this.orderTotal /= 2;
            } else {
                System.out.println("Too bad! You do not meet the conditions to get our 50% discount");
            }
        }
    }

    private void makeCardPayment() {
        Scanner scanner = new Scanner(System.in) ;

        System.out.println("Enter your card number:");
        long cardNumber = scanner.nextLong();

        System.out.println("Enter the card's expiry date (yyyy-MM):");
        String expiryDate = scanner.next();

        System.out.println("Enter the card's CVV:");
        int cvv = scanner.nextInt();

        processCardPayment(cardNumber, expiryDate, cvv);
        scanner.close();
    }

    public void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        String cardNumberStr = Long.toString(cardNumber);
        int cardLength = cardNumberStr.length();
        if (cardLength == 14) {
            System.out.println("Card accepted");
        } else {
            System.out.println("Invalid card");
            return;
        }

        int firstCardDigit = Integer.parseInt(cardNumberStr.substring(0, 1));
        long blacklistedNumber = 21234567890876L;
        if (cardNumber == blacklistedNumber) {
            System.out.println("Card is blacklisted. Please use another card");
            return;
        }

        int lastFourDigits = Integer.parseInt(cardNumberStr.substring(cardLength - 4));
        String cardNumberToDisplay = cardNumberStr.charAt(0) + "*".repeat(cardLength - 5) + cardNumberStr.substring(cardLength - 4);

        System.out.println("Card Number: " + cardNumberToDisplay);
        System.out.println("First Digit: " + firstCardDigit);
        System.out.println("Last Four Digits: " + lastFourDigits);
        System.out.println("Expiry Date: " + expiryDate);
        System.out.println("CVV: " + cvv);
    }

    private void makePizza() {
        System.out.println("Making pizza...");
        System.out.println("Baking pizza...");
        System.out.println("Pizza ready for delivery!");
    }

    private void printReceipt() {
        System.out.println("\n======== RECEIPT ========");
        System.out.println("Order ID: " + orderID);
        System.out.println("Items Ordered:");
        System.out.println("  " + orderDetails.replaceAll(", ", "\n  "));
        System.out.println("------------------------");
        System.out.printf("Total: $%.2f\n", orderTotal);
        System.out.println("Thank you for choosing " + storeName + "!");
        System.out.println("========================");
    }

    public void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, String specialPrice) {
        StringBuilder special = new StringBuilder();
        special.append("Pizza of the Day: ").append(pizzaOfTheDay).append("\n");
        special.append("Side of the Day: ").append(sideOfTheDay).append("\n");
        special.append("Special Price: ").append(specialPrice).append("\n");
        System.out.println(special.toString());
    }
}