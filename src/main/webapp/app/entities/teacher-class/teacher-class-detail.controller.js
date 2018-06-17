(function() {
    'use strict';

    angular
        .module('schoolApp')
        .controller('TeacherClassDetailController', TeacherClassDetailController);

    TeacherClassDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'TeacherClass'];

    function TeacherClassDetailController($scope, $rootScope, $stateParams, previousState, entity, TeacherClass) {
        var vm = this;

        vm.teacherClass = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('schoolApp:teacherClassUpdate', function(event, result) {
            vm.teacherClass = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
