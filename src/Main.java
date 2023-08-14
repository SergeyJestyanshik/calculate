
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Введите выражение c одним оператором двух целых арабских или римских чисел от 1 до 10 : ");
        String input = s.nextLine().trim().toUpperCase(); // принимаем строку и убираем пробелы, приводим к верх.регистру.
        System.out.println("Результат выражения: " + input + '=' + calc(input));
    }
    public static String calc(String input) {
            String operStr = " ";
            int num1 = 0;
            int num2 = 0;
            int result = 0;
            int resultArab = 0;
            char[] symbol = new char[10];
            char oper = '+';

        for (int i = 1; i < input.length(); i++) {
            symbol[i] = input.charAt(i);
            if (symbol[i] == '+') {
                oper = '+';
                operStr = "\\+";
            }
            if (symbol[i] == '-') {
                oper = '-';
                operStr = "-";
            }
            if (symbol[i] == '*') {
                oper = '*';
                operStr = "\\*";
            }
            if (symbol[i] == '/') {
                oper = '/';
                operStr = "/";
            }
        }
        String[] numbers = input.split(operStr);
        if (numbers.length > 2) {
            erores();
        }
        try {
            num1 = romanNumeral(numbers[0]);
            num2 = romanNumeral(numbers[1]);
            if (num1 > 10 | num2 > 10 | num1 < 0 | num2 < 0) {
                erores();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            erores();
        }
        if (num1 == 0 | num2 == 0) {
            try {
                num1 = Integer.parseInt(numbers[0]);
                num2 = Integer.parseInt(numbers[1]);
                if (num1 > 10 | num2 > 10 | num1 < -10 | num2 < -10) {
                    erores();
                }
                resultArab = calculate(num1, num2, oper);
            } catch (NumberFormatException | ArithmeticException e) {
                erores();
            }
            return String.valueOf(resultArab) ;
        } else {
            try {
                result = calculate(num1, num2, oper);
                if (result < 0) {
                    erores();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                erores();
            }
        } return converter(result);
    }

//          Калькулятор входные данные . два числа и оператор
    public static int calculate(int x1, int x2, char op) {
        int result = 0;
        switch (op) {
            case '+' -> result = x1 + x2;
            case '-' -> result = x1 - x2;
            case '*' -> result = x1 * x2;
            case '/' -> result = x1 / x2;
            default -> {
            }
        }
        return result;
    }

    public static int romanNumeral(String roman) {
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> 0;
        };
    }


//            выход из программы в случае ошибки.
     static void  erores(){
        System.out.println("Неверный ввод!!! Проверьте правильность ввода и повторите попытку.");
         System.exit(0);
    }

//          Конвектор из арабских в римские
   public static String converter(int a){
        int[] arabic = new int [] {100, 90, 50,40,10,9,5, 4, 1};
        String[] rimlyan = new String[] {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        String otvet = " ";

            for (int i = 0; a > 0; i++) {
                if (arabic[i] <= a) {
                    otvet += rimlyan[i];
                    a = a - arabic[i];
                    if (arabic[i] <= a){
                     i=0;
                    }
                }
            } return otvet;
    }

}



