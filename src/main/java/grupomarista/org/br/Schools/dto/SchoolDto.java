package grupomarista.org.br.Schools.dto;

import grupomarista.org.br.Schools.model.StatusEnum;
import lombok.Getter;
import java.util.List;

@Getter
public class SchoolDto {
    private int inep;
    private String name;
    private String address;
    private String number;
    private StatusEnum status;
    private List<LevelDto> levels;
}
