(function() {
    'use strict';

    angular
        .module('schoolApp')
        .controller('TeacherAttendanceDetailController', TeacherAttendanceDetailController);

    TeacherAttendanceDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'TeacherAttendance', 'Teacher'];

    function TeacherAttendanceDetailController($scope, $rootScope, $stateParams, previousState, entity, TeacherAttendance, Teacher) {
        var vm = this;

        vm.teacherAttendance = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('schoolApp:teacherAttendanceUpdate', function(event, result) {
            vm.teacherAttendance = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
