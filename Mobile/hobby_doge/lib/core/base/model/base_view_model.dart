import 'package:flutter/material.dart';
import 'package:hobby_doge/core/init/navigation/navgiation_service.dart';

abstract class BaseViewModel {
  BuildContext context;

  NavigationService navigation = NavigationService.instance;

  void setContext(BuildContext context);
  void init();
}