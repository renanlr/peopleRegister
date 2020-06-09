angular.module('app', ['ngRoute', 'ngMessages', 'ngStorage'])
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

            .when('/pessoa', {
                templateUrl : '/pages/pessoa.html',
                controller: 'PessoaController'
            })

            .when('/sobre', {
                templateUrl : '/pages/sobre.html'
            })

            .otherwise({
                redirectTo: '/'
            });
});