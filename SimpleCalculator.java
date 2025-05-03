import java.util.Scanner;

public class SimpleRPNCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("--- Basic RPN Calculator ---");
        System.out.println("Use space-separated numbers and operators (+ - * /)");
        System.out.println("Type 'quit' to exit");

        while (true) {
            System.out.print("> ");
            String line = input.nextLine();

            if (line.equalsIgnoreCase("quit")) break;

            String[] tokens = line.split("\\s+");
            double[] temp = new double[100];
            int index = -1;

            try {
                for (String token : tokens) {
                    if (token.matches("-?\\d+(\\.\\d+)?")) {
                        temp[++index] = Double.parseDouble(token);
                    } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                        if (index < 1) throw new Exception("Not enough operands");

                        double b = temp[index--];
                        double a = temp[index--];
                        double result = 0;

                        if (token.equals("+")) result = a + b;
                        else if (token.equals("-")) result = a - b;
                        else if (token.equals("*")) result = a * b;
                        else {
                            if (b == 0) throw new Exception("Division by zero");
                            result = a / b;
                        }

                        temp[++index] = result;
                    } else {
                        throw new Exception("Unknown input: " + token);
                    }
                }

                if (index == 0) {
                    System.out.println("Result: " + temp[0]);
                } else {
                    System.out.println("Error: Too many numbers left after operations.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Calculator exited.");
        input.close();
    }
}
