public class SliceOHeaven {
    public String storeName = "Slice-o-Heaven";
    public String storeAddress = "666 Pizza Avenue, Cheese City";
    public String storeEmail = "order@sliceoheaven.com";
    public String storePhone = "(666) 888-9999";

    private  String DEF_ORDER_ID = "DEF-SOH-099";
    private  String DEF_PIZZA_INGREDIENTS = "Mozzarella Cheese";
    private  double DEF_ORDER_TOTAL = 15.00;

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

    public void takeOrder(String id, String details) {
        this.orderID = id;
        this.orderDetails = details;
        this.orderTotal = calculateTotal(details);
        System.out.println("Order accepted for: \"" + details + "\"");
        makePizza();
        printReceipt();
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
}