package com.clayton.drools.repository;

import com.clayton.drools.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{

    Optional<EmployeeEntity> findByNameIgnoreCase(String name);
}
