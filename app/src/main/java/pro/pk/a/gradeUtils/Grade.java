package pro.pk.a.gradeUtils;

public class Grade {
    private float grade;
    private String name;

    Grade(float grade) {
        this(null, grade);
    }
    Grade (String name) {
        this(name, -1);
    }
    Grade(String name, float grade) {
        this.name = name;
        this.setGrade(grade);
    }
    static Grade[] makeGrades(float[] grades) {
        Grade[] newGrades = new Grade[grades.length];
        for (int index = 0; index < grades.length; index++) newGrades[index] = new Grade(grades[index]);
        return newGrades;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        if (grade >= 1 && grade <= 10) {
            this.grade = grade;
        } else if (grade == -1) {
            this.grade = 0;
        } else {
            this.grade = 0;
            throw new IllegalArgumentException("Valid grade must be between 1 and 10. Passed value: " + grade);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
