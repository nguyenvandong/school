(function() {
    'use strict';
    angular
        .module('schoolApp')
        .factory('Parents', Parents);

    Parents.$inject = ['$resource', 'DateUtils'];

    function Parents ($resource, DateUtils) {
        var resourceUrl =  'api/parents/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dateOfBirth = DateUtils.convertLocalDateFromServer(data.dateOfBirth);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.dateOfBirth = DateUtils.convertLocalDateToServer(copy.dateOfBirth);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.dateOfBirth = DateUtils.convertLocalDateToServer(copy.dateOfBirth);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
