var userModalCtrl=function($scope,$timeout,$uibModalInstance,commonService,SweetAlert,data,type,btnShow){
    var modal = this;
    var addUrl="/sys/user/save.do";
    var modifyUrl="/sys/user/modify.do";
    modal.type=type;
    modal.data=data;
    modal.btnShow=btnShow;
    modal.sexs=[{id:1,text:'男'},{id:2,text:'女'}]
    modal.states=[{id:0,text:'禁用'},{id:1,text:'正常'}];
    modal.dismiss = function(reason) {
        $uibModalInstance.dismiss(reason);
    };
    modal.close=function () {
        $uibModalInstance.dismiss('no operator!');
    }
    modal.submit=function () {
        if(modal.type==1 ){
            var submitUrl=addUrl;
            if(modal.data.userId){
                submitUrl=modifyUrl;
            }
            commonService.submit(submitUrl,modal.data).then(function (data) {
                if(data){
                    $uibModalInstance.close(true);
                }
            });
        }else{
            $uibModalInstance.dismiss('no operator!');
        }
    }

    modal.saveUser=function () {
        var file=$('#upload_input').get(0).files[0];
        if(file!=null &&  String(file) === '[object File]'){
            commonService.uploadFile('/common/file/uploadFile',file).then(function (result) {
                if(result!=undefined && result!='error'){
                    modal.data.headerUrl=result;
                }
                modal.submit();
            });
        }else{
            modal.submit();
        }
    }

    $scope.uploadHeaderImg=function(file){
        return commonService.uploadFile('/common/file/uploadFile',file).then(function (data) {
            console.info('newImg is:'+data);//新图片地址
            $scope.imgPath=data;
            return data;
        });

    }

    $scope.clickImg = function(){
        $('#upload_input').click();
    }
    $scope.changeFile= function(file) {
        //预览
        var pic = $('#preview').attr("width", 159).attr('height', 175);
        //添加按钮
        var addImg = $('#headerImg');
        //删除按钮
        var deleteImg = $("#delete");
        var ext = file.value.substring(file.value.lastIndexOf(".") + 1).toLowerCase();
        // gif在IE浏览器暂时无法显示
        if (ext != 'png' && ext != 'jpg' && ext != 'jpeg') {
            if (ext != '') {
                alert("图片的格式必须为png或者jpg或者jpeg格式！");
            }
            return;
        }
        //判断IE版本
        var isIE = navigator.userAgent.match(/MSIE/) != null,
            isIE6 = navigator.userAgent.match(/MSIE 6.0/) != null;
        isIE10 = navigator.userAgent.match(/MSIE 10.0/) != null;
        if (isIE && !isIE10) {
            file.select();
            var reallocalpath = document.selection.createRange().text;
            modal.data.headerUrl=reallocalpath;

            // IE6浏览器设置img的src为本地路径可以直接显示图片
            if (isIE6) {
                pic.attr("src", reallocalpath);
            } else {
                // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
                pic.css("filter", "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src=\"" + reallocalpath + "\")");
                // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
                pic.attr('src', 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==');
            }
            addImg.hide();
            deleteImg.show();
        } else {
            $scope.html5Reader(file, pic, addImg, deleteImg);
        }
    }
    //H5渲染
    $scope.html5Reader= function (file,pic,addImg,deleteImg){
        var file = file.files[0];
        modal.data.headerUrl=file;
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function(e){
            pic.attr("src",this.result);
        }
        addImg.hide();
        deleteImg.show();
    }
    //删除
    $scope.deleteImg = function(obj){
        $('#headerFile').val('');
        $('#preview').attr("src","").attr("width",0).attr('height',0);
        //IE9以下
        $('#preview').css("filter","").attr("width",0).attr('height',0);
        $(":file").val('');
        $scope.data.headerUrl='static/core/img/header.jpg';
        $('#delete').hide();
        $('#headerImg').show();
    }


    modal.initOids=function(){
        $('#oname').combotreegrid({
            // value:'006',
            width:'180px',
            panelWidth:500,
            //  label:'Select Item:',
            labelPosition:'top',
            url:'/rest/sys/organization/treeGrid.do',
            idField:'oid',
            method:'POST',
            mode : 'remote',
            treeField:'oname',
            columns:[[
                {field:'oname',title:'名称',width:300},
                {field:'address',title:'地址',width:200},
            ]],
            onBeforeLoad:function(param){

                return true;
             },
            onLoadSuccess:function (row, data) {
                console.info('onLoadSuccess');
            },
            onSelect:function(row){
                $('#oid').val(row.oid);

                modal.data.oid=row.oid;
            }
        });

       $('#oids').combotreegrid('setValue', { oid: '24', name: '三水新华雄陶瓷有限公司' });
        // $('#oids').combotreegrid('onLoadSuccess', function (row, data) {
        //     console.info('onLoadSuccess');
        // });

    }
    modal.init=function(){
        modal.initOids();
    }
    $timeout(function () {
        modal.init();

    },50);
}
