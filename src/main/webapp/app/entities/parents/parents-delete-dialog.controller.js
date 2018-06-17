(function() {
    'use strict';

    angular
        .module('schoolApp')
        .controller('ParentsDeleteController',ParentsDeleteController);

    ParentsDeleteController.$inject = ['$uibModalInstance', 'entity', 'Parents'];

    function ParentsDeleteController($uibModalInstance, entity, Parents) {
        var vm = this;

        vm.parents = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Parents.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
