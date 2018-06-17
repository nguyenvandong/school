(function() {
    'use strict';

    angular
        .module('schoolApp')
        .controller('TeacherClassDialogController', TeacherClassDialogController);

    TeacherClassDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'TeacherClass'];

    function TeacherClassDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, TeacherClass) {
        var vm = this;

        vm.teacherClass = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.teacherClass.id !== null) {
                TeacherClass.update(vm.teacherClass, onSaveSuccess, onSaveError);
            } else {
                TeacherClass.save(vm.teacherClass, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('schoolApp:teacherClassUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
