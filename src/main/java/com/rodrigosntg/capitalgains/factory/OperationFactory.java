package com.rodrigosntg.capitalgains.factory;
import com.rodrigosntg.capitalgains.helper.OperationData;
import com.rodrigosntg.capitalgains.models.BuyOperation;
import com.rodrigosntg.capitalgains.models.Operation;
import com.rodrigosntg.capitalgains.models.SellOperation;


public class OperationFactory {

    private OperationFactory() {}

    public static Operation createOperation(OperationData opData) {
        return switch (opData.operationType()) {
            case BUY -> new BuyOperation(opData.unitCost(), opData.quantity());
            case SELL -> new SellOperation(opData.unitCost(), opData.quantity());
            default -> throw new IllegalArgumentException("Unsupported operation type: " + opData.operationType());
        };
    }
}
