package uz.billsplitter.demo.dto.request;

import java.util.List;

public record BillRequest(
        List<ItemRequest> items
) {}
