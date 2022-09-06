angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    // console.log(123);

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products/')
            .then(function (response) {
                $scope.ProductsList = response.data;
            });
    };


    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + '/products/delete/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.createProduct = function () {
        $http.post(contextPath + '/products/getProduct/', $scope.newProduct)
            .then(function (response) {
                $scope.loadProducts();
            });
    }


    $scope.filter = function () {
        $http({
            url: contextPath + '/products/price_between/',
            method: 'get',
            params: {
                min: $scope.filtr.min,
                max: $scope.filtr.max
            }
        }).then(function (response) {
            $scope.ProductsList = response.data;
        });
    }

    $scope.loadProducts();
});