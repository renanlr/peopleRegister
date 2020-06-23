(function () {
    'use strict';

    var app = angular.module('app')
        .controller('PersonsController', ['$location', 'PersonsService', '$routeParams', personsController]);

    function personsController($location, PersonsService, $routeParams) {

        var vm = {};
        vm.save = save;
        vm.remove = remove;
        vm.startCancelEdit = startCancelEdit;
        vm.edit = edit;
        vm.showHidePassword = showHidePassword;
        vm.addTelephone = addTelephone;
        vm.removeTelephone = removeTelephone;
        vm.voltar = voltar;
        vm.person = {};
        vm.newTelephone = {};
        vm.persons = [];
        vm.errors = [];
        vm.infos = [];

        initController();

        return vm;

        function initController() {
            if ($routeParams['personId']){
                showPerson($routeParams['personId']);
            } else {
                listPersons();
            }
        }

        function listPersons() {
            PersonsService.list(function (result, response) {
                if (result) {
                    vm.persons = response.map((op) => {
                        op.edit = false;
                        op.showPass = false;
                        return op;
                    });
                } else {
                    vm.errors = response.mensagens;
                }
            })
        }

        function showPerson(id) {
            PersonsService.show(id, function (result, response) {
                if (result) {
                    vm.person = response;
                } else {
                    vm.errors = response.mensagens;
                }
            })
        }

        function save() {
            vm.errors = [];
            vm.infos = [];
            var newOperator = vm.person;
            PersonsService.insert(newOperator,function (result, response) {
                if (result) {
                    vm.persons.push(response);
                    vm.person = {};
                    vm.infos.push("Pessoa Criada com sucesso!");
                } else {
                    vm.errors = response.mensagens;
                }
            })
        }

        function remove(id) {
            vm.errors = [];
            vm.infos = [];
            PersonsService.remove(id,function (result, response) {
                if (result) {
                    vm.persons = vm.persons.filter((op) => op.id !== id);
                    vm.infos.push("Pessoa removida com sucesso!");
                } else {
                    vm.errors = response.mensagens;
                }
            })
        }

        function startCancelEdit(id) {
            $location.path('/persons/'+id);
        }

        function voltar() {
            $location.path('/persons/');
        }

        function edit(person) {
            vm.errors = [];
            vm.infos = [];
            PersonsService.edit(person, function (result, response) {
                if (result) {
                    vm.person = response;
                    vm.infos.push("Pessoa alterada com sucesso!");
                } else {
                    vm.errors = response.mensagens;
                }
            })
        }

        function showHidePassword(id) {
            vm.persons = vm.persons.map((op) => {
                if (op.id === id){
                    op.showPass = !op.showPass;
                }
                return op;
            });
        }

        function addTelephone(){
            vm.person.telephones.push(vm.newTelephone);
            vm.newTelephone = {};
        }

        function removeTelephone(index){
            vm.errors = [];
            vm.infos = [];
            vm.person.telephones.splice(index, 1);
        }

    }

})();