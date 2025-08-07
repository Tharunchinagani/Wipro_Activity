package com.user.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.user.model.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {}
