angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/happy/api/v1';

    // $scope.fillTable = function () {
    //     $http.get(contextPath + '/api/v1/products/')
    //         .then(function (response) {
    //             console.log(response);
    //             $scope.ProductsList = response.data;
    //         });
    // };

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                p: pageIndex
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
            $scope.PaginationArray = $scope.generatePagesIndexes(1, $scope.ProductsPage.totalPages);
        });
    };

    $scope.generatePagesIndexes = function(startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.drowCart = function (){
        $http({
            url: contextPath + '/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.Cart = response.data;
        });
    }

    $scope.addProductToCart = function (id){
        $http.get(contextPath + '/cart/add/' + id)
            .then(function (response) {
                $scope.drowCart();
            });
    };

    $scope.deleteProductToCart = function (id){
        $http.get(contextPath + '/cart/delete/' + id)
            .then(function (response) {
                $scope.drowCart();
            });
    };

    $scope.clearCart = function () {
        $http.get(contextPath + '/cart/clear')
            .then(function (response) {
                $scope.drowCart();
            });
    }

    $scope.deleteProductById = function (id){
        $http.delete(contextPath + '/products/' + id)
            .then(function (responce){
                $scope.fillTable();
            });
    };

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                // console.log('sended:');
                // console.log($scope.newProduct);
                // console.log('received');
                // console.log(response.data);
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.fillTable();
});