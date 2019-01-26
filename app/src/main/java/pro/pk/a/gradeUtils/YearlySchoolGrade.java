package pro.pk.a.gradeUtils;

public class YearlySchoolGrade extends GradeSet {
    private static final String[] semmesterNames = new String[]{"Q1", "Q2"};
    private SemmesterGrade[] semmesters = new SemmesterGrade[2];

    YearlySchoolGrade(String name, float[] grades) {
        this(name,
                new SemmesterGrade(null, new GradeSet(null, new Grade[]{new Grade(grades[0]), new Grade(grades[1]), new Grade(grades[2]), new Grade(grades[3])})),
                new SemmesterGrade(null, new GradeSet(null, new Grade[]{new Grade(grades[4]), new Grade(grades[5]), new Grade(grades[6]), new Grade(grades[7])})));
    }
    YearlySchoolGrade(String name, SemmesterGrade semmester1, SemmesterGrade semmester2) {
        super(name, new Grade[]{new Grade(semmester1.getAverage().getGrade()), new Grade(semmester2.getAverage().getGrade())});
        this.semmesters[0] = semmester1;
        this.semmesters[1] = semmester2;
        this.semmesters[0].setName(semmesterNames[0]);
        this.semmesters[1].setName(semmesterNames[1]);
    }
}
