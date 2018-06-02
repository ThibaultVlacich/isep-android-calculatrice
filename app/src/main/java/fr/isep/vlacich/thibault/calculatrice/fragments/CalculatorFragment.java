package fr.isep.vlacich.thibault.calculatrice.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import fr.isep.vlacich.thibault.calculatrice.R;
import fr.isep.vlacich.thibault.calculatrice.operations.models.OperationCode;

public class CalculatorFragment extends Fragment {

    // Result TextView
    private TextView textViewResult;

    // Operation buttons
    private Button buttonPlus;
    private Button buttonMinus;
    private Button buttonMultiply;
    private Button buttonDivide;

    private OperationCode selectedOperation = null;

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

        // Set operations listeners
        buttonPlus.setOnClickListener((View v) -> clickedOnOperation(OperationCode.PLUS, buttonPlus));
        buttonMinus.setOnClickListener((View v) -> clickedOnOperation(OperationCode.MINUS, buttonMinus));
        buttonMultiply.setOnClickListener((View v) -> clickedOnOperation(OperationCode.MINUS, buttonMultiply));
        buttonDivide.setOnClickListener((View v) -> clickedOnOperation(OperationCode.MINUS, buttonDivide));

        return view;
    }

    private void clickedOnDigit(Integer digit) {
        // Do something
        Log.v("", "Clicked on digit button "+digit);
    }

    private void clickedOnOperation(OperationCode operationCode, Button button) {
        selectedOperation = operationCode;

        // Update buttons style
        buttonPlus.setSelected(false);
        buttonMinus.setSelected(false);
        buttonMultiply.setSelected(false);
        buttonDivide.setSelected(false);

        button.setSelected(true);
    }

}
