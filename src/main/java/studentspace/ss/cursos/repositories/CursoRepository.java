package studentspace.ss.cursos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import studentspace.ss.cursos.models.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
