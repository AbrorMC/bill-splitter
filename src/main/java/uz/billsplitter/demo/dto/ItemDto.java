package uz.billsplitter.demo.dto;

import uz.billsplitter.demo.constant.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String name;

    private BigDecimal price;

    private ItemType type;

    private String personName;
}
