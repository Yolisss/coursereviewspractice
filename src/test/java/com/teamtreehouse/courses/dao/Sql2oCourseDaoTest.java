package com.teamtreehouse.courses.dao;

import com.teamtreehouse.courses.model.Course;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oCourseDaoTest {

    private Sql2oCourseDao dao;
    private Connection conn;

    //our pretend db
    @BeforeEach
    void setUp() {
        //allows us to reference that file inside the package
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/init.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        //instance of Sql2oCourseDao file
        dao = new Sql2oCourseDao(sql2o);
        //keep connection open through entire test so that it isn't wiped out
        conn = sql2o.open();
    }

    //closes the connection
    @AfterEach
    void tearDown() {
        conn.close();
    }

    //checks if adding a course assigns a unique ID to it
    @Test
    public void addingCourseSetsId() throws Exception{
        Course course = NewTestCourse();
        int originalCourseId = course.getId();
        dao.add(course);

        assertNotEquals(originalCourseId, course.getId());
    }

    //adds a course and checks if findAll() returns it
    @Test
    public void addedCoursesAreReturnedFromFinalAll() throws Exception{
        Course course = NewTestCourse();
        dao.add(course);
        assertEquals(1, dao.findall().size());
    }

    private static Course NewTestCourse() {
        return new Course("Test", "http://test.com");
    }

    //ensures that if no courses are added, findall() returns an empty list
    @Test
    public void noCoursesReturnEmptyList() throws Exception{
        assertEquals(0, dao.findall().size());
    }

    @Test
    public void existingCoursesCanBeFoundById() throws Exception{
        Course course = newTestCourse();
        dao.add(course);
        Course foundCourse = dao.findById(course.getId());

        assertEquals(course, foundCourse);
    }

    private static Course newTestCourse() {
        Course course = NewTestCourse();
        return course;
    }
}