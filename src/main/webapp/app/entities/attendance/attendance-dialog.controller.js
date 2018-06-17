(function() {
    'use strict';

    angular
        .module('schoolApp')
        .controller('AttendanceDialogController', AttendanceDialogController);

    AttendanceDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Attendance'];

    function AttendanceDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Attendance) {
        var vm = this;

        vm.attendance = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.attendance.id !== null) {
                Attendance.update(vm.attendance, onSaveSuccess, onSaveError);
            } else {
                Attendance.save(vm.attendance, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('schoolApp:attendanceUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dateAttendance = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
