package uz.billsplitter.demo.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record BillRequest(
        @NotEmpty(message = "Список не может быть пустым")
        @Valid
        List<ItemRequest> items
) {}
