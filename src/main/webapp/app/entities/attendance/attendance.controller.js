(function () {
    'use strict';

    angular
        .module('schoolApp')
        .controller('AttendanceController', AttendanceController);

    AttendanceController.$inject = ['$state', 'Attendance', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams', 'Classes'];

    function AttendanceController($state, Attendance, ParseLinks, AlertService, paginationConstants, pagingParams, Classes) {

        var vm = this;
        vm.getAllClass = getAllClass;
        vm.getStudentInClass = getStudentInClass;
        vm.changeClass = changeClass;
        vm.getDaysInMonth = getDaysInMonth;
        vm.getDayString = getDayString;
        vm.save = save;

        vm.classes = [];
        vm.class = null;
        vm.classId = null;
        vm.lstStudent = [];
        vm.lstDay = [];

        getAllClass();

        var toDay = new Date();
        vm.lstDay = getDaysInMonth(toDay.getMonth(), toDay.getYear());
        console.log(vm.lstDay);

        function getAllClass() {
            Classes.query({
                page: 0,
                size: 10000,
                sort: ['name', 'asc']
            }, onSuccess, onError);

            function onSuccess(data) {
                vm.classes = data;
            }

            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function getStudentInClass(classId) {
            Classes.get({id: classId}, onSuccess, onError);
            function onSuccess(data) {
                vm.class = data;
                console.log(vm.class);
            }

            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function changeClass() {
            if (vm.classId !== null) {
                getStudentInClass(vm.classId);
            }
        }

        function getDaysInMonth(month, year) {
            // Since no month has fewer than 28 days
            var date = new Date(year, month, 1);
            var days = [];
            while (date.getMonth() === month) {
                days.push(new Date(date));
                date.setDate(date.getDate() + 1);
            }
            return days;
        }

        function getDayString(day) {
            var days = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
            return days[day];
        }

        function save(){
            console.log("asdfasdf");
        }

    }
})();
