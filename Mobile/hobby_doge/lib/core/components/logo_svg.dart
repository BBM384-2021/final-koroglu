import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';

class LogoSvg extends StatelessWidget {
  const LogoSvg({Key key, this.height}) : super(key: key);
  final double height;
  @override
  Widget build(BuildContext context) {
    return SvgPicture.asset(
      "assets/images/logo_svg.svg",
      height: this.height,
    );
  }
}
