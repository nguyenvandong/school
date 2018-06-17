(function() {
    'use strict';

    angular
        .module('schoolApp')
        .controller('TeacherDetailController', TeacherDetailController);

    TeacherDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Teacher'];

    function TeacherDetailController($scope, $rootScope, $stateParams, previousState, entity, Teacher) {
        var vm = this;

        vm.teacher = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('schoolApp:teacherUpdate', function(event, result) {
            vm.teacher = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
