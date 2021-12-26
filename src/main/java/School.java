public class School {
    private final String school;
    private final int students;
    private final String country;
    private final double expenditure;
    private final double math;

    public School(String school, int students, String country, double expenditure, double math) {
        this.school = school;
        this.students = students;
        this.country = country;
        this.expenditure = expenditure;
        this.math = math;
    }

    public String getSchool() {
        return school;
    }

    public int getStudents() {
        return students;
    }

    public String getCountry() {
        return country;
    }

    public double getExpenditure() {
        return expenditure;
    }

    public double getMath() {
        return math;
    }
}
