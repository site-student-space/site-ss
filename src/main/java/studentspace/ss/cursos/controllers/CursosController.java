package studentspace.ss.cursos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import studentspace.ss.cursos.models.Curso;

@Controller
public class CursosController {

	@RequestMapping("/cursos/form")
	public String form() {
		return "formCurso";
	}

	@PostMapping("/cursos")
	public String adicionar(Curso curso) {
		System.out.println(curso);
		return "curso-adicionado";
	}
}
