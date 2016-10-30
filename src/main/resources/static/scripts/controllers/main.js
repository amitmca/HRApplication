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
    // $rootScope.available = true;

    var self = this;

    var authenticate = function(credentials, callback) {

      var headers = credentials ? {authorization : "Basic "
      + btoa(credentials.username + ":" + credentials.password)
      } : {};

      $http.get('https://desolate-thicket-71372.herokuapp.com/user', {headers : headers}).then(function(response) {//gets user info from backend
        console.log(headers);
        console.log(response);
        $rootScope.authenticated = response.data.authenticated;
        callback && callback();
      }, function(error) {
        console.log(error);
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
      $http.post('https://desolate-thicket-71372.herokuapp.com/logout', {}).finally(function() {
        $rootScope.authenticated = false;
        $location.path("#/login");
      });
    };
  });
