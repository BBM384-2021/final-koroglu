package org.koroglu.hobbydoge.service;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.ClubRequest;
import org.koroglu.hobbydoge.controller.request.NewClubRequest;
import org.koroglu.hobbydoge.dto.mapper.ClubMapper;
import org.koroglu.hobbydoge.dto.model.ClubDTO;
import org.koroglu.hobbydoge.exception.RestClubDoesNotExistException;
import org.koroglu.hobbydoge.exception.RestClubNameAlreadyExistException;
import org.koroglu.hobbydoge.exception.RestUserNotFoundException;
import org.koroglu.hobbydoge.model.Club;
import org.koroglu.hobbydoge.model.User;
import org.koroglu.hobbydoge.repository.ClubRepository;
import org.koroglu.hobbydoge.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClubServiceImpl implements ClubService {

  private final ClubRepository clubRepository;
  private final UserRepository userRepository;

  @Override
  public ClubDTO get(Long id) {

    Club club = clubRepository.findById(id).orElseThrow(RestClubDoesNotExistException::new);
    return ClubMapper.toClubDTO(club);

  }

  @Override
  public List<ClubDTO> getClubs(int offset, int limit) {

    return clubRepository.getAllClubs(offset, limit).stream()
            .map(ClubMapper::toClubDTO).collect(Collectors.toList());

  }

  @Override
  public List<ClubDTO> search(String q) {

    return clubRepository.findByNameContainingIgnoreCase(q).stream()
            .map(ClubMapper::toClubDTO).collect(Collectors.toList());
    
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

  @Override
  public String join(Long id) {
    User contextUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    //TODO: Add interest point check here.

    User user = userRepository.findById(contextUser.getId()).orElseThrow(RestUserNotFoundException::new);

    Club club = clubRepository.findById(id).orElseThrow(RestClubDoesNotExistException::new);

    club.getMembers().add(user);

    clubRepository.save(club);

    return String.format("User with ID: %d joined to the %s club successfully.", user.getId(), club.getName());

  }

  @Override
  public String leave(Long id) {
    User contextUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    User user = userRepository.findById(contextUser.getId()).orElseThrow(RestUserNotFoundException::new);

    Club club = clubRepository.findById(id).orElseThrow(RestClubDoesNotExistException::new);

    club.getMembers().remove(user);

    clubRepository.save(club);

    return String.format("User with ID: %d left the %s club successfully.", user.getId(), club.getName());

  }


}
