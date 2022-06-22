public class NonPositiveRomanNumberException extends Exception{

    NonPositiveRomanNumberException() {
        super("В римской системе счисления нет чисел меньше 1!");
    }
}

