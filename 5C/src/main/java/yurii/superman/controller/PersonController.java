package yurii.superman.controller;

import yurii.superman.DTO.DTOBuilder;
import yurii.superman.DTO.impl.PersonDTO;
import yurii.superman.domain.Person;
import yurii.superman.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yurii.superman.exceptions.*;

import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping(value = "/api/person/gender/{id_gender}")
    public ResponseEntity<List<PersonDTO>> getPersonsByGenderId(@PathVariable Long id_gender) throws Exception {
        List<Person> personList = personService.getPersonsByGenderId(id_gender);

        Link link = linkTo(methodOn(PersonController.class).getPersonsByGenderId(id_gender)).withSelfRel();

        List<PersonDTO> personDTO = DTOBuilder.buildDtoListForCollection(personList, PersonDTO.class, link);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/person/{id_person}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Long id_person) throws Exception {
        Person person = personService.getPerson(id_person);
        Link link = linkTo(methodOn(PersonController.class).getPerson(id_person)).withSelfRel();
        PersonDTO personDTO = DTOBuilder.buildDtoForEntity(person, PersonDTO.class, link);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/person")
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        List<Person> personList=personService.getAllPersons();
        Link link = linkTo(methodOn(PersonController.class).getAllPersons()).withSelfRel();
        List<PersonDTO> cities = DTOBuilder.buildDtoListForCollection(personList, PersonDTO.class, link);
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping(value = "/api/person/messanger/{id_messanger}")
    public ResponseEntity<List<PersonDTO>> getPersonsByMessangerId(@PathVariable Long id_messanger) throws Exception {
        Set<Person> personList = personService.getPersonsByMessangerId(id_messanger);
        Link link = linkTo(methodOn(PersonController.class).getPersonsByMessangerId(id_messanger)).withSelfRel();
        List<PersonDTO> personDTO = DTOBuilder.buildDtoListForCollection(personList, PersonDTO.class, link);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/person/gender/{id_gender}")
    public  ResponseEntity<PersonDTO> addPerson(@RequestBody Person newPerson, @PathVariable Long id_gender)
            throws Exception {
        personService.createPerson(newPerson, id_gender);
        Link link = linkTo(methodOn(PersonController.class).getPerson(newPerson.getId())).withSelfRel();
        PersonDTO personDTO = DTOBuilder.buildDtoForEntity(newPerson,PersonDTO.class, link);
        return new ResponseEntity<>(personDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/person/{id_person}/gender/{id_gender}")
    public  ResponseEntity<PersonDTO> updatePerson(@RequestBody Person updatePerson,
                                                   @PathVariable Long id_person, @PathVariable Long id_gender)
            throws Exception {
        personService.updatePerson(updatePerson, id_person, id_gender);
        Person person =personService.getPerson(id_person);
        Link link = linkTo(methodOn(PersonController.class).getPerson(id_person)).withSelfRel();
        PersonDTO personDTO = DTOBuilder.buildDtoForEntity(person,PersonDTO.class, link);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/person/{id_person}")
    public  ResponseEntity deletePerson(@PathVariable Long id_person) throws Exception {
        personService.deletePerson(id_person);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/api/person/{id_person}/messanger/{id_messanger}")
    public  ResponseEntity<PersonDTO> addBookForPerson(@PathVariable Long id_person, @PathVariable Long id_messanger)
            throws Exception {
        personService.addMessangerForPerson(id_person,id_messanger);
        Person person = personService.getPerson(id_person);
        Link link = linkTo(methodOn(PersonController.class).getPerson(id_person)).withSelfRel();
        PersonDTO personDTO = DTOBuilder.buildDtoForEntity(person,PersonDTO.class, link);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/person/{id_person}/messanger/{id_messanger}")
    public  ResponseEntity<PersonDTO> removeBookForPerson(@PathVariable Long id_person, @PathVariable Long id_messanger)
            throws Exception {
        personService.removeMessangerForPerson(id_person,id_messanger);
        Person person = personService.getPerson(id_person);
        Link link = linkTo(methodOn(PersonController.class).getPerson(id_person)).withSelfRel();
        PersonDTO personDTO = DTOBuilder.buildDtoForEntity(person,PersonDTO.class, link);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

}
