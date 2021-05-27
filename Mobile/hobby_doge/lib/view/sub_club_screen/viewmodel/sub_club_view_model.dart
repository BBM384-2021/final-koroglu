import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/model/base_view_model.dart';
import 'package:hobby_doge/core/init/network/network_service.dart';
import 'package:mobx/mobx.dart';
import 'package:shared_preferences/shared_preferences.dart';
part 'sub_club_view_model.g.dart';

class SubClubViewModel = _SubClubViewModelBase with _$SubClubViewModel;

abstract class _SubClubViewModelBase with Store, BaseViewModel {
  Future club;
  int clubID;

  @observable
  bool isJoined;

  String username;
  @override
  void setContext(BuildContext context) => this.context = context;

  void setID(int id) {
    print("setter " + id.toString());
    clubID = id;
  }

  @override
  void init() async {
    

    club = NetworkService.instance.getSubClubByID(clubID);
  }
}
