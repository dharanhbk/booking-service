package com.booking.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

	Page<Driver> findByFkEntityCode(String string, Pageable page);

}
