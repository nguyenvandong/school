(function() {
    'use strict';
    angular
        .module('schoolApp')
        .factory('Student', Student);

    Student.$inject = ['$resource', 'DateUtils'];

    function Student ($resource, DateUtils) {
        var resourceUrl =  'api/students/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dateOfBirth = DateUtils.convertLocalDateFromServer(data.dateOfBirth);
                        data.dateOfJoin = DateUtils.convertLocalDateFromServer(data.dateOfJoin);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.dateOfBirth = DateUtils.convertLocalDateToServer(copy.dateOfBirth);
                    copy.dateOfJoin = DateUtils.convertLocalDateToServer(copy.dateOfJoin);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.dateOfBirth = DateUtils.convertLocalDateToServer(copy.dateOfBirth);
                    copy.dateOfJoin = DateUtils.convertLocalDateToServer(copy.dateOfJoin);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
