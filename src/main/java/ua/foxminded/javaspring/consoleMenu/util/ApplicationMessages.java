package ua.foxminded.javaspring.consoleMenu.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:ApplicationMessages.properties")
public class ApplicationMessages {

    @Value("${addNewStudent}")
    public String ADD_NEW_STUDENT;
    @Value("${inputFirsName}")
    public String INPUT_FIRST_NAME;
    @Value("${inputLastName}")
    public String INPUT_LAST_NAME;
    @Value("${addGroupToStudent}")
    public String ADD_GROUP_TO_STUDENT;
    @Value("${studentAdded}")
    public String STUDENT_ADDED;
    @Value("${studentRemoved}")
    public String STUDENT_REMOVED;
    @Value("${enterCourseID}")
    public String ENTER_COURSE_ID;
    @Value("${studentAddedToCourse}")
    public String STUDENT_ADDED_TO_COURSE;
    @Value("${addStudentToCourse}")
    public String ADD_STUDENT_TO_COURSE;
    @Value("${removeStudentFromCourse}")
    public String REMOVE_STUDENT_FROM_COURSE;
    @Value("${chooseEnrollmentId}")
    public String CHOOSE_ENROLLMENT_ID;
    @Value("${studentRemovedFromCourse}")
    public String STUDENT_REMOVED_FROM_COURSE;
    @Value("${studentHasNotCourse}")
    public String STUDENT_HAS_NOT_COURSE;
    @Value("${removeStudentByID}")
    public String REMOVE_STUDENT_BY_ID;
    @Value("${groupsByCount}")
    public String GROUPS_BY_COUNT;
    @Value("${hasNotGroupWithCount}")
    public String HAS_NOT_GROUPS_WITH_COUNT;
    @Value("${studentCourses}")
    public String STUDENTS_COURSES;
    @Value("${nameStudyingStudent}")
    public String NAME_STUDYING_STUDENT;
    @Value("${courseNameAndDescription}")
    public String COURSE_NAME_AND_DESCRIPTION;
    @Value("${studentsFromCourse}")
    public String STUDENTS_FROM_COURSE;
    @Value("${amountStudentAtGroup}")
    public String AMOUNT_STUDENTS_AT_GROUP;
    @Value("${printAllGroups}")
    public String PRINT_ALL_GROUPS;
    @Value("${verifyStudent}")
    public String VERIFY_STUDENT;
}
