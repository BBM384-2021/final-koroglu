import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/model/base_view_model.dart';
import 'package:mobx/mobx.dart';
part 'test_viewmodel.g.dart';

class TestViewModel = _TestViewModelBase with _$TestViewModel;

abstract class _TestViewModelBase with Store, BaseViewModel {
  @override
  void setContext(BuildContext context) => this.context = context;

  @override
  void init() {}

  @observable
  int number = 0;
}
