package org.koroglu.hobbydoge.repository;

import org.koroglu.hobbydoge.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
  
}
