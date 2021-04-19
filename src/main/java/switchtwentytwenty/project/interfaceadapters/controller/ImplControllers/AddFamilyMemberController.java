package switchtwentytwenty.project.interfaceadapters.controller.ImplControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import switchtwentytwenty.project.dto.AddPersonFormDTO;
import switchtwentytwenty.project.interfaceadapters.controller.IControllers.IAddFamilyMemberController;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IAddFamilyMemberService;

@Controller
public class AddFamilyMemberController implements IAddFamilyMemberController {
    
    @Autowired
    IAddFamilyMemberService addPersonService;

    public boolean addFamilyMember(AddPersonFormDTO addPersonFormDTO) {
        boolean result;

        try {
            addPersonService.addPerson(addPersonFormDTO);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}