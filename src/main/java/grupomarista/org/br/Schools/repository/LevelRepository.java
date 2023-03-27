package grupomarista.org.br.Schools.repository;

import grupomarista.org.br.Schools.model.LevelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LevelRepository extends JpaRepository<LevelModel, UUID> {
    public LevelModel findByCode(String code);
}
