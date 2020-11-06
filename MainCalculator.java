import java.util.Objects;
import java.util.Scanner;

interface Operation {
    double doOperation(double num1, int num2);
}

class Calculator {
    double num;

    public Calculator() {
        this.num = 0;
    }

    public void executeCommand(Operation operation, int num) {
        Objects.requireNonNull(operation);
        this.num = operation.doOperation(this.num, num);
    }

    public void clearNumber() {
        this.num = 0;
    }

    public String toString() {
        return String.format("%.1f", num);
    }
}

public class MainCalculator {
//    static double num = 0;

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner sc = new Scanner(System.in);
        String command;

        while (true) {
            command = sc.next();
            Operation operation;

            if (command.equals("exit")) {
                break;
            } else if (command.equals("cancel")) {
                calculator.clearNumber();
            } else {
                switch (command) {
                    case "add":
                        calculator.executeCommand(Double::sum, sc.nextInt());
                        sc.nextLine();
                        break;
                    case "subtract":
                        calculator.executeCommand((num1, num2) -> num1 - num2, sc.nextInt());
                        sc.nextLine();
                        break;
                    case "multiply":
                        calculator.executeCommand((num1, num2) -> num1 * num2, sc.nextInt());
                        sc.nextLine();
                        break;
                    case "divide":
                        int numDiv = sc.nextInt();
                        sc.nextLine();
                        if (numDiv == 0) {
                            calculator.clearNumber();
                        } else {
                            calculator.executeCommand((num1, num2) -> num1 / num2, numDiv);
                        }
                        break;
                }
            }
            System.out.println(calculator);
        }
    }
}
