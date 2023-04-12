import java.util.Scanner;

public class Main {
    private static final String[] ones = {"", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"};
    private static final String[] tens = {"", "", "hai mươi", "ba mươi", "bốn mươi", "năm mươi", "sáu mươi", "bảy mươi",
            "tám mươi", "chín mươi"};
    private static final String[] teens = {"mười", "mười một", "mười hai", "mười ba", "mười bốn", "mười lăm", "mười sáu",
            "mười bảy", "mười tám", "mời chín"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        double num = scanner.nextDouble();
        System.out.println(readDecimal(num));
    }




    public static String readDecimal(double num) {
        String[] parts = String.valueOf(num).split("\\.");
        int wholePart = Integer.parseInt(parts[0]);
        int decimalPart = Integer.parseInt(parts[1]);

        String words = readNumber(wholePart) + " và ";
        if (decimalPart < 10) {
            words += ones[decimalPart];
        } else if (decimalPart < 20) {
            words += teens[decimalPart - 10];
        } else {
            words += tens[decimalPart / 10] + " ";
            if (decimalPart % 10 > 0) {
                words += ones[decimalPart % 10];
            }
        }

        return words;
    }



    public static String readNumber(int num) {
        if (num < 0 || num > 999999999) {
            throw new IllegalArgumentException("Number out of range");
        }

        if (num == 0) {
            return "zero";
        }

        String words = "";

        if ((num / 1000000) > 0) {
            words += readNumber(num / 1000000) + " triệu ";
            num %= 1000000;
        }

        if ((num / 1000) > 0) {
            words += readNumber(num / 1000) + " ngìn ";
            num %= 1000;
        }

        if ((num / 100) > 0) {
            words += readNumber(num / 100) + " trăm ";
            num %= 100;
        }

        if (num > 0) {
            if (num < 10) {
                words += ones[num];
            } else if (num < 20) {
                words += teens[num - 10];
            } else {
                words += tens[num / 10] + " ";
                num %= 10;
                if (num > 0) {
                    words += ones[num];
                }
            }
        }

        return words;
    }
}