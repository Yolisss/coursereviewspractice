package com.teamtreehouse.courses.dao;

import com.teamtreehouse.courses.exc.DaoException;
import com.teamtreehouse.courses.model.Course;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

//access to configure sql2o obj
public class Sql2oCourseDao implements CourseDao {

    private final Sql2o sql2o;

    public Sql2oCourseDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Course course) throws DaoException {
        String sql = "INSERT INTO courses(name, url) VALUES (:name, :url)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(course) //takes name parameters from property and replaces them; will push teh result of getName() into the name (after values) same with url
                    .executeUpdate()
                    .getKey();
            course.setId(id);
        } catch(Sql2oException ex){
            ex.printStackTrace();
            throw new DaoException(ex, "Problem adding course");
        }
    }

    @Override
    public List<Course> findall() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM courses")
            .executeAndFetch(Course.class);
        }
    }

    @Override
    public Course findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM courses WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Course.class);
        }
    }
}
