package org.jisu.e_store_user_service.controller;

import lombok.RequiredArgsConstructor;
import org.jisu.e_store_user_service.request.AddressRequest;
import org.jisu.e_store_user_service.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private  final AddressService addressService;

    @PostMapping
    public ResponseEntity<?> addNewAddress(@RequestBody AddressRequest addressRequest){
        return new ResponseEntity<>(addressService.addAddress(addressRequest), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllUserAddress(){
        return new ResponseEntity<>(addressService.getAllAddress(),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAddressOfUser(
            @RequestParam("id") Integer id
    ){
        return new ResponseEntity<>(addressService.deleteAddress(id),HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> getAddressById(
            @PathVariable Integer id
    ){
        return new ResponseEntity<>(addressService.getAddressById(id),HttpStatus.OK);
    }
}
