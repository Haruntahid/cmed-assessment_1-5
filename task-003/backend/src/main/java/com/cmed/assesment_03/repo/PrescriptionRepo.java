package com.cmed.assesment_03.repo;

import com.cmed.assesment_03.entity.model.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepo extends JpaRepository<Prescription, Long> {

    Optional<Prescription> findPrescriptionById(long id);

    @Query(value = "select prescription_date,COUNT(prescription_date) from prescription group by prescription_date", nativeQuery = true)
    List<Object[]> getDayWisePrescriptionCount();

    @Query(value = "select * from prescription where prescription_date between :startDate and :endDate",nativeQuery = true)
    Page<Prescription> dateFilter(String startDate, String endDate, Pageable pageable);
}
