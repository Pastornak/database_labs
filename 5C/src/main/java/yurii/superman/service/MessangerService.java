package yurii.superman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurii.superman.Repository.MessangerRepository;
import yurii.superman.Repository.PersonRepository;
import yurii.superman.domain.Messanger;
import yurii.superman.domain.Person;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class MessangerService {
    @Autowired
    MessangerRepository messangerRepository;

    @Autowired
    PersonRepository personRepository;

    @Transactional
    public void createMessanger(Messanger messanger){
        messangerRepository.save(messanger);
    }

    public List<Messanger> getAllMessangers(){
        return messangerRepository.findAll();
    }

    public Messanger getMessanger(Long id_messanger) throws Exception{
        if(messangerRepository.findById(id_messanger).isPresent()){
            return messangerRepository.findById(id_messanger).get();
        } else{
            throw new Exception("No messanger with such id");
        }
    }

    public Set<Messanger> getMessangersByPersonId(Long id_person) throws Exception{
        Person person;
        if(personRepository.findById(id_person).isPresent()){
            person = personRepository.findById(id_person).get();
        } else{
            throw new Exception("No person with such id");
        }
        return person.getMessangers();
    }

    @Transactional
    public void updateMessanger(Messanger updateMessanger, Long id_messanger) throws Exception{
        Messanger messanger;
        if(messangerRepository.findById(id_messanger).isPresent()){
            messanger = messangerRepository.findById(id_messanger).get();
        } else{
            throw new Exception("No messanger with such id");
        }
        messanger.setMessangerName(updateMessanger.getMessangerName());
    }

    @Transactional
    public void deleteMessanger(Long id_messanger) throws Exception{
        Messanger messanger;
        if(messangerRepository.findById(id_messanger).isPresent()){
            messanger = messangerRepository.findById(id_messanger).get();
        } else{
            throw new Exception("No messanger with such id");
        }
        if(!messanger.getPersons().isEmpty()) throw new Exception("This messanger has linked persons to it");
        messangerRepository.delete(messanger);
    }
}
