import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;


    public class main {
        private static final Map<String, Integer> ROMAN_NUMERALS = new HashMap<>();

        static {
            ROMAN_NUMERALS.put("I", 1);
            ROMAN_NUMERALS.put("II", 2);
            ROMAN_NUMERALS.put("III", 3);
            ROMAN_NUMERALS.put("IV", 4);
            ROMAN_NUMERALS.put("V", 5);
            ROMAN_NUMERALS.put("VI", 6);
            ROMAN_NUMERALS.put("VII", 7);
            ROMAN_NUMERALS.put("VIII", 8);
            ROMAN_NUMERALS.put("IX", 9);
            ROMAN_NUMERALS.put("X", 10);
        }

        private static int calculate(String input) throws Exception {
            String[] tokens = input.split("\\s+");
            if (tokens.length != 3) {
                throw new Exception("Неверный формат математической операции");
            }

            String operand1 = tokens[0];
            String operator = tokens[1];
            String operand2 = tokens[2];

            boolean isRoman = isRomanNumeral(operand1) && isRomanNumeral(operand2);
            boolean isArabic = isArabicNumeral(operand1) && isArabicNumeral(operand2);

            if (isRoman == isArabic) {
                throw new Exception("Используются одновременно разные системы счисления");
            }

            int num1 = isRoman ? romanToArabic(operand1) : Integer.parseInt(operand1);
            int num2 = isRoman ? romanToArabic(operand2) : Integer.parseInt(operand2);

            int result;
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        throw new Exception("Деление на ноль");
                    }
                    result = num1 / num2;
                    break;
                default:
                    throw new Exception("Неверный оператор");
            }

            if (isRoman) {
                if (result <= 0) {
                    throw new Exception("Римская система не поддерживает отрицательные числа и ноль");
                }
                return result;
            } else {
                return result;
            }
        }

        private static boolean isRomanNumeral(String input) {
            return input.matches("^[IVXLCDM]+$");
        }

        private static boolean isArabicNumeral(String input) {
            return input.matches("^[1-9]|10$");
        }

        private static int romanToArabic(String roman) {
            int result = 0;
            int prevValue = 0;

            for (int i = roman.length() - 1; i >= 0; i--) {
                int currentValue = ROMAN_NUMERALS.get(String.valueOf(roman.charAt(i)));

                if (currentValue < prevValue) {
                    result -= currentValue;
                } else {
                    result += currentValue;
                }

                prevValue = currentValue;
            }

            return result;
        }
    }
                