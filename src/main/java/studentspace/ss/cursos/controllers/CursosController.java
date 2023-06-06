package studentspace.ss.cursos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import studentspace.ss.cursos.models.Conteudo;
import studentspace.ss.cursos.models.Curso;
import studentspace.ss.cursos.repositories.ConteudoRepository;
import studentspace.ss.cursos.repositories.CursoRepository;

@Controller
@RequestMapping("/cursos")
public class CursosController {

	@Autowired
	private CursoRepository cr;
	@Autowired
	private ConteudoRepository ctr;
	
	@GetMapping("/form")
	public String form(Curso curso) {
		return "cursos/formCurso";
	}

	@PostMapping
	public String salvar(@Valid Curso curso, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return form(curso);
		}
		
		System.out.println(curso);
		cr.save(curso);
		attributes.addFlashAttribute("mensagemS", "Curso salvo com sucesso!");
		
		return "redirect:/cursos/lista";
	}
	
	@GetMapping("/lista")
	public ModelAndView listar() {
		List<Curso> cursos = cr.findAll();
		ModelAndView mv = new ModelAndView("cursos/lista");
		mv.addObject("cursos", cursos);
		return mv;
	}
	
	@GetMapping("/lista/{id}")
	public ModelAndView detalhar(@PathVariable Long id, Conteudo conteudo) {
		ModelAndView md = new ModelAndView();
		Optional<Curso> opt = cr.findById(id);
		
		if (opt.isEmpty()) {
			md.setViewName("redirect:/cursos/lista");
			return md;
		}
		
		md.setViewName("cursos/detalhes");
		Curso curso = opt.get();
		md.addObject("curso", curso);
		
		List<Conteudo> conteudos = ctr.findByCurso(curso);
		md.addObject("conteudos", conteudos);
		
		return md;
	}
	
	@PostMapping("/lista/{idCurso}")
	public ModelAndView salvarConteudo(@PathVariable Long idCurso, @Valid Conteudo conteudo, BindingResult result, RedirectAttributes attributes) {
		
		ModelAndView md = new ModelAndView();
		
		if(result.hasErrors()) {
			return detalhar(idCurso, conteudo);
		}
		
		System.out.println("Id do curso: " + idCurso);
		System.out.println(conteudo);
		
		Optional<Curso> opt = cr.findById(idCurso);
		if (opt.isEmpty()) {
			md.setViewName("redirect:/cursos/lista");
			return md;
		}
		
		Curso curso = opt.get();
		conteudo.setCurso(curso);
		
		ctr.save(conteudo);
		attributes.addFlashAttribute("mensagemS", "Conteúdo salvo com sucesso!");
		
		md.setViewName("redirect:/cursos/lista/{idCurso}");
		return md;
	}
	
	@GetMapping("/lista/{id}/selecionar")
	public ModelAndView selecionarCurso(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Curso> opt = cr.findById(id);
		if(opt.isEmpty()) {
			md.setViewName("redirect:/cursos/lista");
			return md;
		}
		
		Curso curso = opt.get();
		md.setViewName("cursos/formCurso");
		md.addObject("curso", curso);
		
		return md;
	}
	
	@GetMapping("/lista/{idCurso}/conteudos/{idConteudo}/selecionar")
	public ModelAndView selecionarConteudo(@PathVariable Long idCurso, @PathVariable Long idConteudo) {
		ModelAndView md = new ModelAndView();
		
		Optional<Curso> optCurso = cr.findById(idCurso);
		Optional<Conteudo> optConteudo = ctr.findById(idConteudo);
		
		if(optCurso.isEmpty() || optConteudo.isEmpty()) {
			md.setViewName("redirect:/cursos/lista");
			return md;
		}
		
		Curso curso = optCurso.get();
		Conteudo conteudo = optConteudo.get();
		
		if(curso.getId() != conteudo.getCurso().getId()) {
			md.setViewName("redirect:/cursos/lista");
			return md;
		}
		
		md.setViewName("cursos/detalhes");
		md.addObject("conteudo", conteudo);
		md.addObject("curso", curso);
		md.addObject("conteudos", ctr.findByCurso(curso));
		
		return md;
	}
	
	@GetMapping("/lista/{id}/remover")
	public String apagarCurso(@PathVariable Long id, RedirectAttributes attributes) {
		
		Optional<Curso> opt = cr.findById(id);
		
		if (!opt.isEmpty()) {
			Curso curso = opt.get();
			
			List<Conteudo> conteudos = ctr.findByCurso(curso);
			
			ctr.deleteAll(conteudos);
			cr.delete(curso);
			attributes.addFlashAttribute("mensagemR", "Curso removido com sucesso!");
		}
		
		return "redirect:/cursos/lista";
	}
	
	@GetMapping("/lista/{idCurso}/conteudos/{idConteudo}/remover")
	public String apagarConteudo(@PathVariable Long idCurso, @PathVariable Long idConteudo, RedirectAttributes attributes) {
		
		Optional<Conteudo> opt = ctr.findById(idConteudo);
		
		if(!opt.isEmpty()) {
			Conteudo conteudo = opt.get();
			ctr.delete(conteudo);
			attributes.addFlashAttribute("mensagemR", "Conteúdo removido com sucesso!");
		}
		return "redirect:/cursos/lista/{idCurso}";
	}
}
