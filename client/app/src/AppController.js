
function AppController($mdSidenav, CampaignsDataService) {
  var self = this;

  self.selected     = null;
  self.campaigns    = [ ];
  self.selectView   = selectView;
  self.toggleList   = toggleUsersList;

  function toggleUsersList() {
    $mdSidenav('left').toggle();
  }

  function selectView ( view ) {
    self.selected = view;
    if (view === 'Campanha') {
        CampaignsDataService.query(function(campaigns) {
            self.campaigns = campaigns;
            console.log('self.campaigns', self.campaigns);
        });
    }
  }
}

export default ['$mdSidenav', 'CampaignsDataService', AppController];
