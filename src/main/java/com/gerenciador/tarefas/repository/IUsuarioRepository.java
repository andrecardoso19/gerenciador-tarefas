package com.gerenciador.tarefas.repository;

import com.gerenciador.tarefas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
// Usuario é a entidade e Long tipo do ID
// inteface can't be instantiated, multiple inheritance, dont have constructor, only abstract methods,
// all methods and var public.
// final: nao pode atribuir outros valores
// static: valor igual para todas as instancias, pertence a classe e nao objeto.
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
}
