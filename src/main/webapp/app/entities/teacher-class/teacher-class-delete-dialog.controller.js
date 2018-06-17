(function() {
    'use strict';

    angular
        .module('schoolApp')
        .controller('TeacherClassDeleteController',TeacherClassDeleteController);

    TeacherClassDeleteController.$inject = ['$uibModalInstance', 'entity', 'TeacherClass'];

    function TeacherClassDeleteController($uibModalInstance, entity, TeacherClass) {
        var vm = this;

        vm.teacherClass = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            TeacherClass.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
