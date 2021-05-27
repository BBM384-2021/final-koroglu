import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:hobby_doge/core/base/view/base_view.dart';
import 'package:hobby_doge/core/components/app_bar_with_back_leading.dart';
import 'package:hobby_doge/core/components/large_outlined_button.dart';
import 'package:hobby_doge/core/init/lang/locale_keys.g.dart';
import 'package:hobby_doge/core/init/network/network_service.dart';
import 'package:hobby_doge/core/init/theme/color_scheme.dart';
import 'package:hobby_doge/view/sub_club_screen/model/SubClub.dart';
import 'package:hobby_doge/view/club_screen/viewmodel/club_view_model.dart';
import 'package:hobby_doge/view/sub_club_screen/viewmodel/sub_club_view_model.dart';
import '../../../core/extensions/context_extension.dart';
import '../../../core/extensions/string_extension.dart';

class SubClubView extends StatefulWidget {
  final int clubID;

  SubClubView({Key key, this.clubID}) : super(key: key);
  @override
  _SubClubViewState createState() => _SubClubViewState();
}

class _SubClubViewState extends State<SubClubView>
    with SingleTickerProviderStateMixin {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return BaseView(
        viewModel: SubClubViewModel(),
        onModelReady: (SubClubViewModel model) {
          model.setContext(context);
          model.setID(1);
          model.init();
        },
        onPageBuilder: (BuildContext context, SubClubViewModel viewmodel) =>
            FutureBuilder<SubClub>(
                future: viewmodel.club,
                builder:
                    (BuildContext context, AsyncSnapshot<SubClub> snapshot) {
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
                        return Scaffold(
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

                              // create widgets for each tab bar here
                              Expanded(
                                child: aboutField(context, snapshot.data),
                              ),
                            ],
                          ),
                        );
                    }
                  }
                }));
  }

  Column aboutField(BuildContext context, SubClub club) {
    return Column(
      children: [
        Row(
          children: [
            Spacer(flex: 1),
            Expanded(
              flex: 2,
              child: Text(
                "About",
                style: TextStyle(fontWeight: FontWeight.w500),
              ),
            ),
            Spacer(flex: 15)
          ],
        ),
        Row(
          children: [
            Spacer(flex: 1),
            Expanded(
              flex: 17,
              child: Container(
                alignment: Alignment.center,
                height: context.height * 0.10,
                child: Text(
                  club.description,
                  style: TextStyle(color: Color(0xff667E85)),
                ),
              ),
            ),
          ],
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
        SingleChildScrollView(
          child: Container(
            height: context.height * 0.30,
            child: ListView.builder(
                itemCount: club.reviews.length,
                itemBuilder: (context, index) {
                  if (club.reviews.length > 0) {
                    return ListTile(
                      contentPadding: EdgeInsets.all(context.height * 0.05),
                      title: Text(
                        club.reviews[index].username,
                        style: TextStyle(
                            fontWeight: FontWeight.w500,
                            fontSize: context.height * 0.025),
                      ),
                      subtitle: Text(club.reviews[index].comment),
                      trailing: Text(
                          club.reviews[index].rating.toInt().toString() + "/5"),
                    );
                  } else {
                    return Text("ads");
                  }
                }),
          ),
        ),
      ],
    );
  }

  Padding clubNameTitle(BuildContext context, SubClub club) {
    return Padding(
      padding: const EdgeInsets.only(top: 15.0),
      child: Container(
          height: 100,
          child: Column(
            children: [
              Row(
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
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: [
                  Text(club.members.length.toString() + " members"),
                  OutlinedButton(
                    style: ButtonStyle(
                      backgroundColor: MaterialStateProperty.all<Color>(
                          AppColorScheme.instance.greenLight3),
                    ),
                    child: Text("Join", style: TextStyle(color: Colors.white)),
                    onPressed: () {
                      NetworkService.instance.joinSubClub(club.id);
                    },
                  )
                ],
              )
            ],
          )),
    );
  }
}
