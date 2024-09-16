package com.rodrigosntg.capitalgains.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CapitalGainsServiceTest {

    private final CapitalGainsService service = new CapitalGainsService();

    /**
     * [{"operation":"buy", "unit-cost":10.00, "quantity": 100},{"operation":"sell", "unit-cost":15.00, "quantity": 50},{"operation":"sell", "unit-cost":15.00, "quantity": 50}]
     * @throws Exception
     */
    @Test
    public void testCase1() throws Exception {
        String input = "[" +
                "{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\":100}," +
                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\":50}," +
                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\":50}" +
                "]";

        String expectedOutput = "[{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00}]";

        String actualOutput = service.processInput(input);

        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * [{"operation":"buy", "unit-cost":10.00, "quantity":10000},{"operation":"sell", "unit-cost":20.00, "quantity":5000},{"operation":"sell", "unit-cost":5.00, "quantity":5000}]
     * @throws Exception
     */
    @Test
    public void testCase2() throws Exception {
        String input = "[" +
                "{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\":10000}," +
                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\":5000}," +
                "{\"operation\":\"sell\", \"unit-cost\":5.00, \"quantity\":5000}" +
                "]";

        String expectedOutput = "[{\"tax\":0.00},{\"tax\":10000.00},{\"tax\":0.00}]";

        String actualOutput = service.processInput(input);

        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * [{"operation":"buy", "unit-cost":10.00, "quantity":10000},{"operation":"sell", "unit-cost":5.00, "quantity":5000},{"operation":"sell", "unit-cost":20.00, "quantity":3000}]
     * @throws Exception
     */
    @Test
    public void testCase3() throws Exception {
        String input = "[" +
                "{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\":10000}," +
                "{\"operation\":\"sell\", \"unit-cost\":5.00, \"quantity\":5000}," +
                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\":3000}" +
                "]";

        String expectedOutput = "[{\"tax\":0.00},{\"tax\":0.00},{\"tax\":1000.00}]";

        String actualOutput = service.processInput(input);

        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * [{"operation":"buy", "unit-cost":10.00, "quantity":10000},{"operation":"buy", "unit-cost":25.00, "quantity":5000},{"operation":"sell", "unit-cost":15.00, "quantity":10000}]
     * @throws Exception
     */
    @Test
    public void testCase4() throws Exception {
        String input = "[" +
                "{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\":10000}," +
                "{\"operation\":\"buy\", \"unit-cost\":25.00, \"quantity\":5000}," +
                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\":10000}" +
                "]";

        String expectedOutput = "[{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00}]";

        String actualOutput = service.processInput(input);

        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * [{"operation":"buy", "unit-cost":10.00, "quantity":10000},{"operation":"buy", "unit-cost":25.00, "quantity":5000},{"operation":"sell", "unit-cost":15.00, "quantity":10000},{"operation":"sell", "unit-cost":25.00, "quantity":5000}]
     * @throws Exception
     */
    @Test
    public void testCase5() throws Exception {
        String input = "[" +
                "{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\":10000}," +
                "{\"operation\":\"buy\", \"unit-cost\":25.00, \"quantity\":5000}," +
                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\":10000}," +
                "{\"operation\":\"sell\", \"unit-cost\":25.00, \"quantity\":5000}" +
                "]";

        String expectedOutput = "[{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00},{\"tax\":10000.00}]";

        String actualOutput = service.processInput(input);

        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * [{"operation":"buy", "unit-cost":10.00, "quantity":10000},{"operation":"sell", "unit-cost":2.00, "quantity":5000},{"operation":"sell", "unit-cost":20.00, "quantity":2000},{"operation":"sell", "unit-cost":20.00, "quantity":2000},{"operation":"sell", "unit-cost":25.00, "quantity":1000}]
     * @throws Exception
     */
    @Test
    public void testCase6() throws Exception {
        String input = "[" +
                "{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\":10000}," +
                "{\"operation\":\"sell\", \"unit-cost\":2.00, \"quantity\":5000}," +
                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\":2000}," +
                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\":2000}," +
                "{\"operation\":\"sell\", \"unit-cost\":25.00, \"quantity\":1000}" +
                "]";

        String expectedOutput = "[{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00},{\"tax\":0.00},{\"tax\":3000.00}]";

        String actualOutput = service.processInput(input);

        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * [{"operation":"buy", "unit-cost":10.00, "quantity":10000},{"operation":"sell", "unit-cost":2.00, "quantity":5000},{"operation":"sell", "unit-cost":20.00, "quantity":2000},{"operation":"sell", "unit-cost":20.00, "quantity":2000},{"operation":"sell", "unit-cost":25.00, "quantity":1000},{"operation":"buy", "unit-cost":20.00, "quantity":10000},{"operation":"sell", "unit-cost":15.00, "quantity":5000},{"operation":"sell", "unit-cost":30.00, "quantity":4350},{"operation":"sell", "unit-cost":30.00, "quantity":650}]
     * @throws Exception
     */
    @Test
    public void testCase7() throws Exception {
        String input = "[" +
                "{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\":10000}," +
                "{\"operation\":\"sell\", \"unit-cost\":2.00, \"quantity\":5000}," +
                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\":2000}," +
                "{\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\":2000}," +
                "{\"operation\":\"sell\", \"unit-cost\":25.00, \"quantity\":1000}," +
                "{\"operation\":\"buy\", \"unit-cost\":20.00, \"quantity\":10000}," +
                "{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\":5000}," +
                "{\"operation\":\"sell\", \"unit-cost\":30.00, \"quantity\":4350}," +
                "{\"operation\":\"sell\", \"unit-cost\":30.00, \"quantity\":650}" +
                "]";

        String expectedOutput = "[" +
                "{\"tax\":0.00}," +
                "{\"tax\":0.00}," +
                "{\"tax\":0.00}," +
                "{\"tax\":0.00}," +
                "{\"tax\":3000.00}," +
                "{\"tax\":0.00}," +
                "{\"tax\":0.00}," +
                "{\"tax\":3700.00}," +
                "{\"tax\":0.00}" +
                "]";

        String actualOutput = service.processInput(input);

        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * [{"operation":"buy", "unit-cost":10.00, "quantity":10000},{"operation":"sell", "unit-cost":50.00, "quantity":10000},{"operation":"buy", "unit-cost":20.00, "quantity":10000},{"operation":"sell", "unit-cost":50.00, "quantity":10000}]
     * @throws Exception
     */
    @Test
    public void testCase8() throws Exception {
        String input = "[" +
                "{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\":10000}," +
                "{\"operation\":\"sell\", \"unit-cost\":50.00, \"quantity\":10000}," +
                "{\"operation\":\"buy\", \"unit-cost\":20.00, \"quantity\":10000}," +
                "{\"operation\":\"sell\", \"unit-cost\":50.00, \"quantity\":10000}" +
                "]";

        String expectedOutput = "[{\"tax\":0.00},{\"tax\":80000.00},{\"tax\":0.00},{\"tax\":60000.00}]";

        String actualOutput = service.processInput(input);

        assertEquals(expectedOutput, actualOutput);
    }
}
