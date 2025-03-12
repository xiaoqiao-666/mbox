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
    private static final double PIZZA_BASE_PRICE = 10.0;

    private String[] pizzasOrdered = new String[10];
    private String[] pizzaSizesOrdered = new String[10];
    private String[] sideDishesOrdered = new String[20];
    private String[] drinksOrdered = new String[20];
    private int orderCount = 0;

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
        double totalOrderPrice = 0.0;

        while (true) {
            System.out.println("Welcome to Slice-o-Heaven Pizzeria. Here’s what we serve:");
            for (PizzaSelection pizza : PizzaSelection.values()) {
                System.out.println(pizza);
            }
            System.out.println("6. Custom Pizza with a maximum of 10 toppings that you choose");
            System.out.println("Please enter your choice (1 - 6):");

            int pizzaChoice = scanner.nextInt();
            String pizzaDescription = "";
            double pizzaPrice = 0.0;

            if (pizzaChoice >= 1 && pizzaChoice <= 5) {
                PizzaSelection selectedPizza = PizzaSelection.values()[pizzaChoice - 1];
                pizzaDescription = selectedPizza.getPizzaName() + " with " + selectedPizza.getPizzaToppings();
                pizzaPrice = selectedPizza.getPrice();
            } else if (pizzaChoice == 6) {
                System.out.println("Please pick up to 10 toppings from the following list:");
                for (PizzaToppings topping : PizzaToppings.values()) {
                    System.out.println(topping);
                }
                scanner.nextLine(); // consume newline
                String[] toppings = scanner.nextLine().split(" ");
                StringBuilder customPizzaDescription = new StringBuilder("Custom Pizza with ");
                pizzaPrice = PIZZA_BASE_PRICE;
                for (String toppingChoice : toppings) {
                    int toppingIndex = Integer.parseInt(toppingChoice) - 1;
                    PizzaToppings selectedTopping = PizzaToppings.values()[toppingIndex];
                    customPizzaDescription.append(selectedTopping.getTopping()).append(", ");
                    pizzaPrice += selectedTopping.getToppingPrice();
                }
                pizzaDescription = customPizzaDescription.toString();
                pizzaDescription = pizzaDescription.substring(0, pizzaDescription.length() - 2); // remove last comma
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                continue;
            }

            pizzasOrdered[orderCount] = pizzaDescription + ": €" + pizzaPrice;
            totalOrderPrice += pizzaPrice;

            System.out.println("What size should your pizza be?\n1. Large\n2. Medium\n3. Small\nEnter only one choice (1, 2, or 3):");
            int sizeChoice = scanner.nextInt();
            PizzaSize pizzaSize = PizzaSize.values()[sizeChoice - 1];
            pizzaSizesOrdered[orderCount] = pizzaSize.getPizzaSize();
            totalOrderPrice += pizzaSize.getAddToPizzaPrice();

            System.out.println("Do you want extra cheese (Y/N):");
            String extraCheese = scanner.next();
            if (extraCheese.equalsIgnoreCase("Y")) {
                totalOrderPrice += 1.0; // assuming extra cheese costs 1.0
            }

            System.out.println("Following are the side dish that go well with your pizza:\n1. Calzone\n2. Garlic bread\n3. Chicken puff\n4. Muffin\n5. Nothing for me\nWhat would you like? Pick one (1, 2, 3,…):");
            int sideDishChoice = scanner.nextInt();
            SideDish sideDish = SideDish.values()[sideDishChoice - 1];
            sideDishesOrdered[orderCount] = sideDish.getSideDishName();
            totalOrderPrice += sideDish.getAddToPizzaPrice();

            System.out.println("Choose from one of the drinks below. We recommend Coca Cola:\n1. Coca Cola\n2. Cold coffee\n3. Cocoa Drink\n4. No drinks for me\nEnter your choice:");
            int drinkChoice = scanner.nextInt();
            Drinks drink = Drinks.values()[drinkChoice - 1];
            drinksOrdered[orderCount] = drink.getDrinkName();
            totalOrderPrice += drink.getAddToPizzaPrice();

            orderCount++;

            System.out.println("Would you like to order more? (Y/N):");
            String moreOrder = scanner.next();
            if (!moreOrder.equalsIgnoreCase("Y")) {
                break;
            }
        }

        this.orderTotal = totalOrderPrice;
        isItYourBirthday(scanner);
        makeCardPayment(scanner);
        makePizza();
        System.out.println("Order accepted for: \"" + orderDetails + "\"");
        System.out.println(this);
        scanner.close();
    }

    private void isItYourBirthday(Scanner scanner) {
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
                    System.out.println(" You pay only half the price for your order");
                    this.orderTotal /= 2;
                } else {
                    System.out.println(" You do not meet the conditions to get our 50% discount");
                }
                break;
            }
        }
    }

    private void makeCardPayment(Scanner scanner) {
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

        processCardPayment(cardNumber, expiryDate, cvv, scanner);
    }

    public void processCardPayment(long cardNumber, String expiryDate, int cvv, Scanner scanner) {
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
    }

    private void makePizza() {
        System.out.println("Making pizza...");
        System.out.println("Baking pizza...");
        System.out.println("Pizza ready for delivery!");
    }

    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder("\n======== RECEIPT ========\n");
        receipt.append("Order ID: ").append(orderID).append("\n");
        for (int i = 0; i < orderCount; i++) {
            receipt.append(i + 1).append(". ").append(pizzasOrdered[i]).append("\n");
            receipt.append("   Size: ").append(pizzaSizesOrdered[i]).append("\n");
            receipt.append("   Side Dish: ").append(sideDishesOrdered[i]).append("\n");
            receipt.append("   Drink: ").append(drinksOrdered[i]).append("\n");
        }
        receipt.append("------------------------\n");
        receipt.append(String.format("Total: €%.2f\n", orderTotal));
        receipt.append("Thank you for choosing ").append(storeName).append("!\n");
        receipt.append("========================");
        return receipt.toString();
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

enum PizzaSelection {
    PEPPERONI("Pepperoni", "Lots of pepperoni and extra cheese", 18),
    HAWAIIAN("Hawaiian", "Pineapple, ham, and extra cheese", 22),
    VEGGIE("Veggie", "Green pepper, onion, tomatoes, mushroom, and black olives", 25),
    BBQ_CHICKEN("BBQ Chicken", "Chicken in BBQ sauce, bacon, onion, green pepper, and cheddar cheese", 35),
    EXTRAVAGANZA("Extravaganza", "Pepperoni, ham, Italian sausage, beef, onions, green pepper, mushrooms, black olives, and extra cheese", 45);

    private final String pizzaName;
    private final String pizzaToppings;
    private final double price;

    PizzaSelection(String pizzaName, String pizzaToppings, double price) {
        this.pizzaName = pizzaName;
        this.pizzaToppings = pizzaToppings;
        this.price = price;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public String getPizzaToppings() {
        return pizzaToppings;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return pizzaName + " Pizza with " + pizzaToppings + ", for €" + price;
    }
}

enum PizzaToppings {
    HAM("Ham", 2),
    PEPPERONI("Pepperoni", 2),
    BEEF("Beef", 2),
    CHICKEN("Chicken", 2),
    SAUSAGE("Sausage", 2),
    PINEAPPLE("Pineapple", 1),
    ONION("Onion", 0.5),
    TOMATOES("Tomatoes", 0.4),
    GREEN_PEPPER("Green Pepper", 0.5),
    BLACK_OLIVES("Black Olives", 0.5),
    SPINACH("Spinach", 0.5),
    CHEDDAR_CHEESE("Cheddar Cheese", 0.8),
    MOZZARELLA_CHEESE("Mozzarella Cheese", 0.8),
    FETA_CHEESE("Feta Cheese", 1),
    PARMESAN_CHEESE("Parmesan Cheese", 1);

    private final String topping;
    private final double toppingPrice;

    PizzaToppings(String topping, double toppingPrice) {
        this.topping = topping;
        this.toppingPrice = toppingPrice;
    }

    public String getTopping() {
        return topping;
    }

    public double getToppingPrice() {
        return toppingPrice;
    }

    @Override
    public String toString() {
        return topping + ": €" + toppingPrice;
    }
}

enum PizzaSize {
    LARGE("Large", 10),
    MEDIUM("Medium", 5),
    SMALL("Small", 0);

    private final String pizzaSize;
    private final double addToPizzaPrice;

    PizzaSize(String pizzaSize, double addToPizzaPrice) {
        this.pizzaSize = pizzaSize;
        this.addToPizzaPrice = addToPizzaPrice;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public double getAddToPizzaPrice() {
        return addToPizzaPrice;
    }

    @Override
    public String toString() {
        return pizzaSize + ": €" + addToPizzaPrice;
    }
}

enum SideDish {
    CALZONE("Calzone", 15),
    CHICKEN_PUFF("Chicken Puff", 20),
    MUFFIN("Muffin", 12),
    NOTHING("No side dish", 0);

    private final String sideDishName;
    private final double addToPizzaPrice;

    SideDish(String sideDishName, double addToPizzaPrice) {
        this.sideDishName = sideDishName;
        this.addToPizzaPrice = addToPizzaPrice;
    }

    public String getSideDishName() {
        return sideDishName;
    }

    public double getAddToPizzaPrice() {
        return addToPizzaPrice;
    }

    @Override
    public String toString() {
        return sideDishName + ": €" + addToPizzaPrice;
    }
}

enum Drinks {
    COCA_COLA("Coca Cola", 8),
    COCOA_DRINK("Cocoa Drink", 10),
    NOTHING("No drinks", 0);

    private final String drinkName;
    private final double addToPizzaPrice;

    Drinks(String drinkName, double addToPizzaPrice) {
        this.drinkName = drinkName;
        this.addToPizzaPrice = addToPizzaPrice;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public double getAddToPizzaPrice() {
        return addToPizzaPrice;
    }

    @Override
    public String toString() {
        return drinkName + ": €" + addToPizzaPrice;
    }
}