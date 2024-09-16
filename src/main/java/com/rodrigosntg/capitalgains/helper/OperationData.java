package com.rodrigosntg.capitalgains.helper;

import com.rodrigosntg.capitalgains.enums.OperationType;

import java.math.BigDecimal;

public record OperationData(OperationType operationType, BigDecimal unitCost, int quantity) {

}
