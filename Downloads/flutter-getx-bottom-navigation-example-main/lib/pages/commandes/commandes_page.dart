import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:getx_app/common-widget/commande_card.dart';
import 'package:getx_app/utilities/constants.dart';

import 'commandes_controller.dart';


class CommandesPage extends GetView<CommandesController> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor:  Color(0xffb8c1ff),
      body:SafeArea(
        child:  ListView(
          children: <Widget>[
            Padding(
              padding: EdgeInsets.only(top: 15.0, left: 10.0),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: <Widget>[
                  IconButton(
                    icon: Icon(Icons.filter_list),
                    color: Colors.black,
                    onPressed: () {},
                  ),
                ],
              ),
            ),
            SizedBox(height: 25.0),
            Padding(
              padding: EdgeInsets.only(left: 40.0),
              child: Row(
                children: <Widget>[
                  Text('Mes commandes',
                      style: TextStyle(
                          fontFamily: 'Montserrat',
                          color: Colors.black,
                          fontWeight: FontWeight.bold,
                          fontSize: 25.0)),
                  SizedBox(width: 10.0),
                ],
              ),
            ),
            SizedBox(height: 40.0),
            Container(
              height: MediaQuery.of(context).size.height - 185.0,
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.only(topLeft: Radius.circular(75.0)),
              ),
              child: ListView(
                children: [
                  Padding(
                    padding: EdgeInsets.only(top:50.0),
                      child: Container(
                          height: MediaQuery.of(context).size.height - 300.0,
                          child: ListView(children: [
                            CommandeCard(
                              color: Colors.white,
                              name: 'Reparation de fuite',
                              image: AssetImage(
                                'assets/fuite.jpg',
                              ),
                              description: 'Model',
                              rating: 5.0,
                              comments: 15,
                            ),
                            CommandeCard(
                              color: Colors.white,
                              name: 'Reparation de fuite',
                              image: AssetImage(
                                'assets/fuite.jpg',
                              ),
                              description: 'Model',
                              rating: 5.0,
                              comments: 15,
                            ),
                            CommandeCard(
                              color: Colors.white,
                              name: 'Reparation de fuite',
                              image: AssetImage(
                                'assets/fuite.jpg',
                              ),
                              description: 'Model',
                              rating: 5.0,
                              comments: 15,
                            )
                          ]))
                  )
                ],
              )
            ),
          ],
        ),
      )
    );
  }
}
