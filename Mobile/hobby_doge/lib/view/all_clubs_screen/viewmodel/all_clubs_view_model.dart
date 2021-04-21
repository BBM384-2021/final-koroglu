import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/model/base_view_model.dart';
import 'package:mobx/mobx.dart';
part 'all_clubs_view_model.g.dart';

class AllClubsViewModel = _AllClubsViewModelBase with _$AllClubsViewModel;

abstract class _AllClubsViewModelBase with Store, BaseViewModel {
  @override
  void setContext(BuildContext context) => this.context = context;

  @override
  void init() {}
}
