package view;

/**
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public interface GlobalConstants {

    String INDEX_JSP = "/index.jsp";

    /* JSP */
    String WEB_INF_VIEW = "/WEB-INF/view";
    String PATIENT_INFO_JSP = WEB_INF_VIEW + "/patientInfo.jsp";
    String PATIENTS_JSP = WEB_INF_VIEW + "/patients.jsp";
    String ADD_PATIENT_FORM_JSP = WEB_INF_VIEW + "/addPatient.jsp";

    /* Paths for Commands */
    String LOGIN = "/login";
    String SHOW_PATIENTS = "/show_patients";
    String SHOW_PATIENT_INFO = "/show_patient_info";
    String ADD_PATIENT_FORM = "/add_patient_form";
    String ADD_PATIENT = "/add_patient";

    /* REST */
    String REST = "/rest";
    String REST_SHOW_PATIENTS = REST + SHOW_PATIENTS;
}
