package org.koroglu.hobbydoge.service;

import org.koroglu.hobbydoge.controller.request.ClubRequest;
import org.koroglu.hobbydoge.controller.request.NewClubRequest;
import org.koroglu.hobbydoge.dto.model.ClubDTO;

import java.util.HashMap;
import java.util.List;

public interface ClubService {

  List<ClubDTO> getClubs(int offset, int limit);

  ClubDTO create(NewClubRequest newClubRequest);

  ClubDTO update(Long id, ClubRequest clubRequest);

  HashMap<String, String> delete(Long id);

  List<ClubDTO> search(String q);

}
