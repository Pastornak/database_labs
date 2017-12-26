package yurii.superman.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yurii.superman.domain.Messanger;

@Repository
public interface MessangerRepository extends JpaRepository<Messanger, Long> {
}
