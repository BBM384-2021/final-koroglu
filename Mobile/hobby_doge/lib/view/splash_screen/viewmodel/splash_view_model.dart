import 'package:flutter/material.dart';
import 'package:mobx/mobx.dart';

import '../../../core/base/model/base_view_model.dart';

part 'splash_view_model.g.dart';

class SplashViewModel = _SplashViewModelBase with _$SplashViewModel;

abstract class _SplashViewModelBase with Store, BaseViewModel {
  @override
  void setContext(BuildContext context) => this.context = context;

  @observable
  var connectivityResult;

  void updateConnectivity() async {}

  @observable
  double heightWeightValue;

  @observable
  double animationBorderRadius;

  @action
  void updateAnimation() {
    animationBorderRadius = 0;
    heightWeightValue = 1;
  }

  @override
  void init() {
    heightWeightValue = 0;
    animationBorderRadius = 100;
    //updateConnectivity();
  }
}
