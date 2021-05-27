import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/view/base_view.dart';
import 'package:hobby_doge/core/components/app_bar_with_back_leading.dart';
import 'package:hobby_doge/core/init/lang/locale_keys.g.dart';
import 'package:hobby_doge/core/init/navigation/navigation_service.dart';
import 'package:hobby_doge/core/init/network/network_service.dart';
import 'package:hobby_doge/core/init/theme/color_scheme.dart';
import 'package:hobby_doge/view/club_screen/model/OneClub.dart';
import 'package:hobby_doge/view/club_screen/viewmodel/club_view_model.dart';
import '../../../core/extensions/context_extension.dart';
import '../../../core/extensions/string_extension.dart';

class ClubView extends StatefulWidget {
  final int clubID;

  ClubView({Key key, this.clubID}) : super(key: key);
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
          model.setID(widget.clubID);
          model.init();
        },
        onPageBuilder: (BuildContext context, ClubViewModel viewmodel) =>
            FutureBuilder<OneClub>(
                future: viewmodel.club,
                builder:
                    (BuildContext context, AsyncSnapshot<OneClub> snapshot) {
                  print(snapshot.error);
                  if (!snapshot.hasData) {
                    return Center(
                      child: CircularProgressIndicator(),
                    );
                  } else {
                    switch (snapshot.connectionState) {
                      case ConnectionState.waiting:
                        return Center(
                          child: CircularProgressIndicator(),
                        );

                      default:
                        return DefaultTabController(
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
                                clubNameTitle(context, snapshot.data),
                                tabBarFields(snapshot.data),

                                // create widgets for each tab bar here
                                Expanded(
                                  child: TabBarView(
                                    children: [
                                      // first tab bar view widget
                                      subClubList(snapshot.data),

                                      // second tab bar viiew widget
                                      aboutField(context, snapshot.data),
                                    ],
                                  ),
                                ),
                              ],
                            ),
                          ),
                        );
                    }
                  }
                }));
  }

  Column aboutField(BuildContext context, OneClub club) {
    return Column(
      children: [
        Container(
          alignment: Alignment.center,
          height: context.height * 0.15,
          child: Text(
            club.description,
            style: TextStyle(color: Color(0xff667E85)),
          ),
        ),
        Container(
            height: context.height * 0.1,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                Text(club.reviews.length.toString() + " Reviews",
                    style: TextStyle(
                        fontWeight: FontWeight.w500,
                        fontSize: context.height * 0.02)),
                TextButton.icon(
                    icon: Icon(
                      Icons.add_circle_outline,
                      color: AppColorScheme.instance.greenLight3,
                    ),
                    onPressed: () {},
                    label: Text(
                      "Post a Review ",
                      style: TextStyle(
                          color: AppColorScheme.instance.greenLight3,
                          fontSize: context.height * 0.02,
                          fontWeight: FontWeight.w500),
                    ))
              ],
            )),
        Divider(
          color: Colors.black,
        ),
        SingleChildScrollView(
          child: Container(
            height: context.height * 0.25,
            child: MediaQuery.removePadding(
              context: context,
              removeTop: true,
              child: ListView.builder(
                  itemCount: club.reviews.length,
                  itemBuilder: (context, index) {
                    if (club.reviews.length > 0) {
                      return ListTile(
                        contentPadding: EdgeInsets.only(
                            left: context.height * 0.05,
                            right: context.height * 0.05),
                        title: Text(
                          club.reviews[index].username,
                          style: TextStyle(
                              fontWeight: FontWeight.w500,
                              fontSize: context.height * 0.025),
                        ),
                        subtitle: Text(club.reviews[index].comment),
                        trailing: Text(
                            club.reviews[index].rating.toInt().toString() +
                                "/5"),
                      );
                    } else {
                      return Text("ads");
                    }
                  }),
            ),
          ),
        ),
      ],
    );
  }

  Column subClubList(OneClub club) {
    return Column(
      children: [
        Container(
          child: SingleChildScrollView(
            child: Container(
              height: context.height * 0.5,
              child: ListView.builder(
                  itemCount: club.subClubs.length,
                  itemBuilder: (context, index) {
                    if (club.subClubs.length > 0) {
                      print(club.subClubs[index]);
                      return ListTile(
                        onTap: () {
                          NavigationService.instance.navigateToPage(
                              path: "/sub_club_view",
                              object: club.subClubs[index].id);
                        },
                        title: Text(club.subClubs[index].name),
                        leading: CircleAvatar(
                          backgroundImage: NetworkImage(
                            club.subClubs[index].picture,
                          ),
                        ),
                        trailing: TextButton.icon(
                            icon: Icon(
                              Icons.add_circle_outline,
                              color: AppColorScheme.instance.greenLight3,
                            ),
                            onPressed: () {
                              NetworkService.instance.joinSubClub(club.id);
                            },
                            label: Text(
                              "Join ",
                              style: TextStyle(
                                  color: AppColorScheme.instance.greenLight3,
                                  fontSize: context.height * 0.02,
                                  fontWeight: FontWeight.w500),
                            )),
                      );
                    } else {
                      return Text("ads");
                    }
                  }),
            ),
          ),
        ),
      ],
    );
  }

  Container tabBarFields(OneClub club) {
    return Container(
      height: 50,
      child: TabBar(
        indicatorColor: Color(0xff6AC88F),
        tabs: [
          Text(
            club.subClubs.length.toString() + " Sub Clubs",
            style: TextStyle(color: Color(0xff6AC88F)),
          ),
          Text("About", style: TextStyle(color: Color(0xff6AC88F))),
        ],
      ),
    );
  }

  Container clubNameTitle(BuildContext context, OneClub club) {
    return Container(
        height: 70,
        child: Row(
          children: [
            Spacer(
              flex: 1,
            ),
            Expanded(
                child: Text(
                  club.name,
                  style: TextStyle(
                      color: Color(0xff26342B),
                      fontSize: context.height * 0.04),
                ),
                flex: 20)
          ],
        ));
  }
}
