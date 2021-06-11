package model;
import java.math.BigDecimal;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class CalculatorModel {

    private static CalculatorModel instance;
    private static boolean firstInput = false;
    private BinaryOperator<Double> binaryOperator = Double::sum;
    private UnaryOperator<Double> unaryOperator = a -> Math.pow(a,2);
    private double operand1 = 0, operand2 = 0;
    private static String stringOperator = "+";

    private CalculatorModel() {
    }

    public static CalculatorModel getInstance(){
        if (instance== null) {
            instance = new CalculatorModel();
        }
        return instance;
    }

    public BinaryOperator<Double> getBinaryOperator() {
        return binaryOperator;
    }

    public void setBinaryOperator(String s) throws Exception {
        setStringOperator(s);
        this.binaryOperator = switch (s){
            case "+" -> Double::sum;
            case "-" -> (a,b) ->a-b;
            case "*" -> (a,b) -> a*b;
            case "/" -> (a,b) -> a/b;
            case "x²" -> (a,b) -> Math.pow(a,2);
            default -> throw new Exception("Unknown Operator");
        };
    }

    public void setUnaryOperator(String s){
        unaryOperator = switch (s){
            case "x²" -> a -> Math.pow(a,2);
            case "√" -> Math::sqrt;
            case "+/-" ->  a -> a * -1;
            case "1/x" -> a -> 1/a;
            case "sin" -> Math::sin;
            case "cos" -> Math::cos;
            default -> throw new IllegalStateException("Unexpected value: " + s);
        };
    }

    public String bigDecSubtraction(){
       return String.valueOf(BigDecimal.valueOf(operand1).subtract(BigDecimal.valueOf(operand2)));
    }

    public UnaryOperator<Double> getUnaryOperator() {
        return unaryOperator;
    }

    public String getStringOperator() {
        return stringOperator;
    }

    public void setStringOperator(String stringOperator) {
        CalculatorModel.stringOperator = stringOperator;
    }

    public String normalizeOutput(Double d){
        String sFromDouble = String.valueOf(d);
        System.out.println(sFromDouble);
        return d%1==0 ? sFromDouble.replaceAll("\\.0*","") :
                sFromDouble.replaceAll("\\.0{10,}\\d*\\z","");
    }


    public boolean isFirstInput() {
        return firstInput;
    }

    public void setFirstInput(boolean firstInput) {
        CalculatorModel.firstInput = firstInput;
    }

    public double getOperand1() {
        return operand1;
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

}
