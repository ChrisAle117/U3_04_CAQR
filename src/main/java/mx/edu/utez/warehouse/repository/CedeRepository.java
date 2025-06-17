
package mx.edu.utez.warehouse.repository;

import mx.edu.utez.warehouse.model.Cede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CedeRepository extends JpaRepository<Cede, Long> {
}