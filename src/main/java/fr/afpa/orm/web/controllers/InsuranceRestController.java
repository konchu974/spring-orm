package fr.afpa.orm.web.controllers;

import fr.afpa.orm.dto.InsuranceDto;
import fr.afpa.orm.dto.InsuranceDtoMapper;
import fr.afpa.orm.entities.Insurance;
import fr.afpa.orm.repositories.InsuranceRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/insurances")
public class InsuranceRestController {

    @Autowired
    private InsuranceRepository insuranceRepository;
    private InsuranceDtoMapper insuranceDtoMapper;

    public InsuranceRestController(InsuranceRepository insuranceRepository, InsuranceDtoMapper insuranceDtoMapper) {
        this.insuranceRepository = insuranceRepository;
        this.insuranceDtoMapper = insuranceDtoMapper;
    }
    @GetMapping()
    public List<InsuranceDto> getAll() {
        List<InsuranceDto> result = new ArrayList<>();
        insuranceRepository.findAll().forEach(insurance -> result.add(insuranceDtoMapper.apply(insurance)));
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceDto> getOne(@PathVariable Integer id) {
        Insurance insurance = insuranceRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(insuranceDtoMapper.apply(insurance));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InsuranceDto create(@RequestBody InsuranceDto insuranceDto) {
        Insurance insurance = new Insurance();
        insurance.setName(insuranceDto.name());
        Insurance saved = insuranceRepository.save(insurance);
        return insuranceDtoMapper.apply(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsuranceDto> update(@PathVariable Integer id, @RequestBody InsuranceDto insuranceDto) {
        Insurance existingInsurance = insuranceRepository.findById(id).orElseThrow();
        existingInsurance.setName(insuranceDto.name());
        Insurance saved = insuranceRepository.save(existingInsurance);
        return ResponseEntity.ok(insuranceDtoMapper.apply(saved));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Integer id, HttpServletResponse response) {
        Insurance insuranceDelete = insuranceRepository.findById(id).orElseThrow();
        insuranceRepository.delete(insuranceDelete);
    }
}
