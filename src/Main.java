import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String input = scn.nextLine();
        String result = calc(input);
        System.out.println(result);
    }

    public static String calc(String input) throws Exception{

        String [] operands = input.split("[+\\-*/]");

        if (operands.length != 2) {throw new Exception("Неподдерживаемая математическая операция");}

        String operator = "";
        if (input.contains("+")) {operator = "+";}
        else if (input.contains("-")) {operator = "-";}
        else if (input.contains("*")) {operator = "*";}
        else if (input.contains("/")) {operator = "/";}

        String operand1 = operands[0];
        String operand2 = operands[1];
        operand1 = operand1.trim();
        operand2 = operand2.trim();

        if (operand1.length()>12||operand2.length()>12) {throw new Exception("Строка не может быть длиннее десяти символов");}

        String res = "";

        switch (operator){
            case "+" -> res = getResultPlus(operand1,operand2);
            case "-" -> res = getResultMinus(operand1,operand2);
            case "/" -> res = getResultDivision(operand1,operand2);
            case "*" -> res = getResultMultiply(operand1,operand2);
            default -> throw new Exception("Неподдерживаемая операция");
        }
        return res;
    }
    private static String getResultPlus (String operand1, String operand2)throws Exception{

        if     (operand1.charAt(0) != '\"' ||
                operand2.charAt(0) != '\"' ||
                operand1.charAt(operand1.length() - 1) != '\"' ||
                operand2.charAt(operand2.length() - 1) != '\"')
        {throw new Exception("Неверная матиматическая операция");}

        return operand1.substring(0, operand1.length() - 1) +""+ operand2.substring(1, operand2.length());
    }

    private static String getResultMinus (String operand1, String operand2)throws Exception {

        if (operand1.charAt(0) != '\"' ||
                operand2.charAt(0) != '\"' ||
                operand1.charAt(operand1.length() - 1) != '\"' ||
                operand2.charAt(operand2.length() - 1) != '\"') {
            throw new Exception("Неверная матиматическая операция");
        }

        operand1 = operand1.replace(operand2,"");

        return "\""+operand1+"\"";
    }

    private static String getResultDivision (String operand1, String operand2)throws Exception {

        if     (operand1.charAt(0) != '\"' ||
                operand1.charAt(operand1.length() - 1) != '\"')
        {throw new Exception("Неверная матиматическая операция");}

        int divider = Integer.parseInt(operand2);

        if (divider>10) {throw new Exception("Делитель не может быть больше десяти");}
        if (divider==0) {throw new Exception("Делитель не может быть равен нулю");}

        operand1 = operand1.substring(1,operand1.length() - 1);

        int res = operand1.length() / divider;

        operand1 = operand1.substring(0,res);

        return "\""+operand1+"\"";
    }

    private static String getResultMultiply(String operand1, String operand2)throws Exception{

        if     (operand1.charAt(0) != '\"' ||
                operand1.charAt(operand1.length() - 1) != '\"')
        {throw new Exception("Неверная матиматическая операция");}

        int multiplier = Integer.parseInt(operand2);

        if (multiplier>10) {throw new Exception("Множитель не может быть больше десяти");}
        if (multiplier==0) {throw new Exception("Множитель не может быть равен нулю");}

        operand1 = operand1.substring(1,operand1.length() - 1);
        operand1 = operand1.repeat(multiplier);

        if (operand1.length()>40){
            operand1.substring(0,42);
            operand1 = operand1+"...";
        }

        return "\""+operand1+"\"";
    }
    }