package config;

import java.util.ResourceBundle;

/**
 * interface HospitalConfig
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public interface HospitalConfig {
    ResourceBundle config = ResourceBundle.getBundle("i18n_config");
    String MESSAGES = config.getString("msg.bundle");
}
