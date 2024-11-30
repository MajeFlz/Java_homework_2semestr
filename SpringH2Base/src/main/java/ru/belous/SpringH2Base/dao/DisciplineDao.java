package ru.belous.SpringH2Base.dao;

import org.springframework.stereotype.Repository;
import ru.belous.SpringH2Base.entity.Discipline;

import java.util.List;

@Repository
public interface DisciplineDao {

    List<Discipline> getAllDisciplines();

    Discipline saveDiscipline(Discipline discipline);

    Discipline getDiscipline(int id);

    void deleteDiscipline(int id);
}