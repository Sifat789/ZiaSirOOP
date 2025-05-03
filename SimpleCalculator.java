import java.util.*;
public class SimpleCalculator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Stack<Double> calculationStack = new Stack<>();

        System.out.println("--- RPN Calculator ---");
        System.out.println("Enter numbers and operators (+, -, *, /) separated by spaces.");
        System.out.println("Type 'quit' to exit.");
        System.out.print("> ");

        String line = input.nextLine();

        while (!line.equalsIgnoreCase("quit")) {
            String[] tokens = line.split("\\s+"); 

            try {
                for (String token : tokens) {
                    try {
                        double number = Double.parseDouble(token);
                        calculationStack.push(number);  
                    } catch (NumberFormatException notANumber) {
                        if (calculationStack.size() < 2) {
                            throw new IllegalArgumentException("Error: Not enough numbers on stack for operator '" + token + "'");
                        }
                        double operand2 = calculationStack.pop();
                        double operand1 = calculationStack.pop();
                        double result;

                        switch (token) {
                            case "+":
                                result = operand1 + operand2;
                                break;
                            case "-":
                                result = operand1 - operand2;
                                break;
                            case "*":
                                result = operand1 * operand2;
                                break;
                            case "/":
                                if (operand2 == 0) {
                                    throw new ArithmeticException("Error: Division by zero");
                                }
                                result = operand1 / operand2;
                                break;
                            default:
                                throw new IllegalArgumentException("Error: Unknown operator '" + token + "'");
                        }
                        calculationStack.push(result);
                    }
                } 

                
                if (calculationStack.size() == 1) {
                    System.out.println("Result: " + calculationStack.peek()); 
                } else if (calculationStack.size() > 1) {
                     System.out.println("Error: Too many numbers left on stack. Input might be incomplete.");
                     
                }
                

            } catch (IllegalArgumentException | ArithmeticException | IndexOutOfBoundsException e) {
                
                System.out.println(e.getMessage());
                calculationStack.clear(); 
            }

            System.out.print("> ");
            line = input.nextLine();

        } 

        System.out.println("Calculator exited.");
        input.close();
    }
}
