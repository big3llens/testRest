angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/happy';
    $scope.authorized = false;

    // $scope.fillTable = function () {
    //     $http.get(contextPath + '/api/v1/products/')
    //         .then(function (response) {
    //             console.log(response);
    //             $scope.ProductsList = response.data;
    //         });
    // };

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/products',
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

    $scope.showCart = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.Cart = response.data;
        });
    };

    $scope.clearCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/clear',
            method: 'GET'
        }).then(function (response) {
            $scope.showCart();
        });
    };

    $scope.countMetods = function () {
        $http({
            url: contextPath + '/api/v1/products/count',
            method: 'GET'
        });
    };

    $scope.showOrders = function () {
        $http.get(contextPath + '/api/v1/orders/show')
            .then(function (response) {
                $scope.MyOrders = response.data;
            });
    }

    $scope.generatePagesIndexes = function(startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.drowCart = function (){
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.Cart = response.data;
        });
    }

    $scope.addProductToCart = function (id){
        $http.get(contextPath + '/api/v1/cart/add/' + id)
            .then(function (response) {
                $scope.drowCart();
            });
    };

    $scope.deleteProductToCart = function (id){
        $http.get(contextPath + '/api/v1/cart/delete/' + id)
            .then(function (response) {
                $scope.drowCart();
            });
    };

    $scope.clearCart = function () {
        $http.get(contextPath + '/api/v1/cart/clear')
            .then(function (response) {
                $scope.drowCart();
            });
    }

    $scope.deleteProductById = function (id){
        $http.delete(contextPath + '/api/v1/products/' + id)
            .then(function (responce){
                $scope.fillTable();
            });
    };

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function (response) {
                // console.log('sended:');
                // console.log($scope.newProduct);
                // console.log('received');
                // console.log(response.data);
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.checkout = function (city, street, numberOfHouse) {
        $http({
            url: contextPath + '/api/v1/orders/create',
            method: 'POST',
            params: {
                city: city,
                street: street,
                numberOfHouse: numberOfHouse,
            }
        }).then(function (response) {
            $scope.showOrders();
            $scope.clearCart();
            $scope.city = null;
            $scope.street = null;
            $scope.numberOfHouse = null;
        });
    };

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $scope.user.username = response.data.name;
                    $scope.user.password = null;
                    $scope.authorized = true;
                    $scope.fillTable();
                }
            }, function errorCallback(response) {
                window.alert("Error");
            });
    };


    $scope.userRegistration = function (email) {
        $http.post(contextPath + '/create', $scope.newUser, email)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $scope.newUser.username = null;
                    $scope.newUser.password = null;
                }
            }, function errorCallback(response) {
                window.alert("Error");
            });
    };

    // $scope.checkout = function (city, street, numberOfHouse) {
    //     $http.get(contextPath + '/api/v1/orders/create/' + city + '/' + street + '/' + numberOfHouse)
    //         .then(function (response) {
    //             $scope.showOrders();
    //             $scope.clearCart();
    //         });
    // }

    $scope.fillTable();
});