angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/happy';

    $scope.fillTable = function () {
        $http.get(contextPath + '/')
            .then(function (response) {
                console.log(response);
                $scope.ProductsList = response.data;
            });
    };

    $scope.deleteProductById = function (id){
        $http.get(contextPath + '/delete/' + id)
            .then(function (responce){
                $scope.fillTable();
            });
    };

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/', $scope.newProduct)
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