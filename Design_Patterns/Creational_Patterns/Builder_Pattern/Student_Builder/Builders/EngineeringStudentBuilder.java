package Design_Patterns.Creational_Patterns.Builder_Pattern.Student_Builder.Builders;

import java.util.ArrayList;
import java.util.List;

public class EngineeringStudentBuilder extends StudentBuilder {

    @Override
    public StudentBuilder setSubjects() {
        List<String> subs = new ArrayList<>();
        subs.add("DSA");
        subs.add("OS");
        subs.add("Computer Architecture");
        this.subjects = subs;
        return this;
    }
}
