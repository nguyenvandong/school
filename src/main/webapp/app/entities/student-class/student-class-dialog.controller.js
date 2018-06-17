(function() {
    'use strict';

    angular
        .module('schoolApp')
        .controller('StudentClassDialogController', StudentClassDialogController);

    StudentClassDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'StudentClass'];

    function StudentClassDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, StudentClass) {
        var vm = this;

        vm.studentClass = entity;
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
            if (vm.studentClass.id !== null) {
                StudentClass.update(vm.studentClass, onSaveSuccess, onSaveError);
            } else {
                StudentClass.save(vm.studentClass, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('schoolApp:studentClassUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
