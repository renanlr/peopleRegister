(function () {
    'use strict';

    angular
        .module('app')
        .factory('PersonsService', ['$http', '$localStorage', personsService]);

    function personsService($http, $localStorage) {
        var service = {};

        service.list = list;
        service.insert = insert;
        service.remove = remove;
        service.edit = edit;

        injectToken();

        return service;

        function list(callback) {
            $http.get('/api/person').then(function(response){
                callback(true, response.data);
            }).catch(function (response) {
                callback(false, response.data);
            });
        }

        function insert(operator, callback) {
            $http.post('/api/person', operator).then(function(response){
                callback(true, response.data);
            }).catch(function (response) {
                callback(false, response.data);
            });
        }

        function remove(id, callback) {
            $http.delete('/api/person/'+id).then(function(response){
                callback(true, response.data);
            }).catch(function (response) {
                callback(false, response.data);
            });
        }

        function edit(operator, callback) {
            $http.put('/api/person/'+operator.id, operator).then(function(response){
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