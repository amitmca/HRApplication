'use strict';

/**
 * @ngdoc overview
 * @name hrviewApp
 * @description
 * # hrviewApp
 *
 * Main module of the application.
 */
angular
  .module('hrviewApp', [
    'ngAnimate',
    'ngAria',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
    ])
  .config(function ($routeProvider,$httpProvider) {//$routeProvider
    $httpProvider.defaults.headers.post = {"Content-Type": "application/json"};//
    $routeProvider//$routeProvider
      .when('/', {
        templateUrl: 'views/employees.html',
        controller: 'EmployeesCtrl',
        controllerAs: 'employees'
      })
    .when('/createEmployee', {
        templateUrl: 'views/createEmployee.html',
        controller: 'EmployeesCtrl',
        controllerAs: 'employees'
    })
      .when('/timecards/:id', {
        templateUrl: 'views/timecards.html',
        controller: 'TimeCardsCtrl',
        controllerAs: 'timecards'
      })
      .when('/timecards/create', {
        templateUrl: 'views/createTimecard.html',
        controller: 'TimeCardsCtrl',
        controllerAs: 'timecards'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
