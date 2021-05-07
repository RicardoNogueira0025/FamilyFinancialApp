package switchtwentytwenty.project.interfaceadapters.controller.implcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import switchtwentytwenty.project.dto.AddFamilyMemberDTO;
import switchtwentytwenty.project.dto.assemblers.implassemblers.FamilyMemberExternalInternalAssembler;
import switchtwentytwenty.project.dto.family.InternalFamilyMemberDTO;
import switchtwentytwenty.project.interfaceadapters.controller.IControllers.IAddFamilyMemberController;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IAddFamilyMemberService;

@Controller
public class AddFamilyMemberController implements IAddFamilyMemberController {

    IAddFamilyMemberService addPersonService;

    FamilyMemberExternalInternalAssembler addFamilyMemberAssembler;

    @Autowired
    public AddFamilyMemberController(IAddFamilyMemberService addPersonService) {
        this.addPersonService = addPersonService;
        //this.addFamilyMemberAssembler = addFamilyMemberAssembler;
    }

    // o userID vem como string do controlador ou é logo lá é convertido em PersonID?
    public boolean addFamilyMember(AddFamilyMemberDTO addFamilyMemberDTO) {

        boolean result;
        InternalFamilyMemberDTO InternalFamilyMemberDTO = addFamilyMemberAssembler.toInner(addFamilyMemberDTO);

        //TODO: Substituir
        try {
            addPersonService.addPerson(InternalFamilyMemberDTO);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}