(function() {
    'use strict';

    angular
        .module('schoolApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('student-class', {
            parent: 'entity',
            url: '/student-class?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'schoolApp.studentClass.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/student-class/student-classes.html',
                    controller: 'StudentClassController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'classId,asc',
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
                    $translatePartialLoader.addPart('studentClass');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('student-class-detail', {
            parent: 'student-class',
            url: '/student-class/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'schoolApp.studentClass.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/student-class/student-class-detail.html',
                    controller: 'StudentClassDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('studentClass');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'StudentClass', function($stateParams, StudentClass) {
                    return StudentClass.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'student-class',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('student-class-detail.edit', {
            parent: 'student-class-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/student-class/student-class-dialog.html',
                    controller: 'StudentClassDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['StudentClass', function(StudentClass) {
                            return StudentClass.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('student-class.new', {
            parent: 'student-class',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/student-class/student-class-dialog.html',
                    controller: 'StudentClassDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                studentId: null,
                                classId: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('student-class', null, { reload: 'student-class' });
                }, function() {
                    $state.go('student-class');
                });
            }]
        })
        .state('student-class.edit', {
            parent: 'student-class',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/student-class/student-class-dialog.html',
                    controller: 'StudentClassDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['StudentClass', function(StudentClass) {
                            return StudentClass.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('student-class', null, { reload: 'student-class' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('student-class.delete', {
            parent: 'student-class',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/student-class/student-class-delete-dialog.html',
                    controller: 'StudentClassDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['StudentClass', function(StudentClass) {
                            return StudentClass.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('student-class', null, { reload: 'student-class' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
