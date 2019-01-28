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
    public static Grade[] makeGrades(int grades) {
        Grade[] newGrades = new Grade[grades];
        for (int index = 0; index < newGrades.length; index++) newGrades[index] = new Grade(-1);
        return newGrades;
    }
    public static Grade[] makeGrades(float[] grades) {
        Grade[] newGrades = new Grade[grades.length];
        for (int index = 0; index < grades.length; index++) newGrades[index] = new Grade(grades[index]);
        return newGrades;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        if (isGrade(grade)) {
            this.grade = grade;
        } else if (grade == -1) {
            this.grade = 0;
        } else {
            throw new IllegalArgumentException("Valid grade must be between 1 and 10. Passed value: " + grade);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getGrade());
    }

    public static boolean isGrade (float grade) {
        return grade >= 1 && grade <= 10;
    }
}
