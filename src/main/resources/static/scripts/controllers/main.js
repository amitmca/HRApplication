'use strict';

/**
 * @ngdoc function
 * @name hrviewApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the hrviewApp
 */
angular.module('hrviewApp')
  .controller('MainCtrl', function ($rootScope, $http, $location) {

    // $scope.authenticated = true;


    var self = this;

    var authenticate = function(credentials, callback) {

      var headers = credentials ? {authorization : "Basic "
      + btoa(credentials.username + ":" + credentials.password)
      } : {};

      $http.get('http://localhost:8080/user', {headers : headers}).then(function(response) {//gets user info from backend
        console.log(headers);
        console.log(response);
        $rootScope.authenticated = response.data.authenticated;
        callback && callback();
      }, function() {
        $rootScope.authenticated = false;
        callback && callback();
      });

    };

    authenticate();//checks for authentication when page loads

    self.credentials = {};

    self.login = function() {
      authenticate(self.credentials, function() {
        if ($rootScope.authenticated) {
          $location.path("/");
          self.error = false;
        } else {
          $location.path("#/login");
          self.error = true;
        }
      });
    };

    self.logout = function() {
      $http.post('http://localhost:8080/logout', {}).finally(function() {
        $rootScope.authenticated = false;
        // $location.path("/");
      });
    };
  });
