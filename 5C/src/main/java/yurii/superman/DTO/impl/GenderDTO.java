package yurii.superman.DTO.impl;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import yurii.superman.DTO.DTO;
import yurii.superman.controller.PersonController;
import yurii.superman.domain.Gender;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class GenderDTO  extends DTO<Gender> {
    public GenderDTO(Gender gender, Link link) throws Exception {
        super(gender, link);
        add(ControllerLinkBuilder.linkTo(methodOn(PersonController.class).getPersonsByGenderId(getEntity().getId())).withRel("persons"));
    }

    public Long getGenderId() {
        return getEntity().getId();
    }

    public String getGenderName() {
        return getEntity().getGenderName();
    }
}
