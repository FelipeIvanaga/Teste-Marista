package grupomarista.org.br.Schools.service;

import grupomarista.org.br.Schools.dto.SchoolDto;
import grupomarista.org.br.Schools.error.ResourceNotFoundException;
import grupomarista.org.br.Schools.model.SchoolModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public interface SchoolService {
    Page<SchoolModel> findAll(Pageable page);
    Page<SchoolModel> findAll(Specification<SchoolModel> spec, Pageable page);
    SchoolModel findOne(UUID id) throws ResourceNotFoundException;
    SchoolModel create(SchoolDto schoolDto);
    SchoolModel update(UUID id, SchoolDto schoolDto) throws ResourceNotFoundException;
    void delete(UUID id, Boolean softDelete);
}
