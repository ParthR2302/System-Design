package Design_Patterns.Creational_Patterns.Builder_Pattern.Student_Builder;

import Design_Patterns.Creational_Patterns.Builder_Pattern.Student_Builder.Builders.EngineeringStudentBuilder;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Student_Builder.Builders.MBAStudentBuilder;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Student_Builder.Directors.Director;
import Design_Patterns.Creational_Patterns.Builder_Pattern.Student_Builder.Students.Student;

public class Client {
    public static void main(String[] args) {
        Director directorObj1 = new Director(new EngineeringStudentBuilder());
        Director directorObj2 = new Director(new MBAStudentBuilder());

        Student engineerStudent = directorObj1.createStudent();
        Student mbaStudent =directorObj2.createStudent();

        System.out.println(engineerStudent.toString());
        System.out.println(mbaStudent.toString());
    }
}
