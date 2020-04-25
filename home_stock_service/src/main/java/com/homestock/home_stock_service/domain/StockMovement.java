package com.homestock.home_stock_service.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Data
@RequiredArgsConstructor
@Table(schema = "homestock", name = "stock_movement")
public class StockMovement
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    private Date date;

    @NotNull
    private Integer quantity;

    private boolean up;

    private boolean down;
}
