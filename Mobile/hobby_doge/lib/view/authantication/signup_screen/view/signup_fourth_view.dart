import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/view/base_view.dart';
import 'package:hobby_doge/core/components/large_outlined_button.dart';
import 'package:hobby_doge/core/init/lang/locale_keys.g.dart';
import 'package:hobby_doge/core/init/theme/color_scheme.dart';
import 'package:hobby_doge/view/authantication/signup_screen/viewmodel/signup_view_model.dart';
import 'package:pin_code_fields/pin_code_fields.dart';
import '../../../../core/extensions/context_extension.dart';
import '../../../../core/extensions/string_extension.dart';

class SignUpFourthView extends StatelessWidget {
  const SignUpFourthView({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return BaseView(
      viewModel: SignUpViewModel(),
      onModelReady: (model) {
        model.setContext(context);
        model.init();
      },
      onPageBuilder: (BuildContext context, SignUpViewModel viewmodel) =>
          buildScaffold(context, viewmodel),
    );
  }
}

Scaffold buildScaffold(BuildContext context, SignUpViewModel viewmodel) {
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
              text: LocaleKeys.signUpScreen_verificationCodePage_signUp,
              onPressed: () {}),
          Spacer(flex: 1),
          Expanded(
            flex: 3,
            child: buildTermsStrings(context),
          ),
          Spacer(flex: 5)
        ],
      ),
    ),
  );
}

Column buildTermsStrings(BuildContext context) {
  return Column(
    children: [
      Text(
        LocaleKeys.signUpScreen_verificationCodePage_termsExplenation.locale,
        style: TextStyle(
            color: AppColorScheme.instance.grey,
            fontSize: context.width * 0.04,
            fontWeight: FontWeight.w500),
      ),
      TextButton(
          onPressed: () {},
          child: Text(
              LocaleKeys.signUpScreen_verificationCodePage_termsLink.locale,
              style: TextStyle(
                  color: AppColorScheme.instance.greenLight2,
                  fontSize: context.width * 0.04,
                  fontWeight: FontWeight.w500))),
    ],
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
    LocaleKeys
        .signUpScreen_verificationCodePage_verificationCodeSubTitle.locale,
    textAlign: TextAlign.center,
    style: TextStyle(
        fontSize: context.height * 0.024,
        color: AppColorScheme.instance.grey,
        fontWeight: FontWeight.w600),
  );
}

Text bildMainTitle(BuildContext context) {
  return Text(
    LocaleKeys.signUpScreen_verificationCodePage_verificationCode.locale,
    style: TextStyle(
        fontSize: context.height * 0.03,
        color: AppColorScheme.instance.darkerGrey,
        fontWeight: FontWeight.w600),
  );
}
