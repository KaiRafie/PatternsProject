package patterns.dental.clinic.model.user;

public enum Operations {
    ALL_OPERATIONS, TEETH_CLEANING, FLUORIDE_APPLICATION, CAVITY_CLEANING, DENTAL_EXAMINATIONS;
    @Override
    public String toString(){
        return name().replace('_', ' ');
    }
}
