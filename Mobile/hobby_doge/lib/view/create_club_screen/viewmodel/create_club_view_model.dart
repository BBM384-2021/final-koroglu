import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/model/base_view_model.dart';
import 'package:mobx/mobx.dart';
part 'create_club_view_model.g.dart';

class CreateClubViewModel = _CreateClubViewModelBase with _$CreateClubViewModel;

abstract class _CreateClubViewModelBase with Store, BaseViewModel {
  @override
  void setContext(BuildContext context) => this.context = context;

  @override
  void init() {}

  

}
