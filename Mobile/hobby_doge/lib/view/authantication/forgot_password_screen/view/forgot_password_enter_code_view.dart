import 'package:flutter/material.dart';
import 'package:pin_code_fields/pin_code_fields.dart';

import '../../../../core/base/view/base_view.dart';
import '../../../../core/components/large_outlined_button.dart';
import '../../../../core/constants/navigation_constants.dart';
import '../../../../core/extensions/context_extension.dart';
import '../../../../core/extensions/string_extension.dart';
import '../../../../core/init/lang/locale_keys.g.dart';
import '../../../../core/init/navigation/navigation_service.dart';
import '../../../../core/init/theme/color_scheme.dart';
import '../viewmodel/forgot_password_view_model.dart';

class ForgotPasswordEnterCodeView extends StatelessWidget {
  const ForgotPasswordEnterCodeView({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return BaseView(
      viewModel: ForgotPasswordViewModel(),
      onModelReady: (model) {
        model.setContext(context);
        model.init();
      },
      onPageBuilder:
          (BuildContext context, ForgotPasswordViewModel viewmodel) =>
              buildScaffold(context, viewmodel),
    );
  }
}

Scaffold buildScaffold(
    BuildContext context, ForgotPasswordViewModel viewmodel) {
  return Scaffold(
    backgroundColor: Colors.white,
    resizeToAvoidBottomInset: false,
    extendBodyBehindAppBar: true,
    appBar: buildAppBar(context),
    body: Center(
      child: Column(
        children: [
          Spacer(flex: 5),
          Expanded(flex: 3, child: buildTitles(context)),
          Spacer(flex: 1),
          Expanded(flex: 2, child: buildPinField(context)),
          Spacer(flex: 1),
          LargeOutlinedButton(
              text: LocaleKeys.forgotPassword_enterResetCode_resetCode.locale,
              onPressed: () {
                NavigationService.instance.navigateToPage(
                    path:
                        NavigationConstants.FORGOT_PASSWORD_SET_PASSWORD_VIEW);
              }),
          Spacer(flex: 8)
        ],
      ),
    ),
  );
}

Padding buildPinField(BuildContext context) {
  return Padding(
    padding: EdgeInsets.symmetric(horizontal: context.width * 0.1),
    child: PinCodeTextField(
      enableActiveFill: true,
      length: 6,
      appContext: context,
      onChanged: (String value) {
        print(value);
      },
      pinTheme: PinTheme(
        shape: PinCodeFieldShape.box,
        borderRadius: BorderRadius.circular(8),
        fieldHeight: context.height * 0.07,
        fieldWidth: 40,
        borderWidth: 0.3,
        activeFillColor: AppColorScheme.instance.veryLightGrey,
        inactiveFillColor: AppColorScheme.instance.veryLightGrey,
        selectedFillColor: AppColorScheme.instance.veryLightGrey,
        activeColor: AppColorScheme.instance.greenLight3,
        inactiveColor: AppColorScheme.instance.boxBorderLightGrey,
        selectedColor: AppColorScheme.instance.boxBorderLightGrey,
      ),
    ),
  );
}

AppBar buildAppBar(BuildContext context) {
  return AppBar(
      elevation: 0,
      automaticallyImplyLeading: true,
      backgroundColor: Colors.transparent,
      leading: IconButton(
        onPressed: () {
          Navigator.pop(context);
        },
        icon: Icon(
          Icons.arrow_back_ios_rounded,
          size: context.height * 0.03,
          color: AppColorScheme.instance.greenLight2,
        ),
      ));
}

Padding buildTitles(BuildContext context) {
  return Padding(
    padding: EdgeInsets.symmetric(horizontal: context.height * 0.05),
    child: Column(
      children: [
        Expanded(flex: 1, child: bildMainTitle(context)),
        Expanded(flex: 2, child: buildSubTitle(context)),
      ],
    ),
  );
}

Text buildSubTitle(BuildContext context) {
  return Text(
    LocaleKeys.forgotPassword_enterResetCode_resetCodeExplenation.locale,
    textAlign: TextAlign.center,
    style: TextStyle(
        fontSize: context.height * 0.024,
        color: AppColorScheme.instance.grey,
        fontWeight: FontWeight.w600),
  );
}

Text bildMainTitle(BuildContext context) {
  return Text(
    LocaleKeys.forgotPassword_enterResetCode_resetCode.locale,
    style: TextStyle(
        fontSize: context.height * 0.03,
        color: AppColorScheme.instance.darkerGrey,
        fontWeight: FontWeight.w600),
  );
}
