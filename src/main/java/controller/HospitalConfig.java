package controller;

import java.util.ResourceBundle;

/**
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public interface HospitalConfig {
    ResourceBundle config = ResourceBundle.getBundle("hospital_config");
    String MESSAGES = config.getString("messages.bundle");
}
