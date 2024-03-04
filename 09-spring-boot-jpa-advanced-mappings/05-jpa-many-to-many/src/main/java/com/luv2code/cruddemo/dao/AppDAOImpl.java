package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    // define a field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {

        // retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, id);

        // get the courses
        List<Course> courses = tempInstructor.getCourses();

        // break association of all courses for the instructor
        for (Course tempCourse : courses) {
            tempCourse.setInstructor(null);
        }

        // delete the instructor
        entityManager.remove(tempInstructor);

    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Transactional
    @Override
    public void deleteInstructorDetailById(int id) {

        // retrieve the instructor
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, id);

        // remove the associated object reference
        // break bidirectional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        // delete the instructor
        entityManager.remove(tempInstructorDetail);

    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {

        // create query to find instructor by Id
        TypedQuery<Course> query = entityManager.createQuery("FROM Course WHERE instructor.id = :data", Course.class);

        query.setParameter("data", id);

        // execute the query
        List<Course> courses = query.getResultList();

        return courses;

    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {

        // create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                "SELECT i FROM Instructor i " +
                        "JOIN FETCH i.courses " +
                        "JOIN FETCH i.instructorDetail " +
                        "WHERE i.id = :data", Instructor.class);

        query.setParameter("data", id);

        // execute query
        Instructor instructor = query.getSingleResult();

        return instructor;

    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {

        // retrieve the course
        Course tempCourse = entityManager.find(Course.class, id);

        // delete the course
        entityManager.remove(tempCourse);

    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery("" +
                "SELECT c FROM Course c " +
                "JOIN FETCH c.reviews " +
                "WHERE c.id = :data", Course.class);

        query.setParameter("data", id);

        // execute query
        Course course = query.getSingleResult();

        return course;

    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c " +
                "JOIN FETCH c.students " +
                "WHERE c.id = :data", Course.class);

        query.setParameter("data", id);

        // execute query
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int id) {

        // create query
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT s FROM Student s " +
                "JOIN FETCH s.courses " +
                "WHERE s.id = :data", Student.class);

        query.setParameter("data", id);

        // execute query
        Student student = query.getSingleResult();

        return student;

    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {

        // retrieve the student
        Student student = entityManager.find(Student.class, id);

        // delete the student
        entityManager.remove(student);

    }

}
