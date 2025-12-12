package uz.billsplitter.demo.dto.response;

import java.math.BigDecimal;

public record PersonalBillResponse(
        String name,
        BigDecimal amount
) {}
