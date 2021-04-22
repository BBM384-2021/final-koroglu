import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/model/base_view_model.dart';
import 'package:mobx/mobx.dart';
part 'admin_panel_view_model.g.dart';

class AdminPanelViewModel = _AdminPanelViewModelBase with _$AdminPanelViewModel;

abstract class _AdminPanelViewModelBase with Store, BaseViewModel {
  @override
  void setContext(BuildContext context) => this.context = context;

  @override
  void init() {}
}
