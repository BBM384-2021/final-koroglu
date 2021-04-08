import 'package:flutter/material.dart';

import '../../../core/base/view/base_view.dart';
import '../../../core/components/large_outlined_button.dart';
import '../../../core/components/logo_svg.dart';
import '../../../core/extensions/context_extension.dart';
import '../../../core/extensions/string_extension.dart';
import '../../../core/init/lang/locale_keys.g.dart';
import '../../../core/init/navigation/navigation_service.dart';
import '../../../core/init/theme/color_scheme.dart';
import '../viewmodel/welcome_view_model.dart';

class WelcomeView extends StatelessWidget {
  const WelcomeView({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return BaseView(
      viewModel: WelcomeViewModel(),
      onModelReady: (model) {
        model.setContext(context);
        model.init();
      },
      onPageBuilder: (BuildContext context, WelcomeViewModel viewModel) =>
          buildScaffold(context),
    );
  }

  Scaffold buildScaffold(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      backgroundColor: Colors.white,
      body: Center(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Spacer(flex: 3),
            buildLogo(context),
            buildTitles(context),
            Spacer(flex: 1),
            buildButtons(context),
            Spacer(
              flex: 2,
            )
          ],
        ),
      ),
    );
  }

  Expanded buildLogo(BuildContext context) {
    return Expanded(
      flex: 3,
      child: LogoSvg(
        height: context.height * 0.2,
      ),
    );
  }

  Expanded buildTitles(BuildContext context) {
    return Expanded(
        flex: 1,
        child: Column(
          children: [
            Text(
              LocaleKeys.welcomeScreen_welcomeToHobbyDoge.locale,
              style: TextStyle(
                  fontSize: context.height * 0.03,
                  color: Color(0xff26342B),
                  fontWeight: FontWeight.w600),
            ),
            Spacer(flex: 1),
            Text(
              LocaleKeys.welcomeScreen_timeForHobbyDoge.locale,
              style: TextStyle(
                  fontSize: context.height * 0.024,
                  color: Color(0xff495A5F),
                  fontWeight: FontWeight.w600),
            )
          ],
        ));
  }

  Expanded buildButtons(BuildContext context) {
    return Expanded(
        flex: 2,
        child: Column(
          children: [
            LargeOutlinedButton(
              text: LocaleKeys.welcomeScreen_signup.locale,
              onPressed: () {
                NavigationService.instance.navigateToPage(path: "/signup");
              },
            ),
            Spacer(flex: 1),
            LargeOutlinedButton(
              text: LocaleKeys.welcomeScreen_login,
              onPressed: () {
                NavigationService.instance.navigateToPage(path: "/login");
              },
              backgroundColor: Colors.white,
              textColor: AppColorScheme.instance.greenLight3,
            )
          ],
        ));
  }
}
