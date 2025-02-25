package com.cmed.task_05.repository;

import com.cmed.task_05.model.entity.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    @Query(value = "select prescription_date,COUNT(prescription_date) from prescription group by prescription_date", nativeQuery = true)
    List<Object[]> getDayWisePrescriptionCount();

    Optional<Prescription> findPrescriptionById(long id);

    @Query(value = "select * from prescription where prescription_date between :startDate and :endDate",nativeQuery = true)
    Page<Prescription> dateFilter(String startDate, String endDate, Pageable pageable);

}
