(function() {
    'use strict';

    angular
        .module('schoolApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('teacher-class', {
            parent: 'entity',
            url: '/teacher-class?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'schoolApp.teacherClass.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/teacher-class/teacher-classes.html',
                    controller: 'TeacherClassController',
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
                    $translatePartialLoader.addPart('teacherClass');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('teacher-class-detail', {
            parent: 'teacher-class',
            url: '/teacher-class/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'schoolApp.teacherClass.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/teacher-class/teacher-class-detail.html',
                    controller: 'TeacherClassDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('teacherClass');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'TeacherClass', function($stateParams, TeacherClass) {
                    return TeacherClass.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'teacher-class',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('teacher-class-detail.edit', {
            parent: 'teacher-class-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/teacher-class/teacher-class-dialog.html',
                    controller: 'TeacherClassDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TeacherClass', function(TeacherClass) {
                            return TeacherClass.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('teacher-class.new', {
            parent: 'teacher-class',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/teacher-class/teacher-class-dialog.html',
                    controller: 'TeacherClassDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                teacherId: null,
                                classId: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('teacher-class', null, { reload: 'teacher-class' });
                }, function() {
                    $state.go('teacher-class');
                });
            }]
        })
        .state('teacher-class.edit', {
            parent: 'teacher-class',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/teacher-class/teacher-class-dialog.html',
                    controller: 'TeacherClassDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TeacherClass', function(TeacherClass) {
                            return TeacherClass.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('teacher-class', null, { reload: 'teacher-class' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('teacher-class.delete', {
            parent: 'teacher-class',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/teacher-class/teacher-class-delete-dialog.html',
                    controller: 'TeacherClassDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['TeacherClass', function(TeacherClass) {
                            return TeacherClass.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('teacher-class', null, { reload: 'teacher-class' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
