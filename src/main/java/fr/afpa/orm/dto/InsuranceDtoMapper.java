package fr.afpa.orm.dto;

import fr.afpa.orm.entities.Insurance;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class InsuranceDtoMapper implements Function<Insurance, InsuranceDto> {
    @Override
    public InsuranceDto apply(Insurance insurance) {
        return new InsuranceDto(
                insurance.getId(),
                insurance.getName()
        );
    }
}
