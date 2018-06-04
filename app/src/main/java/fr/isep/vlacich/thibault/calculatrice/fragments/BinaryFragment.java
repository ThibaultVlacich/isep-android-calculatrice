package fr.isep.vlacich.thibault.calculatrice.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.isep.vlacich.thibault.calculatrice.R;

public class BinaryFragment extends Fragment {

    // Result TextView
    private TextView textViewInput;
    private TextView textViewResult;

    private String numberOnScreen = "0";
    private String numberOnScreenConverted = null;
    private String lastKey = null;

    public BinaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_binary, container, false);

        textViewInput  = view.findViewById(R.id.textViewInput);
        textViewResult = view.findViewById(R.id.textViewResult);

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

        // Conversion buttons listeners
        view.findViewById(R.id.buttonToBin).setOnClickListener((View v) -> convertToBin());
        view.findViewById(R.id.buttonToHex).setOnClickListener((View v) -> convertToHex());

        // Setup initial display
        resetValues();
        updateDisplay();

        return view;
    }

    private void clickedOnClear() {
        resetValues();
        updateDisplay();

        lastKey = "CLEAR";
    }

    private void clickedOnDigit(Integer digit) {
        if (numberOnScreen.equals("0") || lastKey.equals("EQUAL")) {
            numberOnScreen = "";
        }

        numberOnScreen += Integer.toString(digit);

        updateDisplay();

        lastKey = "DIGIT";
    }

    private void convertToBin() {
        Integer toConvert = Integer.parseInt(numberOnScreen);

        numberOnScreenConverted = Integer.toString(toConvert, 2);

        updateDisplay();

        lastKey = "BIN";
    }

    private void convertToHex() {
        Integer toConvert = Integer.parseInt(numberOnScreen);

        numberOnScreenConverted = Integer.toString(toConvert, 16);

        updateDisplay();

        lastKey = "HEX";
    }

    private void resetValues() {
        numberOnScreen          = "0";
        numberOnScreenConverted = null;
    }

    private void updateDisplay() {
        textViewInput.setText(numberOnScreen);
        textViewResult.setText(numberOnScreenConverted);
    }

}
