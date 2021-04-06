import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/state/base_state.dart';
import 'package:hobby_doge/core/base/view/base_view.dart';
import 'package:hobby_doge/core/constants/navigation_constants.dart';
import 'package:hobby_doge/core/init/navigation/navigation_service.dart';
import 'package:hobby_doge/core/init/theme/color_scheme.dart';
import 'package:hobby_doge/view/splash_screen/viewmodel/splash_view_model.dart';

class SplashView extends StatefulWidget {
  SplashView({Key key}) : super(key: key);

  @override
  _SplashViewState createState() => _SplashViewState();
}

class _SplashViewState extends BaseState<SplashView>
    with SingleTickerProviderStateMixin {
  AnimationController _controller;
  @override
  void initState() {
    super.initState();
    _controller = AnimationController(
        value: 0.01, vsync: this, duration: Duration(seconds: 10));

    Future.delayed(Duration(seconds: 5), () {
      // 5s over, navigate to a new page
      _controller.forward();
      NavigationService.instance
          .navigatorToPageRemoveOld(path: NavigationConstants.TEST_VIEW);
    });
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return BaseView<SplashViewModel>(
      viewModel: SplashViewModel(),
      onModelReady: (model) {
        model.setContext(context);
        model.init();
      },
      onPageBuilder: (BuildContext context, SplashViewModel viewModel) =>
          buildScaffold(),
    );
  }

  Scaffold buildScaffold() {
    return Scaffold(
        backgroundColor: AppColorScheme.instance.greenLight0,
        body: Center(
          child: Stack(
            alignment: Alignment.center,
            children: [
              buildLogoBackground(),
              buildLogo(),
              buildTitle()
            ],
          ),
        ));
  }

  Positioned buildTitle() {
    return Positioned(
                top: dynamicHeight(0.6),
                bottom: 0,
                child: Text(
                  "HobbyDoge",
                  style: TextStyle(
                      fontSize: dynamicHeight(0.05),
                      color: AppColorScheme.instance.greenLight3,
                      fontWeight: FontWeight.w600),
                ));
  }

  Positioned buildLogoBackground() {
    return Positioned(
      top: 0,
      bottom: dynamicHeight(0.25),
      child: AnimatedBuilder(
        animation: _controller,
        builder: (BuildContext context, Widget child) {
          return Container(
            width: dynamicWidth(_controller.value * 40),
            decoration: BoxDecoration(
                shape: BoxShape.circle,
                color: AppColorScheme.instance.greenLight1),
          );
        },
      ),
    );
  }

  Positioned buildLogo() {
    return Positioned(
      top: 0,
      bottom: dynamicHeight(0.2),
      right: dynamicWidth(0.25),
      left: dynamicWidth(0.25),
      child: Image.asset("assets/images/logo.png"),
    );
  }
}
