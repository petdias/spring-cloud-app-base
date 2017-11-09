class CampaignListController {

    constructor($mdDialog, $log) {
        this.$mdDialog = $mdDialog;
        this.$log = $log;
    }

    editCampaign(ev, campaign) {
        this.$mdDialog.show({
            controller: EditCampaignController,
            templateUrl: 'src/campaigns/components/details/UserDetails.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: true,
            controllerAs: "$ctrl",
            fullscreen: true
        }).then(function (answer) {
            this.$log.debug(answer);
        });

        function EditCampaignController($filter) {
            this.campaign = campaign;
            this.campaign.startDate = $filter('date')(campaign.startDate, "MM/dd/yyyy");
            this.campaign.endDate = $filter('date')(campaign.endDate, "MM/dd/yyyy");

            console.log('campaign', this.campaign)
        }
    };

    newCampaign(ev) {
        this.$mdDialog.show({
            controller: NewCampaignController,
            templateUrl: 'src/campaigns/components/details/UserDetails.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: true,
            fullscreen: true
        }).then(function (answer) {
            this.$log.debug(answer);
        });

        function NewCampaignController( ) {
            this.campaign = {};
        }
    };
}

export default CampaignListController;

