package uz.billsplitter.demo.configutation;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import uz.billsplitter.demo.constant.CommissionType;

import java.math.BigDecimal;

@Component
@ConfigurationProperties(prefix = "bill.commission")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommissionProperties {
    CommissionType type;
    BigDecimal value;
}
