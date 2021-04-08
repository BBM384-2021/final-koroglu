import 'package:flutter/material.dart';
import '../init/theme/color_scheme.dart';
import '../../core/extensions/context_extension.dart';

class CommonTextField extends StatelessWidget {
  const CommonTextField(
      {Key key,
      @required this.text,
      @required this.textController,
      this.validator})
      : super(key: key);
  final text;
  final textController;
  final validator;
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.symmetric(horizontal: context.width * 0.1),
      child: TextFormField(
        validator: validator,
        cursorColor: AppColorScheme.instance.grey,
        style: TextStyle(
            color: AppColorScheme.instance.grey, fontWeight: FontWeight.w500),
        decoration: InputDecoration(
          fillColor: AppColorScheme.instance.veryLightGrey,
          filled: true,
          labelText: text,
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
