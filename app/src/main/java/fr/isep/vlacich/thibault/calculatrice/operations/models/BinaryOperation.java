package fr.isep.vlacich.thibault.calculatrice.operations.models;

public abstract class BinaryOperation implements Operation {
    public Double firstValue, secondValue;

    public BinaryOperation(Double firstValue, Double secondValue) {
        this.firstValue  = firstValue;
        this.secondValue = secondValue;
    }
}
