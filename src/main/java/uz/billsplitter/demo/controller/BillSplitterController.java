package uz.billsplitter.demo.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.billsplitter.demo.dto.request.BillRequest;
import uz.billsplitter.demo.dto.response.BillResponse;
import uz.billsplitter.demo.service.BillSplitterService;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("api/bill")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BillSplitterController {

    BillSplitterService billSplitterService;

    @PostMapping("/split")
    public ResponseEntity<BillResponse> split(@Valid @RequestBody BillRequest billRequest) {
        BillResponse response = billSplitterService.split(billRequest);
        return ResponseEntity.ok(response);
    }
}
