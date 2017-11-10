class UserListController {

    constructor($mdDialog, $log) {
        this.$mdDialog = $mdDialog;
        this.$log = $log;
    }

    editUser(ev, user) {
        this.$mdDialog.show({
            controller: EditUserController,
            templateUrl: 'src/users/components/details/UserDetails.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: true,
            controllerAs: "$ctrl",
            fullscreen: true
        });

        function EditUserController($filter, $scope, $mdDialog, ClubsDataService, UsersDataService) {
            $scope.alert = {};
            this.hasUpdate = true;
            this.user = user;
            this.user.birthDate = $filter('date')(user.birthDate, "yyyy-MM-dd");

            ClubsDataService.query().$promise.then(function(clubs) {
                $scope.clubs = clubs;
            }, function(errResponse) {
                $scope.alert = {type : "Erro", message : "Erro a lista de clubes!" + errResponse};
            });

            $scope.save = function(user) {
                UsersDataService.update({ id:user.id }, user).$promise.then(function() {
                    $scope.alert = {type : "Sucesso", message : "Usuário atualizado com sucesso!"};
                }, function(errResponse) {
                    $scope.alert = {type : "Erro", message : "Erro ao atualizar o usuário!" + errResponse};
                });
            };

            $scope.close = function() {
                $mdDialog.hide();
            };
        }
    };

    newUser(ev) {
        this.$mdDialog.show({
            controller: NewCampaignController,
            templateUrl: 'src/users/components/details/UserDetails.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: true,
            controllerAs: "$ctrl",
            fullscreen: true
        });

        function NewCampaignController($scope, $mdDialog, ClubsDataService, UsersDataService) {
            $scope.alert = {};
            this.hasUpdate = false;

            ClubsDataService.query().$promise.then(function(clubs) {
                $scope.clubs = clubs;
            }, function(errResponse) {
                $scope.alert = {type : "Erro", message : "Erro a lista de clubes!" + errResponse};
            });

            $scope.save = function(user) {
                UsersDataService.save(user).$promise.then(function () {
                    $scope.alert = {type: "Sucesso", message: "suário atualizado com sucesso!"};
                }, function (errResponse) {
                    $scope.alert = {type: "Erro", message: "Erro ao atualizar o usuário!" + errResponse};
                });
            };

            $scope.close = function() {
                $mdDialog.hide();
            };
        }
    };
}
export default UserListController;

