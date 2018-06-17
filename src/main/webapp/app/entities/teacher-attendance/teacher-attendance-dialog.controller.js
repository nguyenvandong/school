(function() {
    'use strict';

    angular
        .module('schoolApp')
        .controller('TeacherAttendanceDialogController', TeacherAttendanceDialogController);

    TeacherAttendanceDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'TeacherAttendance', 'Teacher'];

    function TeacherAttendanceDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, TeacherAttendance, Teacher) {
        var vm = this;

        vm.teacherAttendance = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.teachers = Teacher.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.teacherAttendance.id !== null) {
                TeacherAttendance.update(vm.teacherAttendance, onSaveSuccess, onSaveError);
            } else {
                TeacherAttendance.save(vm.teacherAttendance, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('schoolApp:teacherAttendanceUpdate', result);
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
