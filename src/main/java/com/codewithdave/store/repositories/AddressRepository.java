package com.codewithdave.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.codewithdave.store.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {
    
}
