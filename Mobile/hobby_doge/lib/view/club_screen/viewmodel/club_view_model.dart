import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/model/base_view_model.dart';
import 'package:mobx/mobx.dart';
part 'club_view_model.g.dart';

class ClubViewModel = _ClubViewModelBase with _$ClubViewModel;

abstract class _ClubViewModelBase with Store, BaseViewModel {
  
  @override
  void setContext(BuildContext context) => this.context = context;

  @override
  void init() {}
}
