import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';

class DarkerBackgroundLogoSvg extends StatelessWidget {
  const DarkerBackgroundLogoSvg({Key key, this.height}) : super(key: key);
  final double height;
  @override
  Widget build(BuildContext context) {
    return SvgPicture.asset(
      "assets/images/darker_background_logo.svg",
      height: this.height,
    );
  }
}
