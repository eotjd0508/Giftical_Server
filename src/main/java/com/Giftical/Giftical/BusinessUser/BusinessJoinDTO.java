package com.Giftical.Giftical.BusinessUser;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class BusinessJoinDTO {
    private String businessUserId;
    private String businessUserPw;
    private String businessUserPhoneNum;
    //Bank
    private BusinessBank businessBankCode;
    private String businessBankAccount;

    private String businessStoreNo;
}
