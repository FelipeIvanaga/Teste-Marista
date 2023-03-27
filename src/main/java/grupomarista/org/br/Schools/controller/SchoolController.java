package grupomarista.org.br.Schools.controller;

import com.turkraft.springfilter.converter.FilterSpecification;
import com.turkraft.springfilter.converter.FilterSpecificationConverter;
import grupomarista.org.br.Schools.dto.ErrorResponseDto;
import grupomarista.org.br.Schools.dto.SchoolDto;
import grupomarista.org.br.Schools.error.ResourceNotFoundException;
import grupomarista.org.br.Schools.model.SchoolModel;
import grupomarista.org.br.Schools.service.SchoolServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("school")
public class SchoolController {
    private final SchoolServiceImpl schoolService;
    private final FilterSpecificationConverter filterSpecificationConverter;

    public SchoolController(SchoolServiceImpl schoolService, FilterSpecificationConverter filterSpecificationConverter) {
        this.schoolService = schoolService;
        this.filterSpecificationConverter = filterSpecificationConverter;
    }

    @GetMapping
    public Page<SchoolModel> list(@RequestParam(value = "search", required = false) String search,
                                  @PageableDefault(value = 50) Pageable page) {
        if(search != null) {
            return this.schoolService.findAll(stringToSpecConverter(search), page);
        }
        return this.schoolService.findAll(page);
    }

    @PostMapping
    public ResponseEntity<SchoolModel> create(@RequestBody SchoolDto schoolDto) {
        return ResponseEntity.ok(this.schoolService.create(schoolDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolModel> findOne(@PathVariable UUID id) throws ResourceNotFoundException {
        return ResponseEntity.ok(this.schoolService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolModel> update(@PathVariable UUID id,
                                              @RequestBody SchoolDto schoolDto) throws ResourceNotFoundException {
        SchoolModel school =  this.schoolService.update(id, schoolDto);
        return ResponseEntity.ok(school);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id, @RequestParam Boolean softDelete) {
        this.schoolService.delete(id, softDelete);
    }

    private FilterSpecification<SchoolModel> stringToSpecConverter(String str) {
        String[] like = str.split("~");
        if (like.length == 2) {
            return filterSpecificationConverter.convert(like[0] + "~~'%" + like[1] + "%'");
        }
        String[] equal = str.split(":");
        if (equal.length == 2) {
            return filterSpecificationConverter.convert(equal[0] + ":'" + equal[1] + "'");
        }
        return filterSpecificationConverter.convert(str);
    }

    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<ErrorResponseDto> handleResourceNotFound() {
        return ResponseEntity.internalServerError().body(
            new ErrorResponseDto("500001", "Not Found", "Resource not Found")
        );
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResponseDto> handleRuntimeException() {
        return ResponseEntity.internalServerError().body(
                new ErrorResponseDto("500001", "Unexpected Error", "Runtime exception")
        );
    }
}
