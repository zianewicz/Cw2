import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

class Student {
    private String name;
    private List<Double> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Double> getGrades() {
        return grades;
    }

    public void addGrade(double grade) {
        if (grades.size() >= 20) {
            throw new IllegalArgumentException("Student cannot have more than 20 grades.");
        }
        grades.add(grade);
    }

    public double calculateAverageGrade() {
        if (grades.isEmpty()) {
            throw new IllegalArgumentException("Student has no grades.");
        }

        OptionalDouble average = grades.stream().mapToDouble(Double::doubleValue).average();
        if (!average.isPresent()) {
            throw new IllegalArgumentException("Error calculating the average.");
        }

        double avg = average.getAsDouble();
        return roundGrade(avg);
    }

    private double roundGrade(double grade) {
        double[] possibleGrades = {2, 2.5, 3, 3.5, 4, 4.5, 5};
        double closest = possibleGrades[0];
        double minDifference = Math.abs(grade - closest);

        for (double possibleGrade : possibleGrades) {
            double difference = Math.abs(grade - possibleGrade);
            if (difference < minDifference) {
                minDifference = difference;
                closest = possibleGrade;
            }
        }
        return closest;
    }
}

class StudentGroup {
    private String groupName;
    private List<Student> students;

    public StudentGroup(String groupName) {
        this.groupName = groupName;
        this.students = new ArrayList<>();
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        if (students.size() >= 15) {
            throw new IllegalStateException("Group cannot have more than 15 students.");
        }
        if (students.contains(student)) {
            throw new IllegalStateException("Student is already in the group.");
        }
        students.add(student);
    }
}

public class Main {
    public static void main(String[] args) {
        // Example usage:
        Student student1 = new Student("Ewa Zianewicz");
        student1.addGrade(4.0);
        student1.addGrade(3.7);
        student1.addGrade(5.0);

        System.out.println("Average Grade (rounded): " + student1.calculateAverageGrade());

        StudentGroup group1 = new StudentGroup("Group 1");
        group1.addStudent(student1);

        Student student2 = new Student("Tomasz Kowalski");
        group1.addStudent(student2);

    }
}
