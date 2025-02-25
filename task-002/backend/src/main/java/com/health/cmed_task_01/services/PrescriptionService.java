package com.health.cmed_task_01.services;

import com.health.cmed_task_01.entity.dtos.PrescriptionDto;
import com.health.cmed_task_01.entity.mapper.PrescriptionMapper;
import com.health.cmed_task_01.entity.model.Prescription;
import com.health.cmed_task_01.exceptions.ResourceNotFoundException;
import com.health.cmed_task_01.repository.PrescriptionRepo;
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

    private final PrescriptionRepo repository;
    private final PrescriptionMapper mapper;
    private final RestTemplate restTemplate;

    public Prescription findById(Long id) {
        Optional<Prescription> existingPrescription = repository.findPrescriptionById(id);

        if (existingPrescription.isEmpty()) {
            throw new ResourceNotFoundException("Prescription not found for id " + id);
        }
        return existingPrescription.get();
    }

    public void addPrescription(PrescriptionDto dto) {
        Prescription prescription = mapper.toEntity(dto);
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

    public Page<Prescription> getAllPrescriptions(LocalDate startDate,LocalDate endDate, Pageable pageable) {
        if(startDate == null || endDate == null) {
            startDate = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
            endDate = LocalDate.now();

            repository.dateFilter(startDate.toString(),endDate.toString(),pageable);
        }
        return repository.dateFilter(startDate.toString(),endDate.toString(),pageable);
    }

    public List<Map<String,Object>> dayWiseCount(){
        List<Object[]> results = repository.getDayWisePrescriptionCount();

        return results.stream().map(result -> {
            Map<String, Object> map = new HashMap<>();
            map.put("date", result[0]);
            map.put("count", result[1]);
            return map;
        }).toList();
    }

    public Object getDataFromExternalApi(){
//        String url = "https://rxnav.nlm.nih.gov/REST/interaction/interaction.json?rxcui=341248";
        String url = "https://jsonplaceholder.typicode.com/posts";
        ResponseEntity<?> response = restTemplate.getForEntity(url, Object.class);
        return response.getBody();
    }

}
