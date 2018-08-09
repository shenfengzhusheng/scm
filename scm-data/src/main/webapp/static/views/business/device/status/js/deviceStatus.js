var deviceStatusCtrl=function($scope,$timeout,$uibModal,commonService){
    var app = this;
    var ws;
    var wsStatus=false;
    $scope.content="";
    $('#page-wrapper').removeClass('nav-small');

    $scope.init=function(){
        $scope.initConnect();
        //   $scope.search();
    }

    $scope.initConnect=function(){
        if( typeof window.WebSocket !='undefined'){
            ws = new WebSocket( wsPath+'/socketService');

        }else if( typeof window.MozWebSocket != 'undefined' ){
            ws = new MozSocket(wsPath+'/socketService');
        }else{
            ws = new SockJS(httpPath+'/socketService');
        }
        ws.onopen=function(evt){
            console.log("open!"+evt);
        };
        ws.onmessage=function(evt){
            console.info('evt'+evt.data);
            var obj = $.parseJSON(evt.data);

            angular.element("#messages").prepend($scope.buildMessage(obj));

        };
        ws.onerror=function(evt){
            console.info("socket onerror");

        };
        ws.onclose=function(evt){
            console.info("socket closed");
            //  window.alert('closed');
        };
        window.onunload=function(){
            console.info("socket onunload");

            if( ws != null ){
                ws.close();
                ws = null;
            }
        }

    }

    $scope.buildMessage=function(data){
        var htmlStr="   <div class=\"conversation-item item-left clearfix\">\n" +
            "                <div class=\"conversation-user\">\n" +
            "                    <img src=\""+data.image+"\" alt=\"\">\n" +
            "                </div>\n" +
            "                <div class=\"conversation-body\">\n" +
            "                    <div class=\"name\">\n" +
            data.name+
            "                    </div>\n" +
            "                    <div class=\"time hidden-xs\">\n" +
            data.sendTime +
            "                    </div>\n" +
            "                    <div class=\"text\">\n" +
            data.message+
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>"


        if(data.type>=4){
            htmlStr="     <div class=\"conversation-item item-right clearfix\">\n" +
                "                <div class=\"conversation-user\">\n" +
                "                    <img src='"+data.image+"' alt=\"\">\n" +
                "                </div>\n" +
                "                <div class=\"conversation-body\">\n" +
                "                    <div class=\"name\">\n" +
                data.name+
                "                    </div>\n" +
                "                    <div class=\"time hidden-xs\">\n" +
                data.sendTime+
                "                    </div>\n" +
                "                    <div class=\"text\">\n" +
                data.message +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>";
        }
        return htmlStr;
    }



    $timeout(function () {
        $scope.init();

    },100);
}
