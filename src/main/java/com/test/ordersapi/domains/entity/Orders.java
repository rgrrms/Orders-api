package com.test.ordersapi.domains.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "control_number")
    private Long controlNumber;

    private LocalDate date;

    private String name;

    @Column(name = "unitary_value")
    private BigDecimal unitaryValue;

    private BigDecimal quantity;

    private BigDecimal amount;

    @Column(name = "customer_code")
    private Long customerCode;
}
//CREATE TABLE `orders`.`orders` (
//  `id` INT NOT NULL AUTO_INCREMENT,
//  `control_number` INT NOT NULL,
//  `date` DATE NOT NULL,
//  `name` VARCHAR(100) NOT NULL,
//  `unitary_value` DECIMAL(10) NOT NULL,
//  `quantity` DECIMAL(10) NOT NULL,
//  `amount` DECIMAL(10) NOT NULL,
//  `customer_code` INT NOT NULL,
//  PRIMARY KEY (`id`),
//  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);