package org.koroglu.hobbydoge.service;

import org.koroglu.hobbydoge.controller.request.NewSubClubRequest;
import org.koroglu.hobbydoge.controller.request.SubClubRequest;
import org.koroglu.hobbydoge.dto.model.SubClubDTO;

import java.util.HashMap;
import java.util.List;

public interface SubClubService {

    SubClubDTO get(Long id);
    List<SubClubDTO> getSubClub(int offset, int limit);

    List<SubClubDTO> search(String q);

    SubClubDTO create(NewSubClubRequest newSubClubRequest);

    SubClubDTO update(Long id, SubClubRequest subClubRequest);

    HashMap<String, String> delete(Long id);

    String join(Long id);

    String leave(Long id);

}
