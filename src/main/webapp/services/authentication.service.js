(function () {
    'use strict';

    angular
        .module('app')
        .factory('AuthenticationService', ['$http', '$localStorage', authenticationService]);

    function authenticationService($http, $localStorage) {
        var service = {};

        service.Login = Login;
        service.Logout = Logout;

        return service;

        function Login(username, password, callback) {
            $http.post('/api/login', { username: username, password: password })
                .then(function (response) {
                    // login successful if there's a token in the response
                    if (response.data.token) {
                        // store username and token in local storage to keep user logged in between page refreshes
                        $localStorage.currentUser = { username: username,
                                                      token: response.data.token,
                                                      profile:response.data.profile };

                        // add jwt token to auth header for all requests made by the $http service
                        $http.defaults.headers.common.Authorization = response.data.token;

                        // execute callback with true to indicate successful login
                        callback(true, response);
                    } else {
                        // execute callback with false to indicate failed login
                        response.data.mensagens = ['Erro inesperado: Token não retornado.'];
                        callback(false, response);
                    }
                }).catch(function (response) {
                    callback(false, response);
                });
        }

        function Logout() {
            // remove user from local storage and clear http auth header
            delete $localStorage.currentUser;
            $http.defaults.headers.common.Authorization = '';
        }
    }
})();