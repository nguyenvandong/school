(function() {
    'use strict';
    angular
        .module('schoolApp')
        .factory('StudentClass', StudentClass);

    StudentClass.$inject = ['$resource'];

    function StudentClass ($resource) {
        var resourceUrl =  'api/student-classes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
