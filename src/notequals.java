
import java.util.Scanner;

public class notequals {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first number: ");
        int num1 = scanner.nextInt();
        System.out.print("Enter second number: ");
        int num2 = scanner.nextInt();
        if (num1 != num2) {
            System.out.println("The numbers are not equal.");
        } else {
            System.out.println("The numbers are equal.");
        }
        scanner.close();
        
    }
}
