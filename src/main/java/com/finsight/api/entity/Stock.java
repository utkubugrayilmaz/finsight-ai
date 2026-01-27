package com.finsight.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "stocks")
@Data //lombok
@NoArgsConstructor
@AllArgsConstructor

public class Stock{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true,length = 10)
    private String symbol;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 50)
    private String sector;

    @Column(precision = 15, scale = 4)
    private BigDecimal currentPrice;

    @Column
    private LocalDateTime lastUpdated;

}