/**
 * created by Harbon
 * date :2014-10-26
 */

AdministratorPlatform.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
//    $locationProvider.html5Mode(true);
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
    $routeProvider.when('/', {
        templateUrl:'/pages/login.html',
        controller:'loginCtrl'
    }).when('/login', {
        templateUrl:'/pages/login.html',
        controller:'loginCtrl'
    }).when('/booksList', {
        templateUrl:'/pages/booksList.html',
        controller:'booksListCtrl'
    }).when('/booksInLending', {
        templateUrl:'/pages/booksInLending.html',
        controller:'booksInLendingCtrl'
    }).when('/barCodeScan', {
        templateUrl:'/pages/barCodeScan.html',
        controller:'barCodeScanCtrl'
    }).otherwise({
        redirectTo:'/booksList'
    })
}])