package org.koroglu.hobbydoge.service;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.NewEventRequest;
import org.koroglu.hobbydoge.dto.mapper.EventMapper;
import org.koroglu.hobbydoge.dto.model.EventDTO;
import org.koroglu.hobbydoge.exception.RestEventDoesNotExistException;
import org.koroglu.hobbydoge.exception.RestSubClubDoesNotExistException;
import org.koroglu.hobbydoge.exception.RestUserAlreadyJoinedException;
import org.koroglu.hobbydoge.model.Event;
import org.koroglu.hobbydoge.model.SubClub;
import org.koroglu.hobbydoge.model.User;
import org.koroglu.hobbydoge.repository.EventRepository;
import org.koroglu.hobbydoge.repository.SubClubRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;
  private final SubClubRepository subClubRepository;

  @Override
  public EventDTO createEvent(NewEventRequest newEventRequest) {

    SubClub subClub = subClubRepository.findById(newEventRequest.getSubClubId()).orElseThrow(RestSubClubDoesNotExistException::new);

    System.out.println(String.format("%s %s %s", newEventRequest.getTitle(), newEventRequest.getTime(), newEventRequest.getAddress()));

    Event event = new Event(
            newEventRequest.getTitle(),
            newEventRequest.getDescription(),
            LocalDateTime.parse(newEventRequest.getTime(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
            newEventRequest.getIsOnline(),
            newEventRequest.getAddress(),
            subClub
    );

    eventRepository.save(event);

    subClub.getEvents().add(event);

    subClubRepository.save(subClub);

    return EventMapper.toEventDTO(event);
  }

  @Override
  public String deleteEvent(Long eventId) {

    //TODO: Add new exception
    Event event = eventRepository.findById(eventId).orElseThrow(RestSubClubDoesNotExistException::new);

    SubClub subClub = subClubRepository.findById(event.getSubClub().getId()).orElseThrow(RestSubClubDoesNotExistException::new);

    subClub.getEvents().remove(event);

    subClubRepository.save(subClub);

    eventRepository.delete(event);

    return "Event deleted successfully.";
  }

  @Override
  public String joinEvent(Long eventId) {

    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    Event event = eventRepository.findById(eventId).orElseThrow(RestEventDoesNotExistException::new);

    if (event.getJoinedUsers().contains(user)) throw new RestUserAlreadyJoinedException();

    event.getJoinedUsers().add(user);

    eventRepository.save(event);

    return "Successfully joined.";

  }

  @Override
  public String leaveEvent(Long eventId) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    Event event = eventRepository.findById(eventId).orElseThrow(RestEventDoesNotExistException::new);

    event.getJoinedUsers().remove(user);

    eventRepository.save(event);

    return "Successfully left.";
  }

}
