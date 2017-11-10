
function ClubsDataService($resource) {
  return $resource('/app/clubs/');
}
export default ['$resource', ClubsDataService];

