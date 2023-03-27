package grupomarista.org.br.Schools.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class SchoolModel extends BaseModel {
    @Column(name = "inep",nullable = false)
    private int inep;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "address_number", nullable = false)
    private String number;

    @ManyToMany
    private List<LevelModel> levels;
}
