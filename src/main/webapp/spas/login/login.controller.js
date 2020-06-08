(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', ['$location', 'AuthenticationService', loginController]);

    function loginController($location, AuthenticationService) {
        var vm = {};

        vm.login = login;

        initController();

        return vm;

        function initController() {
            // reset login status
            AuthenticationService.Logout();
        }

        function login() {
            console.log('Vou chamar o service de login');
            console.log(AuthenticationService);
            vm.loading = true;
            AuthenticationService.Login(vm.username, vm.password, function (result, response) {
                if (result === true) {
                    $location.path('/');
                } else {
                    vm.error = 'Username or password is incorrect';
                    vm.loading = false;
                }
            });
        }
    }

})();