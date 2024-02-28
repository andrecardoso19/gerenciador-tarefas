package com.gerenciador.tarefas.repository;

import com.gerenciador.tarefas.entity.Role;
import com.gerenciador.tarefas.entity.Usuario;
import com.gerenciador.tarefas.permissoes.PermissaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

//    Role findByname(PermissaoEnum nome);
}