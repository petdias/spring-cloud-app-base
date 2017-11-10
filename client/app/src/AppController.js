
function AppController($mdSidenav, $scope, CampaignsDataService, UsersDataService) {
  var self = this;

  self.selected     = null;
  self.campaigns    = [ ];
  self.users        = [ ];
  self.selectView   = selectView;
  self.toggleList   = toggleUsersList;

  function toggleUsersList() {
    $mdSidenav('left').toggle();
  }

  function selectView ( view ) {
      $scope.alert = {};
    self.selected = view;
    if (view === 'Campanha') {
        CampaignsDataService.query().$promise.then(function(campaigns) {
            self.campaigns = campaigns;
        }, function(errResponse) {
            $scope.alert = {type : "Erro", message : "Erro a lista de campanhas!" + errResponse};
        });
    }

      if (view === 'Usuario') {
          UsersDataService.query().$promise.then(function(users) {
              self.users = users;
          }, function(errResponse) {
              $scope.alert = {type : "Erro", message : "Erro ao carregar a lista de usuários!" + errResponse};
          });
      }
  }
}

export default ['$mdSidenav', '$scope', 'CampaignsDataService', 'UsersDataService', AppController];
