package com.homestock.home_stock_service.domain;

import com.homestock.home_stock_service.domain.validator.ValidUnit;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Entity
@Table(schema = "homestock", name = "stock")
public class Stock implements Serializable
{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "product_id")
  private Product product;

  @NotNull
  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "unit_id")
  @ValidUnit
  private Unit unit;

  @NotNull
  private int unit_quantity;
}
