package pro.pk.a.gradeUtils;

import org.apache.commons.lang3.StringUtils;

class SchoolPartial extends GradeSet {
    private String[] gradeNames = new String[]{null, null, null, null, "partialTest"};

    SchoolPartial(String name, float[] grades) {
        this(name, new Grade[]{new Grade(grades[0]), new Grade(grades[1]), new Grade(grades[2]), new Grade(grades[3]), new Grade(grades[4])});;
    }
    SchoolPartial(Grade[] grades) {
        this(null,grades);
    }
    SchoolPartial(String name, Grade[] grades) {
        super(name, new Grade[]{grades[0], grades[1], grades[2], grades[3], grades[4]});
    }

    public String[] getGradeNames() {
        return this.gradeNames;
    }
    public void setGradeNames(String[] gradeNames) {
        if (this.gradeNames.length == gradeNames.length){
            for (int index = 0; index < gradeNames.length; index++) {
                if (!StringUtils.isEmpty(gradeNames[index])) this.gradeNames[index] = gradeNames[index];
            }
        }
        else throw new IllegalArgumentException("Argument array must be same size as that which will be set. " + this.gradeNames.length + " != " + gradeNames.length);
    }
}
