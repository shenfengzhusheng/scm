var app = angular.module('qhtWeb', ['ui.router', 'angular-loading-bar', 'ngAnimate','easypiechart','tm.pagination',
    'oitozero.ngSweetAlert','jcs-autoValidate','ui.bootstrap','xeditable','ui.tree','hierarchical-selector',
    'monospaced.qrcode','wzwizard']);
app.config(['cfpLoadingBarProvider',
    function(cfpLoadingBarProvider) {
        cfpLoadingBarProvider.includeBar = true;
        cfpLoadingBarProvider.includeSpinner = true;
        cfpLoadingBarProvider.latencyThreshold = 100;
    }]
);

app.config(['$controllerProvider', function($controllerProvider) {
    // this option might be handy for migrating old apps, but please don't use it
    // in new ones!
    $controllerProvider.allowGlobals();
}]);

// app.config(['mapScriptServiceProvider', function(provider) {
//     provider.setKey('LFSfL6uM1o4WQOIvFveIAqfD');
// }]);

app.config(['$httpProvider', function($httpProvider) {
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
   // $httpProvider.interceptors.push('ParamInterceptor');
    // Override $http service's default transformRequest
    $httpProvider.defaults.transformRequest = [function(data) {
        /**
         * The workhorse; converts an object to x-www-form-urlencoded serialization.
         * @param {Object} obj
         * @return {String}
         */
        var param = function(obj) {
            var query = '';
            var name, value, fullSubName, subName, subValue, innerObj, i;
          //  console.info('data is:'+JSON.stringify(data));
            for (name in obj) {
                value = obj[name];
                if (value instanceof Array) {
                    for (i = 0; i < value.length; ++i) {
                        subValue = value[i];
                        fullSubName = name + '[' + i + ']';
                        innerObj = {};
                        innerObj[fullSubName] = subValue;
                        query += param(innerObj) + '&';
                    }
                } else if (value instanceof Object) {
                    for (subName in value) {
                        subValue = value[subName];
                    //    fullSubName = name + '[' + subName + ']';

                        fullSubName = name + '.' + subName;
                        innerObj = {};
                        innerObj[fullSubName] = subValue;
                        query += param(innerObj) + '&';
                    }
                } else if (value !== undefined && value !== null) {
                    query += encodeURIComponent(name) + '='
                        + encodeURIComponent(value) + '&';
                }
            }
            return query.length ? query.substr(0, query.length - 1) : query;
        };

        return angular.isObject(data) && String(data) !== '[object File]'? param(data): data;
    }];
}]);


//title bind
// app.run(['$location', '$rootScope',
// function($location, $rootScope) {
//     $rootScope.$on('$routeChangeSuccess',
//     function(event, current, previous) {
//         var showTitle=current.$$route.title;
//         if(showTitle==null || showTitle== undefined){
//             showTitle='404'
//             console.info('-----');
//         }
//
//
//         $rootScope.title =showTitle;
//     });
// }]);
// app.run(['$rootScope','$state',
//     '$stateParams',
//     '$http',function ($rootScope,$state,$stateParams,$http) {
//         $state.go('index',{},{reload:true});
//     }])


angular.module('jcs-autoValidate')
    .run(['defaultErrorMessageResolver',
        function (defaultErrorMessageResolver) {
            // To change the root resource file path
            //defaultErrorMessageResolver.setI18nFileRootPath('some/path);
            defaultErrorMessageResolver.setCulture('zh-cn');
        }
    ]);
app.run(['editableOptions', '$rootScope', '$state', function($rootScope, $state, editableOptions) {
    $rootScope.$state = $state;

    editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
    //session过期统一处理
    // $rootScope.$on("SessionExpire",function(path){
    //     console.info('ddddddddddddddddddddddddddddd');
    //     $state.go('login',{path:path},{reload:true});
    // });
}]);