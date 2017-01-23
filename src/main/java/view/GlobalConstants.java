package view;

/**
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public interface GlobalConstants {

    String INDEX_JSP = "/index.jsp";

    /* JSP */
    String PREFIX = "/WEB-INF/view";
    String LOGIN_JSP = PREFIX + "/login.jsp";
    String PATIENT_INFO_JSP = PREFIX + "/patientInfo.jsp";
    String PATIENTS_JSP = PREFIX + "/patients.jsp";
    String ADD_PATIENT_FORM_JSP = PREFIX + "/addPatient.jsp";
    String DIAGNOSES_JSP = PREFIX + "/diagnoses.jsp";
    String ASSIGNATIONS_JSP = PREFIX + "/assignations.jsp";
    String ADD_ASSIGNATIONS_JSP = PREFIX + "/addAssignations.jsp";

    /* Paths for Commands */
    String LOGIN = "/login";
    String SHOW_PATIENTS = "/show_patients";
    String SHOW_PATIENT_INFO = "/show_patient_info";
    String ADD_PATIENT_FORM = "/add_patient_form";
    String ADD_PATIENT = "/add_patient";
    String SET_DIAGNOSIS = "/set_diagnosis";
    String SHOW_ASSIGNATIONS = "/show_assignations";
    String ADD_ASSIGNATIONS_FORM = "/add_assignations_form";
    String ADD_ASSIGNATIONS = "/add_assignations";
    String ADD_DIAGNOSIS = "/add_diagnosis";

    /* REST */
    String REST = "/rest";
    String REST_SHOW_PATIENTS = REST + SHOW_PATIENTS;
    String REST_SHOW_PATIENT_INFO = REST + SHOW_PATIENT_INFO;
}
