import 'package:flutter/material.dart';
import 'package:hobby_doge/view/authantication/signup_screen/view/signup_first_view.dart';
import 'package:hobby_doge/view/authantication/signup_screen/view/signup_second_view.dart';
import 'package:hobby_doge/view/authantication/signup_screen/view/signup_third_view.dart';

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
      case NavigationConstants.SIGNUP_FIRST_VIEW:
        return MaterialPageRoute(builder: (context) => SignUpFirstView());
      case NavigationConstants.SIGNUP__SECOND_VIEW:
        return MaterialPageRoute(builder: (context) => SignUpSecondView());
      case NavigationConstants.SIGNUP__THIRD_VIEW:
        return MaterialPageRoute(builder: (context) => SignUpThirdView());
      default:
        return MaterialPageRoute(
            builder: (context) => Scaffold(
                  body: Center(child: Text("Not found page")),
                ));
    }
  }
}
