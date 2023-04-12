import java.util.Scanner;

public class Test {

    private static final String[] ones = {"", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"};
    private static final String[] tens = {"", "", "hai mươi", "ba mươi", "bốn mươi", "năm mươi", "sáu mươi", "bảy mươi",
            "tám mươi", "chín mươi"};
    private static final String[] teens = {"mười", "mười một", "mười hai", "mười ba", "mười bốn", "mười lăm", "mười sáu",
            "mười bảy", "mười tám", "mời chín"};
//    private static final String[] ones = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
//    private static final String[] tens = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
//    private static final String[] teens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static final String[] thousands = {"", "nghìn", "triệu", "tỷ", "nghìn tỷ", "triệu tỷ", "tỷ tỷ"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an amount: ");
        double amount = scanner.nextDouble();
        System.out.println(convertToWords(amount));
    }

    public static String convertToWords(double amount) {
        String[] parts = String.valueOf(amount).split("\\.");
        int wholePart = Integer.parseInt(parts[0]);
        int decimalPart = Integer.parseInt(parts[1]);

        String words = "";

        if (wholePart == 0) {
            words += "không";
        } else {
            int groupIndex = 0;
            while (wholePart > 0) {
                int hundreds = wholePart % 1000;
                if (hundreds > 0) {
                    if (groupIndex > 0) {
                        words = readNumber(hundreds) + " " + thousands[groupIndex] + " " + words;
                    } else {
                        words = readNumber(hundreds) + " " + words;
                    }
                }
                wholePart /= 1000;
                groupIndex++;
            }
        }

        if (decimalPart > 0) {
            if (words.length() > 0) {
                words += " việt nam đồng và ";
            }
            if (decimalPart < 10) {
                words += ones[decimalPart] + " xu";
            } else if (decimalPart < 20) {
                words += teens[decimalPart - 10] + " xu";
            } else {
                words += tens[decimalPart / 10] + " ";
                if (decimalPart % 10 > 0) {
                    words += ones[decimalPart % 10] + " ";
                }
                words += "xu";
            }
        } else {
            words += "việt nam đồng";
        }

        return words;
    }

    public static String readNumber(int num) {
        if (num < 0 || num > 999) {
            throw new IllegalArgumentException("Number out of range");
        }

        String words = "";

        if ((num / 100) > 0) {
            words += ones[num / 100] + " trăm ";
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
