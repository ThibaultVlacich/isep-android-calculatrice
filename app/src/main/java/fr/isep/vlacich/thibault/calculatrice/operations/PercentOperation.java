package fr.isep.vlacich.thibault.calculatrice.operations;

import fr.isep.vlacich.thibault.calculatrice.operations.models.BinaryOperation;

public class PercentOperation extends BinaryOperation {

    public PercentOperation(Double firstValue, Double secondValue) {
        super(firstValue, secondValue);
    }

    @Override
    public Double getResult() {
        return (firstValue / 100) * secondValue;
    }

}
