package pro.pk.a.javatest;

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
        for (int i = 0; i < this.getPartialGrades().length; i++) {
            this.getPartialGrades()[i] = partialGrades[i];
            this.getPartialGrades()[i].setName(partialNames[i]);
        }
    }
    SemmesterGrade(String name, GradeSet gradeSet) {
        super(name, new Grade[]{gradeSet.getGradeSet()[0], gradeSet.getGradeSet()[1], gradeSet.getGradeSet()[2], gradeSet.getGradeSet()[3]});
        for (int i = 0; i < gradeSet.getGradeSet().length; i++) {
            gradeSet.getGradeSet()[i].setName(partialNames[i]);
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
    }

    public static  void main(String[] args) {
        long start = System.nanoTime();

        Grade[] grades = massGrades(new float[]{8.3f, 10, 9.3f, 1, 7, 7.1f, 5.5f, 4, 5});
        PartialGrade p1 = new PartialGrade(new Grade[]{grades[0], grades[6], grades[5], grades[3], grades[3]});
        PartialGrade p2 = new PartialGrade(new Grade[]{grades[8], grades[8], grades[7], grades[1], grades[6]});
        PartialGrade p3 = new PartialGrade(new Grade[]{grades[4], grades[0], grades[6], grades[4], grades[1]});
        SemmesterGrade s = new SemmesterGrade("q4", new PartialGrade[]{p1, p2, p3}, grades[3]);
        for (PartialGrade p : s.getPartialGrades()) {
            for (Grade g : p.getGradeSet()) System.out.print("\t\t" + g.getGrade());
            System.out.println();
            System.out.println();
        }
        System.out.println(s.getGradeSet()[3].getGrade());
        System.out.println(s.getAverage().getName() + "\t" + s.getAverage().getGrade());

        long end = System.nanoTime() - start;
        System.out.println(end);
    }
}
