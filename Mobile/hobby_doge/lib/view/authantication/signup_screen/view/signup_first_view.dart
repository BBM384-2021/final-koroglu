import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/view/base_view.dart';
import 'package:hobby_doge/core/components/common_text_field.dart';
import 'package:hobby_doge/core/components/large_outlined_button.dart';
import 'package:hobby_doge/core/init/lang/locale_keys.g.dart';
import 'package:hobby_doge/core/init/navigation/navigation_service.dart';
import 'package:hobby_doge/core/init/theme/color_scheme.dart';
import 'package:hobby_doge/view/authantication/signup_screen/viewmodel/signup_view_model.dart';
import '../../../../core/extensions/context_extension.dart';
import '../../../../core/extensions/string_extension.dart';

class SignUpFirstView extends StatelessWidget {
  const SignUpFirstView({Key key}) : super(key: key);

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
            buildNextAndLoginButtons(context, viewmodel),
            Spacer(flex: 3)
          ],
        ),
      ),
    );
  }

  Expanded buildNextAndLoginButtons(
      BuildContext context, SignUpViewModel viewmodel) {
    return Expanded(
      flex: 3,
      child: Column(
        children: [
          buildNextButton(viewmodel),
          Spacer(flex: 1),
          buildAlreadyHaveAccountTextButton(context)
        ],
      ),
    );
  }

  Expanded buildAlreadyHaveAccountTextButton(BuildContext context) {
    return Expanded(
      flex: 5,
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text(LocaleKeys.signUpScreen_alreadyHaveAccount.locale,
              style: TextStyle(
                  color: AppColorScheme.instance.grey,
                  fontWeight: FontWeight.w400,
                  fontSize: context.height * 0.02)),
          TextButton(
            child: Text(
              LocaleKeys.signUpScreen_login.locale,
              style: TextStyle(
                  color: AppColorScheme.instance.greenLight3,
                  fontWeight: FontWeight.w400,
                  fontSize: context.height * 0.02),
            ),
            onPressed: () {
              NavigationService.instance.navigateToPage(path: "/login");
            },
          )
        ],
      ),
    );
  }

  LargeOutlinedButton buildNextButton(SignUpViewModel viewmodel) {
    return LargeOutlinedButton(
        text: LocaleKeys.signUpScreen_next.locale,
        onPressed: () {
          if (viewmodel.firstViewFormState.currentState.validate()) {
            NavigationService.instance
                .navigateToPage(path: "/signup_second_view", object: viewmodel);
          }
        });
  }

  Expanded buildTextFields(BuildContext context, SignUpViewModel viewmodel) {
    return Expanded(
      flex: 6,
      child: Form(
          key: viewmodel.firstViewFormState,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              buildNameField(viewmodel),
              buildSurnameField(viewmodel),
            ],
          )),
    );
  }

  CommonTextField buildNameField(SignUpViewModel viewmodel) {
    return CommonTextField(
      text: LocaleKeys.signUpScreen_name.locale,
      textController: viewmodel.nameController,
      validator: (value) => value.isNotEmpty
          ? null
          : LocaleKeys.signUpScreen_fieldRequired.locale,
    );
  }

  CommonTextField buildSurnameField(SignUpViewModel viewmodel) {
    return CommonTextField(
      text: LocaleKeys.signUpScreen_surname.locale,
      textController: viewmodel.surnameController,
      validator: (value) => value.isNotEmpty
          ? null
          : LocaleKeys.signUpScreen_fieldRequired.locale,
    );
  }

  Expanded buildTitles(BuildContext context) {
    return Expanded(
        flex: 3,
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
        LocaleKeys.signUpScreen_firstPageStep.locale,
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
        LocaleKeys.signUpScreen_firstPageSubTitle.locale,
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
