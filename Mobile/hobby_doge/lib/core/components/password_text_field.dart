import 'package:flutter/material.dart';

import '../extensions/context_extension.dart';
import '../extensions/string_extension.dart';
import '../init/lang/locale_keys.g.dart';
import '../init/theme/color_scheme.dart';

class CustomPasswordTextField extends StatelessWidget {
  const CustomPasswordTextField(
      {Key key,
      @required this.text,
      @required this.isHiddenChange,
      @required this.isHidden,
      @required this.textController, this.validator})
      : super(key: key);
  final text;
  final isHiddenChange;
  final isHidden;
  final textController;
  final validator;
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.symmetric(horizontal: context.width * 0.1),
      child: TextFormField(
        validator: validator,
        obscureText: isHidden,
        cursorColor: AppColorScheme.instance.grey,
        style: TextStyle(
            color: AppColorScheme.instance.grey, fontWeight: FontWeight.w500),
        decoration: InputDecoration(
          suffixIcon: IconButton(
            splashColor: Colors.transparent,
            highlightColor: Colors.transparent,
            icon: Icon(
              Icons.remove_red_eye,
              color: AppColorScheme.instance.greenLight2,
            ),
            onPressed: () {
              isHiddenChange();
            },
          ),
          fillColor: AppColorScheme.instance.veryLightGrey,
          filled: true,
          labelText: LocaleKeys.loginScreen_password.locale,
          floatingLabelBehavior: FloatingLabelBehavior.auto,
          labelStyle: TextStyle(
              color: AppColorScheme.instance.lightGrey,
              height: context.height * 0.001,
              fontWeight: FontWeight.w400,
              fontSize: context.height * 0.02),
          focusedBorder: OutlineInputBorder(
              borderSide:
                  BorderSide(color: AppColorScheme.instance.greenLight2),
              borderRadius: BorderRadius.circular(8)),
          enabledBorder: OutlineInputBorder(
              borderSide: BorderSide.none,
              borderRadius: BorderRadius.circular(8)),
          errorBorder: OutlineInputBorder(
              borderSide: BorderSide.none,
              borderRadius: BorderRadius.circular(8)),
          focusedErrorBorder: OutlineInputBorder(
              borderSide: BorderSide.none,
              borderRadius: BorderRadius.circular(8)),
          disabledBorder: OutlineInputBorder(
              borderSide: BorderSide.none,
              borderRadius: BorderRadius.circular(8)),
        ),
      ),
    );
  }
}
