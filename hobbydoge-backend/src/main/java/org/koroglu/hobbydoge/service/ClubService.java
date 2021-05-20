package org.koroglu.hobbydoge.service;

import org.koroglu.hobbydoge.controller.request.ClubRequest;
import org.koroglu.hobbydoge.controller.request.NewClubRequest;
import org.koroglu.hobbydoge.dto.model.ClubDTO;

import java.util.HashMap;
import java.util.List;

public interface ClubService {

  ClubDTO get(Long id);

  List<ClubDTO> getClubs(int offset, int limit);

  List<ClubDTO> search(String q);

  ClubDTO create(NewClubRequest newClubRequest);

  ClubDTO update(Long id, ClubRequest clubRequest);

  HashMap<String, String> delete(Long id);
  
  String join(Long id);

  String leave(Long id);

}
