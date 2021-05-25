package org.koroglu.hobbydoge.service;

import org.koroglu.hobbydoge.controller.request.NewEventRequest;
import org.koroglu.hobbydoge.dto.model.EventDTO;

public interface EventService {

  EventDTO createEvent(NewEventRequest newEventRequest);

  String deleteEvent(Long eventId);

}
