package com.rodrigosntg.capitalgains.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rodrigosntg.capitalgains.dto.OperationDTO;
import com.rodrigosntg.capitalgains.dto.TaxDTO;
import com.rodrigosntg.capitalgains.enums.OperationType;
import com.rodrigosntg.capitalgains.factory.OperationFactory;
import com.rodrigosntg.capitalgains.helper.OperationData;
import com.rodrigosntg.capitalgains.models.Operation;
import com.rodrigosntg.capitalgains.portfolio.Portfolio;
import com.rodrigosntg.capitalgains.tax.DefaultTaxCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class CapitalGainsService {

    private final Gson gson = new Gson();

    public String processInput(String inputLine) throws Exception {
        try {
            List<Operation> operations = parseOperations(inputLine);
            List<TaxDTO> taxesOutput = processOperations(operations);
            return gson.toJson(taxesOutput);
        } catch (JsonSyntaxException e) {
            throw new Exception("Formato JSON inválido.", e);
        } catch (Exception e) {
            throw new Exception("Erro ao processar as operações.", e);
        }
    }

    private List<Operation> parseOperations(String inputLine) {
        OperationDTO[] opsData = gson.fromJson(inputLine, OperationDTO[].class);

        List<Operation> operations = new ArrayList<>();
        for (OperationDTO opData : opsData) {
            OperationData operationData = new OperationData(
                    OperationType.fromString(opData.getOperation()),
                    opData.getUnitCost(),
                    opData.getQuantity()
            );
            Operation operation = OperationFactory.createOperation(operationData);
            operations.add(operation);
        }
        return operations;
    }

    private List<TaxDTO> processOperations(List<Operation> operations) {
        Portfolio portfolio = new Portfolio(new DefaultTaxCalculator());
        List<TaxDTO> taxesOutput = new ArrayList<>();

        for (Operation operation : operations) {
            BigDecimal tax = operation.process(portfolio);
            TaxDTO taxDTO = new TaxDTO(tax.setScale(2, RoundingMode.HALF_UP));
            taxesOutput.add(taxDTO);
        }
        return taxesOutput;
    }
}