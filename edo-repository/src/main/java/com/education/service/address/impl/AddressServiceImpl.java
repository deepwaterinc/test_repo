package com.education.service.address.impl;

import com.education.entity.Address;
import com.education.model.dto.AddressDto;
import com.education.repository.AddressRepository;
import com.education.service.address.AddressService;
import static com.education.util.DtoConverter.*;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service for entity Address
 */
@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    /**
     * Repository
     */
    private AddressRepository addressRepository;

    /**
     * Save method
     * @param obj AddressDto
     * @return AddressDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddressDto save(AddressDto obj) {
        Address address = convertFrom(obj);
        return convertTo(addressRepository.saveAndFlush(address));
    }

    /**
     * Delete method
     * @param id Long
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

    /**
     * findById
     * @param id Long
     * @return Optional<Address>
     */
    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    /**
     * findAllById
     * @param list List<Long></Long>
     * @return List<Address>
     */
    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Address> findAllById(Iterable<Long> list) {
        return addressRepository.findAllById(list);
    }
}
