function bsNavbar($window, $location) {
    var defaults = this.defaults = {
        activeClass: 'active',
        routeAttr: 'data-match-route',
        strict: true
    };
    return {
        restrict: 'A',
        link: function postLink(scope, element, attr, controller) {
            var options = angular.copy(defaults);
            angular.forEach(Object.keys(defaults),
            function(key) {
                if (angular.isDefined(attr[key])) options[key] = attr[key];
            });
            scope.$watch(function() {
                return $location.path();
            },
            function(newValue, oldValue) {
                var liElements = element[0].querySelectorAll('li[' + options.routeAttr + '],li > a[' + options.routeAttr + ']');
                angular.forEach(liElements,
                function(li) {
                    var liElement = angular.element(li);
                    var pattern = liElement.attr(options.routeAttr).replace('/', '\\/');
                    if (options.strict) {
                        pattern = '^' + pattern;
                    }
                    var regexp = new RegExp(pattern, ['i']);
                    if (regexp.test(newValue)) {
                        liElement.addClass(options.activeClass);
                    } else {
                        liElement.removeClass(options.activeClass);
                    }
                });
                var op = $('#sidebar-nav').find('.open:not(.active)');
                op.children('.submenu').slideUp('fast');
                op.removeClass('open');
            });
        }
    };
}
function gd(year, day, month) {
    return new Date(year, month - 1, day).getTime();
}
function showTooltip(x, y, label, data) {
    $('<div id="flot-tooltip">' + '<b>' + label + ': </b><i>' + data + '</i>' + '</div>').css({
        top: y + 5,
        left: x + 20
    }).appendTo("body").fadeIn(200);
}
function showtab() {

    return {
        link: function(scope, element, attrs) {
            element.click(function(e) {
                e.preventDefault();
                $(element).tab('show');
            });
        }
    };
}
function pageDirective(){
    return {
        restrict : "AE",
        template :
            "<div class='pull-right'><label style='float:left;padding: 6px 12px;'>每页 "
                    +"<select name='table-example_length'class='input-sm' ng-model='pageSize'>"
                    +"<option value='1'>1</option>"
                    +"<option value='5'>5</option>"
                    +"<option value='10'>10</option>"
                    +"<option value='25'>25</option>"
                    +"<option value='50'>50</option>"
                    +"<option value='100'>100</option>"
                +"</select>"
                +"行</label>"
                +"有【10000】记录"
                +"<ul class='pagination '>"
                    + "<li><a href=''><i class='fa fa-chevron-left'></i></a></li>"
                    + "<li><a href='' data-ng-click=''>1</a></li>"
                    + "<li><a href=''>2</a></li>"
                    + "<li><a href=''>3</a></li>"
                    + "<li><a href=''>4</a></li>"
                    + "<li><a href=''>5</a></li>"
                    + "<li><a href=''><i class='fa fa-chevron-right'></i></a></li>"
                + "</ul>"
            +"</div>"
    };
}
function messageBox(){
    return {
        restrict: 'EA',
        templateUrl: 'static/core/views/common/messageBox.html',
        scope : {
            message : "=",
            type : "="
        },
        link: function(scope, element, attrs){

            scope.hideAlert = function() {
                scope.message = null;
                scope.type = null;
            };

        }
    };
}

function compileHtml($compile){
    return {
        restrict: 'A',
        replace: true,
        link: function (scope, ele, attrs) {
            scope.$watch(function () {return scope.$eval(attrs.ngBindHtml);},
                function(html) {
                    ele.html(html);
                    $compile(ele.contents())(scope);
                });
        }
    };
}
function linked(){
    return function (scope, element, attrs) {
        var id = attrs["linked"];
        element.on("click",function(){
            document.getElementById(id).click();
        });
    };
}
/*----------------------------------------------------------------------*/

function hello(){
    return {
        restrict:'AE',
        //template:'<div>hello everyone !</div> <div ng-transclude>你看不见我</div>',
        link:function (scope,element,attrs,supermanCtrl) {
            console.log(element);
            element.bind('mouseenter',function () {
                console.info('鼠标进入...');
            });
            element.bind('mouseout',function () {
                console.info('鼠标滑出...');

            });
        }
    }
}

function superman(){
    return{
        scope:{},
        restrict:'AE',
        controller:function ($scope) {
            $scope.abilities =[];
            this.addStrength=function () {
                $scope.abilities.push('strenght');
            };
            this.addSpeed=function () {
                $scope.abilities.push('speed');
            };
            this.addLight=function () {
                $scope.abilities.push('light');
            }
        },
        link:function (scope,element,attrs) {
            element.addClass('btn btn-primary');
            element.bind('mouseenter',function () {
                console.info(scope.abilities);
            })
        }
    }
}

function strength() {
    return{
        require:'^superman',
        link:function(scope,element,attrs,supermanCtrl){
            supermanCtrl.addStrength();
        }
    }
}

function speed() {
    return{
        require:'^superman',
        link:function(scope,element,attrs,supermanCtrl){
            supermanCtrl.addSpeed();
        }
    }
}
function light() {
    return{
        require:'^superman',
        link:function(scope,element,attrs,supermanCtrl){
            supermanCtrl.addLight();
        }
    }
}

function ccd() {
    return{
        restrict: 'AE',
        scope:false,//ture or {}
        template: '<div><input type="text" ng-model="userName"/>{{userName}}</div>',
        replace: true
    }
}
function drink() {
    return{
        restrict:'AE',
        template:'<div>{{flavor}}</div>',
        link:function (scope,element,attrs) {
            scope.flavor=attrs.flavor;
        }
    }
}
function drank() {
    return{
        restrict:'AE',
        scope:{
            cc:'@'
        },
        template:'<div>{{cc}}</div>'

    }
}
function xfs() {
    return{
        restrict:'AE',
        scope:{
            content:'='
        },
        template:'<input type="text" ng-model="content"/>'

    }
}

function greeting() {
    return{
        restrict:'AE',
        scope:{
            greet:'&'
        },
        template:'<input type="text" ng-model="greetName"/> <br/>'+
                '<button class="btn btn-default" data-ng-click="greet({name:greetName})">Greeting</button> <br>'
    }
}

/*----------------------------------------------------------------------*/
// function treeView(){
//    return{
//        restrict:'E',
//        templateUrl: '/treeView.html',
//        scope:{
//            treeData: '=',
//            canChecked: '=',
//            textField: '@',
//            itemClicked: '&',
//            itemCheckedChanged: '&',
//            itemTemplateUrl: '@'
//        },
//        controller:function ($scope) {
//            $scope.itemExpended=function (item,$event) {
//                item.$$isExpend = ! item.$$isExpend;
//                $event.stopPropagation();
//            };
//            $scope.getItemIcon=function (item) {
//                var isLeaf=$scope.isLeaf(item);
//
//                if(isLeaf){
//                    return 'fa fa-leaf';
//                }
//
//                return item.$$isExpand?'fa fa-minus': 'fa fa-plus';
//            };
//            $scope.isLeaf=function(item){
//                return !item.children || !item.children.length;
//            };
//
//            $scope.check=function (callback,item) {
//                var itemId=item.id;
//            }
//
//            $scope.warpCallback=function (callback,item,$event) {
//                ($scope[callback] || angular.noop)({
//                    $item:item,
//                    $event:$event
//                });
//            };
//
//        }
//    }
//
// }

function treeView(treeViewConfig){

    return {
        restrict: 'EA',
        templateUrl: '/treeView.html',
        scope: {
            treeData: '=',
            selectedData:'&',
            canChecked: '=',
            textField: '@',
            iconField: '@',
            itemClicked: '&',
            itemCheckedChanged: '&',
            itemExpended:'&',
            itemTemplateUrl: '@',
            toolbars:'='
        },
        link:function (scope,$scope,element,attrs) {
            $scope.checkedData=[];
            scope.$on('tree-view:expand-all', function () {
                scope.expand(scope.treeData,true);
            });
            scope.$on('tree-view:collapse-all', function () {
                scope.expand(scope.treeData,false);
            });
            scope.$on('tree-view:get-selected', function () {
                scope.slected(scope.treeData);
               // console.info(scope.getSelectedData({data:'data'}));
               scope.selectedData=$scope.checkedData;
                console.info('data:'+angular.toJson(scope.selectedData));
                return $scope.checkedData;
            });

            scope.expand =function(items,expandFlag){
                angular.forEach(items,function (node) {
                    if(scope.hasChild(node)){
                        node.$$isExpend=expandFlag;
                        scope.expand(node.children,expandFlag);
                    }
                })
            }
            scope.hasChild=function(item){
                if(item!=null){
                    if(item.children!=undefined && item.children.length>0 ){
                        return true;
                    }
                }
                return false;
            }
            scope.slected=function (nodes) {
                angular.forEach(nodes,function (node) {
                    if(node.$$isChecked){
                        $scope.checkedData.push(node.id);
                    }
                    if(scope.hasChild(node)){
                        scope.slected(node.children);
                    }
                })
            }
        },
        controller:function($scope){
            $scope.defaultItemTemplateUrl = treeViewConfig.itemTemplateUrl;
            $scope.innerItemExpended = function(item, $parentItem, $event){
                item.$$isExpend = !item.$$isExpend;
               // $event.stopPropagation();
               // $scope.warpCallback('itemExpended',item, $parentItem, $event);
            };
            $scope.expand=function () {
                console.info('driective expand');
            }
            $scope.getItemIcon = function(item){
                var isLeaf = $scope.isLeaf(item);
                if(isLeaf){
                    //return treeViewConfig.itemLeafIcon;
                    return treeViewConfig.itemExpendIcon;
                }
                return item.$$isExpend ? treeViewConfig.itemExpendIcon: treeViewConfig.itemCollapseIcon;
            };

            $scope.isLeaf = function(item){
                return !item.children || !item.children.length;
            };

            //一个子节点选中父节点选中,未级子节点全部不选中递归影响父节点
            $scope.childIsChecked=function (nodes) {
                var selectedCount=0;
                for(var i=0;i<nodes.length;i++){
                    if(!$scope.isLeaf(nodes[i])){
                        var flag=$scope.childIsChecked(nodes[i].children);
                        nodes[i].$$isChecked=flag;
                    }
                    if(nodes[i].$$isChecked){
                        selectedCount++;
                    }
                }
                if(selectedCount>0){
                    return true;
                }
                return false;
            }

            $scope.checkedChanged=function (item) {
                if($scope.hasChild(item)){
                    $scope.checked(item.children,item.$$isChecked);
                }
                //父节点选中状态
                angular.forEach($scope.treeData,function (node) {
                    if(!$scope.isLeaf(node)){
                        var flag=$scope.childIsChecked(node.children);
                        node.$$isChecked=flag;
                    }
                })
                //console.info('checkedChanged'+angular.toJson($scope.treeData));
            }
            $scope.checked =function(nodes,checkedFlag){
                angular.forEach(nodes,function (node) {
                    node.$$isChecked=checkedFlag;
                    if(!$scope.isLeaf(node)){
                        $scope.checked(node.children,checkedFlag);
                    }
                })
            }
            $scope.warpCallback = function(callback, item, $parentItem, $event){
               // console.info('warpCallback:'+callback);
                if(callback=='itemCheckedChanged'){
                    $scope.checkedChanged(item);
                }
                ($scope[callback] || angular.noop)({
                    $item:item,
                    $parentItem:$parentItem,
                    $event:$event
                });
            };
        }
    };
}
function autoHeight ($window) {
    return {
        restrict : 'A',
        scope : {},
        link : function($scope, element, attrs) {
            var winowHeight = $window.innerHeight; //获取窗口高度
            //var headerHeight = 60;
            var height=winowHeight-0;
            element.css('min-height',height + 'px');
        }
    };
}
angular.module('qhtWeb').constant('treeViewConfig', {
    itemExpendIcon : 'fa fa-minus',
    itemCollapseIcon: 'fa fa-plus',
    itemLeafIcon: 'fa fa-leaf',
    itemTemplateUrl: '/treeItem.html'
}).directive('bsNavbar', bsNavbar)
    .directive('showtab', showtab).directive('pageDirective',pageDirective)
    .directive('compileHtml',compileHtml).directive('linked',linked)
    .directive('hello',hello)
    .directive('superman',superman).directive('strength',strength)
    .directive('speed',speed).directive('light',light)
    .directive('ccd',ccd)
    .directive('drink',drink).directive('drank',drank).directive('xfs',xfs)
    .directive('greeting',greeting)
    .directive('treeView',treeView)
    .directive('autoHeight',autoHeight);
