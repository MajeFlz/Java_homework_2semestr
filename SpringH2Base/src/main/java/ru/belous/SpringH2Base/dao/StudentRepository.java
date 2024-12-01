package ru.belous.SpringH2Base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belous.SpringH2Base.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}