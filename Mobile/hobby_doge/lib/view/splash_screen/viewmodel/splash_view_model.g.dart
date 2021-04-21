// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'splash_view_model.dart';

// **************************************************************************
// StoreGenerator
// **************************************************************************

// ignore_for_file: non_constant_identifier_names, unnecessary_brace_in_string_interps, unnecessary_lambdas, prefer_expression_function_bodies, lines_longer_than_80_chars, avoid_as, avoid_annotating_with_dynamic

mixin _$SplashViewModel on _SplashViewModelBase, Store {
  final _$connectivityResultAtom =
      Atom(name: '_SplashViewModelBase.connectivityResult');

  @override
  dynamic get connectivityResult {
    _$connectivityResultAtom.reportRead();
    return super.connectivityResult;
  }

  @override
  set connectivityResult(dynamic value) {
    _$connectivityResultAtom.reportWrite(value, super.connectivityResult, () {
      super.connectivityResult = value;
    });
  }

  final _$heightWeightValueAtom =
      Atom(name: '_SplashViewModelBase.heightWeightValue');

  @override
  double get heightWeightValue {
    _$heightWeightValueAtom.reportRead();
    return super.heightWeightValue;
  }

  @override
  set heightWeightValue(double value) {
    _$heightWeightValueAtom.reportWrite(value, super.heightWeightValue, () {
      super.heightWeightValue = value;
    });
  }

  final _$animationBorderRadiusAtom =
      Atom(name: '_SplashViewModelBase.animationBorderRadius');

  @override
  double get animationBorderRadius {
    _$animationBorderRadiusAtom.reportRead();
    return super.animationBorderRadius;
  }

  @override
  set animationBorderRadius(double value) {
    _$animationBorderRadiusAtom.reportWrite(value, super.animationBorderRadius,
        () {
      super.animationBorderRadius = value;
    });
  }

  final _$_SplashViewModelBaseActionController =
      ActionController(name: '_SplashViewModelBase');

  @override
  void updateAnimation() {
    final _$actionInfo = _$_SplashViewModelBaseActionController.startAction(
        name: '_SplashViewModelBase.updateAnimation');
    try {
      return super.updateAnimation();
    } finally {
      _$_SplashViewModelBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  String toString() {
    return '''
connectivityResult: ${connectivityResult},
heightWeightValue: ${heightWeightValue},
animationBorderRadius: ${animationBorderRadius}
    ''';
  }
}
