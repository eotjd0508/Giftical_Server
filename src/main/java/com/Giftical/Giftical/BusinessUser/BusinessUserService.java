package com.Giftical.Giftical.BusinessUser;

import com.Giftical.Giftical.Store.Store;
import com.Giftical.Giftical.Store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusinessUserService {
    private final BusinessUserRepository businessUserRepository;
    private final StoreRepository storeRepository;

    public BusinessUser join(BusinessJoinDTO user) {
        BusinessUser newUser = new BusinessUser(null, user.getBusinessUserId(),
                user.getBusinessUserPw(), user.getBusinessBankAccount(), user.getBusinessBankCode(),
                user.getBusinessUserPhoneNum());
        newUser = businessUserRepository.save(newUser);
        storeRepository.save(new Store(newUser.getId(), user.getBusinessStoreNo()));
        return newUser;
    }
    public ResponseEntity<List<Store>> login(String id, String pw){
        Optional<BusinessUser> found = Optional.ofNullable(businessUserRepository.findByBusinessUserId(id));

        // Wrong id
        if(found.isEmpty()) return new ResponseEntity<>(null, HttpStatus.valueOf(204));
        //
        else{
            //Wrong pw
            if( !found.get().getBusinessUserPw().equals(pw)){ return new ResponseEntity<>(null, HttpStatus.valueOf(204)); }
            //Success
            return new ResponseEntity<>( storeRepository.findByBusinessUserId(found.get().getId()), HttpStatus.valueOf(200));
        }
    }
}
