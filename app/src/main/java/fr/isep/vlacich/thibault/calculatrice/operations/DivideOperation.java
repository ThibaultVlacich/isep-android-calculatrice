package fr.isep.vlacich.thibault.calculatrice.operations;

import fr.isep.vlacich.thibault.calculatrice.operations.models.BinaryOperation;

public class DivideOperation extends BinaryOperation {

    public DivideOperation(Double firstValue, Double secondValue) {
        super(firstValue, secondValue);
    }

    @Override
    public Double getResult() {
        if (secondValue != 0.0) {
            return firstValue / secondValue;
        }

        return 0.0;
    }

}
