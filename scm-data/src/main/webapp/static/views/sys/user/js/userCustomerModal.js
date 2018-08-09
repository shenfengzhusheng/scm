var userCustomerModalCtrl=function($uibModalInstance,$timeout,commonService,data){
    var modal = this;
    modal.step = 0;
    modal.data=data;
    modal.customers=[];
    modal.selectedItem=[];
    modal.dismiss = function(reason) {
        $uibModalInstance.dismiss(reason);
    };
    modal.close=function () {
        $uibModalInstance.dismiss('no operator!');
    }


    modal.grantRole=function () {
        var selecteds=[];
        var nodes=  $('#customer').treegrid('getCheckedNodes');
        for(var i=0;i<nodes.length;i++){
            selecteds.push(nodes[i].custId);
        }

        commonService.submit("/rest/crm/user-cust/grant.do",{userId:modal.data.userId,custIds:selecteds}).then(function (data) {
            if(data){
                $uibModalInstance.close(true);
            }
        });
    }
    modal.initCustomer=function(){
        $('#customer').treegrid({
            url:'/rest/crm/customer/treeGrid.do',
            rownumbers: true,
            animate:true,
            idField:'custId',
            treeField:'custName',
            method:'post',
            checkbox: true,
            cascadeCheck : false,
            formatter : function(node) {
                return node.text;
            },
            columns:[[
                {field:'custName',title:'客户名称',width:370},
                {field:'linkman',title:'联系人',width:60},
                {field:'tel',title:'电话',width:120}
            ]],
            onBeforeLoad:function (row,param) {
                // parent.$.messager.progress({
                //     text : '数据加载中....'
                // });
            },
            onLoadSuccess:function (node, data) {
                console.info('onLoadSuccess'+node);
                var url=xfs.contextPath + '/rest/crm/user-cust/getUserCustomers.do/';
                url+=modal.data.userId;
                $.get(url,
                    function(result) {
                        if(result.code==100){
                            var rows=result.data;
                            for (var i = 0; i <rows.length; i++) {
                                $('#customer').treegrid('checkNode',rows[i].custId);
                            }
                        }
                        parent.$.messager.progress('close');

                    }
                ,'json');
            }
        });
    }
    $timeout(function () {
        modal.initCustomer();
    },50);
}
