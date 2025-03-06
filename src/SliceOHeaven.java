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
    private static final long BLACKLISTED_NUMBER = 21234567890876L;

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
        Scanner scanner = new Scanner(System.in); 
            String[] ingredients = {"Mushroom", "Paprika", "Sun-dried tomatoes", "Chicken", "Pineapple"};
            int ingChoice1, ingChoice2, ingChoice3;
            String ing1 = "", ing2 = "", ing3 = "";

            while (true) {
                System.out.println("Please pick any three of the following ingredients:\n1. Mushroom\n2. Paprika\n3. Sun-dried tomatoes\n4. Chicken\n5. Pineapple\nEnter any three choices (1, 2, 3,…) separated by spaces:");
                ingChoice1 = scanner.nextInt();
                ingChoice2 = scanner.nextInt();
                ingChoice3 = scanner.nextInt();

                if (ingChoice1 >= 1 && ingChoice1 <= 5 && ingChoice2 >= 1 && ingChoice2 <= 5 && ingChoice3 >= 1 && ingChoice3 <= 5) {
                    ing1 = ingredients[ingChoice1 - 1];
                    ing2 = ingredients[ingChoice2 - 1];
                    ing3 = ingredients[ingChoice3 - 1];
                    break;
                } else {
                    System.out.println("Invalid choice(s). Please pick only from the given list:");
                }
            }

            String orderDetails = ing1 + ", " + ing2 + ", " + ing3;

            int sizeChoice;
            String pizzaSize = "";
            while (true) {
                System.out.println("What size should your pizza be?\n1. Large\n2. Medium\n3. Small\nEnter only one choice (1, 2, or 3):");
                sizeChoice = scanner.nextInt();

                switch (sizeChoice) {
                    case 1:
                        pizzaSize = "Large";
                        break;
                    case 2:
                        pizzaSize = "Medium";
                        break;
                    case 3:
                        pizzaSize = "Small";
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                        continue;
                }
                break;
            }

            System.out.println("Do you want extra cheese (Y/N):");
            String extraCheese = scanner.next();

            int sideDishChoice;
            String sideDish = "";
            while (true) {
                System.out.println("Following are the side dish that go well with your pizza:\n1. Calzone\n2. Garlic bread\n3. Chicken puff\n4. Muffin\n5. Nothing for me\nWhat would you like? Pick one (1, 2, 3,…):");
                sideDishChoice = scanner.nextInt();

                switch (sideDishChoice) {
                    case 1:
                        sideDish = "Calzone";
                        break;
                    case 2:
                        sideDish = "Garlic bread";
                        break;
                    case 3:
                        sideDish = "Chicken puff";
                        break;
                    case 4:
                        sideDish = "Muffin";
                        break;
                    case 5:
                        sideDish = "Nothing for me";
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                        continue;
                }
                break;
            }

            int drinkChoice;
            String drinks = "";
            while (true) {
                System.out.println("Choose from one of the drinks below. We recommend Coca Cola:\n1. Coca Cola\n2. Cold coffee\n3. Cocoa Drink\n4. No drinks for me\nEnter your choice:");
                drinkChoice = scanner.nextInt();

                switch (drinkChoice) {
                    case 1:
                        drinks = "Coca Cola";
                        break;
                    case 2:
                        drinks = "Cold coffee";
                        break;
                    case 3:
                        drinks = "Cocoa Drink";
                        break;
                    case 4:
                        drinks = "No drinks for me";
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                        continue;
                }
                break;
            }

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
            System.out.println(this);
        
        scanner.close();
    }

    private void isItYourBirthday() {
        Scanner scanner = new Scanner(System.in); 
            while (true) {
                System.out.println("Enter your birthday (yyyy-MM-dd):");
                String birthdateStr = scanner.next();
                LocalDate birthdate = LocalDate.parse(birthdateStr, DateTimeFormatter.ISO_LOCAL_DATE);
                LocalDate today = LocalDate.now();
                int age = Period.between(birthdate, today).getYears();

                if (age < 5 || age > 120) {
                    System.out.println("Invalid date. You are either too young or too dead to order. Please enter a valid date:");
                } else {
                    if (age < 18 && birthdate.getMonth() == today.getMonth() && birthdate.getDayOfMonth() == today.getDayOfMonth()) {
                        System.out.println("Congratulations! You pay only half the price for your order");
                        this.orderTotal /= 2;
                    } else {
                        System.out.println("Too bad! You do not meet the conditions to get our 50% discount");
                    }
                    break;
                }
            } 
        scanner.close();
    }

    private void makeCardPayment() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your card number:");
        long cardNumber = scanner.nextLong();

        String expiryDate;
        while (true) {
            System.out.println("Enter the card's expiry date (yyyy-MM):");
            expiryDate = scanner.next();
            LocalDate expiry = LocalDate.parse(expiryDate + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (expiry.isBefore(LocalDate.now())) {
                System.out.println("Invalid expiry date. Please enter a future date:");
            } else {
                break;
            }
        }

        System.out.println("Enter the card's CVV:");
        int cvv = scanner.nextInt();

            processCardPayment(cardNumber, expiryDate, cvv);
            scanner.close();
        }
       

    public void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String cardNumberStr = Long.toString(cardNumber);
            int cardLength = cardNumberStr.length();
            if (cardLength == 14 && cardNumber != BLACKLISTED_NUMBER) {
                break;
            } else {
                System.out.println("Invalid card number. Please enter a valid 14-digit card number:");
                cardNumber = scanner.nextLong();
            }
        }

        String cardNumberStr = Long.toString(cardNumber);
        int firstCardDigit = Integer.parseInt(cardNumberStr.substring(0, 1));
        int lastFourDigits = Integer.parseInt(cardNumberStr.substring(cardNumberStr.length() - 4));
        String cardNumberToDisplay = cardNumberStr.charAt(0) + "*".repeat(cardNumberStr.length() - 5) + cardNumberStr.substring(cardNumberStr.length() - 4);

        System.out.println("Card Number: " + cardNumberToDisplay);
        System.out.println("First Digit: " + firstCardDigit);
        System.out.println("Last Four Digits: " + lastFourDigits);
        System.out.println("Expiry Date: " + expiryDate);
        System.out.println("CVV: " + cvv);
        scanner.close();
    }

    private void makePizza() {
        System.out.println("Making pizza...");
        System.out.println("Baking pizza...");
        System.out.println("Pizza ready for delivery!");
    }

    @Override
    public String toString() {
        return "\n======== RECEIPT ========\n" +
                "Order ID: " + orderID + "\n" +
                "Items Ordered:\n  " + orderDetails.replaceAll(", ", "\n  ") + "\n" +
                "------------------------\n" +
                String.format("Total: $%.2f\n", orderTotal) +
                "Thank you for choosing " + storeName + "!\n" +
                "========================";
    }

    public void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, String specialPrice) {
        StringBuilder special = new StringBuilder();
        special.append("Pizza of the Day: ").append(pizzaOfTheDay).append("\n");
        special.append("Side of the Day: ").append(sideOfTheDay).append("\n");
        special.append("Special Price: ").append(specialPrice).append("\n");
        System.out.println(special.toString());
    }
    public static void main(String[] args) {
        SliceOHeaven sliceOHeaven = new SliceOHeaven();
        sliceOHeaven.takeOrder();
    }
}