(function() {
    'use strict';

    angular
        .module('schoolApp')
        .controller('ParentsDetailController', ParentsDetailController);

    ParentsDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Parents'];

    function ParentsDetailController($scope, $rootScope, $stateParams, previousState, entity, Parents) {
        var vm = this;

        vm.parents = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('schoolApp:parentsUpdate', function(event, result) {
            vm.parents = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
