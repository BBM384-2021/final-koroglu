import 'dart:async';

import 'package:connectivity/connectivity.dart';
import 'package:flutter/material.dart';
import 'package:mobx/mobx.dart';

import '../../components/connectivity_snack_bar.dart';

class BaseView<T extends Store> extends StatefulWidget {
  final Widget Function(BuildContext context, T value) onPageBuilder;
  final T viewModel;
  final Function(T model) onModelReady;
  final VoidCallback onDispose;

  BaseView(
      {Key key,
      @required this.viewModel,
      @required this.onModelReady,
      this.onPageBuilder,
      this.onDispose})
      : super(key: key);

  @override
  _BaseViewState<T> createState() => _BaseViewState<T>();
}

class _BaseViewState<T extends Store> extends State<BaseView<T>> {
  T model;
  StreamSubscription _listener;

  @override
  void initState() {
    model = widget.viewModel;
    widget.onModelReady(model);
    var connectivityResult;
    if (connectivityResult == null) {
      Connectivity().checkConnectivity().then((value) {
        if (value == ConnectivityResult.none) {
          ScaffoldMessenger.of(context)
              .showSnackBar(connectivitySnackBar(context));
        }
      });
    }
    _listener = Connectivity().onConnectivityChanged.listen((event) {
      print(event);
      if (event == ConnectivityResult.none) {
        ScaffoldMessenger.of(context)
            .showSnackBar(connectivitySnackBar(context));
      } else {
        ScaffoldMessenger.of(context).removeCurrentSnackBar();
      }
    });
    super.initState();
  }

  @override
  void dispose() {
    _listener.cancel();
    super.dispose();
    if (widget.onDispose != null) widget.onDispose();
  }

  @override
  Widget build(BuildContext context) {
    return widget.onPageBuilder(context, model);
  }
}
