import 'dart:async';

import 'package:connectivity/connectivity.dart';
import 'package:flutter/material.dart';
import 'package:flutter_mobx/flutter_mobx.dart';
import 'package:hobby_doge/core/components/darker_background_logo_svg.dart';
import 'package:hobby_doge/core/components/logo_svg.dart';

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
  SplashViewModel viewModel = SplashViewModel();
  StreamSubscription _connectivityListener;
  @override
  void initState() {
    super.initState();

    _connectivityListener =
        Connectivity().onConnectivityChanged.listen((event) {
      if (event != ConnectivityResult.none) {
        viewModel.updateAnimation();
        Future.delayed(Duration(seconds: 1), () {
          NavigationService.instance
              .navigatorToPageRemoveOld(path: NavigationConstants.WELCOME_VIEW);
        });
      }
    });
  }

  @override
  void dispose() {
    _connectivityListener.cancel();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return BaseView<SplashViewModel>(
      viewModel: viewModel,
      onModelReady: (model) {
        model.setContext(context);
        model.init();
      },
      onPageBuilder: (BuildContext context, SplashViewModel viewModel) =>
          Observer(builder: (_) => buildScaffold(viewModel)),
    );
  }

  Scaffold buildScaffold(SplashViewModel viewModel) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      backgroundColor: AppColorScheme.instance.greenLight0,
      body: Center(
        child: Stack(
          alignment: Alignment.center,
          children: [
            AnimatedContainer(
              height: dynamicHeight(viewModel.heightWeightValue),
              width: dynamicHeight(viewModel.heightWeightValue),
              duration: Duration(seconds: 1),
              curve: Curves.fastOutSlowIn,
              decoration: BoxDecoration(
                  color: AppColorScheme.instance.greenLight1,
                  borderRadius:
                      BorderRadius.circular(viewModel.animationBorderRadius)),
            ),
            DarkerBackgroundLogoSvg(
              height: dynamicHeight(0.2),
            ),
            Container(
              alignment: Alignment.bottomCenter,
              height: dynamicHeight(0.5),
              width: dynamicWidth(1),
              child: Text(
                "HobbyDoge",
                style: TextStyle(
                    fontSize: dynamicHeight(0.05),
                    color: AppColorScheme.instance.greenLight3,
                    fontWeight: FontWeight.w600),
              ),
            )
          ],
        ),
      ),
    );
  }
}
