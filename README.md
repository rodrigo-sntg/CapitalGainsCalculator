# Capital Gains Calculator

## Descrição

Projeto desafio para um banco, onde foi implementada uma solução baseada em OO, estruturada para fazer o cálculo de imposto devido de acordo com as operações financeiras de compra e venda de ações.

O programa lê entradas no formato JSON contendo operações de compra e venda de ações, processa essas operações e retorna o valor de imposto devido para cada operação de venda, considerando lucros, prejuízos e deduções de prejuízos passados.

## Funcionalidades

- **Cálculo do imposto sobre operações de venda com lucro**: O imposto sobre ganhos de capital é de 20% sobre o lucro obtido.
- **Prejuízos acumulados**: Se houver prejuízo em uma operação de venda, ele é acumulado e deduzido dos lucros futuros.
- **Isenção de imposto em operações menores**: Nenhum imposto é cobrado se o valor total da venda for menor ou igual a R$ 20.000,00.
- **Cálculo do preço médio ponderado de ações compradas**: O preço médio é recalculado a cada compra de ações.

## Estrutura do Projeto

Essas são as principais classes do projeto:

- **`CapitalGainsCalculator`**: Classe principal que recebe a entrada do usuário, chama o serviço de cálculo e imprime o resultado. Utiliza a classe `CapitalGainsService` para processar as operações.
- **`CapitalGainsService`**: Contém a lógica principal para processar a entrada, realizar o cálculo de impostos e organizar a saída.
- **`Portfolio`**: Responsavel por gerenciar o portfólio de ações do usuároi, incluindo o cálculo do preço médio ponderado e o controlar perdas acumuladas.
- **`DefaultTaxCalculator`**: Implementa a lógica para o cálculo do imposto, incluindo deduções de prejuízos e isenções de operações menores.
- **`OperationFactory`**: Fabrica objetos de operações (compra ou venda) com base nos dados de entrada.
- **`Operation`**: Classe abstrata que representa uma operação (compra ou venda).
- **`BuyOperation`** e **`SellOperation`**: Subclasses de `Operation`, que implementam o comportamento específico para compra e venda de ações.

## Regras de Negócio

O programa segue as seguintes regras para o cálculo de impostos:

1. **Imposto de 20% sobre lucros**: O imposto é calculado quando o preço de venda for superior ao preço médio ponderado das ações compradas.

2. **Cálculo do preço médio ponderado**: A cada compra de ações, o preço médio ponderado é recalculado utilizando a fórmula:
   ```
   nova-média-ponderada = ((quantidade-de-ações-atual * média-ponderada-atual) 
                                         + (quantidade-de-ações-compradas * valor-de-compra)) 
                                         / (quantidade-de-ações-atual + quantidade-de-ações-compradas)
   ```

3. **Dedução de prejuízos acumulados**: Prejuízos em vendas anteriores são acumulados e deduzidos de lucros futuros até serem completamente utilizados.

4. **Isenção de imposto para operações menores**: Se o valor total de uma operação de venda (preço unitário x quantidade) for menor ou igual a R$ 20.000,00, nenhum imposto é cobrado, independentemente do lucro ou prejuízo.

5. **Prejuízos não geram imposto**: Quando há prejuízo em uma venda, o imposto não é cobrado, e o prejuízo é utilizado para deduzir lucros futuros.


## Como Rodar o Projeto

### Pré-requisitos

- **Java 22**
- **Maven**

### Executando o Projeto

1. Clone o repositório:

```bash
git clone https://github.com/rodrigo-sntg/CapitalGainsCalculator.git
cd CapitalGainsCalculator
```

2. Compile o projeto usando Maven:

```bash
mvn clean install
```

3. Execute o programa:

```bash
java -jar target/CapitalGainsCalculator.jar
```

4. Insira os dados de operações no terminal no formato JSON.

### Executando os Testes

Para rodar os testes, execute:

```bash
mvn test
```

## Estrutura de Entrada e Saída

### Entrada
O programa espera receber uma lista de operações em formato JSON. Cada operação contém os seguintes campos:

- **operation**: Tipo da operação ("buy" ou "sell")
- **unit-cost**: Preço unitário da ação
- **quantity**: Quantidade de ações

### Saída
O programa retorna uma lista de objetos JSON contendo o valor do imposto pago por cada operação de venda. Exemplo:

```json
[{"tax": 0.00}, {"tax": 10000.00}]
```

## Exemplo de Uso

### Entrada
Cada linha de entrada representa uma lista de operações no formato JSON. Por exemplo:

```json
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},{"operation":"sell", "unit-cost":20.00, "quantity": 5000},{"operation":"sell", "unit-cost":5.00, "quantity": 5000}]
```

### Saída
A saída é uma lista de objetos JSON, contendo o imposto calculado para cada operação de venda. Exemplo de saída para o exemplo acima:

```json
[{"tax":0.00},{"tax":10000.00},{"tax":0.00}]
```   

## Testes Unitários

Testes foram criados para verificar diferentes cenários, incluindo:

- Compra e venda de ações com lucro.
- Compra e venda de ações com prejuízo.
- Operações isentas de imposto (valor total da venda inferior a R$ 20.000,00).
- Acumulação de prejuízos e dedução em vendas futuras.

## Decisões Técnicas

- **Gson** foi utilizado para facilitar a conversão entre JSON e objetos Java.
- O cálculo de impostos foi implementado na classe `DefaultTaxCalculator` para facilitar futuras extensões ou alterações nas regras de cálculo.
- O uso de interfaces como `TaxCalculator` foi adotado para permitir flexibilidade na implementação de diferentes regras de cálculo de impostos.

## Autor

Rodrigo Santiago Silva