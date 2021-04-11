import 'package:flutter/material.dart';
import 'package:flutter_mobx/flutter_mobx.dart';
import 'package:hobby_doge/core/base/view/base_view.dart';
import 'package:hobby_doge/core/components/common_text_field.dart';
import 'package:hobby_doge/core/components/large_outlined_button.dart';
import 'package:hobby_doge/core/components/password_text_field.dart';
import 'package:hobby_doge/core/init/lang/locale_keys.g.dart';
import 'package:hobby_doge/core/init/navigation/navigation_service.dart';
import 'package:hobby_doge/core/init/theme/color_scheme.dart';
import 'package:hobby_doge/view/authantication/signup_screen/viewmodel/signup_view_model.dart';
import '../../../../core/extensions/context_extension.dart';
import '../../../../core/extensions/string_extension.dart';

class SignUpThirdView extends StatelessWidget {
  const SignUpThirdView({Key key}) : super(key: key);

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

  Scaffold buildScaffold(BuildContext context, SignUpViewModel viewmodel) {
    return Scaffold(
      backgroundColor: Colors.white,
      resizeToAvoidBottomInset: false,
      extendBodyBehindAppBar: true,
      appBar: buildAppBar(context),
      body: Center(
        child: Column(
          children: [
            Spacer(flex: 4),
            buildTitles(context),
            Spacer(flex: 1),
            buildTextFields(context, viewmodel),
            Spacer(flex: 1),
            buildSendCodeButton(context, viewmodel),
            Spacer(flex: 3)
          ],
        ),
      ),
    );
  }

  Expanded buildSendCodeButton(
      BuildContext context, SignUpViewModel viewmodel) {
    return Expanded(
      flex: 3,
      child: Column(
        children: [
          LargeOutlinedButton(
              text: LocaleKeys.signUpScreen_sendCode.locale,
              onPressed: () {
                if (viewmodel.formState.currentState.validate() &&
                    viewmodel.checkPasswordMatched()) {
                  NavigationService.instance
                      .navigateToPage(path: "/signup_fourth_view");
                }
              }),
        ],
      ),
    );
  }

  Expanded buildTextFields(BuildContext context, SignUpViewModel viewmodel) {
    return Expanded(
      flex: 4,
      child: Form(
          key: viewmodel.formState,
          child: Column(
            children: [
              Expanded(flex: 4, child: buildPasswordField(viewmodel)),
              Spacer(
                flex: 1,
              ),
              Expanded(flex: 4, child: buildConfirmPasswordField(viewmodel)),
            ],
          )),
    );
  }

  Expanded buildTitles(BuildContext context) {
    return Expanded(
        flex: 2,
        child: Column(
          children: [
            buildMainTitle(context),
            Spacer(flex: 1),
            buildSubTitle(context),
            buildStepTitle(context),
          ],
        ));
  }

  Expanded buildStepTitle(BuildContext context) {
    return Expanded(
      flex: 3,
      child: Text(
        LocaleKeys.signUpScreen_thirdPageStep.locale,
        style: TextStyle(
            fontSize: context.height * 0.02,
            color: AppColorScheme.instance.grey,
            fontWeight: FontWeight.w600),
      ),
    );
  }

  Expanded buildSubTitle(BuildContext context) {
    return Expanded(
      flex: 3,
      child: Text(
        LocaleKeys.signUpScreen_thirdPageSubTitle.locale,
        style: TextStyle(
            fontSize: context.height * 0.024,
            color: AppColorScheme.instance.grey,
            fontWeight: FontWeight.w600),
      ),
    );
  }

  Expanded buildMainTitle(BuildContext context) {
    return Expanded(
      flex: 3,
      child: Text(
        LocaleKeys.signUpScreen_signup.locale,
        style: TextStyle(
            fontSize: context.height * 0.03,
            color: AppColorScheme.instance.darkerGrey,
            fontWeight: FontWeight.w600),
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
}

Observer buildPasswordField(SignUpViewModel viewmodel) {
  return Observer(
    builder: (_) => CustomPasswordTextField(
      text: LocaleKeys.signUpScreen_password.locale,
      isHidden: viewmodel.isHidden,
      isHiddenChange: viewmodel.isHiddenChange,
      textController: viewmodel.passwordController,
      validator: (String value) {
        if (value.isEmpty) {
          return LocaleKeys.signUpScreen_fieldRequired.locale;
        } else if (value.length < 6) {
          return LocaleKeys.signUpScreen_tooShortPassword.locale;
        } else {
          return null;
        }
      },
    ),
  );
}

Observer buildConfirmPasswordField(SignUpViewModel viewmodel) {
  return Observer(
    builder: (_) => CustomPasswordTextField(
      text: LocaleKeys.signUpScreen_confirmPassword.locale,
      isHidden: viewmodel.isHidden,
      isHiddenChange: viewmodel.isHiddenChange,
      textController: viewmodel.confirmPasswordController,
      validator: (String value) {
        if (value.isEmpty) {
          return LocaleKeys.signUpScreen_fieldRequired.locale;
        } else if (value.length < 6) {
          return LocaleKeys.signUpScreen_tooShortPassword.locale;
        } else if (!viewmodel.checkPasswordMatched()) {
          return LocaleKeys.signUpScreen_passwordsNotMatched.locale;
        } else {
          return null;
        }
      },
    ),
  );
}
