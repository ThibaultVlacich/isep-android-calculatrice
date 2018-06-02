package fr.isep.vlacich.thibault.calculatrice.operations;

import fr.isep.vlacich.thibault.calculatrice.operations.models.BinaryOperation;

public class MinusOperation extends BinaryOperation {

    public MinusOperation(Double firstValue, Double secondValue) {
        super(firstValue, secondValue);
    }

    @Override
    public Double getResult() {
        return firstValue - secondValue;
    }

}
