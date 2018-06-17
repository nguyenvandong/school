(function() {
    'use strict';

    angular
        .module('schoolApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('teacher-attendance', {
            parent: 'entity',
            url: '/teacher-attendance?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'schoolApp.teacherAttendance.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/teacher-attendance/teacher-attendances.html',
                    controller: 'TeacherAttendanceController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('teacherAttendance');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('teacher-attendance-detail', {
            parent: 'teacher-attendance',
            url: '/teacher-attendance/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'schoolApp.teacherAttendance.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/teacher-attendance/teacher-attendance-detail.html',
                    controller: 'TeacherAttendanceDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('teacherAttendance');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'TeacherAttendance', function($stateParams, TeacherAttendance) {
                    return TeacherAttendance.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'teacher-attendance',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('teacher-attendance-detail.edit', {
            parent: 'teacher-attendance-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/teacher-attendance/teacher-attendance-dialog.html',
                    controller: 'TeacherAttendanceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TeacherAttendance', function(TeacherAttendance) {
                            return TeacherAttendance.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('teacher-attendance.new', {
            parent: 'teacher-attendance',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/teacher-attendance/teacher-attendance-dialog.html',
                    controller: 'TeacherAttendanceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                dateAttendance: null,
                                reason: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('teacher-attendance', null, { reload: 'teacher-attendance' });
                }, function() {
                    $state.go('teacher-attendance');
                });
            }]
        })
        .state('teacher-attendance.edit', {
            parent: 'teacher-attendance',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/teacher-attendance/teacher-attendance-dialog.html',
                    controller: 'TeacherAttendanceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TeacherAttendance', function(TeacherAttendance) {
                            return TeacherAttendance.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('teacher-attendance', null, { reload: 'teacher-attendance' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('teacher-attendance.delete', {
            parent: 'teacher-attendance',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/teacher-attendance/teacher-attendance-delete-dialog.html',
                    controller: 'TeacherAttendanceDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['TeacherAttendance', function(TeacherAttendance) {
                            return TeacherAttendance.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('teacher-attendance', null, { reload: 'teacher-attendance' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
