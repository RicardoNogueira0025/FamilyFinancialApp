package switchtwentytwenty.project.usecaseservices.applicationservices.implappservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.PersonID;
import switchtwentytwenty.project.dto.InternalProfileDTO;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonToDTO;
import switchtwentytwenty.project.dto.person.OutputPersonDTO;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IGetFamilyMemberProfileService;
import switchtwentytwenty.project.usecaseservices.irepositories.IPersonRepository;

@Service
public class GetFamilyMemberProfileService implements IGetFamilyMemberProfileService {

    @Autowired
    IPersonRepository personRepository;

    @Autowired
    PersonToDTO personToDTO;

    public OutputPersonDTO getFamilyMemberProfile(InternalProfileDTO internalProfileDTO){
        PersonID personID = new PersonID(internalProfileDTO.unpackID());
        Person person = personRepository.getByID(personID);

        return personToDTO.createPersonProfileDTO(person);

    }

}
