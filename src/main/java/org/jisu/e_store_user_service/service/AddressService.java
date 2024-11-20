package org.jisu.e_store_user_service.service;

import org.jisu.e_store_user_service.request.AddressRequest;
import org.jisu.e_store_user_service.response.AddressResponse;
import org.jisu.e_store_user_service.response.ApiResponse;

import java.util.List;

public interface AddressService {
    AddressResponse addAddress(AddressRequest addressRequest);
    List<AddressResponse> getAllAddress();
    ApiResponse<?> deleteAddress(Integer addressId);
    AddressResponse getAddressById(Integer id);
}
