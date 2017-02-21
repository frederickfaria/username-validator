<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>AngularJS Username Validator</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
    <script language="Javascript" type="text/javascript">
        function onlyAlphabets(e, t) {
            try {
                if (window.event) {
                    var charCode = window.event.keyCode;
                }
                else if (e) {
                    var charCode = e.which;
                }
                else { return true; }
                if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))
                    return true;
                else
                    return false;
            }
            catch (err) {
            }
        }

        function onlyLettersNumbersAndPeriod(e, t) {
            try {
                if (window.event) {
                    var charCode = window.event.keyCode;
                }
                else if (e) {
                    var charCode = e.which;
                }
                else { return true; }
                if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || (charCode > 47 && charCode < 58) || (charCode == 46))
                    return true;
                else
                    return false;
            }
            catch (err) {
            }
        }
    </script>
</head>
<body ng-app="myApp" class="ng-cloak">
<div class="generic-container" ng-controller="ValidatorController as ctrl">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Restricted Words Module</span></div>
        <div class="formcontainer">
            <form>
                <div class="form-group">
                    <label for="restrictedTextBox">Enter a restricted word</label>
                    <input type="text" id="restrictedTextBox" class="form-control" placeholder="Only letters allowed"
                           aria-describedby="helpBlock" ng-model="restrictedTextBox" onkeypress="return onlyAlphabets(event,this);" >
                    <div class="form-group"><span class="fixed" ng-bind="restrictedWordMessage" ng-class="{successMessage : success, errorMessage : !success}"></span></div>
                </div>
                <div class="row">
                    <div class="form-actions floatRight">
                        <button class="btn btn-primary btn-sm" type="button" ng-click='saveRestrictedWord()' ng-disabled="!restrictedTextBox.length">Save
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Username Validation Module</span></div>
        <div class="formcontainer">
            <form>
                <div class="form-group">
                    <label for="restrictedTextBox">Choose your username</label>
                    <input type="text" id="usernameTextBox" class="form-control"
                           placeholder="You can use letters, numbers and periods" aria-describedby="helpBlock"
                           ng-model="usernameTextBox" onkeypress="return onlyLettersNumbersAndPeriod(event,this);">
                    <div class="form-group"><span class="fixed" ng-bind="usernameMessage" ng-class="{successMessage : success, errorMessage : !success}"></span></div>
                </div>
                <div class="row">
                    <div class="form-actions floatRight">
                        <button class="btn btn-primary btn-sm" type="button" ng-disabled="!usernameTextBox.length" ng-click='validateUsername()'>Validate
                        </button>
                    </div>
                </div>
                <div class="form-group">
                    <div ng-repeat="u in suggestions">
                        <div><span>Available: </span><span ng-bind="u"></span></div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.2/angular.min.js"></script>
<script src="<c:url value='/resources/js/app.js' />"></script>
<script src="<c:url value='/resources/js/controller/username_validator_controller.js' />"></script>
</body>
</html>