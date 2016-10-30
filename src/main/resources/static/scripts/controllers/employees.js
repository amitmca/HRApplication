'use strict';

/**
 * @ngdoc function
 * @name hrviewApp.controller:AboutCtrl
 * @description
 * # EmployeesCtrl
 * Controller of the hrviewApp
 */
angular.module('hrviewApp')
    .controller('EmployeesCtrl', function ($scope, $http) {
        $http.get("http://localhost:8080/employees")
            .then(function (response) {
                $scope.employees = response.data;
            }, function (error) {
                console.log(error);
            });
    
        var headers = {
            "Content-Type": "application/json"
        };
    
        $scope.employee = {
            "firstName" : "",
            "lastName" : "",
            "hourlyPayRate" : 0
        };
    
        $scope.create = function (employee) {
//            console.log(employee);
            var urll = "http://localhost:8080/employees";
            $http.post(urll, employee, headers)
                .then(function (response) {
                    console.log(response.data);
                }, function (error) {
                    console.log(error);
                });
            $scope.employee = {
                "firstName" : "",
                "lastName" : ""
            };
        };
    });
