(function() {
    'use strict';

    angular
        .module('schoolApp')
        .controller('ClassesDetailController', ClassesDetailController);

    ClassesDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Classes'];

    function ClassesDetailController($scope, $rootScope, $stateParams, previousState, entity, Classes) {
        var vm = this;

        vm.classes = entity;
        vm.students = vm.classes.students;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('schoolApp:classesUpdate', function(event, result) {
            vm.classes = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
