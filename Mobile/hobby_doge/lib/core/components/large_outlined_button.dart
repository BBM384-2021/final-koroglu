import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';

import '../extensions/context_extension.dart';
import '../extensions/string_extension.dart';
import '../init/theme/color_scheme.dart';

class LargeOutlinedButton extends StatelessWidget {
  const LargeOutlinedButton(
      {Key key,
      this.backgroundColor,
      this.textColor,
      this.borderColor,
      this.fontSize,
      this.fontWeight = FontWeight.w600,
      this.borderRadius = 8,
      @required this.text,
      @required this.onPressed})
      : super(key: key);

  final String text;
  final Function onPressed;

  final double borderRadius;
  final Color backgroundColor;
  final Color textColor;
  final Color borderColor;
  final double fontSize;
  final FontWeight fontWeight;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: context.width * 0.8,
      height: context.height * 0.07,
      child: OutlinedButton(
        onPressed: onPressed,
        child: Text(
          text.locale,
          style: TextStyle(
              fontSize: fontSize != null ? fontSize : context.height * 0.024,
              fontWeight: fontWeight,
              color: textColor != null ? textColor : Colors.white),
        ),
        style: ButtonStyle(
            backgroundColor: MaterialStateProperty.all<Color>(
                backgroundColor != null
                    ? backgroundColor
                    : AppColorScheme.instance.greenLight3),
            shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(8),
            )),
            side: MaterialStateProperty.all<BorderSide>(BorderSide(
              color: borderColor != null
                  ? borderColor
                  : AppColorScheme.instance.greenLight3,
            ))),
      ),
    );
  }
}
