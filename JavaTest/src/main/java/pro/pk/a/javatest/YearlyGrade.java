package pro.pk.a.javatest;

public class YearlyGrade extends SemmesterGrade {
    private final String[] semmesterNames = new String[]{"Q1", "Q2"};

    YearlyGrade(float[] grades) {
        this(null,grades);
    }
    YearlyGrade(String name, float[] grades) {
        super(name, grades);
    }
    YearlyGrade(Grade[] grades) {
        this(null,grades);
    }
    YearlyGrade(String name, Grade[] grades) {
        super(name, grades);
    }

    @Override
    protected void setAverage() {
        super.setAverage();
    }
}
