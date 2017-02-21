angular.module('myApp').controller('ValidatorController', ['$scope', '$location', '$http' , function($scope, $location, $http) {

    var REST_SERVICE_URI = $location.protocol() + "://" + $location.host() + ":" + $location.port() + '/validator/rest/';

    $scope.suggestions = null;
    $scope.usernameMessage = null;
    $scope.restrictedWordMessage = null;

    $scope.saveRestrictedWord=function()
    {
        $http({
            url: REST_SERVICE_URI + "save-restricted-word/" + $scope.restrictedTextBox,
            method: "POST"
        })
        .then(function successCallback(response) {
                $scope.success = true;
                $scope.restrictedWordMessage = "Word saved successfully.";
            }, function errorCallback(response) {
                if(response.status == 302){
                    $scope.success = false;
                    $scope.restrictedWordMessage = "The word already exists.";
                }
                else if(response.status == 406){
                    $scope.success = false;
                    $scope.restrictedWordMessage = "Word not acceptable.";
                }
            });
    }

    $scope.validateUsername=function()
    {
        $http.get(REST_SERVICE_URI + "validate-user-name/" + $scope.usernameTextBox)
            .then(function (response) {
                $scope.suggestions = null;
                $scope.success = true;
                $scope.usernameMessage = "Username is available.";
            }, function errorCallback(response) {
                if(response.status == 406){
                    $scope.suggestions = response.data.suggestedUserNames;
                    $scope.success = false;
                    $scope.usernameMessage = "Username not acceptable.";
                }
                else if(response.status == 302){
                    $scope.suggestions = response.data.suggestedUserNames;
                    $scope.success = false;
                    $scope.usernameMessage = "Someone already has that username.";
                }
            });
    }

    $scope.$watch('usernameTextBox', function(newValue){
        if(newValue.length === 0){
            $scope.suggestions = null;
            $scope.usernameMessage = null;
        }
    });

    $scope.$watch('restrictedTextBox', function(newValue){
        if(newValue.length === 0){
            $scope.suggestions = null;
            $scope.restrictedWordMessage = null;
        }
    });
}]);