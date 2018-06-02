package fr.isep.vlacich.thibault.calculatrice.operations;

import fr.isep.vlacich.thibault.calculatrice.operations.models.Operation;
import fr.isep.vlacich.thibault.calculatrice.operations.models.OperationCode;

public class OperationFactory {

    public static Operation withCode(OperationCode code, Double firstValue, Double secondValue) {
        switch (code) {
            case PLUS:
                return new PlusOperation(firstValue, secondValue);
            case MINUS:
                return new MinusOperation(firstValue, secondValue);
            case MULTIPLY:
                return new MultiplyOperation(firstValue, secondValue);
            case DIVIDE:
                return new DivideOperation(firstValue, secondValue);
            default:
                return null;
        }
    }

}
