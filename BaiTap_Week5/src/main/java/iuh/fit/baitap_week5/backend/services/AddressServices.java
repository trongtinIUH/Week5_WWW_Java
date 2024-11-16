package iuh.fit.baitap_week5.backend.services;

import iuh.fit.baitap_week5.backend.models.Address;
import iuh.fit.baitap_week5.backend.reponsitories.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AddressServices {

    @Autowired
    private  AddressRepository addressRepository;
    public void saveAddress(Address address) {
        addressRepository.save(address);
    }
}
