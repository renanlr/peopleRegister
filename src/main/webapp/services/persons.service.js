(function () {
    'use strict';

    angular
        .module('app')
        .factory('PersonsService', ['$http', '$localStorage', personsService]);

    function personsService($http, $localStorage) {
        var service = {};

        service.list = list;
        service.show = show;
        service.insert = insert;
        service.remove = remove;
        service.edit = edit;
        service.removeTelephone = removeTelephone;

        injectToken();

        return service;

        function list(callback) {
            $http.get('/api/person').then(function(response){
                callback(true, response.data);
            }).catch(function (response) {
                callback(false, response.data);
            });
        }

        function show(id, callback) {
            $http.get('/api/person/'+id).then(function(response){
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

        function edit(person, callback) {
            $http.put('/api/person/'+person.id, person).then(function(response){
                callback(true, response.data);
            }).catch(function (response) {
                callback(false, response.data);
            });
        }

        function removeTelephone(id, callback) {
            $http.delete('/api/person/telephone/'+id).then(function(response){
                callback(true, response.data);
            }).catch(function (response) {
                callback(false, response.data);
            });
        }

        function injectToken(){
            var currentUser = $localStorage.currentUser;
            if(currentUser){
                $http.defaults.headers.common.Authorization = currentUser.token;
            }
        }
    }
})();