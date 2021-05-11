package switchtwentytwenty.project.interfaceadapters.controller.implcontrollers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.domain.valueobject.PersonID;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonInputDTOAssembler;
import switchtwentytwenty.project.dto.person.*;
import switchtwentytwenty.project.exceptions.EmailAlreadyRegisteredException;
import switchtwentytwenty.project.exceptions.InvalidEmailException;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IAddEmailService;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IAddFamilyMemberService;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.IGetFamilyMemberProfileService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
class PersonRESTControllerTest {


    @Mock
    PersonInputDTOAssembler mockPersonInputDTOAssembler;

    @Mock
    IAddEmailService mockAddEmailService;

    @Mock
    IGetFamilyMemberProfileService getFamilyMemberProfileService;

    @Mock
    IAddFamilyMemberService mockAddFamilyMemberService;


    @Mock
    InputAddFamilyMemberDTO anInternalAddFamilyMemberDTO;


    @Mock
    PersonInputDTOAssembler profileInternalExternalAssembler;

    //@Mock
    //OutputPersonDTO anOutputPersonDTO;

    @InjectMocks
    PersonRESTController personRESTController;
    // Este Person precisa de ser instanciado para o email lhe ser adicionado após a implementação e poder testar se adiciona?
    //Ou pode ser mocked e o Spring faz magia negra e sabe que é o Tony e adiciona-lhe o email?
    //Person personToAddEmail;

    String emailAddressAsID = "tonyze@latinlover.com";
    String emailToAdd = "tony@emailtoadd.com";
    String invalidEmailToAdd = "invalidemail.com";
    InputEmailDTO internalEmailDTO = new InputEmailDTO(emailAddressAsID, emailToAdd);

    OutputEmailDTO outputEmailDTO = new OutputEmailDTO(emailToAdd);

    AddEmailDTO addEmailDTO = new AddEmailDTO(emailAddressAsID, emailToAdd);
    AddEmailDTO INVALIDAddEmailDTO = new AddEmailDTO(emailAddressAsID, invalidEmailToAdd);
    InputEmailDTO INVALIDInternalEmailDTO = new InputEmailDTO(INVALIDAddEmailDTO.unpackUserID(), INVALIDAddEmailDTO.unpackEmail());

    AddFamilyMemberDTO addFamilyMemberDTO = new AddFamilyMemberDTO("2L", "3L", "tony", "12/02/1999", 123456789, 961962963, "Rua da Estrada", "Porto", "12", "4000");


    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        // We can create mock based on the Interface or a Class
//    	templateEngine = mock(TemplateEngine.class);

        // init mock with annotations
        //MockitoAnnotations.initMocks(this);

        // just another version of initilization of mocks with annotation
        // pay attention to tear down method - we have to call close method
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }


    OutputPersonDTO realOutPutPersonDTO = new OutputPersonDTO();


    @Disabled
    @Test
    @DisplayName("Success case of adding an email to a Person")
    void successCaseInAddEmail() {

        when(mockPersonInputDTOAssembler.toInputEmailDTO(addEmailDTO)).thenReturn(internalEmailDTO);
        when(mockAddEmailService.addEmail(internalEmailDTO)).thenReturn(outputEmailDTO);


        OutputEmailDTO expectedOutputEmailDTO = new OutputEmailDTO(emailAddressAsID);
        //    Link link = linkTo(methodOn(IPersonRESTController.class).getEmail(personID.toString(), outputEmailDTO.unpackEmailID())).withSelfRel();
        //    expectedOutputEmailDTO.add(link);

        ResponseEntity expected = new ResponseEntity(expectedOutputEmailDTO, HttpStatus.OK);

        ResponseEntity result = personRESTController.addEmail(addEmailDTO);

        assertEquals(expected, result);
    }

    @Disabled
    @DisplayName("Fail test when Email is already registered in the Person")
    @Test
    void failCaseInAddEmailWhenProvidedEmailIsAlreadyRegisteredInThePerson() {

        when(mockPersonInputDTOAssembler.toInputEmailDTO(addEmailDTO)).thenReturn(internalEmailDTO);
        when(mockAddEmailService.addEmail(internalEmailDTO)).thenThrow(EmailAlreadyRegisteredException.class);


        ResponseEntity expected = new ResponseEntity("Error message to be implemented", HttpStatus.BAD_REQUEST);

        ResponseEntity result = personRESTController.addEmail(addEmailDTO);

        assertEquals(expected, result);
    }

    @Disabled
    @DisplayName("Fail test when Email is in invalid format")
    @Test
    void failCaseInAddEmailWhenProvidedEmailIsWrongfullyInsertedExpectingInvalidEmailException() {

        when(mockPersonInputDTOAssembler.toInputEmailDTO(INVALIDAddEmailDTO)).thenReturn(INVALIDInternalEmailDTO);
        when(mockAddEmailService.addEmail(INVALIDInternalEmailDTO)).thenThrow(InvalidEmailException.class);

        //Sem certeza que Bad_Request se enquadra neste HttpStatus
        ResponseEntity expected = new ResponseEntity("Error message to be implemented", HttpStatus.BAD_REQUEST);

        ResponseEntity result = personRESTController.addEmail(INVALIDAddEmailDTO);

        assertEquals(expected, result);
    }

    @DisplayName("Success - Get Profile Info")
    @Test
    void successCaseInGetProfileInfo() {
        String personID = "tony@gmail.com";

        InputGetProfileDTO inputGetProfileDTO = new InputGetProfileDTO();

        when(profileInternalExternalAssembler.toInternalGetProfileDTO(any(String.class))).thenReturn(inputGetProfileDTO);

        OutputPersonDTO outputPersonDTO = new OutputPersonDTO();

        when(getFamilyMemberProfileService.getFamilyMemberProfile(inputGetProfileDTO)).thenReturn(outputPersonDTO);

        OutputPersonDTO expectedOutputPersonDTO = new OutputPersonDTO();

        Link expectedLink = linkTo(methodOn(PersonRESTController.class).getPersonOptions(personID)).withSelfRel();

        expectedOutputPersonDTO.add(expectedLink);

        ResponseEntity expectedResponse = new ResponseEntity(expectedOutputPersonDTO, HttpStatus.FOUND);

        ResponseEntity resultResponse = personRESTController.getProfileInfo(personID);

        //assertEquals(expectedResponse, resultResponse);
        assertEquals(expectedResponse.getBody(), resultResponse.getBody());
        assertEquals(expectedResponse.getStatusCode(), resultResponse.getStatusCode());
    }

    @DisplayName("Unsuccess - Get Profile Info - throws EmailNotRegisteredException")
    @Test
    void unsuccessCaseInGetProfileInfo() {
        String personID = "tony@gmail.com";

        InputGetProfileDTO inputGetProfileDTO = new InputGetProfileDTO();

        when(profileInternalExternalAssembler.toInternalGetProfileDTO(any(String.class))).thenReturn(inputGetProfileDTO);

        when(getFamilyMemberProfileService.getFamilyMemberProfile(any(InputGetProfileDTO.class))).thenThrow(EmailAlreadyRegisteredException.class);

        assertThrows(EmailAlreadyRegisteredException.class, () -> personRESTController.getProfileInfo(personID));

    }

    @DisplayName("Controller-level Unit test for a success case in adding family member")
    @Test
    void successCaseInAddFamilyMember() {
        OutputPersonDTO expectedOutputPersonDTO = new OutputPersonDTO();

        expectedOutputPersonDTO.setId("id@gmail.com");
        realOutPutPersonDTO.setId("id@gmail.com");
        when(mockPersonInputDTOAssembler.toInputAddFamilyMemberDTO(addFamilyMemberDTO)).thenReturn(anInternalAddFamilyMemberDTO);
        when(mockAddFamilyMemberService.addPerson(any(InputAddFamilyMemberDTO.class))).thenReturn(realOutPutPersonDTO);


        Link link = linkTo(methodOn(PersonRESTController.class).getPersonOptions("id@gmail.com")).withRel("Person Options");
        expectedOutputPersonDTO.add(link);

        ResponseEntity expected = new ResponseEntity(expectedOutputPersonDTO, HttpStatus.CREATED);

        ResponseEntity result = personRESTController.addFamilyMember(addFamilyMemberDTO);


        assertEquals(expected.getBody(), result.getBody());
        assertEquals(expected.getStatusCode(), result.getStatusCode());


    }


}