package ru.belous.SpringH2Base.dao;

import org.springframework.stereotype.Repository;
import ru.belous.SpringH2Base.entity.Student;

import java.util.List;

@Repository
public interface StudentDAO {

    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getStudent(int id);

    void deleteStudent(int id);
}