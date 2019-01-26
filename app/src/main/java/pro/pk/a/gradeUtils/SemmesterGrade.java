package pro.pk.a.gradeUtils;

public class SemmesterGrade extends GradeSet {
    private static final String[] partialNames = new String[]{"p1", "p2", "p3", "exam"};
    private PartialGrade[] partialGrades = new PartialGrade[3];

    SemmesterGrade(float[] grades) {
        this(null, grades);
    }
    SemmesterGrade(String name, float[] grades) {
        this(name, new Grade[]{new Grade(grades[0]), new Grade(grades[1]), new Grade(grades[2]), new Grade(grades[3])});
    }
    SemmesterGrade(String name, Grade[] grades) {
        this(name, new GradeSet(new Grade[]{grades[0], grades[1], grades[2], grades[3]}));
    }
    SemmesterGrade(String name, PartialGrade[] partialGrades, Grade test) {
        this(name, new GradeSet(name, new Grade[]{partialGrades[0].getAverage(), partialGrades[1].getAverage(), partialGrades[2].getAverage(), test}));
        for (int index = 0; index < this.getPartialGrades().length; index++) {
            this.partialGrades[index] = partialGrades[index];
            this.partialGrades[index].setName(partialNames[index]);
        }
    }
    SemmesterGrade(String name, GradeSet gradeSet) {
        super(name, new Grade[]{gradeSet.getGradeSet()[0], gradeSet.getGradeSet()[1], gradeSet.getGradeSet()[2], gradeSet.getGradeSet()[3]});
        for (int index = 0; index < gradeSet.getGradeSet().length; index++) {
            gradeSet.getGradeSet()[index].setName(partialNames[index]);
        }
    }

    @Override
    protected void setAverage() {
        boolean allGradesSet = true;
        for (Grade grade : this.gradeSet) {
            if (grade.getGrade() == 0) {
                allGradesSet = false;
                break;
            }
        }
        if (allGradesSet) {
            this.getAverage().setGrade((float) ((gradeSet[0].getGrade() + gradeSet[1].getGrade() + gradeSet[2].getGrade()) * .8 / 3 + gradeSet[3].getGrade() * .2));
        }
    }

    public PartialGrade[] getPartialGrades() {
        return partialGrades;
    }
    public void setPartialGrades(PartialGrade[] partialGrades) {
        this.partialGrades = partialGrades;
        for (int index = 0; index < this.partialGrades.length; index++) {
            this.getGradeSet()[index].setGrade(this.partialGrades[index].getAverage().getGrade());
        }
    }
    public void setPartialGrades(PartialGrade partialGrades, int index) {
        this.partialGrades[index].setGradeSet(partialGrades.getGradeSet());
        this.getGradeSet()[index].setGrade(this.partialGrades[index].getAverage().getGrade());
    }
}
