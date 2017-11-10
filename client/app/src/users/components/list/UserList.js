import UserListController from './UserListController'

export default {
  name : 'userList',
  config : {
    bindings    : {  users: '<', selected : '<', showDetails : '&onSelected' },
    templateUrl : 'src/users/components/list/UserList.html',
      controller       : [ '$mdDialog', '$log', '$resource', UserListController ]
  }
};