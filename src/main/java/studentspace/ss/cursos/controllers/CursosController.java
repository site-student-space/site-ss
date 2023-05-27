package studentspace.ss.cursos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import studentspace.ss.cursos.models.Curso;
import studentspace.ss.cursos.repositories.CursoRepository;

@Controller
@RequestMapping("/cursos")
public class CursosController {

	@Autowired
	private CursoRepository cr;
	
	@GetMapping("/form")
	public String form() {
		return "cursos/formCurso";
	}

	@PostMapping
	public String adicionar(Curso curso) {
		
		System.out.println(curso);
		cr.save(curso);
		
		return "cursos/curso-adicionado";
	}
	
	@GetMapping("/lista")
	public ModelAndView listar() {
		List<Curso> cursos = cr.findAll();
		ModelAndView mv = new ModelAndView("cursos/lista");
		mv.addObject("cursos", cursos);
		return mv;
	}
	
	@GetMapping("/lista/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Curso> opt = cr.findById(id);
		
		if (opt.isEmpty()) {
			md.setViewName("redirect:/cursos/lista");
			return md;
		}
		
		md.setViewName("cursos/detalhes");
		Curso curso = opt.get();
		md.addObject("curso", curso);
		
		return md;
	}
}
