class CampaignListController {

    constructor($mdDialog, $log) {
        this.$mdDialog = $mdDialog;
        this.$log = $log;
    }

    editCampaign(ev, campaign) {
        this.$mdDialog.show({
            controller: EditCampaignController,
            templateUrl: 'src/campaigns/components/details/CampaignDetails.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: true,
            controllerAs: "$ctrl",
            fullscreen: true
        });

        function EditCampaignController($filter, $scope, $mdDialog, ClubsDataService, CampaignsDataService) {
            $scope.alert = {};
            this.hasUpdate = true;
            this.campaign = campaign;
            this.campaign.startDate = $filter('date')(campaign.startDate, "yyyy-MM-dd");
            this.campaign.endDate = $filter('date')(campaign.endDate, "yyyy-MM-dd");

            ClubsDataService.query().$promise.then(function(clubs) {
                $scope.clubs = clubs;
            }, function(errResponse) {
                $scope.alert = {type : "Erro", message : "Erro a lista de clubes!" + errResponse};
            });

            $scope.save = function(campaign) {
                CampaignsDataService.update({ id:campaign.id }, campaign).$promise.then(function() {
                    $scope.alert = {type : "Sucesso", message : "Campanha atualizada com sucesso!"};
                }, function(errResponse) {
                    $scope.alert = {type : "Erro", message : "Erro ao atualizar a campanha!" + errResponse};
                });
            };

            $scope.close = function() {
                $mdDialog.hide();
            };
        }
    };

    newCampaign(ev) {
        this.$mdDialog.show({
            controller: NewCampaignController,
            templateUrl: 'src/campaigns/components/details/CampaignDetails.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: true,
            fullscreen: true,
            controllerAs: "$ctrl"
        });

        function NewCampaignController($scope, $mdDialog, ClubsDataService, CampaignsDataService) {
            this.campaign = {};
            $scope.alert = {};
            ClubsDataService.query(function(clubs) {
                $scope.clubs = clubs;
            });

            $scope.save = function(campaign) {
                CampaignsDataService.save(campaign).$promise.then(function () {
                    $scope.alert = {type: "Sucesso", message: "Campanha salva com sucesso!"};
                }, function (errResponse) {
                    $scope.alert = {type: "Erro", message: "Erro ao inserir a campanha!" + errResponse};
                });
            };

            $scope.close = function() {
                $mdDialog.hide();
            };
        }
    };
}
export default CampaignListController;

