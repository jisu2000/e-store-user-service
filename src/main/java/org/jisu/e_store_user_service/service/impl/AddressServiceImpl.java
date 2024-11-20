package org.jisu.e_store_user_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.jisu.e_store_user_service.entities.AddressEO;
import org.jisu.e_store_user_service.entities.UserEO;
import org.jisu.e_store_user_service.exception.ResourceNotFoundException;
import org.jisu.e_store_user_service.exception.UnauthorizeException;
import org.jisu.e_store_user_service.repo.AddressRepo;
import org.jisu.e_store_user_service.request.AddressRequest;
import org.jisu.e_store_user_service.response.AddressResponse;
import org.jisu.e_store_user_service.response.ApiResponse;
import org.jisu.e_store_user_service.service.AddressService;
import org.jisu.e_store_user_service.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final ModelMapper modelMapper;
    private final AddressRepo addressRepo;
    private final AuthService authService;
    @Override
    public AddressResponse addAddress(AddressRequest addressRequest) {
        AddressEO goingToSaved = modelMapper.map(addressRequest,AddressEO.class);
        UserEO user = authService.getCurrentUserObject();
        goingToSaved.setUserId(user.getId());
        AddressEO saved = addressRepo.save(goingToSaved);
        return modelMapper.map(saved,AddressResponse.class);
    }

    @Override
    public List<AddressResponse> getAllAddress() {

        UserEO userEO = authService.getCurrentUserObject();
        List<AddressResponse> response =
                addressRepo.findByUserId(userEO.getId())
                        .stream()
                        .map(e -> modelMapper.map(e,AddressResponse.class))
                        .toList();

        return response;
    }

    @Override
    public ApiResponse<?> deleteAddress(Integer addressId) {
        UserEO userEO = authService.getCurrentUserObject();
        AddressEO addressEO = addressRepo.findById(addressId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Address","ID",addressId.toString()));

        if(addressEO.getUserId()!=userEO.getId()){
            throw new UnauthorizeException("You have not Access to do this");
        }

        addressRepo.delete(addressEO);

        return  new ApiResponse<String>("Address Deleted");
    }

    @Override
    public AddressResponse getAddressById(Integer addressId) {
        AddressEO addressEO = addressRepo.findById(addressId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Address","ID",addressId.toString()));
        return modelMapper.map(addressEO,AddressResponse.class);
    }
}
