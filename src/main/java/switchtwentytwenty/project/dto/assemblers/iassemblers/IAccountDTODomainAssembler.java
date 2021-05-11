package switchtwentytwenty.project.dto.assemblers.iassemblers;

import switchtwentytwenty.project.domain.aggregates.account.IAccount;
import switchtwentytwenty.project.dto.accounts.OutputAccountDTO;

public interface IAccountDTODomainAssembler {

    public OutputAccountDTO toDTO(IAccount IAccount);


    //TODO: método para cada value object
}