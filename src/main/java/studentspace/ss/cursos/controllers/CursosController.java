package studentspace.ss.cursos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CursosController {

	@RequestMapping("/cursos/form")
	public String form() {
		return "formCurso";
	}
	
}
