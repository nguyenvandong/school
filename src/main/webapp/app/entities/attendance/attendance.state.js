(function() {
    'use strict';

    angular
        .module('schoolApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('attendance', {
            parent: 'entity',
            url: '/attendance?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'schoolApp.attendance.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/attendance/attendances.html',
                    controller: 'AttendanceController',
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
                    $translatePartialLoader.addPart('attendance');
                    $translatePartialLoader.addPart('student');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('attendance-detail', {
            parent: 'attendance',
            url: '/attendance/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'schoolApp.attendance.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/attendance/attendance-detail.html',
                    controller: 'AttendanceDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('attendance');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Attendance', function($stateParams, Attendance) {
                    return Attendance.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'attendance',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('attendance-detail.edit', {
            parent: 'attendance-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/attendance/attendance-dialog.html',
                    controller: 'AttendanceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Attendance', function(Attendance) {
                            return Attendance.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('attendance.new', {
            parent: 'attendance',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/attendance/attendance-dialog.html',
                    controller: 'AttendanceDialogController',
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
                    $state.go('attendance', null, { reload: 'attendance' });
                }, function() {
                    $state.go('attendance');
                });
            }]
        })
        .state('attendance.edit', {
            parent: 'attendance',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/attendance/attendance-dialog.html',
                    controller: 'AttendanceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Attendance', function(Attendance) {
                            return Attendance.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('attendance', null, { reload: 'attendance' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('attendance.delete', {
            parent: 'attendance',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/attendance/attendance-delete-dialog.html',
                    controller: 'AttendanceDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Attendance', function(Attendance) {
                            return Attendance.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('attendance', null, { reload: 'attendance' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
