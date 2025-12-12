package uz.billsplitter.demo.dto.request;

import uz.billsplitter.demo.constant.enums.ItemType;

import java.math.BigDecimal;

public record ItemRequest(
    String name,
    BigDecimal price,
    ItemType type,
    String personName
) {}
