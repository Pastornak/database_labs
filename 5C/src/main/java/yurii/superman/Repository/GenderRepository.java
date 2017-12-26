package yurii.superman.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yurii.superman.domain.Gender;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long>{
}
