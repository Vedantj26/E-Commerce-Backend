package net.javaguide.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguide.springboot.entity.Role;

@Repository
public interface RoleRepo  extends JpaRepository<Role, Integer>{

}
