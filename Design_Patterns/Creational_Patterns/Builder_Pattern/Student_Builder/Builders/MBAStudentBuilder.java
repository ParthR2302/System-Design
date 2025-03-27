package Design_Patterns.Creational_Patterns.Builder_Pattern.Student_Builder.Builders;

import java.util.ArrayList;
import java.util.List;

public class MBAStudentBuilder extends StudentBuilder {
    @Override
    public StudentBuilder setSubjects() {
        List<String> subs = new ArrayList<>();
        subs.add("Micro Economics");
        subs.add("Business Studies");
        subs.add("Operations Management");
        this.subjects = subs;
        return this;
    }

}
