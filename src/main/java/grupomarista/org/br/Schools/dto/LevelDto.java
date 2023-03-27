package grupomarista.org.br.Schools.dto;

import grupomarista.org.br.Schools.model.StatusEnum;
import lombok.Getter;

@Getter
public class LevelDto {
    private String name;
    private String code;
    private StatusEnum status;
}
