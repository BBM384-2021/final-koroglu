import 'dart:async';

import 'package:connectivity/connectivity.dart';
import 'package:flutter/material.dart';

import '../../../core/base/state/base_state.dart';
import '../../../core/base/view/base_view.dart';
import '../../../core/constants/navigation_constants.dart';
import '../../../core/init/navigation/navigation_service.dart';
import '../../../core/init/theme/color_scheme.dart';
import '../viewmodel/splash_view_model.dart';

class SplashView extends StatefulWidget {
  SplashView({Key key}) : super(key: key);

  @override
  _SplashViewState createState() => _SplashViewState();
}

class _SplashViewState extends BaseState<SplashView>
    with SingleTickerProviderStateMixin {
  AnimationController _controller;
  Animation _animation;
  StreamSubscription _connectivityListener;
  @override
  void initState() {
    super.initState();

    _controller =
        AnimationController(vsync: this, duration: Duration(seconds: 2));
    _animation = Tween<double>(begin: 0.40, end: 1).animate(_controller);
    _connectivityListener =
        Connectivity().onConnectivityChanged.listen((event) {
      if (event != ConnectivityResult.none) {
        _controller.forward();
        Future.delayed(Duration(seconds: 1), () {
          print("hadi");
          NavigationService.instance
              .navigatorToPageRemoveOld(path: NavigationConstants.TEST_VIEW);
        });
      }
    });
  }

  @override
  void dispose() {
    _controller.dispose();
    _connectivityListener.cancel();
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
          buildScaffold(viewModel),
    );
  }

  Scaffold buildScaffold(SplashViewModel viewModel) {
    return Scaffold(
        backgroundColor: AppColorScheme.instance.greenLight0,
        body: Center(
          child: Stack(
            alignment: Alignment.center,
            children: [buildLogoBackground(), buildLogo(), buildTitle()],
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
            width: dynamicWidth(_animation.value),
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
