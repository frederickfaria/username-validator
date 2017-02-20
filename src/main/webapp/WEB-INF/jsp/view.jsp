<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>AngularJS Username Validator</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body ng-app="myApp" class="ng-cloak">
<div class="generic-container" ng-controller="ValidatorController as ctrl">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead"></span></div>
        <div class="formcontainer">
            <form>
                <div class="form-group">
                    <label for="restrictedTextBox">Enter a restricted word</label>
                    <input type="text" id="restrictedTextBox" class="form-control" placeholder="Only letters allowed" aria-describedby="helpBlock" ng-model="restrictedTextBox">
                </div>
                <button class="btn btn-default" type="button" ng-click='saveRestrictedWord()'>Save</button>
            </form>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.2/angular.min.js"></script>
<script src="<c:url value='/resources/js/app.js' />"></script>
<script src="<c:url value='/resources/js/controller/username_validator_controller.js' />"></script>
</body>
</html>