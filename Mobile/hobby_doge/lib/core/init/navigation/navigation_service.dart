import 'package:flutter/material.dart';

class NavigationService {
  static NavigationService _instance = NavigationService._init();
  static NavigationService get instance => _instance;

  NavigationService._init();

  GlobalKey<NavigatorState> navigatorKey = GlobalKey();

  Future<void> navigateToPage({String path, Object object}) async {
    await navigatorKey.currentState.pushNamed(path, arguments: object);
  }

  Future<void> navigatorToPageRemoveOld({String path, Object object}) async {
    navigatorKey.currentState
        .pushNamedAndRemoveUntil(path, (route) => false, arguments: object);
  }
}
