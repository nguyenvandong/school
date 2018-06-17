(function() {
    'use strict';
    angular
        .module('schoolApp')
        .factory('TeacherClass', TeacherClass);

    TeacherClass.$inject = ['$resource'];

    function TeacherClass ($resource) {
        var resourceUrl =  'api/teacher-classes/:id';

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
