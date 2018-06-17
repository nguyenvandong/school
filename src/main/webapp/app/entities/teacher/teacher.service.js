(function() {
    'use strict';
    angular
        .module('schoolApp')
        .factory('Teacher', Teacher);

    Teacher.$inject = ['$resource', 'DateUtils'];

    function Teacher ($resource, DateUtils) {
        var resourceUrl =  'api/teachers/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dateOfBirth = DateUtils.convertLocalDateFromServer(data.dateOfBirth);
                        data.dateOfJoin = DateUtils.convertLocalDateFromServer(data.dateOfJoin);
                        data.dateOfLeaving = DateUtils.convertLocalDateFromServer(data.dateOfLeaving);
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
                    copy.dateOfLeaving = DateUtils.convertLocalDateToServer(copy.dateOfLeaving);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.dateOfBirth = DateUtils.convertLocalDateToServer(copy.dateOfBirth);
                    copy.dateOfJoin = DateUtils.convertLocalDateToServer(copy.dateOfJoin);
                    copy.dateOfLeaving = DateUtils.convertLocalDateToServer(copy.dateOfLeaving);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
