(function () {
    'use strict';

    var app = angular.module('app')
        .controller('OperatorsController', ['$location', 'OperatorsService', operatorsController]);

    function operatorsController($location, OperatorsService) {

        var vm = {};
        vm.save = save;
        vm.remove = remove;
        vm.startCancelEdit = startCancelEdit;
        vm.edit = edit;
        vm.showHidePassword = showHidePassword;
        vm.operator = {};
        vm.operators = [];
        vm.errors = [];
        vm.infos = [];

        initController();

        return vm;

        function initController() {
            listOperators();
        }

        function listOperators() {
            OperatorsService.list(function (result, response) {
                if (result) {
                    vm.operators = response.map((op) => {
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
            var newOperator = vm.operator;
            OperatorsService.insert(newOperator,function (result, response) {
                if (result) {
                    vm.operators.push(response);
                    vm.operator = {};
                    vm.infos.push("Operador Criado com sucesso!");
                } else {
                    vm.errors = response.mensagens;
                }
            })
        }

        function remove(id) {
            vm.errors = [];
            vm.infos = [];
            OperatorsService.remove(id,function (result, response) {
                if (result) {
                    vm.operators = vm.operators.filter((op) => op.id !== id);
                    vm.infos.push("Operador removido com sucesso!");
                } else {
                    vm.errors = response.mensagens;
                }
            })
        }

        function startCancelEdit(id) {
            vm.errors = [];
            vm.infos = [];
            vm.operators = vm.operators.map((op) => {
                if (op.id === id){
                    op.edit = !op.edit;
                    op.editOperator = {
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

        function edit(operator) {
            vm.errors = [];
            vm.infos = [];
            OperatorsService.edit(operator, function (result, response) {
                if (result) {
                    vm.operators = vm.operators.map((op) => {
                        if (op.id === operator.id){
                            Object.assign(op, operator);
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
            vm.operators = vm.operators.map((op) => {
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