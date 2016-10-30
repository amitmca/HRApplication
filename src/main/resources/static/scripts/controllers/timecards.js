'use strict';

/**
 * @ngdoc function
 * @name hrviewApp.controller:AboutCtrl
 * @description
 * # TimeCardsCtrl
 * Controller of the hrviewApp
 */
angular.module('hrviewApp')
    .controller('TimeCardsCtrl', function ($scope, $http, $routeParams, $rootScope, $location) {
//        var disDate = new Date(Date.parse("Jul 21, 2000 12:44:14"));
//        console.log(disDate.toDateString());
//        console.log(disDate.getMinutes());
//        console.log(disDate.toLocaleTimeString());
//        $scope.isEmployee = ($routeParams.employeeID>0)?true:false;
//
      if(!$rootScope.authenticated)$location.path("/login");

      $scope.paid = true;

        $scope.showIt = function () {
            $scope.paid = false;
        };

        $http.get("https://desolate-thicket-71372.herokuapp.com/employees/" + $routeParams.id)
            .then(function (response) {
                $scope.employee = response.data;
                console.log($scope.employee);
            }, function (error) {
                console.log(error);
            });

        $scope.timecardList = {

        };

        $http.get("https://desolate-thicket-71372.herokuapp.com/timecards")
            .then(function (response) {
                $scope.date = response.data[0].end;
                console.log($scope.date);
                var check = new Date(Date.parse($scope.date));
                console.log(check.toLocaleTimeString());
            }, function (error) {
                console.log(error);
            });

        var iddd = $routeParams.id;

        $scope.newTimeCard = {
            "start" : "",
            "end" : "",
            "employeeID" : iddd,
            "overtime" : false
        };

        $scope.addTimeCard = function () {
//            $scope.newTimeCard.start = $scope.newTimeCard.start.toUTCString();
//            $scope.newTimeCard.end = $scope.newTimeCard.end.toUTCString();
            console.log($scope.newTimeCard);
            $http.post("https://desolate-thicket-71372.herokuapp.com/timecards", $scope.newTimeCard)
                .then(function (response) {
                    $scope.timecards = response.data;
                    console.log($scope.timecards);
                }, function (error) {
                    console.log(error);
                });
        };

//        var dateSwitch = function (oldDate) {
//            var timeHolder = parseInt(oldDate.substring(11,13),10);
//            var tHolder = timeSwitch(timeHolder);
//            var timeString = tHolder.toString();
//            var newTime =(tHolder<10)?"0"+timeString:timeString;
//            return oldDate.replace(("[T][0-9]{2}"),newTime);
//        };
//
//        var timeSwitch = function (oldTime) {
//            var newTime = (oldTime+19)%24;
//            return newTime;
//        };
//
        $http.get("https://desolate-thicket-71372.herokuapp.com/timecards/" + $routeParams.id)
                .then(function (response) {
                $scope.timecards = response.data;
                console.log($scope.timecards);
            }, function () {

            });

        $scope.calculatePay = function () {
            $http.post("https://desolate-thicket-71372.herokuapp.com/pay/" + $routeParams.id, $scope.timecardList)
                .then(function (response) {
                    $scope.pay = response.data;
                    console.log($scope.pay);
                }, function (error) {
                    console.log(error);
                });
            $scope.showIt();
        };

        $scope.deleteEmployee = function () {
            var firstConfirm = confirm("Are You sure?");
            if (firstConfirm === true) {
                prompt("Is it their day off?");
                $http.delete("https://desolate-thicket-71372.herokuapp.com/employees/" + $routeParams.id)
                    .then(function (response) {
                        console.log(response.data);
                    }, function (error) {
                        console.log(error);
                    });
            }
        };

//        $scope.timecards = function (id) {
//            $state.go('/timecards/'+id);
//        };
    });
