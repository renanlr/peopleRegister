angular.module('app', ['ngRoute', 'ngMessages'])
    .config(function($routeProvider) {

        $routeProvider
            .when('/', {
                templateUrl : '/spas/login/login.view.html',
                controller: 'LoginController',
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