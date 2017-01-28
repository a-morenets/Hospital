package controller.commands;

import view.Paths;

import java.util.HashMap;
import java.util.Map;

/**
 * CommandsHolder
 * Created by alexey.morenets@gmail.com on 27.01.2017.
 */
public class CommandsHolder {

    private static final String GET = "GET:";
    private static final String POST = "POST:";

    private Map<String, Command> commands;

    public CommandsHolder() {
        initCommands();
    }

    public void initCommands() {
        commands = new HashMap<String, Command>() {
            {
                put(GET + Paths.HOME, new HomeCommand());
                put(GET + Paths.SHOW_LOGIN_FORM, new ShowLoginFormCommand());
                put(GET + Paths.SHOW_ADD_PATIENT_FORM, new ShowAddPatientFormCommand());
                put(GET + Paths.SHOW_PATIENT_INFO, new ShowPatientInfoCommand());
                put(GET + Paths.SHOW_PATIENTS, new ShowPatientsCommand());
                put(GET + Paths.SHOW_ASSIGNATIONS, new ShowAssignationsCommand());
                put(GET + Paths.SET_DIAGNOSIS, new SetDiagnosisCommand());
                put(GET + Paths.SHOW_ADD_ASSIGNATIONS_DRUGS_FORM, new ShowAddAssignationsDrugsFormCommand());
                put(GET + Paths.SHOW_ADD_ASSIGNATIONS_PROCEDURES_FORM, new ShowAddAssignationsProceduresFormCommand());
                put(GET + Paths.SHOW_ADD_ASSIGNATIONS_SURGERIES_FORM, new ShowAddAssignationsSurgeriesFormCommand());

                put(POST + Paths.LOGIN, new LoginCommand());
                put(POST + Paths.SHOW_PATIENTS, new ShowPatientsCommand());
                put(POST + Paths.ADD_PATIENT, new AddPatientCommand());
                put(POST + Paths.SHOW_PATIENT_INFO, new ShowPatientInfoCommand());
                put(POST + Paths.ADD_DIAGNOSIS, new AddDiagnosisCommand());
                put(POST + Paths.ADD_ASSIGNATIONS_DRUGS, new AddAssignationsDrugsCommand());
                put(POST + Paths.ADD_ASSIGNATIONS_PROCEDURES, new AddAssignationsProceduresCommand());
                put(POST + Paths.ADD_ASSIGNATIONS_SURGERIES, new AddAssignationsSurgeriesCommand());
            }
        };
    }

    public Command getCommand(String key) {
        return commands.getOrDefault(key, (req, resp) -> Paths.REDIRECT);
    }
}
