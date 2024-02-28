package com.gerenciador.tarefas;

import com.gerenciador.tarefas.entity.Role;
import com.gerenciador.tarefas.entity.Usuario;
import com.gerenciador.tarefas.permissoes.PermissaoEnum;
import com.gerenciador.tarefas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;


// Usuario é a entidade e Long tipo do ID
// inteface can't be instantiated, multiple inheritance, dont have constructor, only abstract methods,
// all methods and var public.

// final: nao pode atribuir outros valores
// static: valor igual para todas as instancias, pertence a classe e nao objeto.

// Controller -> Service -> Repository

// Serializable transforma o objeto em bits para salvar na memoria
// int nao tem null, Integer possui Null

// extends: inherits properties and methods
// implements: protocolo, um set de metodos
@SpringBootApplication
public class GerenciadorTarefasApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorTarefasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario usuario = new Usuario();
		usuario.setUsername("admin");
		usuario.setPassword("123456");

		List<Role> roles = new ArrayList<>();

		Role roleAdmin = new Role();
		roleAdmin.setNome(PermissaoEnum.ADMINISTRADOR);
		Role roleUsuario = new Role();
		roleUsuario.setNome(PermissaoEnum.USUARIO);

		roles.add(roleAdmin);
		roles.add(roleUsuario);

		usuario.setRoles(roles);

		usuarioService.salvarUsuario(usuario);
	}
}
