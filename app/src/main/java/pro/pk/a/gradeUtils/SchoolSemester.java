package pro.pk.a.gradeUtils;

public class SchoolSemester extends GradeSet {
    private static final String[] partialNames = new String[]{"p1", "p2", "p3", "exam"};
    private SchoolPartial[] schoolPartials = new SchoolPartial[3];

    SchoolSemester(String name, Grade[] grades) {
        this(name, new GradeSet(new Grade[]{grades[0], grades[1], grades[2], grades[3]}));
    }
    public SchoolSemester(String name, SchoolPartial[] schoolPartials, Grade test) {
        this(name, new GradeSet(name, new Grade[]{schoolPartials[0].getAverage(), schoolPartials[1].getAverage(), schoolPartials[2].getAverage(), test}));
        for (int index = 0; index < this.getSchoolPartials().length; index++) {
            this.schoolPartials[index] = schoolPartials[index];
            this.schoolPartials[index].setName(partialNames[index]);
        }
    }
    public SchoolSemester(GradeSet gradeSet) {
        this(null, gradeSet);
    }
    SchoolSemester(String name, GradeSet gradeSet) {
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

    public SchoolPartial[] getSchoolPartials() {
        return schoolPartials;
    }
    public void setSchoolPartials(SchoolPartial[] schoolPartials) {
        this.schoolPartials = schoolPartials;
        for (int index = 0; index < this.schoolPartials.length; index++) {
            this.getGradeSet()[index].setGrade(this.schoolPartials[index].getAverage().getGrade());
        }
    }
    public void setPartialGrades(SchoolPartial schoolPartialGrades, int index) {
        this.schoolPartials[index].setGradeSet(schoolPartialGrades.getGradeSet());
        this.getGradeSet()[index].setGrade(this.schoolPartials[index].getAverage().getGrade());
    }
}
