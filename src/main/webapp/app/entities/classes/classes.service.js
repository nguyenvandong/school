(function() {
    'use strict';
    angular
        .module('schoolApp')
        .factory('Classes', Classes);

    Classes.$inject = ['$resource', 'DateUtils'];

    function Classes ($resource, DateUtils) {
        var resourceUrl =  'api/classes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.sDate = DateUtils.convertLocalDateFromServer(data.sDate);
                        data.eDate = DateUtils.convertLocalDateFromServer(data.eDate);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.sDate = DateUtils.convertLocalDateToServer(copy.sDate);
                    copy.eDate = DateUtils.convertLocalDateToServer(copy.eDate);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.sDate = DateUtils.convertLocalDateToServer(copy.sDate);
                    copy.eDate = DateUtils.convertLocalDateToServer(copy.eDate);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
