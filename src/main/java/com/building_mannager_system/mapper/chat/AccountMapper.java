package com.building_mannager_system.mapper.chat;


import com.building_mannager_system.dto.requestDto.chat.AccountDto;
import com.building_mannager_system.entity.Account.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {


     @Mapping(source = "isOnlineWebsocket",target = "isOnlineWebsocket")
     @Mapping(source = "customer.id",target = "customerId")
    AccountDto toDto(Account account);


    @Mapping(source = "customerId",target = "customer.id")
    Account toEntity(AccountDto accountDto);

    List<AccountDto> toDtoList(List<Account> accounts);

    List<Account> toEntityList(List<AccountDto> accountDtos);
}
