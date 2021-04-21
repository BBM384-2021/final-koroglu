package org.koroglu.hobbydoge.repository;

import org.koroglu.hobbydoge.model.Role;
import org.koroglu.hobbydoge.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

  Optional<Role> findByName(RoleEnum name);

}
