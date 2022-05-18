package com.japp.Blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.japp.Blog.model.Postagem;
import com.japp.Blog.repository.PostagemRepository;

@RestController  // DEFINIR QUE É UM CONTROLER 
@RequestMapping("/postagens") // CAMINHO PRA SAIDA
@CrossOrigin("*") // ACEITAR QUALQUER API
public class PostagemController {

	
	@Autowired  // INJETAR DE DEPENDENCIA, ASSIM ESSE REPOSITORIO É ACESSADO
	private PostagemRepository repositoty;
	

	
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll (){
		return ResponseEntity.ok(repositoty.findAll());
	}
	
	// 6. Crie um método findById no controller *OK*
	// 7. Teste o método findByID via  postman. *OK*


	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable Long id){
	return repositoty.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	// 9. Crie um método getByTitulo no controller. *OK*
	// 10. Teste o método getByTitulo no postman. *OK*

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem> >GetByTitulo(@PathVariable String titulo){
	return ResponseEntity.ok(repositoty.findAllByTituloContainingIgnoreCase(titulo)); 
	}
	
	@PostMapping //Cadastrar dados
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repositoty.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repositoty.save(postagem));
	}
	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id){
		repositoty.deleteById(id);
	}
}
