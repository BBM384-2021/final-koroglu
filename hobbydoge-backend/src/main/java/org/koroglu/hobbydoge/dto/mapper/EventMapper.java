package org.koroglu.hobbydoge.dto.mapper;

import org.koroglu.hobbydoge.dto.model.EventDTO;
import org.koroglu.hobbydoge.model.Event;

public class EventMapper {
  public static EventDTO toEventDTO(Event event) {
    return new EventDTO()
            .setId(event.getId())
            .setTitle(event.getTitle())
            .setDescription(event.getDescription())
            .setTime(event.getTime())
            .setIsOnline(event.getIsOnline())
            .setAddress(event.getAddress())
            .setJoinedUserCount(event.getJoinedUsers().size());
  }
}
