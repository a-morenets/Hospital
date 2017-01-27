package i18n;

import java.util.Locale;

/**
 * SupportedLocale
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public enum SupportedLocale {
    UA (new Locale("uk", "UA"), "ua"),
    EN (new Locale("en", "EN"), "en"),
    RU (new Locale("ru", "RU"), "ru");

    final private Locale locale;
    final private String param;
    final static private SupportedLocale defaultLocale = EN;

    SupportedLocale(Locale locale, String param) {
        this.locale = locale;
        this.param = param;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getParam() {
        return param;
    }

    public static Locale getDefault() {
        return defaultLocale.getLocale();
    }

}