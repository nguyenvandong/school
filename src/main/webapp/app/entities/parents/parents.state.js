(function() {
    'use strict';

    angular
        .module('schoolApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('parents', {
            parent: 'entity',
            url: '/parents?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'schoolApp.parents.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/parents/parents.html',
                    controller: 'ParentsController',
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
                    $translatePartialLoader.addPart('parents');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('parents-detail', {
            parent: 'parents',
            url: '/parents/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'schoolApp.parents.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/parents/parents-detail.html',
                    controller: 'ParentsDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('parents');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Parents', function($stateParams, Parents) {
                    return Parents.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'parents',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('parents-detail.edit', {
            parent: 'parents-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/parents/parents-dialog.html',
                    controller: 'ParentsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Parents', function(Parents) {
                            return Parents.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('parents.new', {
            parent: 'parents',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/parents/parents-dialog.html',
                    controller: 'ParentsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                firstName: null,
                                midleName: null,
                                lastName: null,
                                dateOfBirth: null,
                                gender: null,
                                address: null,
                                phoneNumber: null,
                                relation: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('parents', null, { reload: 'parents' });
                }, function() {
                    $state.go('parents');
                });
            }]
        })
        .state('parents.edit', {
            parent: 'parents',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/parents/parents-dialog.html',
                    controller: 'ParentsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Parents', function(Parents) {
                            return Parents.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('parents', null, { reload: 'parents' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('parents.delete', {
            parent: 'parents',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/parents/parents-delete-dialog.html',
                    controller: 'ParentsDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Parents', function(Parents) {
                            return Parents.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('parents', null, { reload: 'parents' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
