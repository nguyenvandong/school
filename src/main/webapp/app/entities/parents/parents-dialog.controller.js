(function() {
    'use strict';

    angular
        .module('schoolApp')
        .controller('ParentsDialogController', ParentsDialogController);

    ParentsDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Parents'];

    function ParentsDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Parents) {
        var vm = this;

        vm.parents = entity;
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
            if (vm.parents.id !== null) {
                Parents.update(vm.parents, onSaveSuccess, onSaveError);
            } else {
                Parents.save(vm.parents, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('schoolApp:parentsUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dateOfBirth = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
