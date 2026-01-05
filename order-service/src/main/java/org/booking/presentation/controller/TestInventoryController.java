package org.booking.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.booking.application.usecase.GetInventoryUsecase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/inventories")
@RequiredArgsConstructor
public class TestInventoryController {

  private final GetInventoryUsecase getInventoryUsecase;

  @GetMapping
  public String test() {
    return getInventoryUsecase.execute();
  }
}
