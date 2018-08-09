/**
 * 提示框数据
 */
// angular.module('qhtWeb').studry('TipService', ['$timeout', function($timeout) {
//     return {
//         message : null,
//         type : null,
//         setMessage : function(msg,type){
//
//             this.message = msg;
//             this.type = type;
//
//             //提示框显示最多3秒消失
//             var _self = this;
//             $timeout(function(){
//                 _self.clear();
//             },3000);
//         },
//         clear : function(){
//             this.message = null;
//             this.type = null;
//         }
//     };
// }]);
// var app = angular.module('qhtWeb', []);

angular.module('qhtWeb').service('commonService',function($q, $window,$document, $http,SweetAlert){

    //has return services
    return{
        loadMap:function(key){
            var deferred = $q.defer();
            promise = $q(function(resolve, reject){

                $window.initMap = function(){
                    resolve();
                    return;
                };

                var script = document.createElement("script");
                script.type = "text/javascript";
                script.src = "http://api.map.baidu.com/api?v=2.0&ak="+key;
                $document[0].body.appendChild(script);

            });

            return promise;
        },
        hasChild:function(item){
            if(item!=undefined && item!=null){
                if(item.children!=undefined && item.children.length>0 ){
                    return true;
                }
            }
            return false;
        },
        submit:function (submitUrl,submitData) {
            var deferred = $q.defer();
            $http({
                method: 'POST',
                url: submitUrl,
                data: submitData
            }).then(function successCallback(response) {
                if(response.data.code==100) {
                    deferred.resolve(true);
                }else{
                    deferred.resolve(false);
                    SweetAlert.swal("数据提交异常！", response.data.message, "error");
                }
            }, function errorCallback(response) {
                deferred.resolve(false);
                // 请求失败执行代码
                SweetAlert.swal("服务器异常！", "", "error");
            });
            return deferred.promise;
        },
        remove:function(url,id){
            var deferred = $q.defer();
            SweetAlert.swal({
                    title: "确定要删除?",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    closeOnConfirm: false,
                    closeOnCancel: true },
                function(isConfirm){
                    if (isConfirm) {
                        // 简单的 post 请求，可以改为 GET
                        $http({
                            method: 'POST',
                            url: url,
                            data:{id:id}
                        }).then(function successCallback(response) {
                            // 请求成功执行代码
                            if(response.data.code==100){
                                SweetAlert.swal("Deleted", response.data.message, "success");
                                deferred.resolve(true);
                            }else{
                                SweetAlert.swal("Cancelled",response.data.message, "error");
                            }
                        }, function errorCallback(response) {
                            deferred.resolve(true);
                            // 请求失败执行代码
                            SweetAlert.swal("Cancelled", response.data.message, "error");
                        });
                    }
                });
            return deferred.promise;
        },
        grid:function (searchUrl,searchItem) {
            var deferred = $q.defer();
            // 简单的 post 请求，可以改为 GET
            $http({
                method: 'POST',
                url: searchUrl,
                data:searchItem
            }).then(function successCallback(response) {
                // 请求成功执行代码
                if(response.data.code==100){
                    deferred.resolve(response.data.data);
                }else{
                    deferred.resolve({rows:[],total:0});
                }
            }, function errorCallback(response) {
                deferred.resolve({rows:[],total:0});
                SweetAlert.swal("查询出错!", "服务器异常！", "error");
            });
            return deferred.promise;
        },
        info:function (infoUrl) {
            var deferred = $q.defer();
            // 简单的 post 请求，可以改为 GET
            $http({
                method: 'GET',
                url: infoUrl
            }).then(function successCallback(response) {
                // 请求成功执行代码
                if(response.data.code==100){
                    deferred.resolve(response.data);
                }else{
                    deferred.resolve({code:400});
                    SweetAlert.swal("查询出错!",response.data.message, "error");
                }
            }, function errorCallback(response) {
                deferred.resolve({code:400});
                // 请求失败执行代码
                SweetAlert.swal("查询出错!", "服务器异常！", "error");
            });
            return deferred.promise;
        },
        get:function (infoUrl) {
            var deferred = $q.defer();
            // 简单的 post 请求，可以改为 GET
            $http({
                method: 'GET',
                url: infoUrl
            }).then(function successCallback(response) {
                // 请求成功执行代码
                if(response.data.code==100){
                    deferred.resolve(response.data);
                }else{
                    deferred.resolve({code:400});
                    SweetAlert.swal("查询出错!",response.data.message, "error");
                }
            }, function errorCallback(response) {
                deferred.resolve({code:400});
                // 请求失败执行代码
                SweetAlert.swal("查询出错!", "服务器异常！", "error");
            });
            return deferred.promise;
        },
        getAreaInfo:function(level,areaCode){
            var deferred = $q.defer();
            $http({
                method: 'POST',
                url: '/basic/area/queryArea',
                data:{
                    areaLevel: level,
                    parentAreaCode : areaCode
                }
            }).then(function successCallback(response) {
                // 请求成功执行代码
                if(response.data.code==100){
                    deferred.resolve(response.data.data);
                }else{
                    deferred.resolve([]);
                    SweetAlert.swal("获取地址信息失败!", response.data.message, "error");
                }
            }, function errorCallback(response) {
                deferred.resolve([]);
                // 请求失败执行代码
                SweetAlert.swal("获取地址信息失败!", "服务器异常！", "error");

            });
            return deferred.promise;
        },
        uploadFile:function(url,file){
            var deferred = $q.defer();

            if(file!=null && String(file) !== '[object File]'){
                SweetAlert.swal("上传文件错误", "请检查你上传是不是文件！", "error");
                deferred.resolve('error');
            }
            var fd=new FormData();
            fd.append('file',file);
            $http({
                method: 'POST',
                url: url,
                data:fd,
                headers: { 'Content-Type': undefined},
                //prevents serializing payload.  don't do it.
                transformRequest: angular.identity
            }).then(function successCallback(response) {
                var filePath='nothing';
                if(response.data.code==100) {
                    filePath=response.data.data;
                }else{
                    filePath='error';
                    SweetAlert.swal("图片上传出现错误！", '原因'+response.data.message, "error");
                }
                deferred.resolve(filePath);
            }, function errorCallback(response) {
                //console.info(angular.toJson(response));
                // 请求失败执行代码
                SweetAlert.swal("图片上传异常！", "", "error");
                deferred.resolve('error');
            });
            return deferred.promise;
        }
    }
  })
