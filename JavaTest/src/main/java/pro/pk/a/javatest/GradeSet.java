package pro.pk.a.javatest;

import java.util.Arrays;

public class GradeSet extends Grade {
    protected Grade[] gradeSet;
    private final Grade average = new Grade("average", -1);

    GradeSet(float[] grades) {
        this(null, grades);
    }
    GradeSet(String name, float[] grades) {
        this(name, massGrades(grades));
    }
    GradeSet(String name, Grade[] grades) {
        super(name, -1);
        this.gradeSet = new Grade[grades.length];
        for (int i = 0; i < grades.length; i++) {
            gradeSet[i] = grades[i];
        }
        this.setAverage();
    }

    public Grade[] getGradeSet() {
        return gradeSet;
    }
    public void setGradeSet(float[] grades) {
        if (this.gradeSet.length <= grades.length) {
            Grade[] gradeSet = new Grade[this.gradeSet.length];
            for (int i = 0; i < gradeSet.length; i++) {
                gradeSet[i].setGrade(grades[i]);
                gradeSet[i].setName(this.gradeSet[i].getName());
            }
        } else throw new IllegalArgumentException("Array argument must contain the sema number of grades.");
        this.setGradeSet(gradeSet);
    }
    public void setGradeSet(Grade[] grades) {
        if (this.gradeSet.length <= grades.length) {
            this.gradeSet = Arrays.copyOf(grades, grades.length);
        } else throw new IllegalArgumentException("Array argument must contain the sema number of grades.");
        this.setAverage();
    }

    public Grade getAverage() {
        return average;
    }
    protected void setAverage() {
        float newAverage = 0;
        boolean allGradesSet = true;
        for (Grade grade : gradeSet) {
            if (grade.getGrade() != 0) newAverage += grade.getGrade();
            else {
                allGradesSet = false;
                break;
            }
        }
        if (allGradesSet) {
            this.average.setGrade(newAverage / gradeSet.length);
        } else {
            this.average.setGrade(-1);
            try {
                this.setGrade(average.getGrade());
            } catch (IllegalArgumentException e) {
            }
        }
    }
}