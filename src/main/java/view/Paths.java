package view;

/**
 * Paths
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public final class Paths {

    public static final String REDIRECT = "REDIRECT";

    /* JSP */
    private static final String PREFIX = "/WEB-INF/view";
    public static final String HOME_JSP = PREFIX + "/home.jsp";
    public static final String LOGIN_JSP = PREFIX + "/login.jsp";
    public static final String PATIENT_INFO_JSP = PREFIX + "/patientInfo.jsp";
    public static final String PATIENTS_JSP = PREFIX + "/patients.jsp";
    public static final String ADD_PATIENT_FORM_JSP = PREFIX + "/addPatient.jsp";
    public static final String DIAGNOSES_JSP = PREFIX + "/diagnoses.jsp";
    public static final String ASSIGNATIONS_JSP = PREFIX + "/assignations.jsp";
    public static final String ADD_ASSIGNATIONS_DRUGS_JSP = PREFIX + "/addAssignationsDrugs.jsp";
    public static final String ADD_ASSIGNATIONS_PROCEDURES_JSP = PREFIX + "/addAssignationsProcedures.jsp";
    public static final String ADD_ASSIGNATIONS_SURGERIES_JSP = PREFIX + "/addAssignationsSurgeries.jsp";

    /* Paths for Commands */
    public static final String SHOW_LOGIN_FORM = "/show_login_form";
    public static final String SHOW_REGISTRATION_FORM = "/show_registration_form";
    public static final String HOME = "/home";
    public static final String LOGIN = "/login";
    public static final String SHOW_PATIENTS = "/show_patients";
    public static final String SHOW_PATIENT_INFO = "/show_patient_info";
    public static final String SHOW_ADD_PATIENT_FORM = "/show_add_patient_form";
    public static final String ADD_PATIENT = "/add_patient";
    public static final String SET_DIAGNOSIS = "/set_diagnosis";
    public static final String SHOW_ASSIGNATIONS = "/show_assignations";
    public static final String ADD_DIAGNOSIS = "/add_diagnosis";
    public static final String SHOW_ADD_ASSIGNATIONS_DRUGS_FORM = "/show_add_assignations_drugs_form";
    public static final String ADD_ASSIGNATIONS_DRUGS = "/add_assignations_drugs";
    public static final String SHOW_ADD_ASSIGNATIONS_PROCEDURES_FORM = "/show_add_assignations_procedures_form";
    public static final String ADD_ASSIGNATIONS_PROCEDURES = "/add_assignations_procedures";
    public static final String SHOW_ADD_ASSIGNATIONS_SURGERIES_FORM = "/show_add_assignations_drugs_surgeries";
    public static final String ADD_ASSIGNATIONS_SURGERIES = "/add_assignations_surgeries";

    /* REST */
    private static final String REST = "/rest";
    public static final String REST_HOME = REST + HOME;
    public static final String REST_SHOW_PATIENTS = REST + SHOW_PATIENTS;
    public static final String REST_SHOW_PATIENT_INFO = REST + SHOW_PATIENT_INFO;
}
