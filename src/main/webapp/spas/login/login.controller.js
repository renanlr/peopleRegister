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
            vm.loading = true;
            vm.errors = [];
            AuthenticationService.Login(vm.username, vm.password, function (result, response) {
                if (result) {
                    if(response.data.profile === 'ADMINISTRADOR'){
                        $location.path('/operators');
                    }
                } else {
                    vm.errors = response.data.mensagens;
                    vm.loading = false;
                }
            });
        }
    }

})();