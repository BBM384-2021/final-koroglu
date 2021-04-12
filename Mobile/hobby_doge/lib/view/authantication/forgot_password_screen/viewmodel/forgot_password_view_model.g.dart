// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'forgot_password_view_model.dart';

// **************************************************************************
// StoreGenerator
// **************************************************************************

// ignore_for_file: non_constant_identifier_names, unnecessary_brace_in_string_interps, unnecessary_lambdas, prefer_expression_function_bodies, lines_longer_than_80_chars, avoid_as, avoid_annotating_with_dynamic

mixin _$ForgotPasswordViewModel on _ForgotPasswordViewModelBase, Store {
  final _$isHiddenAtom = Atom(name: '_ForgotPasswordViewModelBase.isHidden');

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

  final _$_ForgotPasswordViewModelBaseActionController =
      ActionController(name: '_ForgotPasswordViewModelBase');

  @override
  void isHiddenChange() {
    final _$actionInfo = _$_ForgotPasswordViewModelBaseActionController
        .startAction(name: '_ForgotPasswordViewModelBase.isHiddenChange');
    try {
      return super.isHiddenChange();
    } finally {
      _$_ForgotPasswordViewModelBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  String toString() {
    return '''
isHidden: ${isHidden}
    ''';
  }
}
