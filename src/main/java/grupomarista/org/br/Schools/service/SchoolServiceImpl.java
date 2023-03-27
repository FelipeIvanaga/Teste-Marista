package grupomarista.org.br.Schools.service;

import grupomarista.org.br.Schools.dto.LevelDto;
import grupomarista.org.br.Schools.dto.SchoolDto;
import grupomarista.org.br.Schools.error.ResourceNotFoundException;
import grupomarista.org.br.Schools.model.LevelModel;
import grupomarista.org.br.Schools.model.SchoolModel;
import grupomarista.org.br.Schools.model.StatusEnum;
import grupomarista.org.br.Schools.repository.LevelRepository;
import grupomarista.org.br.Schools.repository.SchoolRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final LevelRepository levelRepository;

    public SchoolServiceImpl(SchoolRepository schoolRepository,
                             LevelRepository levelRepository) {
        this.schoolRepository = schoolRepository;
        this.levelRepository = levelRepository;
    }

    public Page<SchoolModel> findAll(Pageable page) {
        return schoolRepository.findAll(page);
    }

    public Page<SchoolModel> findAll(Specification<SchoolModel> spec, Pageable page) {
        return this.schoolRepository.findAll(spec, page);
    }

    public SchoolModel findOne(UUID id) throws ResourceNotFoundException {
        Optional<SchoolModel> school = this.schoolRepository.findById(id);
        if (school.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return school.get();
    }

    public SchoolModel create(SchoolDto schoolDto) {
        SchoolModel school = new SchoolModel();
        school.setInep(schoolDto.getInep());
        school.setName(schoolDto.getName());
        school.setAddress(schoolDto.getAddress());
        school.setNumber(schoolDto.getNumber());
        school.setStatus(schoolDto.getStatus());
        school.setLevels(getLevels(schoolDto.getLevels()));

        this.levelRepository.saveAll(school.getLevels());
        return this.schoolRepository.save(school);
    }

    public SchoolModel update(UUID id, SchoolDto schoolDto) throws ResourceNotFoundException {
        Optional<SchoolModel> optSchool = this.schoolRepository.findById(id);

        if (optSchool.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        SchoolModel school = optSchool.get();
        school.setInep(schoolDto.getInep());
        school.setName(schoolDto.getName());
        school.setAddress(schoolDto.getAddress());
        school.setNumber(schoolDto.getNumber());
        school.setStatus(schoolDto.getStatus());
        school.setLevels(getLevels(schoolDto.getLevels()));

        this.levelRepository.saveAll(school.getLevels());
        return this.schoolRepository.save(school);
    }

    public void delete(UUID id, Boolean softDelete) {
        if (!softDelete) {
            this.schoolRepository.deleteById(id);
            return;
        }
        Optional<SchoolModel> school = this.schoolRepository.findById(id);
        if (school.isPresent()) {
            school.get().setStatus(StatusEnum.inative);
            school.get().setDeletedAt(LocalDateTime.now());
            this.schoolRepository.save(school.get());
        }
    }

    private List<LevelModel> getLevels(List<LevelDto> levelsDto) {
        ArrayList<LevelModel> levels = new ArrayList<>();
        for (LevelDto level : levelsDto) {
            LevelModel lev = this.levelRepository.findByCode(level.getCode());
            if(lev == null) {
                LevelModel newLevel = new LevelModel();
                newLevel.setName(level.getName());
                newLevel.setCode(level.getCode());
                newLevel.setStatus(level.getStatus());
                levels.add(newLevel);
            } else {
                levels.add(lev);
            }
        }
        return levels;
    }
}
