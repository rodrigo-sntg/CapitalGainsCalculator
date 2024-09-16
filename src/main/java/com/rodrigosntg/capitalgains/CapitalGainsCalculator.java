package com.rodrigosntg.capitalgains;

import com.rodrigosntg.capitalgains.service.CapitalGainsService;

import java.io.InputStreamReader;
import java.util.Scanner;

public class CapitalGainsCalculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        CapitalGainsService service = new CapitalGainsService();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            try {
                String output = service.processInput(line);
                System.out.println(output);
            } catch (Exception e) {
                System.err.println("Erro ao processar a entrada: " + e.getMessage());
            }
        }

        scanner.close();
    }

//    public static void main1(String[] args) {
//
//        Scanner scanner = new Scanner(new InputStreamReader(System.in));
//        Gson gson = new Gson();
//
//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine().trim();
//            if (line.isEmpty()) {
//                continue;
//            }
//
//
//            List<Map<String, Object>> opsData = gson.fromJson(line, new TypeToken<List<Map<String, Object>>>() {}.getType());
//
//            List<Operation> operations = new ArrayList<>();
//            for (Map<String, Object> opData : opsData) {
//
//                OperationData operationData = OperationData.fromMap(opData);
//
//                Operation operation = OperationFactory.createOperation(operationData);
//                operations.add(operation);
//            }
//
//            Portfolio portfolio = new Portfolio(new DefaultTaxCalculator());
//            List<Map<String, String>> taxesOutput = new ArrayList<>();
//
//
//            for (Operation operation : operations) {
//                BigDecimal tax = operation.process(portfolio);
//                Map<String, String> taxEntry = new HashMap<>();
//                taxEntry.put("tax", tax.setScale(2, RoundingMode.HALF_UP).toPlainString());
//                taxesOutput.add(taxEntry);
//            }
//
//            String outputr = gson.toJson(taxesOutput);
//            System.out.println(outputr);
//        }
//
//        // Fechar o Scanner
//        scanner.close();
//    }


}