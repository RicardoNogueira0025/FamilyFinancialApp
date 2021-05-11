package switchtwentytwenty.project.interfaceadapters.implrepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa.FamilyDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyJPA;
import switchtwentytwenty.project.datamodel.domainjpa.PersonIDJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.IFamilyRepositoryJPA;
import switchtwentytwenty.project.domain.aggregates.family.Family;
import switchtwentytwenty.project.domain.valueobject.FamilyID;
import switchtwentytwenty.project.domain.valueobject.PersonID;
import switchtwentytwenty.project.exceptions.UserIsNotAdminException;
import switchtwentytwenty.project.usecaseservices.irepositories.IFamilyRepository;

import java.util.*;

@Repository
public class FamilyRepository implements IFamilyRepository {

    private final IFamilyRepositoryJPA familyRepositoryJPA;

    private final FamilyDataDomainAssembler familyAssembler;

    @Autowired
    public FamilyRepository (IFamilyRepositoryJPA iFamilyRepositoryJPA, FamilyDataDomainAssembler familyDataDomainAssembler) {
        this.familyRepositoryJPA = iFamilyRepositoryJPA;
        this.familyAssembler = familyDataDomainAssembler;
    }


    /**
     * Method to add a Family domain object to the repository.
     * The Family domain object will be converted into a FamilyJPA data object and saved in the repository.
     *
     * @param family domain object we want to add to the family repository
     * @return returns a domain object of the type Family with the id generated in the database
     */
    @Override
    public Family add(Family family) {
        FamilyJPA registeredFamilyJPA;
        Family registeredFamily;
        FamilyJPA familyJPA = familyAssembler.toData(family);
        registeredFamilyJPA = familyRepositoryJPA.save(familyJPA);
        registeredFamily = familyAssembler.toDomain(registeredFamilyJPA);
        return registeredFamily;
    }

    /**
     * Method to retrieve a Family domain object by presenting a FamilyID
     *
     * @param familyID domain object of the Family we want to obtain
     * @return Family domain object
     */
    public Family getByID(FamilyID familyID) {
        FamilyIDJPA familyIDJPA = new FamilyIDJPA(familyID.toString());
        Optional<FamilyJPA> familyJPA = familyRepositoryJPA.findById(familyIDJPA);
        if (familyJPA.isPresent()) {
            return familyAssembler.toDomain(familyJPA.get());
        } else {
            throw new IllegalArgumentException("Family does not exists");
        }
    }


    public void verifyAdmin(PersonID loggedUserID) {
        boolean result = false;

        PersonIDJPA personIDJPA = new PersonIDJPA(loggedUserID.toString());

        // Substitui para evitar instaciar-se todas as families, assim a query é já feita apenas pelo personID
        result = familyRepositoryJPA.existsByAdminID(personIDJPA);

        if (!result) {
            throw new UserIsNotAdminException();
        }


    }


}