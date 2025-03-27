import java.util.Scanner;
import java.util.InputMismatchException;

public class ExceptionDemo {
    private double divisor;
    private double dividend;
    private double result;
    
    public void divide() throws InputMismatchException, ArithmeticException {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter the divisor: ");
        divisor = input.nextDouble();
        
        System.out.print("Enter the dividend: ");
        dividend = input.nextDouble();
        
        result = divisor / dividend;
        
        System.out.println("Result: " + result);
        
        input.close();
    }
    
    public void goToDivideMethod() throws InputMismatchException, ArithmeticException {
        divide();
    }
    
    public void displayChoices() {
        Scanner input = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n1. Divide");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            
            choice = input.nextInt();
            
            switch (choice) {
                case 1:
                    try {
                        goToDivideMethod();
                    } catch (InputMismatchException e) {
                        System.out.println("Exception " + e + " occurred. A number was expected, but wasn't provided");
                    } catch (ArithmeticException e) {
                        System.out.println("Exception " + e + " occurred. Division by zero was attempted");
                    } finally {
                        
                    }
                    break;
                case 2:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 2);
        
        input.close();
    }

}
    

