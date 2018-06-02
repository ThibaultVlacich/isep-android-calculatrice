package fr.isep.vlacich.thibault.calculatrice.operations;

import fr.isep.vlacich.thibault.calculatrice.operations.models.BinaryOperation;

public class PlusOperation extends BinaryOperation {

    public PlusOperation(Double firstValue, Double secondValue) {
        super(firstValue, secondValue);
    }

    @Override
    public Double getResult() {
        return firstValue + secondValue;
    }

}
