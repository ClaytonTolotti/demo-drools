package com.clayton.drools.repository;

import com.clayton.drools.entity.AnalyzesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyzesRepository extends JpaRepository<AnalyzesEntity, Long> {
}
