package org.fastcampus.student_management;

import org.fastcampus.student_management.application.course.CourseService;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.application.student.dto.StudentInfoDto;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.repo.CourseCommandRepositoryImpl;
import org.fastcampus.student_management.repo.CourseJdbcCommandRepository;
import org.fastcampus.student_management.repo.StudentRepository;
import org.fastcampus.student_management.ui.course.CourseController;
import org.fastcampus.student_management.ui.course.CoursePresenter;
import org.fastcampus.student_management.ui.student.StudentController;
import org.fastcampus.student_management.ui.student.StudentPresenter;
import org.fastcampus.student_management.ui.UserInputType;

public class Main {

    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentRepository();
        CourseCommandRepositoryImpl courseRepositoryImpl = new CourseCommandRepositoryImpl();
        CourseJdbcCommandRepository jdbcCommandRepository = new CourseJdbcCommandRepository();

        StudentService studentService = new StudentService(studentRepository);
        CourseService courseService = new CourseService(courseRepositoryImpl, jdbcCommandRepository,
            new StudentRepository());

        CoursePresenter coursePresenter = new CoursePresenter();
        StudentPresenter studentPresenter = new StudentPresenter();

        CourseController courseController = new CourseController(coursePresenter, courseService,
            studentPresenter);
        StudentController studentController = new StudentController(studentPresenter,
            studentService);

        // 기본 데이터 추가
        StudentInfoDto studentInfoDto1 = new StudentInfoDto("한강", 31, "대만");
        StudentInfoDto studentInfoDto2 = new StudentInfoDto("김사랑", 24, "서울시 중구");
        studentService.saveStudent(studentInfoDto1);
        studentService.saveStudent(studentInfoDto2);

        CourseInfoDto courseInfoDto1 = new CourseInfoDto("피아노", 35000, DayOfWeek.MONDAY.name(),
            studentInfoDto1.getName(), 1717299088L);
        CourseInfoDto courseInfoDto2 = new CourseInfoDto("바이올린", 45000, DayOfWeek.MONDAY.name(),
            studentInfoDto2.getName(), 1717299088L);
        courseService.registerCourse(courseInfoDto1);
        courseService.registerCourse(courseInfoDto2);

        studentPresenter.showMenu();
        UserInputType userInputType = studentController.getUserInput();
        while (userInputType != UserInputType.EXIT) {
            switch (userInputType) {
                case NEW_STUDENT:
                    studentController.registerStudent();
                    break;
                case NEW_COURSE:
                    courseController.registerCourse();
                    break;
                case SHOW_COURSE_DAY_OF_WEEK:
                    courseController.showCourseDayOfWeek();
                    break;
                case ACTIVATE_STUDENT:
                    studentController.activateStudent();
                    break;
                case DEACTIVATE_STUDENT:
                    studentController.deactivateStudent();
                    break;
                case CHANGE_FEE:
                    courseController.changeFee();
                    break;
                default:
                    studentPresenter.showErrorMessage();
                    break;
            }
            studentPresenter.showMenu();
            userInputType = studentController.getUserInput();
        }
    }
}