(function() {
    'use strict';
    angular
        .module('schoolApp')
        .factory('Attendance', Attendance);

    Attendance.$inject = ['$resource', 'DateUtils'];

    function Attendance ($resource, DateUtils) {
        var resourceUrl =  'api/attendances/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dateAttendance = DateUtils.convertLocalDateFromServer(data.dateAttendance);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.dateAttendance = DateUtils.convertLocalDateToServer(copy.dateAttendance);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.dateAttendance = DateUtils.convertLocalDateToServer(copy.dateAttendance);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
