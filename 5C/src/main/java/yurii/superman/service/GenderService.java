package yurii.superman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yurii.superman.Repository.GenderRepository;
import yurii.superman.domain.Gender;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GenderService {
    @Autowired
    GenderRepository genderRepository;

    @Transactional
    public void createGender(Gender gender){
        genderRepository.save(gender);
    }

    public List<Gender> getAllGenders(){
        return genderRepository.findAll();
    }

    public Gender getGender(Long id_gender) throws Exception{
        if(genderRepository.findById(id_gender).isPresent()){
            return genderRepository.findById(id_gender).get();
        } else{
            throw new Exception("No gender with such id");
        }
    }

    @Transactional
    public void updateGender(Gender updateGender, Long id_gender) throws Exception{
        Gender gender;
        if(genderRepository.findById(id_gender).isPresent()){
            gender = genderRepository.findById(id_gender).get();
        } else{
            throw new Exception("No gender with such id");
        }
        gender.setGenderName(updateGender.getGenderName());
    }

    @Transactional
    public void deleteGender(Long id_gender) throws Exception{
        Gender gender;
        if(genderRepository.findById(id_gender).isPresent()){
            gender = genderRepository.findById(id_gender).get();
        } else{
            throw new Exception("No gender with such id");
        }
        if(!gender.getPersons().isEmpty()) throw new Exception("This gender has linked person to it");
        genderRepository.delete(gender);
    }
}
