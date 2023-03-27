package grupomarista.org.br.Schools.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class ErrorResponseDto {
    private String code;
    private String message;
    private String details;
}
