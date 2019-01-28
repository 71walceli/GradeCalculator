package pro.pk.a.gradeUtils;

public class SchoolYear extends GradeSet {
    private static final String[] semmesterNames = new String[]{"Q1", "Q2"};
    private SchoolSemester[] semmesters = new SchoolSemester[2];

    public SchoolYear(String name, SchoolSemester semmester1, SchoolSemester semmester2) {
        super(name, new Grade[]{new Grade(semmester1.getAverage().getGrade()), new Grade(semmester2.getAverage().getGrade())});
        this.semmesters[0] = semmester1;
        this.semmesters[1] = semmester2;
        this.semmesters[0].setName(semmesterNames[0]);
        this.semmesters[1].setName(semmesterNames[1]);
    }
}
