package yurii.superman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurii.superman.Repository.GenderRepository;
import yurii.superman.Repository.MessangerRepository;
import yurii.superman.Repository.PersonRepository;
import yurii.superman.domain.Gender;
import yurii.superman.domain.Messanger;
import yurii.superman.domain.Person;
import yurii.superman.exceptions.*;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    GenderRepository genderRepository;

    @Autowired
    MessangerRepository messangerRepository;

    @Transactional
    public void createPerson(Person person, Long id_gender) throws Exception {
        Gender gender;
        if(genderRepository.findById(id_gender).isPresent()){
            gender = genderRepository.findById(id_gender).get();
        } else{
            throw new Exception("No gender with such id");
        }
        person.setGender(gender);
        personRepository.save(person);
    }

    public Person getPerson(Long id_person) throws Exception {
        if(personRepository.findById(id_person).isPresent()){
            return personRepository.findById(id_person).get();
        } else{
            throw new Exception("No person with such id");
        }
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public List<Person> getPersonsByGenderId(Long id_gender) throws Exception {
        if(genderRepository.findById(id_gender).isPresent()){
            return genderRepository.findById(id_gender).get().getPersons();
        } else{
            throw new Exception("No gender with such id");
        }
    }

    public Set<Person> getPersonsByMessangerId(Long id_messanger) throws Exception {
        if(messangerRepository.findById(id_messanger).isPresent()){
            return messangerRepository.findById(id_messanger).get().getPersons();
        } else{
            throw new Exception("No messanger with such id");
        }
    }

    @Transactional
    public void updatePerson(Person updatePerson, Long id_person, Long id_gender) throws Exception{
        Gender gender;
        if(genderRepository.findById(id_gender).isPresent()){
            gender = genderRepository.findById(id_gender).get();
        } else{
            throw new Exception("No gender with such id");
        }

        Person person;
        if(personRepository.findById(id_person).isPresent()){
            person = personRepository.findById(id_person).get();
        } else{
            throw new Exception("No person with such id");
        }
        //update
        person.setSurname(updatePerson.getSurname());
        person.setName(updatePerson.getName());
        person.setGender(gender);
        personRepository.save(person);
    }

    @Transactional
    public void deletePerson(Long id_person) throws Exception{
        Person person;
        if(personRepository.findById(id_person).isPresent()){
            person = personRepository.findById(id_person).get();
        } else{
            throw new Exception("No person with such id");
        }
        personRepository.delete(person);
    }

    @Transactional
    public void addMessangerForPerson(Long id_person, Long id_messanger) throws Exception {
        Person person;
        if(personRepository.findById(id_person).isPresent()){
            person = personRepository.findById(id_person).get();
        } else{
            throw new Exception("No person with such id");
        }

        Messanger messanger;
        if(messangerRepository.findById(id_messanger).isPresent()){
            messanger = messangerRepository.findById(id_messanger).get();
        } else{
            throw new Exception("No messanger with such id");
        }

        if (person.getMessangers().contains(messanger)) throw new Exception("This messanger is already " +
                "linked to this person");
        person.getMessangers().add(messanger);
        personRepository.save(person);
    }

    @Transactional
    public void removeMessangerForPerson(Long id_person, Long id_messanger) throws Exception {
        Person person;
        if(personRepository.findById(id_person).isPresent()){
            person = personRepository.findById(id_person).get();
        } else{
            throw new Exception("No person with such id");
        }

        Messanger messanger;
        if(messangerRepository.findById(id_messanger).isPresent()){
            messanger = messangerRepository.findById(id_messanger).get();
        } else{
            throw new Exception("No messanger with such id");
        }

        if (!person.getMessangers().contains(messanger)) throw new Exception("This person hasn`t got the messanger");
        person.getMessangers().remove(messanger);
        personRepository.save(person);
    }


}
