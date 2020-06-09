(function () {
    'use strict';

    angular
        .module('app')
        .factory('OperatorsService', ['$http', '$localStorage', operatorsService]);

    function operatorsService($http, $localStorage) {
        var service = {};

        service.list = list;
        service.insert = insert;

        injectToken();

        return service;



        function list(callback) {
            $http.get('/api/operator').then(function(response){
                callback(true, response.data);
            }).catch(function (response) {
                callback(false, response.data);
            });
        }

        function insert(operator, callback) {
            $http.post('/api/operator', operator).then(function(response){
                callback(true, response.data);
            }).catch(function (response) {
                callback(false, response.data);
            });
        }

        function injectToken(){
            var currentUser = $localStorage.currentUser;
            console.log("currentUser", currentUser);
            if(currentUser){
                $http.defaults.headers.common.Authorization = currentUser.token;
            }
        }
    }
})();