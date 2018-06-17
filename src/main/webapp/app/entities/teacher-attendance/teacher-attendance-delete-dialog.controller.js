(function() {
    'use strict';

    angular
        .module('schoolApp')
        .controller('TeacherAttendanceDeleteController',TeacherAttendanceDeleteController);

    TeacherAttendanceDeleteController.$inject = ['$uibModalInstance', 'entity', 'TeacherAttendance'];

    function TeacherAttendanceDeleteController($uibModalInstance, entity, TeacherAttendance) {
        var vm = this;

        vm.teacherAttendance = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            TeacherAttendance.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
