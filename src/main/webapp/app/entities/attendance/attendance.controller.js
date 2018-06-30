(function () {
    'use strict';

    angular
        .module('schoolApp')
        .controller('AttendanceController', AttendanceController);

    AttendanceController.$inject = ['$state', 'Attendance', 'ParseLinks', 'AlertService', 'paginationConstants', 'pagingParams', 'Classes', '$cookies'];

    function AttendanceController($state, Attendance, ParseLinks, AlertService, paginationConstants, pagingParams, Classes, $cookies) {

        var vm = this;

        vm.getAllClass = getAllClass;
        vm.changeClass = changeClass;
        vm.getDaysInMonth = getDaysInMonth;
        vm.getDayString = getDayString;
        vm.save = save;

        vm.classes = [];
        vm.class = null;
        vm.classId = null;
        vm.lstStudent = [];

        var tableStudent = null;
        var toDay = new Date();
        var lstDay = getDaysInMonth(toDay.getMonth(), toDay.getYear());
        var lstTemp = [];
        var headers = [{title: 'Họ tên'}];

        for (var i = 0; i < lstDay.length; i++) {
            lstTemp.push(lstDay[i].getDate());
            headers.push({title: getDayString(lstDay[i].getDay()) + ' ' + lstDay[i].getDate()});
        }

        angular.element(document).ready(function () {

            tableStudent = $('#tableStudent').DataTable({
                ajax: {
                    url: 'api/attendances/get-students-in-class',
                    beforeSend: function (request) {
                        request.setRequestHeader("X-XSRF-TOKEN", $cookies.get('XSRF-TOKEN'));
                    },
                    type: "POST",
                    dataType: "json",
                    contentType: "application/json",
                    data: function (data) {
                        data.classId = vm.classId === null ? -1 : vm.classId;
                        return angular.toJson(data);
                    }
                },
                searching: false,
                serverSide: true,
                paging: false,
                ordering: true,
                info: false,
                processing: true,
                scrollY: 400,
                scrollX: true,
                pageLength: -1,
                columns: headers,
                columnDefs: [
                    {
                        targets: 0,
                        render: function (data, type, row, meta) {
                            return row.firstName + " " + row.midleName + " " + row.lastName;
                        },
                        width: 150
                    },
                    {
                        targets: lstTemp,
                        className: "dt-center",
                        render: function (data, type, row, meta) {
                            return '<input type="checkbox"/>';
                        }
                    }
                ],
                rowCallback: function (row, data, index) {
                    var i = 0;
                    $(row).find('td').each(function () {
                        if (i !== 0) {
                            var date = new Date(2018, 5, i);
                            var now = new Date();
                            // $(this).find('input[type="checkbox"]').prop('disabled', date < now);
                        }
                        i++;
                    });
                },
                fixedColumns: {
                    leftColumns: 1
                }
            });
        });

        getAllClass();

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

        function changeClass() {
            if (vm.classId !== null && tableStudent !== null) {
                tableStudent.ajax.reload();
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

        function save() {
            console.log("asdfasdf");
        }

    }
})();
