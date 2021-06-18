import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:getx_app/common-widget/freelancer_card.dart';
import 'package:getx_app/common-widget/ring.dart';
import 'package:getx_app/common-widget/service_card.dart';
import 'package:getx_app/themes/light_theme.dart';
import 'package:getx_app/utilities/constants.dart';
import 'home_controller.dart';

class HomePage extends GetView<HomeController> {
  TextEditingController _localisation =TextEditingController(text: "");
  @override
  Widget build(BuildContext context) {
    return Scaffold(
/*      appBar: AppBar(
        title: Text(
          controller.title,
          style: TextStyle(color: Colors.black),
        ),
        elevation: 0.0,
        bottomOpacity: 0.0,
        backgroundColor: Colors.transparent,
      ),*/
      body: Stack(
        children: <Widget>[
          CustomBackground(),
          Scaffold(
            backgroundColor: Color(0xFFFFFF),
            appBar:
            AppBar(
              backgroundColor: Colors.transparent,
              elevation: 0.0,
              actions: <Widget>[
                Icon(
                  FontAwesomeIcons.bell,
                  color: Colors.black,
                ),
                SizedBox(
                  width: 10,
                )
              ],
              title: Row(
                children: <Widget>[
                  /*Icon(
                    Icons.location_on,
                    color: Colors.black,
                  ),
                  *//*Text(
                    '16 Ème Étage - Plateau',
                    style: TextStyle(color: Colors.black),
                  ),*/

                ],
              ),
            ),
            body:
            SafeArea(
              child: ListView(
                padding: EdgeInsets.symmetric(
                  vertical: 10,
                  horizontal: 10,
                ),
                children: <Widget>[
                  Material(
                    borderRadius: BorderRadius.circular(15.0),
                    elevation: 5,
                    child: TextField(
                      decoration: InputDecoration(
                          filled: true,
                          fillColor: Colors.white,
                          focusedBorder: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(15.0),
                            borderSide: BorderSide(
                              color: Colors.blue,
                            ),
                          ),
                          enabledBorder: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(15.0),
                            borderSide: BorderSide(
                              color: Colors.transparent,
                            ),
                          ),
                          prefixIcon: Icon(FontAwesomeIcons.search),
                          hintText: 'Search'),
                    ),
                  ),
                  SizedBox(
                    height: 10,
                  ),
                  Row(
                    children: [
                      Expanded(
                        child: TextField(
                          controller: _localisation,
                          cursorColor: black,
                          style: TextStyle(
                              fontSize: 17, fontWeight: FontWeight.normal, color: Colors.black),
                          decoration: InputDecoration(
                              prefixIcon: Icon(FontAwesomeIcons.mapPin),
                              suffixIcon: Icon(FontAwesomeIcons.map),
                              hintText: "Cocody 7e Tranche, Centre Kodesh",border: InputBorder.none),
                        ),
                      ),
                    ],
                  ),
                  SizedBox(
                    height: 20,
                  ),
                  Container(
                    padding: EdgeInsets.all(10),
                    height: 170,
                    decoration: BoxDecoration(
                      image: DecorationImage(
                        image: AssetImage(
                          'assets/cleaning.jpg',
                        ),
                        fit: BoxFit.fitWidth,
                      ),
                      color: Colors.white,
                      borderRadius: BorderRadius.all(
                        Radius.circular(20),
                      ),
                    ),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: <Widget>[
                        Text(
                          'Nous nettoyons comme',
                          style: TextStyle(
                            color: kPrimaryBlue,
                            fontSize: 15,
                            fontWeight: FontWeight.w700,
                          ),
                        ),
                        Row(
                          crossAxisAlignment: CrossAxisAlignment.end,
                          children: <Widget>[
                            Text(
                              'Jamais',
                              style: TextStyle(
                                color: kPrimaryBlue,
                                fontSize: 15,
                                fontWeight: FontWeight.w700,
                              ),
                            ),
                            SizedBox(
                              width: 5,
                            ),
                            Text(
                              'n\'a été sale',
                              style: TextStyle(
                                color: Color(0xffe2345d),
                                fontSize: 20,
                                fontWeight: FontWeight.w900,
                              ),
                            ),
                          ],
                        ),
                        SizedBox(
                          height: 10,
                        ),
                        RaisedButton(
                          padding: EdgeInsets.symmetric(horizontal: 30),
                          color: Color(0xff040f48),
                          shape: kShape,
                          onPressed: () {},
                          child: const Text(
                            'Reservez',
                            style: TextStyle(
                              fontSize: 15,
                              color: Colors.white,
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                  SizedBox(
                    height: 10,
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: <Widget>[
                      Text(
                        'Service à domicile',
                        style: kCategoryTextStyle,
                      ),
                      Text(
                        'Voir plus',
                        style: kCategoryTextStyle,
                      ),
                    ],
                  ),
                  SizedBox(
                    height: 10,
                  ),
                  SingleChildScrollView(
                    scrollDirection: Axis.horizontal,
                    child: Row(
                      children: <Widget>[
                        ServiceCard(
                            icon: FontAwesomeIcons.toilet, label: 'Plomberie'),
                        ServiceCard(
                            icon: FontAwesomeIcons.plug, label: 'Electricité'),
                        ServiceCard(
                            icon: FontAwesomeIcons.broom, label: 'Ménage'),
                        ServiceCard(
                            icon: FontAwesomeIcons.handsWash, label: 'Blanchisserie'),
                        ServiceCard(
                            icon: FontAwesomeIcons.paintRoller, label: 'Peinture'),
                        ServiceCard(
                            icon: FontAwesomeIcons.houseUser, label: 'Ménage'),
                      ],
                    ),
                  ),
                  SizedBox(
                    height: 10,
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: <Widget>[
                      Text(
                        'Services les plus demandés',
                        style: kCategoryTextStyle,
                      ),
                      Text(
                        'Voir plus',
                        style: kCategoryTextStyle,
                      ),
                    ],
                  ),
                  SizedBox(
                    height: 10,
                  ),
                  FreelancerCard(
                    color: Colors.amber[300],
                    name: 'Pose de papier',
                    image: AssetImage(
                      'assets/pose.jpg',
                    ),
                    description: 'Investment Consultant',
                    rating: 5.0,
                    comments: 15,
                  ),
                  FreelancerCard(
                    color: Color(0xffb8c1ff),
                    name: 'Réparation de lampe',
                    image: AssetImage(
                      'assets/lampe.jpg',
                    ),
                    description: 'Consulting Detective',
                    rating: 5.0,
                    comments: 15,
                  ),
                  FreelancerCard(
                    color: Color(0xffffb8fa),
                    name: 'Reparation de fuite',
                    image: AssetImage(
                      'assets/fuite.jpg',
                    ),
                    description: 'Model',
                    rating: 5.0,
                    comments: 15,
                  ),
                  FreelancerCard(
                    color: Colors.blueGrey[100],
                    name: 'Pose de baignoire',
                    image: AssetImage(
                      'assets/baignoire.jpg',
                    ),
                    description: 'Joker',
                    rating: 5.0,
                    comments: 15,
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}

class CustomBackground extends StatelessWidget {
  const CustomBackground({
    Key key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.white,
      width: double.infinity,
      height: double.infinity,
      child: Stack(
        children: <Widget>[
          Ring(
            backgroundColor: Colors.white,
            ringColor: Color(0xfffef4f7),
            radius: 200,
            top: 140,
            left: 150,
            ringThickness: 100,
          ),
          Ring(
            backgroundColor: Colors.white,
            ringColor: Color(0xfff4f3ff),
            radius: 200,
            top: -120,
            left: -150,
            ringThickness: 100,
          ),

          // myScaffold(_selectedIndex, _onItemTapped),
        ],
      ),
    );
  }
}