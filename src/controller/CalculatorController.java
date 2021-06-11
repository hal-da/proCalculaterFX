package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.CalculatorModel;

public class CalculatorController {
    @FXML
    private Label display;
    private final CalculatorModel calculatorModel = CalculatorModel.getInstance();

    @FXML
    public void handleNumberEntered(ActionEvent event) {
        if(event.getTarget() instanceof Button button) {
            String s = display.getText();
            String text = button.getText();
            if (calculatorModel.isFirstInput()) {
                if (text.equals(".")) {
                    if (!s.contains(".")) display.setText(s + text);
                } else display.setText(s + text);
            } else {
                if (text.equals(".")) display.setText("0.");
                else display.setText(text);
                calculatorModel.setFirstInput(true);
            }
        }
    }

    /*
        Line 46: BigDecimal.subtract() is used for subtractions with comma digits involved, to avoid floating point error when using Double
        Line 49: if division by zero, output is "Division by 0 not possible" otherwise teh result is the output
     */

    public void handleOperatorEntered(ActionEvent event)  throws Exception {
        Button button = (Button) event.getTarget();
        String s = button.getText();
        calculatorModel.setFirstInput(false);

        switch (s) {
            case "=" -> {
                String output;
                calculatorModel.setOperand2(Double.parseDouble(display.getText()));
                if(calculatorModel.getStringOperator().equals("-") && (calculatorModel.getOperand1() % 1 != 0 || calculatorModel.getOperand2() % 1 != 0)){
                    output = calculatorModel.bigDecSubtraction();
                } else {
                    double result = calculatorModel.getBinaryOperator().apply(calculatorModel.getOperand1(), calculatorModel.getOperand2());
                    output = calculatorModel.getStringOperator().equals("/") && calculatorModel.getOperand2() == 0 ? "Division by 0 not possible" : calculatorModel.normalizeOutput(result);
                }
                display.setText(output);
            }
            case "+", "-", "*", "/" -> {
                calculatorModel.setOperand1(Double.parseDouble(display.getText()));
                calculatorModel.setBinaryOperator(s);
            }
            case "C" -> {
                calculatorModel.setOperand1(0);
                display.setText("0");
            }
            case "<-" -> {
                if(display.getText().length() == 1) display.setText("0");
                else display.setText(display.getText().substring(0,display.getText().length()-1));
            }
            case "x²", "√", "+/-", "1/x","sin","cos" -> {
                calculatorModel.setUnaryOperator(s);
                Double result = calculatorModel.getUnaryOperator().apply(
                        Double.parseDouble(display.getText())
                );
                display.setText(calculatorModel.normalizeOutput(result));
            }
        }
    }

    @FXML
    void initialize(){
        assert  display != null : "fx:id=\"display\"was not injected: check your display";
        System.out.println("controller instantiated and initialised!");
    }
}
