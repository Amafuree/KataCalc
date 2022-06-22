import java.util.Scanner;

class KataCalc {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение -> ");
        String[] input = scanner.nextLine().split(" ");
        Calculator.calculate(input);
    }
}
