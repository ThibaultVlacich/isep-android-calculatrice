package fr.isep.vlacich.thibault.calculatrice.operations.models;

public abstract class UnaryOperation implements Operation {
    public Double value;

    public UnaryOperation(Double value) {
        this.value = value;
    }
}
