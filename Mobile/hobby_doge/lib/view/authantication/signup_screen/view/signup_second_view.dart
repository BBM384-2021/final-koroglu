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

class SignUpSecondView extends StatelessWidget {
  const SignUpSecondView({Key key}) : super(key: key);

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
            buildNextButton(context, viewmodel),
            Spacer(flex: 3)
          ],
        ),
      ),
    );
  }

  Expanded buildNextButton(BuildContext context, SignUpViewModel viewmodel) {
    return Expanded(
      flex: 3,
      child: Column(
        children: [
          LargeOutlinedButton(
              text: LocaleKeys.signUpScreen_next.locale,
              onPressed: () {
                if (viewmodel.formState.currentState.validate()) {
                  NavigationService.instance
                      .navigateToPage(path: "/signup_third_view");
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
              buildUsernameField(viewmodel),
              Spacer(
                flex: 1,
              ),
              buildEmailField(viewmodel),
            ],
          )),
    );
  }

  Observer buildUsernameField(SignUpViewModel viewmodel) {
    return Observer(
        builder: (_) => CommonTextField(
              text: LocaleKeys.signUpScreen_username.locale,
              textController: viewmodel.usernameController,
              validator: (value) => value.isNotEmpty
                  ? null
                  : LocaleKeys.signUpScreen_fieldRequired.locale,
            ));
  }

  Expanded buildTitles(BuildContext context) {
    return Expanded(
        flex: 2,
        child: Column(
          children: [
            bildMainTitle(context),
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
        LocaleKeys.signUpScreen_secondPageStep.locale,
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
        LocaleKeys.signUpScreen_secondPageSubTitle.locale,
        style: TextStyle(
            fontSize: context.height * 0.024,
            color: AppColorScheme.instance.grey,
            fontWeight: FontWeight.w600),
      ),
    );
  }

  Expanded bildMainTitle(BuildContext context) {
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

Observer buildEmailField(SignUpViewModel viewmodel) {
  return Observer(
      builder: (_) => CommonTextField(
            text: LocaleKeys.signUpScreen_email.locale,
            textController: viewmodel.emailController,
            validator: (String value) => value.isValidEmail
                ? null
                : LocaleKeys.signUpScreen_invalidEmail.locale,
          ));
}
