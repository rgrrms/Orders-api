package com.test.ordersapi.domains.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

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
