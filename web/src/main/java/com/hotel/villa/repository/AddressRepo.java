package com.hotel.villa.repository;

import com.hotel.villa.entity.Address;
import org.hibernate.mapping.OneToMany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {

    Address findAddressById(Long id);

}
