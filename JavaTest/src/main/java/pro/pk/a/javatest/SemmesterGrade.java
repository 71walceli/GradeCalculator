package pro.pk.a.javatest;

public class SemmesterGrade extends GradeSet {
    private final String[] gradeNames = new String[]{"p1", "p2", "p3", "exam"};

    SemmesterGrade(float[] grades) {
        this(null,grades);
    }
    SemmesterGrade(String name, float[] grades) {
        this(name, new Grade[]{new Grade(grades[0]), new Grade(grades[1]), new Grade(grades[2]), new Grade(grades[3])});
    }
    SemmesterGrade(String name, Grade[] grades) {
        this(name, new GradeSet(name, new Grade[]{grades[0], grades[1], grades[2], grades[3]}));
        for (int i = 0; i < gradeSet.length; i++) {
            gradeSet[i].setName(gradeNames[i]);
        }
    }
    SemmesterGrade(String name, GradeSet gradeSet) {
        super(name, new Grade[]{gradeSet.getGradeSet()[0], gradeSet.getGradeSet()[1], gradeSet.getGradeSet()[2], gradeSet.getGradeSet()[3]});
        for (int i = 0; i < gradeSet.getGradeSet().length; i++) {
            gradeSet.getGradeSet()[i].setName(gradeNames[i]);
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

    public static  void main(String[] args) {
        SemmesterGrade s = new SemmesterGrade("q4", new Grade[]{new Grade("mike", 8.9f), new Grade(7.5f), new Grade(10f), new Grade(10f)});
        for (Grade g : s.getGradeSet()) {
            System.out.println(g.getName() + "\t" + g.getGrade());
        }
        System.out.println(s.getAverage().getName() + "\t" + s.getAverage().getGrade());
    }
}
