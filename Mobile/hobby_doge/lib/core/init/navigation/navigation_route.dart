import 'package:flutter/material.dart';

import '../../../view/authantication/login_screen/view/login_view.dart';
import '../../../view/splash_screen/view/splash_view.dart';
import '../../../view/test/view/test_view.dart';
import '../../../view/welcome_sceen/view/welcome_view.dart';
import '../../constants/navigation_constants.dart';

class NavigationRoute {
  static NavigationRoute _instance = NavigationRoute._init();
  static NavigationRoute get instance => _instance;

  NavigationRoute._init();

  Route<dynamic> generateRoute(RouteSettings args) {
    switch (args.name) {
      case NavigationConstants.TEST_VIEW:
        return MaterialPageRoute(builder: (context) => TestEkrani());
      case NavigationConstants.SPLASH_VIEW:
        return MaterialPageRoute(builder: (context) => SplashView());
      case NavigationConstants.LOGIN_VIEW:
        return MaterialPageRoute(builder: (context) => LoginView());
      case NavigationConstants.WELCOME_VIEW:
        return MaterialPageRoute(builder: (context) => WelcomeView());
      default:
        return MaterialPageRoute(
            builder: (context) => Scaffold(
                  body: Center(child: Text("Not found page")),
                ));
    }
  }
}
