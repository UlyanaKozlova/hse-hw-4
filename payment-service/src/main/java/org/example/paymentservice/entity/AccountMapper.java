package org.example.paymentservice.entity;

import org.example.paymentservice.dto.AccountRequest;
import org.example.paymentservice.dto.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "balance", ignore = true)
    Account requestToAccount(AccountRequest accountRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "balance", target = "balance")
    AccountResponse accountToResponse(Account account);
}
