package pro.pk.a.javatest;

public class SemmesterGrade extends GradeSet {
    private final Grade[] gradeSet = {new Grade("p1"), new Grade("p2"), new Grade("p3"), new Grade("exam")};

    SemmesterGrade(float[] grades) {
        super(grades);
    }

    SemmesterGrade(String name, float[] grades) {
        super(name, grades);
    }

    /*
    SemmesterGrade(String name, float[] grades) {
        super(name, grades);
    }
    SemmesterGrade(Grade[] grades) {
        this(null, grades);
    }
    SemmesterGrade(String name, Grade[] grades) {
        super(name, grades);
    }
    */

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
            this.getAverage().setGrade((float) ((gradeSet[0].getGrade() + gradeSet[1].getGrade() + gradeSet[2].getGrade()) * .8 / 3 + gradeSet[3].getGrade()));
        }
    }

    public static  void main(String[] args) {
        SemmesterGrade s = new SemmesterGrade("q4", new Grade[]{new Grade(".p1", 9.3f), new Grade(".p2", 6.3f), new Grade(".p3", 8.2f), new Grade("ex", 6f)});
        for (Grade g : s.getGradeSet()) {
            System.out.println(g.getName() + "\t" + g.getGrade());
        }
        System.out.println(s.getAverage().getName() + "\t" + s.getAverage().getGrade());
    }
}
