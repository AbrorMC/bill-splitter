package uz.billsplitter.demo.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record BillResponse(
        BigDecimal totalAmount,
        List<PersonalBillResponse> personalBills
) {}
