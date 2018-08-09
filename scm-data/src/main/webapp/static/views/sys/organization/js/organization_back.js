var organizationCtrl=function($scope,$rootScope,$http,$compile,$window){
    //  $scope.layerIndex=1;
    $scope.treeOptions = {
        accept: function(sourceNodeScope, destNodesScope, destIndex) {
            //console.info('1111');
            return true;
        },
        beforeDrop:function (event) {
            console.info('beforeDrop');

        },
        toggle:function(collapsed, sourceNodeScope){
            console.info('afterToggle ');

        },
        remove:function () {
            console.info('now is remove');
        },
        removed:function (node) {
            console.info('remove!'+node.id);
        }

    };




    $scope.remove = function (scope) {
        console.info('yes remove');

        scope.remove();
    };

    $scope.toggle = function (scope) {
        console.info('yes toggle');

        scope.toggle();
        console.info('yes toggle');

    };

    $scope.moveLastToTheBeginning = function () {
        console.info('1111');
        var a = $scope.data.pop();
        $scope.data.splice(0, 0, a);
    };

    $scope.newSubItem = function (scope) {
        var nodeData = scope.$modelValue;
        if(nodeData.id<10000){
            if(nodeData.data!=undefined){
                console.info('ndoeData is :'+nodeData.data.name);

            }
            nodeData.nodes.push({
                id: nodeData.id * 10 + nodeData.nodes.length,
                title: nodeData.title + '.' + (nodeData.nodes.length + 1),
                nodes: []
            });
        }
    };

    $scope.collapseAll = function () {
        $scope.$broadcast('angular-ui-tree:collapse-all');
    };

    $scope.expandAll = function () {
        $scope.$broadcast('angular-ui-tree:expand-all');
    };
    $scope.organizationTree =[{
        'id':0,
        'title':'中国',
        'data':{
            'id':50,
            'name':'大米'
        },'nodes': [{
            'id': 1,
            'title': '集团A',
            'nodes': []
        }, {
            'id': 2,
            'title': '集团B',
            'nodes': []
        }, {
            'id': 3,
            'title': '集团C',
            'nodes': []
        }, {
            'id': 4,
            'title': '集团D',
            'nodes': []
        }]
    },{ 'id': 3,
        'title': '企货通',
        'nodes': []
    }];

}