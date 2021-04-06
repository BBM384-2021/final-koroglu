import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/state/base_state.dart';
import 'package:hobby_doge/core/base/view/base_view.dart';
import 'package:hobby_doge/view/test/viewmodel/test_viewmodel.dart';

class TestEkrani extends StatefulWidget {
  TestEkrani({Key key}) : super(key: key);

  @override
  _TestEkraniState createState() => _TestEkraniState();
}

class _TestEkraniState extends BaseState<TestEkrani> {
  @override
  Widget build(BuildContext context) {
    return BaseView(
      viewModel: TestViewModel(),
      onModelReady: (model) {
        model.setContext(context);
        model.init();
      },
      onPageBuilder: (context, value) => Scaffold(
        body: Container(
          child: Center(
            child: Text(
              "Denemere yazıları deneme bir iki ",
              style: TextStyle(fontSize: 50),
            ),
          ),
        ),
      ),
    );
  }
}
