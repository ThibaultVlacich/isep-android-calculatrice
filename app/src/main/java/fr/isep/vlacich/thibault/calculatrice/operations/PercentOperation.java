package fr.isep.vlacich.thibault.calculatrice.operations;

import fr.isep.vlacich.thibault.calculatrice.operations.models.UnaryOperation;

public class PercentOperation extends UnaryOperation {

    public PercentOperation(Double value) {
        super(value);
    }

    @Override
    public Double getResult() {
        return value * 0.01;
    }

}
