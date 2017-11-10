
function UsersDataService($resource) {
  return $resource('/app/users/:id', null,
          {'get':   {method:'GET'},
          'save':   {method:'POST'},
          'query':  {method:'GET', isArray:true},
          'update': {method:'PUT'},
          'delete': {method:'DELETE'}});
}
export default ['$resource', UsersDataService];

