package studentspace.ss.cursos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import studentspace.ss.cursos.models.Conteudo;
import studentspace.ss.cursos.models.Curso;

public interface ConteudoRepository extends JpaRepository<Conteudo, Long>{

	List<Conteudo> findByCurso(Curso curso);
	
}
