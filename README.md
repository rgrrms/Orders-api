# ORDERS API

Api de Pedidos desenvolvida em JAVA com Spring Boot utilizando:

* Gradle
* Swagger
* Hibernate
* Lombok
* Validation
* Mysql
  ...

Para rodar a aplicação é necessario ter instalado:

* Java 21
* Gradle 8.5
* Mysql

Para o banco utilizei o MySQL criando a base e a tabela:

```roomsql
CREATE database orders;

CREATE TABLE `orders`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `control_number` INT NOT NULL,
  `date` DATE NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `unitary_value` DECIMAL(10,2) NOT NULL,
  `quantity` DECIMAL(10,2) NOT NULL,
  `amount` DECIMAL(10,2) NOT NULL,
  `customer_code` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
```
Para inserir as primeiras das linhas
```roomsql
INSERT INTO `orders`.`orders` (`id`, `control_number`, `date`, `name`, `unitary_value`, `quantity`, `amount`, `customer_code`) VALUES ('1', '100', '2024-01-01', 'Produto UM', '1.00', '1.00', '1.00', '1');
INSERT INTO `orders`.`orders` (`id`, `control_number`, `date`, `name`, `unitary_value`, `quantity`, `amount`, `customer_code`) VALUES ('2', '101', '2024-01-02', 'Produto DOIS', '1.50', '2.00', '3.00', '2');
INSERT INTO `orders`.`orders` (`id`, `control_number`, `date`, `name`, `unitary_value`, `quantity`, `amount`, `customer_code`) VALUES ('3', '102', '2024-01-03', 'Produto TRES', '2.00', '3.00', '6.00', '3');
INSERT INTO `orders`.`orders` (`id`, `control_number`, `date`, `name`, `unitary_value`, `quantity`, `amount`, `customer_code`) VALUES ('4', '103', '2024-01-04', 'Produto QUATRO', '2.50', '4.00', '10.00', '4');
INSERT INTO `orders`.`orders` (`id`, `control_number`, `date`, `name`, `unitary_value`, `quantity`, `amount`, `customer_code`) VALUES ('5', '104', '2024-01-05', 'Produto CINCO', '3.00', '5.00', '15.00', '5');
INSERT INTO `orders`.`orders` (`id`, `control_number`, `date`, `name`, `unitary_value`, `quantity`, `amount`, `customer_code`) VALUES ('6', '105', '2024-01-06', 'Produto SEIS', '3.50', '6.00', '19.95', '6');
INSERT INTO `orders`.`orders` (`id`, `control_number`, `date`, `name`, `unitary_value`, `quantity`, `amount`, `customer_code`) VALUES ('7', '106', '2024-01-07', 'Produto SETE', '4.00', '7.00', '26.60', '7');
INSERT INTO `orders`.`orders` (`id`, `control_number`, `date`, `name`, `unitary_value`, `quantity`, `amount`, `customer_code`) VALUES ('8', '107', '2024-01-08', 'Produto OITO', '4.50', '8.00', '34.20', '8');
INSERT INTO `orders`.`orders` (`id`, `control_number`, `date`, `name`, `unitary_value`, `quantity`, `amount`, `customer_code`) VALUES ('9', '108', '2024-01-09', 'Produto NOVE', '5.00', '9.00', '42.75', '9');
INSERT INTO `orders`.`orders` (`id`, `control_number`, `date`, `name`, `unitary_value`, `quantity`, `amount`, `customer_code`) VALUES ('10', '109', '2024-01-10', 'Produto DEZ', '5.50', '11.00', '54.45', '10');
```

Então será possível rodar a aplicação que vai subir na porta 8080

Após rodar, poderá acessar o link abaixo ou baixar a collection do postman que está no projeto

Para verificar se a API está Up com o Actuator

[Actuator](http://localhost:8080/orders-api/v1/actuator/health) (http://localhost:8080/orders-api/v1/actuator/health)

Deve retornar
`{
"status": "UP"
}`

### ENDPOINTS

1. Criar pedidos:

`POST http://localhost:8080/orders-api/v1/orders`
* Campos obrigatórios:
  * numeroControle
  * nome
  * valor
  * codigoCliente
* Campos opcionais:
  * data (A data tem o formato yyyy-MM-dd e recebe o dia atual se não for enviada)
  * quantidade (Recebe 1 se não for enviado)


* Pode ser no formato JSON
```json
{
  "pedidos": [
    {
      "numeroControle": 63,
      "nome": "Produto Um",
      "data": "2024-01-05",
      "valor": 2.50,
      "quantidade": 5,
      "codigoCliente": 40
    }
  ]
}
```
* ou no formato XML
```xml
<OrdersRequest>
    <pedidos>
        <numeroControle>62</numeroControle>
        <nome>Produto Dois</nome>
        <data>2024-01-11</data>
        <valor>3.00</valor>
        <quantidade>3</quantidade>
        <codigoCliente>20</codigoCliente>
    </pedidos>
</OrdersRequest>
```
Quando tiver sucesso retorna Status:
`201 CREATED`


2. Buscar pedidos:
* Pode ser passado filtros por parametros
  * numeroControle
  * data
  * page
* Essa consulta tem paginação
`GET http://localhost:8080/orders-api/v1/orders`

Retorna:
```json
{
  "content": [
    {
      "numeroControle": 2,
      "data": "2024-01-13",
      "nome": "Produto Tres",
      "valorUnitario": 3.00,
      "quantidade": 3.00,
      "valorTotal": 8.00,
      "codigoCliente": 1
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalElements": 1,
  "totalPages": 1,
  "first": true,
  "size": 10,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "numberOfElements": 1,
  "empty": false
}
```