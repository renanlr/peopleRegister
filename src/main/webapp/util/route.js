angular.module('app', ['ngRoute', 'ngMessages', 'ngStorage', 'app-navbar'])
    .config(function($routeProvider) {

        $routeProvider
            .when('/', {
                templateUrl : '/spas/login/index.view.html',
                controller: 'LoginController',
                controllerAs: 'vm'
            })
            .when('/operators', {
                templateUrl : '/spas/operators/index.view.html',
                controller: 'OperatorsController',
                controllerAs: 'vm'
            })
            .when('/persons', {
                templateUrl : '/spas/persons/index.view.html',
                controller: 'PersonsController',
                controllerAs: 'vm'
            })
            .when('/persons/:personId', {
                templateUrl : '/spas/persons/edit.view.html',
                controller: 'PersonsController',
                controllerAs: 'vm'
            })
            .otherwise({
                redirectTo: '/'
            });
});