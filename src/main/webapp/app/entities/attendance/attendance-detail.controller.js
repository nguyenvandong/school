(function() {
    'use strict';

    angular
        .module('schoolApp')
        .controller('AttendanceDetailController', AttendanceDetailController);

    AttendanceDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Attendance'];

    function AttendanceDetailController($scope, $rootScope, $stateParams, previousState, entity, Attendance) {
        var vm = this;

        vm.attendance = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('schoolApp:attendanceUpdate', function(event, result) {
            vm.attendance = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
