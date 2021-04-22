package org.koroglu.hobbydoge.service;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.ClubRequest;
import org.koroglu.hobbydoge.controller.request.NewClubRequest;
import org.koroglu.hobbydoge.dto.mapper.ClubMapper;
import org.koroglu.hobbydoge.dto.model.ClubDTO;
import org.koroglu.hobbydoge.exception.RestClubDoesNotExistException;
import org.koroglu.hobbydoge.exception.RestClubNameAlreadyExistException;
import org.koroglu.hobbydoge.model.Club;
import org.koroglu.hobbydoge.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClubServiceImpl implements ClubService {

  @Autowired
  private final ClubRepository clubRepository;

  @Override
  public List<ClubDTO> getClubs(int offset, int limit) {
    System.out.println(clubRepository.getAllClubs(offset, limit));
    return clubRepository.getAllClubs(offset, limit).stream().map(club -> {
              System.out.println(club);
              return ClubMapper.toClubDTO(club);
            }
    ).collect(Collectors.toList());
  }

  @Override
  public ClubDTO create(NewClubRequest newClubRequest) {

    Optional<Club> optionalClub = clubRepository.findByName(newClubRequest.getName());
    if (optionalClub.isPresent()) {
      throw new RestClubNameAlreadyExistException();
    }

    Club club = new Club(
            newClubRequest.getName(),
            newClubRequest.getDescription(),
            newClubRequest.getPicture()
    );

    return ClubMapper.toClubDTO(clubRepository.save(club));
  }

  public ClubDTO update(Long id, ClubRequest clubRequest) {

    Optional<Club> optionalClub = clubRepository.findById(id);

    if (!optionalClub.isPresent()) {
      throw new RestClubDoesNotExistException();
    }

    Club club = optionalClub.get();

    if (clubRequest.getName() != null) {
      club.setName(clubRequest.getName());
    }

    if (clubRequest.getDescription() != null) {
      club.setDescription(clubRequest.getDescription());
    }

    if (clubRequest.getPicture() != null) {
      club.setPicture(clubRequest.getPicture());
    }

    return ClubMapper.toClubDTO(clubRepository.save(club));
  }

  @Override
  public HashMap<String, String> delete(Long id) {


    Optional<Club> optionalClub = clubRepository.findById(id);

    if (!optionalClub.isPresent()) {
      throw new RestClubDoesNotExistException();
    }

    HashMap<String, String> response = new HashMap<String, String>();
    response.put("message", String.format("Club %s deleted succesfully.", optionalClub.get().getName()));

    clubRepository.delete(optionalClub.get());

    return response;
  }


}
