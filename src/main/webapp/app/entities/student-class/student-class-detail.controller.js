(function() {
    'use strict';

    angular
        .module('schoolApp')
        .controller('StudentClassDetailController', StudentClassDetailController);

    StudentClassDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'StudentClass'];

    function StudentClassDetailController($scope, $rootScope, $stateParams, previousState, entity, StudentClass) {
        var vm = this;

        vm.studentClass = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('schoolApp:studentClassUpdate', function(event, result) {
            vm.studentClass = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
