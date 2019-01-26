package pro.pk.a.gradeUtils;

import java.util.Arrays;

public class GradeSet extends Grade {
    protected Grade[] gradeSet;
    private final Grade average = new Grade("average", -1);

    GradeSet(float[] grades) {
        this(null, grades);
    }
    public GradeSet(Grade[] grades) {
        this(null, grades);
    }
    GradeSet(String name, float[] grades) {
        this(name, makeGrades(grades));
    }
    GradeSet(String name, Grade[] grades) {
        super(name, -1);
        this.gradeSet = new Grade[grades.length];
        for (int index = 0; index < grades.length; index++) {
            gradeSet[index] = grades[index];
        }
        this.setAverage();
    }

    public Grade[] getGradeSet() {
        return gradeSet;
    }
    public void setGradeSet(float[] grades) {
        if (this.gradeSet.length == grades.length) {
            Grade[] gradeSet = new Grade[this.gradeSet.length];
            for (int index = 0; index < gradeSet.length; index++) {
                gradeSet[index].setGrade(grades[index]);
                gradeSet[index].setName(this.gradeSet[index].getName());
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

    public GradeSet makeAverageSet(SemmesterGrade[] semmesterGrades) {
        return this.makeAverageSet((GradeSet[]) semmesterGrades);
    }
    public GradeSet makeAverageSet(GradeSet[] gradeSets) {
        Grade[] averages = new Grade[gradeSets.length];
        for (int index = 0; index < gradeSets.length; index++) {
            averages[index] = new Grade(gradeSets[index].getAverage().getGrade());
        }
        return new GradeSet(null,averages);
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