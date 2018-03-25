package service.options;

import model.Employee;
import model.Right;
import model.Role;
import repository.security.RightsRolesRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static database.Constants.Rights.RIGHTS;

public class OptionServiceImpl implements OptionsService{

    private final RightsRolesRepository rightsRolesRepository;

    public OptionServiceImpl(RightsRolesRepository rightsRolesRepository) {
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public List<Boolean> getOptions(Employee employee) {
        List<String> allRights = new ArrayList<>(Arrays.asList(RIGHTS));
        List<Boolean> options = new ArrayList<>(allRights.size());

        List<String> actualRights = new ArrayList<>();
        for(Role r: employee.getRoles()){
            actualRights.addAll(rightsRolesRepository.findRightsForRole(r.getId())
                    .stream().map(Right::getRight).collect(Collectors.toList()) );
        }
        for(int i = 0; i < allRights.size(); i++){
            if(actualRights.contains(allRights.get(i))){
                options.add(true);
            } else {
                options.add(false);
            }
        }

        return options;
    }
}
