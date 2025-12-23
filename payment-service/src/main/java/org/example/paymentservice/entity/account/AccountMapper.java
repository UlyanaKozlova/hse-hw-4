package org.example.paymentservice.entity.account;

import org.example.paymentservice.dto.account.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "balance", target = "balance")
    AccountResponse accountToResponse(Account account);
}
