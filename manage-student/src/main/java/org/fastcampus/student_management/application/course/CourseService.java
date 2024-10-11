package org.fastcampus.student_management.application.course;

import java.util.List;
import java.util.stream.Collectors;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.course.interfaces.CourseCommandRepository;
import org.fastcampus.student_management.application.course.interfaces.CourseQueryRepository;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.CourseList;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.domain.Student;
import org.fastcampus.student_management.repo.CourseCommandRepositoryImpl;
import org.fastcampus.student_management.repo.CourseJdbcCommandRepository;
import org.fastcampus.student_management.repo.StudentRepository;

public class CourseService {

    private final CourseCommandRepository courseCommandRepository;
    private final CourseQueryRepository courseQueryRepository;
    private final StudentRepository studentRepository;

    public CourseService(CourseCommandRepositoryImpl courseRepository,
        CourseJdbcCommandRepository courseJdbcCommandRepository,
        StudentRepository studentRepository) {
        this.courseCommandRepository = courseRepository;
        this.courseQueryRepository = courseJdbcCommandRepository;
        this.studentRepository = studentRepository;
    }

    public void registerCourse(CourseInfoDto courseInfoDto) {
        Student student = studentRepository.getStudent(courseInfoDto.getStudentName());
        Course course = new Course(student, courseInfoDto.getCourseName(), courseInfoDto.getFee(),
            courseInfoDto.getDayOfWeek(), courseInfoDto.getCourseTime());
        courseCommandRepository.save(course);
    }

    public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
        List<Course> courses = courseQueryRepository.getCourseDayOfWeek(dayOfWeek);

        return courses.stream().map(CourseInfoDto::new).collect(Collectors.toList());
    }

    public void changeFee(String studentName, int fee) {
        List<Course> courses = courseQueryRepository.getCourseListByStudent(studentName);
        CourseList courseList = new CourseList(courses);
        courseList.changeAllCourses(fee);
    }
}
