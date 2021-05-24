package org.koroglu.hobbydoge.repository;

import org.koroglu.hobbydoge.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
