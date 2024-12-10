package patterns.dental.clinic.controller;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageController {
    private static Locale currentLocale = Locale.of("en", "CA");
    private static ResourceBundle currentBundle = ResourceBundle.getBundle("languages/language", currentLocale);

    // Get the current locale
    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    // Set and update the locale
    public static void setLocale(String language, String country) {
        currentLocale = Locale.of(language, country);
        currentBundle = ResourceBundle.getBundle("languages/language", currentLocale);
    }

    // Retrieve localized text by key
    public static String getText(String key) {
        return currentBundle.getString(key);
    }
}
