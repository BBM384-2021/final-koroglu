// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'login_view_model.dart';

// **************************************************************************
// StoreGenerator
// **************************************************************************

// ignore_for_file: non_constant_identifier_names, unnecessary_brace_in_string_interps, unnecessary_lambdas, prefer_expression_function_bodies, lines_longer_than_80_chars, avoid_as, avoid_annotating_with_dynamic

mixin _$LoginViewModel on _LoginViewModelBase, Store {
  final _$isHiddenAtom = Atom(name: '_LoginViewModelBase.isHidden');

  @override
  bool get isHidden {
    _$isHiddenAtom.reportRead();
    return super.isHidden;
  }

  @override
  set isHidden(bool value) {
    _$isHiddenAtom.reportWrite(value, super.isHidden, () {
      super.isHidden = value;
    });
  }

  final _$_LoginViewModelBaseActionController =
      ActionController(name: '_LoginViewModelBase');

  @override
  void isHiddenChange() {
    final _$actionInfo = _$_LoginViewModelBaseActionController.startAction(
        name: '_LoginViewModelBase.isHiddenChange');
    try {
      return super.isHiddenChange();
    } finally {
      _$_LoginViewModelBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  String toString() {
    return '''
isHidden: ${isHidden}
    ''';
  }
}
