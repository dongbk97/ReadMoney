public class ConvertNumToString {


    public static String Read(String sNumber, int iPo) {
        // Tao mot bien tra ve
        String sReturn = "";
        // Tao mot bien so
        String sPo[] = {"", "ngàn" + " ", "triệu" + " ", "tỷ" + " "};
        String sSo[] = {"không" + " ", "một" + " ", "hai" + " ", "ba" + " ", "bốn" + " ", "năm" + " ", "sáu" + " ", "bảy" + " ", "tám" + " ", "chín" + " "};
        String sDonvi[] = {"", "mươi" + " ", "trăm" + " "};
        // Tim chieu dai cua chuoi
        int iLen = sNumber.length();
        // Tao mot bien nho vi tri doc
        int iRe = 0;
        // Tao mot vong lap de doc so
        for (int i = 0; i < iLen; i++) {
            String sTemp = "" + sNumber.charAt(i);
            int iTemp = Integer.parseInt(sTemp);
            // Tao mot bien ket qua
            String sRead = "";
            // Kiem tra xem so nhan vao co = 0 hay ko
            if (iTemp == 0) {
                switch (iRe) {
                    case 0:
                        break;
                    // Khong lam gi ca
                    case 1: {
                        if (Integer.parseInt("" + sNumber.charAt(0)) != 0) {
                            sRead = "lẻ" + " ";
                        }
                        break;
                    }
                    case 2: {
                        if (Integer.parseInt("" + sNumber.charAt(0)) != 0 && Integer.parseInt("" + sNumber.charAt(1)) != 0) {
                            sRead = "không trăm" + " ";
                        }
                        break;
                    }
                }
            } else if (iTemp == 1) {
                switch (iRe) {
                    case 1:
                        sRead = "mười" + " ";
                        break;
                    default:
                        sRead = "một" + " " + sDonvi[iRe];
                        break;
                }
            } else if (iTemp == 5) {
                switch (iRe) {
                    case 0: {
                        if (sNumber.length() <= 1) {
                            sRead = "năm" + " ";
                        } else if (Integer.parseInt("" + sNumber.charAt(1)) != 0) {
                            sRead = "lăm" + " ";
                        } else sRead = "năm" + " ";
                        break;
                    }
                    default:
                        sRead = sSo[iTemp] + sDonvi[iRe];
                }
            } else {
                sRead = sSo[iTemp] + sDonvi[iRe];
            }
            sReturn = sRead + sReturn;
            iRe++;
        }
        if (sReturn.length() > 0) {
            sReturn += sPo[iPo];
        }
        return sReturn;
    }

    public static String ConvertMoney(String sNumber) {
        // Tao mot bien tra ve
        String sReturn = "";
        // Tim chieu dai cua chuoi
        int iLen = sNumber.length();
        // Lat nguoc chuoi nay lai
        String sNumber1 = "";
        for (int i = iLen - 1; i >= 0; i--) {
            sNumber1 += sNumber.charAt(i);
        }
        // Tao mot vong lap de doc so // Tao mot bien nho vi tri cua sNumber
        int iRe = 0;
        do {
            // Tao mot bien cat tam
            String sCut = "";
            if (iLen > 3) {
                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + 3);
                sReturn = Read(sCut, iRe) + sReturn;
                iRe++;
                iLen -= 3;
            } else {
                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + iLen);
                sReturn = Read(sCut, iRe) + sReturn;
                break;
            }
        } while (true);
        if (sReturn.length() > 1) {
            sReturn = sReturn.substring(0, 1).toUpperCase() + sReturn.substring(1);
        }
        sReturn = sReturn;
        return sReturn;
    }

    public static String parametersToJson(String parameters) {
        String json = "{\"";
        try {
            if (!"".equals(parameters)) {
                parameters = parameters.replace("\r", "");
                parameters = parameters.replace("\n", "");
                parameters = parameters.replace("+", " ");
                parameters = parameters.replace("+=", "=");
                parameters = parameters.replace("&", "\",\"");
                parameters = parameters.replace("=", "\":\"");
                json += parameters;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        json += "\"}";
        return json;
    }


    public String readMoney(String number, String currency) {

        if (number.contains(".")) {

            int index = number.indexOf(".");
            String numberChan = number.substring(0, index);
            String numberLe = number.substring(index + 1, number.length());

            String numberLe1 = numberLe.replace("0", "");


            return ConvertMoney(numberChan) +  convertCurrency(currency) +" và " + numberLe1+ " xu";
        } else
            return ConvertMoney(number) + " " +  convertCurrency(currency);
    }

    public String convertCurrency(String currency){
        String result= "";
        if (currency.matches("VND|VNĐ")){
            result =" việt nam đồng";
        } else if (currency.matches("USD")){
            result ="đô la mỹ";

        } else
            result= currency;
        return result;

    }

    public static void main(String[] args) {
        ConvertNumToString ConvertMoney = new ConvertNumToString();

        System.out.println(ConvertMoney.readMoney("1000040", "USD"));

// một triệu bốn mươi   và ba mươi bốn xu
    }

}

