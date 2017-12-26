package yurii.superman.DTO.impl;

import yurii.superman.DTO.DTO;
import yurii.superman.controller.MessangerController;
import yurii.superman.domain.Person;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class PersonDTO extends DTO<Person> {
    public PersonDTO(Person person, Link link) throws Exception {
        super(person, link);
        add(ControllerLinkBuilder.linkTo(methodOn(MessangerController.class)
                .getMessangersByPersonId(getEntity().getId())).withRel("messangers"));
    }

    public Long getPersonId() {
        return getEntity().getId();
    }

    public String getSurname() {
        return getEntity().getSurname();
    }

    public String getName() {
        return getEntity().getName();
    }

    public String getGender() {
        if (getEntity().getGender() == null) {
            return "";
        } else {
            return getEntity().getGender().getGenderName();
        }
    }
}
