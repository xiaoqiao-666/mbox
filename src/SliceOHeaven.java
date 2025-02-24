
public class SliceOHeaven {
    
    public void takeOrder(String string, String string2) {
        
        throw new UnsupportedOperationException("Unimplemented method 'takeOrder'");
    } 
    public class  SliceOHeaven {
       
        public String storeName = "Slice-o-Heaven";
        public String storeAddress = "666 Pizza Avenue, Cheese City";
        public String storeEmail = "order@sliceoheaven.com";
        public String storePhone = "(666) 888-9999";
        
        
        private String orderID;
        private String orderDetails;
        private double orderTotal;
        
        
        private Map<String, Double> pizzaMenu = new HashMap<>();
        private Map<String, String> pizzaIngredients = new HashMap<>();
        private Map<String, Double> sides = new HashMap<>();
        private Map<String, Double> drinks = new HashMap<>();
    
        public SliceOHeaven() {
            
            pizzaMenu.put("Margherita Pizza", 12.99);
            pizzaMenu.put("Pepperoni Pizza", 14.99);
            pizzaMenu.put("Veggie Pizza", 13.99);
            
            
            pizzaIngredients.put("Margherita Pizza", "Tomato Sauce, Mozzarella, Basil");
            pizzaIngredients.put("Pepperoni Pizza", "Tomato Sauce, Mozzarella, Pepperoni");
            pizzaIngredients.put("Veggie Pizza", "Tomato Sauce, Mozzarella, Mushrooms, Bell Peppers");
            
            
            sides.put("Garlic Bread", 4.99);
            sides.put("Cheese Sticks", 5.99);
            drinks.put("Coke", 2.99);
            drinks.put("Sprite", 2.99);
        }
    
        
        public void takeOrder(String id, String details) {
            this.orderID = id;
            this.orderDetails = details;
            this.orderTotal = calculateTotal(details); 
            System.out.println("Order accepted for: \"" + details + "\"");
            makePizza();
            printReceipt();
        }
    
        }
        private void printReceipt() {
            System.out.println("\n======== RECEIPT ========");
            String orderID;
            System.out.println("Order ID: " + orderID);
            System.out.println("Items Ordered:");
            String orderDetails;
            System.out.println("  " + orderDetails.replaceAll(", ", "\n  "));
            System.out.println("------------------------");
            Object orderTotal;
            System.out.printf("Total: $%.2f\n", orderTotal);
            String storeName;
            System.out.println("Thank you for choosing " + storeName + "!");
            System.out.println("========================");
        }
    }