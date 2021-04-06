import 'package:connectivity/connectivity.dart';
import 'package:flutter/material.dart';
import '../../../core/base/model/base_view_model.dart';
import 'package:mobx/mobx.dart';
part 'splash_view_model.g.dart';

class SplashViewModel = _SplashViewModelBase with _$SplashViewModel;

abstract class _SplashViewModelBase with Store, BaseViewModel {
  @override
  void setContext(BuildContext context) => this.context = context;

  @observable
  var connectivityResult;

  void updateConnectivity() async {}

  @override
  void init() {
    //updateConnectivity();
  }
}
