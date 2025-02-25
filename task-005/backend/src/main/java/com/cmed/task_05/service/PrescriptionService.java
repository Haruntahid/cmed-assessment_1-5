package com.cmed.task_05.service;

import com.cmed.task_05.exception.NotFoundException;
import com.cmed.task_05.model.dtos.PrescriptionDto;
import com.cmed.task_05.model.entity.Prescription;
import com.cmed.task_05.model.mapper.PrescriptionMapper;
import com.cmed.task_05.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository repository;
    private final PrescriptionMapper mapper;
    private final RestTemplate restTemplate;

    public Prescription findById(Long id) {
        Optional<Prescription> existingPrescription = repository.findPrescriptionById(id);

        if (existingPrescription.isEmpty()) {
            throw new NotFoundException("Prescription not found for id " + id);
        }
        return existingPrescription.get();
    }


    public void savePrescription(PrescriptionDto dto) {
        Prescription prescription = mapper.dtoToPrescription(dto);
        repository.save(prescription);
    }

    public void updatePrescription(Long id, PrescriptionDto dto) {
        Prescription prescription = findById(id);
        mapper.updatePrescriptionFromDto(dto, prescription);
        repository.save(prescription);
    }


    public void deletePrescription(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    public Page<Prescription> getAllPrescriptions(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        if (startDate == null || endDate == null) {
            startDate = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
            endDate = LocalDate.now();

            repository.dateFilter(startDate.toString(), endDate.toString(), pageable);
        }
        return repository.dateFilter(startDate.toString(), endDate.toString(), pageable);
    }

    public List<Map<String, Object>> dayWiseCount() {
        List<Object[]> reports = repository.getDayWisePrescriptionCount();

        return reports.stream().map(report -> {
            Map<String, Object> map = new HashMap<>();
            map.put("date", report[0]);
            map.put("count", report[1]);
            return map;
        }).toList();
    }


    public Object getDataFromExternalApi() {
        String url = "https://rxnav.nlm.nih.gov/REST/interaction/interaction.json?rxcui=341248";
        ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
        return response.getBody();
    }




}
