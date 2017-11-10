
function AppController($mdSidenav, $scope, CampaignsDataService) {
  var self = this;

  self.selected     = null;
  self.campaigns    = [ ];
  self.alert        = {};
  self.selectView   = selectView;
  self.toggleList   = toggleUsersList;

  function toggleUsersList() {
    $mdSidenav('left').toggle();
  }

  function selectView ( view ) {
    self.selected = view;
    if (view === 'Campanha') {
        CampaignsDataService.query().$promise.then(function(campaigns) {
            self.campaigns = campaigns;
        }, function(errResponse) {
            $scope.alert = {type : "Erro", message : "Erro a lista de campanhas!" + errResponse};
        });
    }
  }
}

export default ['$mdSidenav', '$scope', 'CampaignsDataService', AppController];
