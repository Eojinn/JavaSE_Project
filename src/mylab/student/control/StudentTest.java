package mylab.student.control;

import mylab.student.entity.Student;
import mylab.student.exception.InvalidGradeException;

public class StudentTest {
    public static void main(String[] args) {
        try {
            // 1. 정상 학생 정보 생성
            Student stu = new Student("2026001", "김민수", "컴퓨터공학", 3);
            System.out.println(stu.toString());

            // 2. 5학년으로 변경 시도 (오류 발생  지점)
            System.out.println("5학년으로 변경");
            stu.setGrade(5);

        } catch (InvalidGradeException e) {
            // 예외 메시지 출력
            System.out.println(e.getMessage());
        }
    }
}