package fr.isep.vlacich.thibault.calculatrice.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import fr.isep.vlacich.thibault.calculatrice.R;
import fr.isep.vlacich.thibault.calculatrice.operations.OperationFactory;
import fr.isep.vlacich.thibault.calculatrice.operations.models.Operation;
import fr.isep.vlacich.thibault.calculatrice.operations.models.OperationCode;

public class CalculatorFragment extends Fragment {

    // Result TextView
    private TextView textViewResult;

    // Operation buttons
    private Button buttonPlus;
    private Button buttonMinus;
    private Button buttonMultiply;
    private Button buttonDivide;

    private String numberOnScreen = "0";
    private String previousNumber = null;
    private OperationCode currentOperation = null;
    private String lastKey = null;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        textViewResult = view.findViewById(R.id.textViewResult);
        buttonPlus     = view.findViewById(R.id.buttonPlus);
        buttonMinus    = view.findViewById(R.id.buttonMinus);
        buttonMultiply = view.findViewById(R.id.buttonMultiply);
        buttonDivide   = view.findViewById(R.id.buttonDivide);

        // Clear button
        view.findViewById(R.id.buttonClear).setOnClickListener((View v) -> clickedOnClear());

        // Set digits listeners
        view.findViewById(R.id.buttonDigit0).setOnClickListener((View v) -> clickedOnDigit(0));
        view.findViewById(R.id.buttonDigit1).setOnClickListener((View v) -> clickedOnDigit(1));
        view.findViewById(R.id.buttonDigit2).setOnClickListener((View v) -> clickedOnDigit(2));
        view.findViewById(R.id.buttonDigit3).setOnClickListener((View v) -> clickedOnDigit(3));
        view.findViewById(R.id.buttonDigit4).setOnClickListener((View v) -> clickedOnDigit(4));
        view.findViewById(R.id.buttonDigit5).setOnClickListener((View v) -> clickedOnDigit(5));
        view.findViewById(R.id.buttonDigit6).setOnClickListener((View v) -> clickedOnDigit(6));
        view.findViewById(R.id.buttonDigit7).setOnClickListener((View v) -> clickedOnDigit(7));
        view.findViewById(R.id.buttonDigit8).setOnClickListener((View v) -> clickedOnDigit(8));
        view.findViewById(R.id.buttonDigit9).setOnClickListener((View v) -> clickedOnDigit(9));

        // Decimal button
        view.findViewById(R.id.buttonDecimal).setOnClickListener((View v) -> clickedOnDecimal());

        // Set operations listeners
        buttonPlus.setOnClickListener((View v) -> clickedOnOperation(OperationCode.PLUS));
        buttonMinus.setOnClickListener((View v) -> clickedOnOperation(OperationCode.MINUS));
        buttonMultiply.setOnClickListener((View v) -> clickedOnOperation(OperationCode.MINUS));
        buttonDivide.setOnClickListener((View v) -> clickedOnOperation(OperationCode.MINUS));

        // Equal button
        view.findViewById(R.id.buttonEqual).setOnClickListener((View v) -> clickedOnEqual());

        // Setup initial display
        resetValues();
        updateDisplay();

        return view;
    }

    private void clickedOnClear() {
        numberOnScreen = "0";
        updateDisplay();
    }

    private void clickedOnDigit(Integer digit) {
        if (numberOnScreen.equals("0")) {
            numberOnScreen = "";
        }

        numberOnScreen += Integer.toString(digit);

        updateDisplay();

        lastKey = "DIGIT";
    }

    private void clickedOnDecimal() {
        if (!numberOnScreen.contains(".")) {
            numberOnScreen += ".";
        }

        updateDisplay();

        lastKey = "DECIMAL";
    }

    private void clickedOnOperation(OperationCode operationCode) {
        if (previousNumber != null && currentOperation != null) {
            commitOperation();
        }

        previousNumber = numberOnScreen;
        numberOnScreen = "0";
        currentOperation = operationCode;

        updateDisplay();

        lastKey = "OPERATION";
    }

    private void clickedOnEqual() {
        commitOperation();
        updateDisplay();

        lastKey = "EQUAL";
    }

    private void resetValues() {
        numberOnScreen   = "0";
        previousNumber   = null;
        currentOperation = null;
    }

    private void commitOperation() {
        if (currentOperation == null) {
            // No operation selected, do nothing
            return;
        }

        Double firstNumber = Double.parseDouble(previousNumber);
        Double secondNumber = Double.parseDouble(numberOnScreen);

        Operation operation = OperationFactory.withCode(currentOperation, firstNumber, secondNumber);

        if (operation == null) {
            // Unrecognized operation, do nothing
            return;
        }

        Double result = operation.getResult();

        if (lastKey == "EQUAL") {
            numberOnScreen = Double.toString(result);
            return;
        }

        previousNumber = numberOnScreen;
        numberOnScreen = Double.toString(result);
    }

    private void updateDisplay() {
        // Show the result on the display
        textViewResult.setText(numberOnScreen);

        // Set the style on the operation buttons
        buttonPlus.setSelected(currentOperation == OperationCode.PLUS);
        buttonMinus.setSelected(currentOperation == OperationCode.MINUS);
        buttonMultiply.setSelected(currentOperation == OperationCode.MULTIPLY);
        buttonDivide.setSelected(currentOperation == OperationCode.DIVIDE);
    }

}
