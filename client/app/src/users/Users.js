
import UsersDataService from 'src/users/services/UsersDataService';
import ClubsDataService from 'src/users/services/ClubsDataService';

import UserList from 'src/users/components/list/UserList';
import UserDetails from 'src/users/components/details/UserDetails';

export default angular
  .module("users", ['ngMaterial', 'ngResource'])

  .component(UserList.name, UserList.config)
  .component(UserDetails.name, UserDetails.config)

  .service("UsersDataService", UsersDataService)
  .service("ClubsDataService", ClubsDataService);
