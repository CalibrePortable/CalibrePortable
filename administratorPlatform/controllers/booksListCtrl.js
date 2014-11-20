/**
 * created by Harbon
 * date:2014-10-26
 */

AdministratorPlatform.controller('booksListCtrl', ['$rootScope', '$scope', 'requestService', function ($rootScope, $scope, requestService) {


    $rootScope.booksList = "uk-active";
    $rootScope.booksInLending = "unActive";
    $rootScope.barCodeScan = "unActive";
    $rootScope.paginationShouldShow = true;
    $rootScope.searchShouldShow = true;
//    身份验证
    requestService.validate();
//    分页初始化
    $scope.pageInit = function () {

        requestService.paginationInit(0,$scope);
    }
//    页面请求
    $scope.pageRequest = function (page) {

        requestService.booksListRequest(page, $scope);

    }
//    modal提交修改
    $scope.modalSubmit = function (index, bookId) {
        var modal = $.UIkit.modal("#"+bookId);
        if ( modal.isActive() ) {
            modal.hide();
        }
        requestService.bookUpdate(index, $scope);
    }
//
    $scope.delete = function (index) {
        requestService.bookDelete(index, $scope);
    }
//    添加书籍
    $scope.addSubmit = function () {
        var modal = $.UIkit.modal("#add");
        if ( modal.isActive() ) {
            modal.hide();
        }
        requestService.bookAdd ($scope);
    }
//        请求前一页
    $scope.thePreviousPage = function (currentPage) {
        currentPage = currentPage -1;
        $scope.currentPage = currentPage;
        requestService.booksListRequest(currentPage, $scope);

    }
//    请求下一页
    $scope.theNextPage = function (currentPage) {
        currentPage = currentPage +1;
        $scope.currentPage = currentPage;
        requestService.booksListRequest(currentPage, $scope);
    }

}])