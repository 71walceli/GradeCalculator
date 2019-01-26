package pro.pk.a.javatest;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Calendar;

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

    public static void main(String[] args) {
        long start = System.nanoTime();

        YearlySchoolGrade y = new YearlySchoolGrade(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)),
                new SemmesterGrade(null, new GradeSet(null, new Grade[]{new Grade(7.8f), new Grade(8.4f), new Grade(9.5f), new Grade(3f)})),
                new SemmesterGrade(null, new GradeSet(null, new Grade[]{new Grade(10f), new Grade(7f), new Grade(5.75f), new Grade(4f)})));
        for (SemmesterGrade s : y.semmesters) {
            System.out.println(s.getName());
            for (Grade g : s.getGradeSet()) System.out.println(g.getName()+ "\t" +g.getGrade());
            System.out.println("\t" + s.getAverage().getGrade());
        }
        System.out.println(y.getAverage().getName() + "\t" + y.getAverage().getGrade());

        long end = System.nanoTime() - start;
        System.out.println(end);
    }
}
