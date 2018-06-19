(function() {
    'use strict';

    angular
        .module('schoolApp')
        .controller('ClassesDialogController', ClassesDialogController);

    ClassesDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Classes'];

    function ClassesDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Classes) {
        var vm = this;

        vm.classes = entity;
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
            if (vm.classes.id !== null) {
                Classes.update(vm.classes, onSaveSuccess, onSaveError);
            } else {
                Classes.save(vm.classes, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('schoolApp:classesUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.sDate = false;
        vm.datePickerOpenStatus.eDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
