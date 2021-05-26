import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/view/base_view.dart';
import 'package:hobby_doge/core/components/app_bar_with_back_leading.dart';
import 'package:hobby_doge/core/init/lang/locale_keys.g.dart';
import 'package:hobby_doge/view/club_screen/viewmodel/club_view_model.dart';
import '../../../core/extensions/context_extension.dart';
import '../../../core/extensions/string_extension.dart';

class ClubView extends StatefulWidget {
  ClubView({Key key}) : super(key: key);

  @override
  _ClubViewState createState() => _ClubViewState();
}

class _ClubViewState extends State<ClubView>
    with SingleTickerProviderStateMixin {
  TabController _controller;
  @override
  void initState() {
    super.initState();
    _controller = new TabController(length: 2, vsync: this);
  }

  @override
  Widget build(BuildContext context) {
    return BaseView(
        viewModel: ClubViewModel(),
        onModelReady: (ClubViewModel model) {
          model.setContext(context);
          model.init();
        },
        onPageBuilder: (BuildContext context, ClubViewModel viewmodel) =>
            DefaultTabController(
              length: 2,
              child: Scaffold(
                extendBodyBehindAppBar: true,
                appBar: appBarWithBackLeading(context, ""),
                body: Column(
                  children: <Widget>[
                    // construct the profile details widget here
                    Container(
                      width: context.width,
                      height: context.height * 0.3,
                      child: Image.network(
                          'https://firebasestorage.googleapis.com/v0/b/hobbydoge.appspot.com/o/Uploads%2F1621670766657_800.jpg?alt=media&token=c7b0e8a5-5c7b-49cb-a37f-fb9d2f6e47c1',
                          fit: BoxFit.fitWidth),
                    ),

                    // the tab bar with two items
                    Container(
                        height: 70,
                        child: Row(
                          children: [
                            Spacer(
                              flex: 1,
                            ),
                            Expanded(
                                child: Text(
                                  "Club Name",
                                  style: TextStyle(
                                      color: Color(0xff26342B),
                                      fontSize: context.height * 0.04),
                                ),
                                flex: 20)
                          ],
                        )),
                    Container(
                      height: 50,
                      child: TabBar(
                        indicatorColor: Color(0xff6AC88F),
                        tabs: [
                          Text(
                            "Sub Clubs",
                            style: TextStyle(color: Color(0xff6AC88F)),
                          ),
                          Text("About",
                              style: TextStyle(color: Color(0xff6AC88F))),
                        ],
                      ),
                    ),
                    Container(
                      alignment: Alignment.center,
                      height: context.height * 0.15,
                      child: Text(
                        "Introduction of club",
                        style: TextStyle(color: Color(0xff667E85)),
                      ),
                    ),

                    // create widgets for each tab bar here
                    Expanded(
                      child: TabBarView(
                        children: [
                          // first tab bar view widget
                          SingleChildScrollView(
                            child: Center(
                              child: Text(
                                'Bike',
                              ),
                            ),
                          ),

                          // second tab bar viiew widget
                          SingleChildScrollView(
                            child: Center(
                              child: Text(
                                'Car',
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
            ));
  }
}
