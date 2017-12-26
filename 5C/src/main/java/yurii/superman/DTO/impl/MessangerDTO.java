package yurii.superman.DTO.impl;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import yurii.superman.DTO.DTO;
import yurii.superman.controller.PersonController;
import yurii.superman.domain.Messanger;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class MessangerDTO extends DTO<Messanger> {
    public MessangerDTO(Messanger messanger, Link link) throws Exception {
        super(messanger, link);
        add(ControllerLinkBuilder.linkTo(methodOn(PersonController.class).getPersonsByMessangerId(getEntity().getId())).withRel("persons"));
    }

    public Long getMessangerId(){return getEntity().getId();}

    public String getMessangerName(){return getEntity().getMessangerName();}
}
