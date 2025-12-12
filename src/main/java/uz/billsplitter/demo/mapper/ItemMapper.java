package uz.billsplitter.demo.mapper;


import uz.billsplitter.demo.dto.ItemDto;
import uz.billsplitter.demo.dto.request.ItemRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto toEntity(ItemRequest request);

}
