package mylab.student.entity;

import mylab.student.exception.InvalidGradeException;

public class Student {
    private String studentId;
    private String name;
    private String major;
    private int grade;

    // 생성자
    public Student(String studentId, String name, String major, int grade) throws InvalidGradeException {
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        setGrade(grade); // 생성 시에도 학년 검증 적용
    }

    // Getter & Setter
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public int getGrade() { return grade; }

    // 학년 검증 로직이 포함된 Setter
    public void setGrade(int grade) throws InvalidGradeException {
        if (grade < 1 || grade > 4) {
            throw new InvalidGradeException("학년은 1~4 사이여야 합니다.");
        }
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("%s / %s / %d학년", name, major, grade);
    }
}