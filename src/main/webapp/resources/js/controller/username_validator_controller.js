

angular.module('myApp').controller('ValidatorController', ['$scope', '$location', '$http' , function($scope, $location, $http) {

    var REST_SERVICE_URI = $location.protocol() + "://" + $location.host() + ":" + $location.port() + '/validator/rest/';

    $scope.saveRestrictedWord=function()
    {

        $http({
            url: REST_SERVICE_URI + "save-restricted-word/" + $scope.restrictedTextBox,
            method: "POST"
        })
        //$http.get(REST_SERVICE_URI + "save-restricted-word/" + $scope.restrictedTextBox)
            .then(function successCallback(response) {
                alert(response.status);
            }, function errorCallback(response) {
                alert(response.status);
            });
    }

}]);