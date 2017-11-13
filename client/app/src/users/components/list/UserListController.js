class UserListController {

    constructor($mdDialog, $log) {
        this.$mdDialog = $mdDialog;
        this.$log = $log;
    }

    editUser(ev, userId) {
        this.$mdDialog.show({
            controller: EditUserController,
            templateUrl: 'src/users/components/details/UserDetails.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: true,
            controllerAs: "$ctrl"
        });

        function EditUserController($filter, $scope, $mdDialog, ClubsDataService, UsersDataService) {
            $scope.alert = {};
            this.hasUpdate = true;

            UsersDataService.get({ id:userId }).$promise.then(function(user) {
                this.user = user;
            }, function(errResponse) {
                $scope.alert = {type : "Erro", message : "Erro ao recuperar dados do usuário!" + errResponse};
            });

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

            $scope.checked = function(campaign) {
                return campaign.selected = !campaign.selected;
            };

            $scope.connect = function(campaigns) {
                return campaigns;
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
            height: 300
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
                UsersDataService.save(user).$promise.then(function (user) {
                    $scope.alert = {type: "Sucesso", message: "Usuário atualizado com sucesso!"};
                }, function (errResponse) {
                    $scope.alert = {type: "Erro", message: "Erro ao atualizar o usuário!" + errResponse};
                });
            };

            $scope.close = function() {
                $mdDialog.hide();
            };

            $scope.checked = function(campaign) {
                return campaign.selected = !campaign.selected;
            };
        }
    };
}
export default UserListController;

