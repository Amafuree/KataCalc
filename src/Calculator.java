class Calculator {
    private static int firstOp;
    private static int secondOp;
    private static int result;
    private static boolean isRoman = false;
    private static final String NUMS = "1 2 3 4 5 6 7 8 9 10"; // чек строка для арабских чисел

    // заглавный метод калькулятора, единственный доступный в main
    public static void calculate(String[] elements) {
        try {
            checkAndAssign(elements);
            process(elements[1].charAt(0));
            if (isRoman) {
                System.out.println(arabicToRomanConverter());
            } else {
                System.out.println(result);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //метод проверки входных значений
    private static void checkAndAssign (String[] elements)
            throws WrongInputStringFormatException, WrongNumberFormatException, IllegalArgumentException{
        if (elements.length == 3) {
            if ("IVX".contains(elements[0].substring(0, 1)) &&
                    "IVX".contains(elements[2].substring(0, 1))){
                firstOp = roman.valueOf(elements[0]).getValue();    // здесь может выбросить
                secondOp = roman.valueOf(elements[2]).getValue();   // IllegalArgumentException для enum
                isRoman = true;
            } else if (NUMS.contains(elements[0]) && NUMS.contains(elements[2])){
                firstOp = Integer.parseInt(elements[0]);
                secondOp = Integer.parseInt(elements[2]);
            } else {
                throw new WrongNumberFormatException();             // выброс исключения входных чисел
            }
        } else {
            throw new WrongInputStringFormatException();            // выброс исключения неправильной строки
        }
    }

    // метод рассчетов
    private static void process(char operator)
            throws UnsupportedOperationException{
        switch (operator) {
            case ('+'): {
                result = firstOp + secondOp;
                break;
            }
            case ('-'): {
                result = firstOp - secondOp;
                break;
            }
            case ('*'): {
                result = firstOp * secondOp;
                break;
            }
            case ('/'): {
                result = firstOp / secondOp;
                break;
            }
            default: {
                throw new UnsupportedOperationException("Неверная операция!"); // выброс исключения несоответствия операции
            }
        }
    }

    // метод конвертер арабских в римские
    private static String arabicToRomanConverter()
            throws NonPositiveRomanNumberException{
        if (result > 0) {                       // проверка на положительное число
            int[] postions = {result / 100, result % 100 / 10, result % 10};
            String[] romanEquivalent = new String[3];
            for (int i = 0; i < 3; i++) {
                if (postions[i] <= 3) {
                    romanEquivalent[i] = "I".repeat(postions[i]);
                } else if (postions[i] == 4) {
                    romanEquivalent[i] = "IV";
                } else if (postions[i] <= 8) {
                    romanEquivalent[i] = "V" + "I".repeat(postions[i] - 5);
                } else {
                    romanEquivalent[i] = "IX";
                }
            }
            return romanEquivalent[0].replace('I', 'C') +
                    romanEquivalent[1].replace('X', 'C').
                            replace('V', 'L').replace('I', 'X') +
                    romanEquivalent[2];
        } else {
            throw new NonPositiveRomanNumberException();    // выброс исключения неположительного числа
        }
    }
}
// перечисление допустимых римских входных значений с арабским эквивалентом
@SuppressWarnings("unused")
enum roman {
    I(1), II(2), III(3), IV(4), V(5),
    VI(6), VII(7), VIII(8), IX(9), X(10);
    private final int value;
    roman(int value) {
        this.value = value;
    }
    public int getValue(){
        return(this.value); // вовзрат значения в арабской системе
    }
}