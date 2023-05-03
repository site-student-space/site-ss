package studentspace.ss.cursos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import studentspace.ss.cursos.models.Curso;
import studentspace.ss.cursos.repositories.CursoRepository;

@Controller
public class CursosController {

	@Autowired
	private CursoRepository cr;
	
	@RequestMapping("/cursos/form")
	public String form() {
		return "formCurso";
	}

	@PostMapping("/cursos")
	public String adicionar(Curso curso) {
		
		System.out.println(curso);
		cr.save(curso);
		
		return "curso-adicionado";
	}
}
