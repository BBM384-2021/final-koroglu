import 'package:connectivity/connectivity.dart';
import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/model/base_view_model.dart';
import 'package:mobx/mobx.dart';
part 'splash_view_model.g.dart';

class SplashViewModel = _SplashViewModelBase with _$SplashViewModel;

abstract class _SplashViewModelBase with Store, BaseViewModel {
  @override
  void setContext(BuildContext context) => this.context = context;

  @observable
  var connectivityResult;

  @override
  void init() async {
    connectivityResult = await (Connectivity().checkConnectivity());
  }
}
