package uz.billsplitter.demo.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import uz.billsplitter.demo.configutation.CommissionProperties;
import uz.billsplitter.demo.constant.enums.CommissionType;
import uz.billsplitter.demo.constant.enums.ItemType;
import uz.billsplitter.demo.dto.ItemDto;
import uz.billsplitter.demo.dto.request.BillRequest;
import uz.billsplitter.demo.dto.response.BillResponse;
import uz.billsplitter.demo.dto.response.PersonalBillResponse;
import uz.billsplitter.demo.mapper.ItemMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BillSplitterService {

    ItemMapper mapper;
    CommissionProperties properties;

    public BillResponse split(BillRequest request) {
        List<ItemDto> items = request.items()
                .stream()
                .map(mapper::toEntity)
                .toList();

        Set<String> persons = items.stream()
                .filter(e->e.getType() == ItemType.PERSONAL)
                .map(ItemDto::getPersonName)
                .collect(Collectors.toSet());

        int personCount = persons.size();

        BigDecimal shared = items.stream()
                .filter(e->e.getType().equals(ItemType.SHARED))
                .map(ItemDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal sharedPerPerson = personCount == 0 ?
                BigDecimal.ZERO :
                shared.divide(BigDecimal.valueOf(personCount), RoundingMode.HALF_DOWN);

        BigDecimal total = items.stream()
                .map(ItemDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalWithCommission = total.add(calculateCommission(total));

        Map<String, BigDecimal> personalBills = new HashMap<>();

        persons.forEach(e->personalBills.put(e,BigDecimal.ZERO));

        items.stream()
                .filter(e->e.getType().equals(ItemType.PERSONAL))
                .forEach(e->personalBills.merge(
                        e.getPersonName(),
                        e.getPrice(),
                        BigDecimal::add
                ));

        List<PersonalBillResponse> result = new ArrayList<>();

        for (String person : persons) {
            BigDecimal totalPersonAmount = personalBills.get(person)
                    .add(sharedPerPerson)
                    .add(calculateCommission(personalBills.get(person).add(sharedPerPerson)));

            result.add(new PersonalBillResponse(person, totalPersonAmount));
        }

        return new BillResponse(totalWithCommission, result);
    }

    BigDecimal calculateCommission(BigDecimal total) {
        BigDecimal result = BigDecimal.ZERO;
        if (properties.getType() == CommissionType.PERCENT) {
            result = total
                    .multiply(properties.getValue())
                    .divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN);
        } else {
            result = properties.getValue();
        }
        return result;
    }
}
