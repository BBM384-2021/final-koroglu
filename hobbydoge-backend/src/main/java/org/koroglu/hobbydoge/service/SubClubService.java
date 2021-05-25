package org.koroglu.hobbydoge.service;

import org.koroglu.hobbydoge.controller.request.NewSubClubRequest;
import org.koroglu.hobbydoge.controller.request.SubClubRequest;
import org.koroglu.hobbydoge.dto.model.SubClubDTO;

import java.util.HashMap;

public interface SubClubService {

    SubClubDTO get(Long id);

    SubClubDTO create(NewSubClubRequest newSubClubRequest);

    SubClubDTO update(Long id, SubClubRequest subClubRequest);

    HashMap<String, String> delete(Long id);

    String join(Long id);

    String leave(Long id);

    String adminRequest(Long id);

}
