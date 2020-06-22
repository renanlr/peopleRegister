(function () {
    'use strict';

    angular
        .module('app-navbar', [])
        .directive('navbar', ['$location', '$localStorage', navbarDirective]);

    function navbarDirective($location, $localStorage) {
        return {
            restrict: 'E',
            scope: {},
            templateUrl: '/directives/navbar/navbar-tpl.html',
            link: function(scope, element){

                loadMenuPermissions();

                function loadMenuPermissions(){
                    console.log("Recarregando Menu");
                    if ($localStorage.currentUser.profile === 'ADMINISTRADOR'){
                        scope.showLogin = false;
                        scope.showLogout = true;
                        scope.showOperators = true;
                        scope.showPersons = false;
                    } else if($localStorage.currentUser.profile === 'GERENTE' ||
                        $localStorage.currentUser.profile === 'ANALISTA'){
                        scope.showLogin = false;
                        scope.showLogout = true;
                        scope.showOperators = false;
                        scope.showPersons = true;
                    } else {
                        scope.showLogin = true;
                        scope.showLogout = false;
                        scope.showOperators = false;
                        scope.showPersons = false;
                    }
                }
            }
        };
    }

})();
