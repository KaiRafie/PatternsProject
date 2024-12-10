package patterns.dental.clinic.model.user;

import patterns.dental.clinic.controller.LanguageController;

import java.util.Locale;
import java.util.ResourceBundle;

public enum Operations {
    ALL_OPERATIONS, TEETH_CLEANING, FLUORIDE_APPLICATION, CAVITY_CLEANING, DENTAL_EXAMINATIONS;


    public String getLocalizedName() {
        ResourceBundle bundle = ResourceBundle.getBundle("languages/language", LanguageController.getCurrentLocale());
        return bundle.getString(this.name());
    }

    @Override
    public String toString(){
        return getLocalizedName();
    }
}
