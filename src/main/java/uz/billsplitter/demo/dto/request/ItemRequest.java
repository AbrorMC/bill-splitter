package uz.billsplitter.demo.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import uz.billsplitter.demo.constant.enums.ItemType;

import java.math.BigDecimal;

public record ItemRequest(
    @NotBlank(message = "Назвние блюды обязательно")
    String name,

    @NotNull(message = "Цена обязательна")
    @Positive
    BigDecimal price,

    @NotNull(message = "Тип обязателен")
    ItemType type,

    String personName
) {}
