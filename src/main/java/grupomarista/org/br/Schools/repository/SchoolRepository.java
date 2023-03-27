package grupomarista.org.br.Schools.repository;

import grupomarista.org.br.Schools.model.SchoolModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolModel, UUID>, JpaSpecificationExecutor<SchoolModel> {
}
