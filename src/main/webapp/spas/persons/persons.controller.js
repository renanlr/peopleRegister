(function () {
    'use strict';

    var app = angular.module('app')
        .controller('PersonsController', ['$location', 'PersonsService', personsController]);

    function personsController($location, PersonsService) {

        var vm = {};
        vm.save = save;
        vm.remove = remove;
        vm.startCancelEdit = startCancelEdit;
        vm.edit = edit;
        vm.showHidePassword = showHidePassword;
        vm.person = {};
        vm.persons = [];
        vm.errors = [];
        vm.infos = [];

        initController();

        return vm;

        function initController() {
            listPersons();
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

        function save() {
            vm.errors = [];
            vm.infos = [];
            var newOperator = vm.person;
            PersonsService.insert(newOperator,function (result, response) {
                if (result) {
                    vm.persons.push(newOperator);
                    vm.person = {};
                    vm.infos.push("Operador Criado com sucesso!");
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
                    vm.infos.push("Operador removido com sucesso!");
                } else {
                    vm.errors = response.mensagens;
                }
            })
        }

        function startCancelEdit(id) {
            vm.errors = [];
            vm.infos = [];
            vm.persons = vm.persons.map((op) => {
                if (op.id === id){
                    op.edit = !op.edit;
                    op.editPerson = {
                        id:op.id,
                        name:op.name,
                        profile:op.profile,
                        password:op.password
                    }
                } else {
                    op.edit = false;
                }
                return op;
            });
        }

        function edit(person) {
            vm.errors = [];
            vm.infos = [];
            PersonsService.edit(person, function (result, response) {
                if (result) {
                    vm.persons = vm.persons.map((op) => {
                        if (op.id === person.id){
                            Object.assign(op, person);
                            op.edit = false;
                        }
                        return op;
                    });
                    vm.infos.push("Operador alterado com sucesso!");
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

    }

    app.filter('password', function(){
        return function(str, show){
            return show? str : '******';
        };
    });

})();