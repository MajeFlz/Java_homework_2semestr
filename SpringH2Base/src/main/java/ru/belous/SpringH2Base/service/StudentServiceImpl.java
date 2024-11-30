package ru.belous.SpringH2Base.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.belous.SpringH2Base.dao.StudentDAO;
import ru.belous.SpringH2Base.entity.Student;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDAO studentDAO;

    @Override
    @Transactional
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        return studentDAO.saveStudent(student);
    }

    @Override
    @Transactional
    public Student getStudent(int id) {
        Student student = studentDAO.getStudent(id);
        if (student == null) {
            throw new EntityNotFoundException("Студент с " + id + " не найден");
        }
        return student;
    }

    @Override
    @Transactional
    public void deleteStudent(int id) {
        Student student = studentDAO.getStudent(id);
        if (student == null) {
            throw new EntityNotFoundException("Студент с " + id + " не найден");
        }
        studentDAO.deleteStudent(id);
    }
}