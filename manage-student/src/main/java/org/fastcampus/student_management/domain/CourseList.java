package org.fastcampus.student_management.domain;

import java.util.List;

public class CourseList {

    private final List<Course> courses;

    public CourseList(List<Course> courses) {
        this.courses = courses;
    }

    public void changeAllCourses(int fee) {
        courses.forEach(course -> {
            if (course.isSameDay(DayOfWeek.SUNDAY) || course.isSameDay(DayOfWeek.SATURDAY)) {
                course.changeFee((int) (fee * 1.5));
                return;
            }
            course.changeFee(fee);
        });
    }
}
