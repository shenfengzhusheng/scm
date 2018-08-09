app.config(['$stateProvider',"$locationProvider","$urlRouterProvider",
    function($stateProvider,$locationProvider, $urlRouterProvider) {
        // Use $urlRouterProvider to configure any redirects (when) and invalid urls (otherwise).
        $urlRouterProvider
            .when('/dashboard', '/')
            .otherwise('/');
        $locationProvider.hashPrefix('');

        console.info('begin ui-router!');

        var dashboard={
            name: 'dashboard',
            url: '/',
            templateUrl: 'static/views/common/dashboard.html'
        }

        //用户中心
        var account={
            name:'account',
            url:"/user/account",
            templateUrl: "static/views/business/user/account/account.jsp",
            controller: "accountCtrl",
            title: '用户管理'
        }

        var driverAccount={
            name:'driverAccount',
            url:'/user/driver/:id',
            templateUrl: "static/views/business/user/driver/driver/driver.jsp",
            controller: "driverCtrl",
            title: '司机资料'
        }
        var shipperAccount={
            name:'shipperAccount',
            url:'/user/shipper/:id',
            templateUrl: "static/views/business/user/shipper/shipper.jsp",
            controller: "shipperCtrl",
            title: '货主资料'
        }
        var shipperContact={
            name:'shipperAccount.contact',
            url:'/conatct',
            views:{
                "contact":{
                    templateUrl: "static/views/business/user/shipper/contact/contact.jsp",
                    controller: "shipperContactCtrl",
                    params:{"id":null}
                }
            },
            //进入页面触发的方法
            onEnter: function () {
                console.info('on onEnter');
            },
            //离开页面触发的方法
            onExit: function () {
                console.info('on onExit');
            }
        }

        var shipperFamiliar={
            name:'shipperAccount.familiar',
            url:'/familiar',
            views:{
                "familiar":{
                    templateUrl: "static/views/business/user/shipper/familiar/familiar.jsp",
                    controller: "familiarCtrl",
                    params:{"id":null}
                }
            },
            //进入页面触发的方法
            onEnter: function () {
                console.info('on onEnter');
            },
            //离开页面触发的方法
            onExit: function () {
                console.info('on onExit');
            }
        }

        var shipperArea={
            name:'shipperAccount.area',
            url:'/contact',
            templateUrl: "static/views/basic/area/area.html",
            controller: "areaCtrl",
            //进入页面触发的方法
            onEnter: function () {
                console.info('on onEnter');
            },
            //离开页面触发的方法
            onExit: function () {
                console.info('on onExit');
            }
        }

        var bill={
            name:'bill',
            url:'/basic/bill',
            templateUrl: "static/views/business/basic/bill/bill.jsp",
            controller: "billCtrl"
        }

        var area={
            name:'area',
            url:'/basic/areacode',
            templateUrl: "static/views/basic/area/area.html",
            controller: "areaCtrl",
            title: '地址信息'
        }
        var bdArea={
            name:'bdArea',
            url:'/basic/area',
            templateUrl: "static/views/business/basic/area/area.jsp",
            controller: "bdAreaCtrl",
            title: '地址信息'
        }

        var dict={
            name:'dict',
            url:'/basic/dict',
            templateUrl: "static/views/basic/dict/dict.jsp",
            controller: "dictCtrl",
            title: '字典管理'
        }

        var map={
            name:'map',
            url:'/sys/map',
            templateUrl: "static/views/sys/map/map.jsp",
            controller: "mapCtrl",
            title: '地图DEMO'
        }

        var organization={
            name:'organization',
            url:'/sys/organization',
            templateUrl: "static/views/sys/organization/organization.jsp",
            controller: "organizationCtrl",
            title: '机管管理'
        }
        var druidWeb={
            name:'druidWeb',
            url:'/sys/druid',
            templateUrl: "static/views/sys/druid/druid.jsp",
            controller: "druidCtrl",
            title: 'Awesome icons'
        }

        var order={
            name:"order",
            url:"/busi/order",
            templateUrl: "static/views/business/order/order/order.jsp",
            controller: "orderCtrl",
            title: '订单列表'
        }
        var addOrder={
            name:"addOrder",
            url:"/busi/order/add",
            templateUrl: "static/views/business/order/order/orderAdd.jsp",
            controller: "orderAddCtrl",
            title: '熟车竞标订单'
        }




        var simpleTable={
            name:"simpleTable",
            url:"/goods/simple",
            templateUrl: "static/views/ui/table/tables.jsp",
            controller: "mainCtrl",
            title: '简单表格'
        }
        var advancedTable={
            name:"advancedTable",
            url:"/goods/tables-advanced",
            templateUrl: "static/views/ui/table/tables-advanced.html",
            controller: "mainCtrl",
            title: 'Advanced tables'
        }
        var itemTable={
            name:'itemTable',
            url:'/goods/item',
            templateUrl: "static/views/business/goods/item/item.jsp",
            controller: "itemCtrl",
            title: '商品'
        }
        var userTable={
            name:'userTable',
            url:'/goods/user',
            templateUrl: "static/views/ui/table/user.html",
            controller: "mainCtrl",
            title: 'user'
        }
        var fooTables={
            name:'fooTables',
            url:'/goods/footables',
            templateUrl: "static/views/ui/table/tables-footables.html",
            controller: "mainCtrl",
            title: 'FooTables'
        }

        //系统管理
        var sysUser={
            name:'sysUser',
            url:'/sys/user',
            templateUrl: "static/views/sys/user/user.jsp",
            controller: "sysUserCtrl",
            title: '用户管理'
        }

        //支付帐号
        var payAccount={
            name:'payAccount',
            url:'/crm/payaccount',
            templateUrl: "static/views/sys/pay/account/account.jsp",
            controller: "payAccountCtrl",
            title: '支付帐号'
        }
        //付款凭证
        var payRecord={
            name:'payRecord',
            url:'/pay/record',
            templateUrl: "static/views/sys/pay/record/payRecord.jsp",
            controller: "payRecordCtrl",
            title: '支付凭证'
        }
        var payInvoice={
            name:'payInvoice',
            url:'/pay/invoice',
            templateUrl: "static/views/sys/pay/record/invoice.jsp",
            controller: "payInvoiceCtrl",
            params:{"id":null},
            //进入页面触发的方法
            onEnter: function () {
              //  console.info('on onEnter');
            },
            //离开页面触发的方法
            onExit: function () {
                //console.info('on onExit');
            }
        }


        var sysRole={
            name:'sysRole',
            url:'/sys/role',
            templateUrl: "static/views/sys/role/role.jsp",
            controller: "sysRoleCtrl",
            title: '角色管理'
        }

        var sysResources={
            name:'sysResources',
            url:'/sys/resources',
            templateUrl: "static/views/sys/resources/resources.jsp",
            controller: "sysResourcesCtrl",
            title: '资源管理'
        }

        var uiTree={
            name:'uiTree',
            url:'/sys/tree',
            templateUrl:'static/views/sys/tree/tree.jsp',
            controller:'treeCtrl',
            title:'树型UI'
        }

        var authorityTree={
            name:'authorityTree',
            url:'/sys/authority',
            templateUrl:'static/views/sys/authority/authority.jsp',
            controller:'authorityCtrl',
            title:'权限资源'
        }
        var webSocket={
            name:'webSocket',
            url:'/sys/socket',
            templateUrl:'static/views/sys/socket/socket.jsp',
            controller:'sysSocketCtrl',
            title:'webSocket'
        }
        var tcpConnect={
            name:'tcpConnect',
            url:'/sys/tcp',
            templateUrl:'static/views/sys/tcp/tcp.jsp',
            controller:'tcpCtrl',
            title:'TCP连接'
        }
        var iconWeb={
            name:'iconWeb',
            url:'/ui-kit/icons/icons-awesome',
            templateUrl: "static/views/ui/ui kit/icons-awesome.jsp",
            controller: "mainCtrl",
            title: 'Awesome icons'
        }


        var userProfile={
            name:'userProfile',
            url:'/pages/user-profile',
            templateUrl: "static/views/common/user-profile.jsp",
            controller: "mainCtrl",
            title: '用户资料'
        }
        var invoice= {
            name:'invoice',
            url:'/pages/invoice',
            templateUrl: "static/views/ui/page/invoice.jsp",
            controller: "mainCtrl",
            title: 'Invoice'
        };
        var teamMembers={
            name:'teamMembers',
            url:'/pages/team-members',
            templateUrl: "static/views/ui/page/team-members.jsp",
            controller: "mainCtrl",
            title: '成员'
        }
        var widgets={
            name:'widgets',
            url:'/widgets',
            templateUrl: "static/views/ui/ui kit/widgets.jsp",
            controller: "easyChartCtrl",
            title: 'Widgets'
        }

        var projects={
            name:'projects',
            url:'/pages/projects',
            templateUrl: "static/views/ui/page/projects.jsp",
            controller: "mainCtrl",
            title: 'project'
        }

        var timeline={
            name:'timeline',
            url:'/pages/timeline',
            templateUrl: "static/views/ui/page/timeline.jsp",
            controller: "mainCtrl",
            title: 'timeline'
        }
        var timelineGrid={
            name:'timelineGrid',
            url:'/pages/timeline-grid',
            templateUrl: "static/views/ui/page/timeline-grid.jsp",
            controller: "mainCtrl",
            title: 'timeline'
        }

        var formElements={
            name:'formElements',
            url:'/forms/form-elements',
            templateUrl: "static/views/ui/form/form-elements.jsp",
            controller: "mainCtrl",
            title: 'formElements'
        }
        var wizard={
            name:'wizard',
            url:"/forms/wizard",
            templateUrl: "static/views/ui/form/wizard/form-wizard.html",
            controller: "mainCtrl",
            title: 'Wizard'
        }
        var wizardPopup={
            name:'wizardPopup',
            url:"/forms/form-wizard-popup",
            templateUrl: "static/views/ui/form/wizard/form-wizard-popup.jsp",
            controller: "mainCtrl",
            title: 'Wizard '
        }
        var uiElements={
            name:'uiElements',
            url:'/ui-kit/ui-elements',
            templateUrl: "static/views/ui/ui-elements.html",
            controller: "mainCtrl",
            title: 'UI elements'
        }
        var modals={
            name:'modals',
            url:'/ui-kit/modals',
            templateUrl: "static/views/ui/ui kit/modals.jsp",
            controller: "mainCtrl",
            title: 'Modals'
        }
        var error_404={
            name:'error_404',
            url:'/extra/error-404-v2',
            templateUrl:'static/views/common/error-404-v2.jsp',
            controller: "mainCtrl",
            title: 'error_404'
        }

//-------------------------------------------crm begin-----------------------------------------------
        var company={
            name:'company',
            url:'/crm/company',
            templateUrl: "static/views/business/crm/company/company.jsp",
            controller: "companyCtrl",
            title: '业务主体'
        }
       var customer={
            name:'customer',
            url:'/crm/customer',
            templateUrl: "static/views/business/crm/customer/customer.jsp",
            controller: "customerCtrl",
            title: '客户资料'
        }

//-------------------------------------------crm end-----------------------------------------------

//-------------------------------------------device begin-----------------------------------------------
        var device={
            name:'device',
            url:'/device/device',
            templateUrl: "static/views/business/device/device/device.jsp",
            controller: "deviceCtrl",
            title: '设备资料'
        }
        var deviceStatus={
            name:'deviceStatus',
            url:'/device/status',
            templateUrl: "static/views/business/device/status/deviceStatus.jsp",
            controller: "deviceStatusCtrl",
            title: '设备状态'
        }


//-------------------------------------------device end-----------------------------------------------

        $stateProvider
            .state(dashboard)
            .state(account)
            .state(shipperAccount)
            .state(shipperContact)
            .state(shipperFamiliar)
            .state(shipperArea)
            .state(driverAccount)
            .state(bill)
            .state(area)
            .state(bdArea)
            .state(dict)
            .state(organization)
            .state(order)
            .state(addOrder)
            .state(simpleTable)
            .state(advancedTable)
            .state(itemTable)
            .state(userTable)
            .state(fooTables)
            .state(sysUser)
            .state(payAccount)
            .state(payRecord)
          //  .state(payInvoice)
            .state(sysRole)
            .state(sysResources)
            .state(authorityTree)
            .state(userProfile)
            .state(invoice)
            .state(teamMembers)
            .state(druidWeb)
            .state(uiTree)
            .state(webSocket)
            .state(iconWeb)
            .state(tcpConnect)
            .state(map)
            .state(widgets)
            .state(projects)
            .state(timeline)
            .state(timelineGrid)
            .state(formElements)
            .state(wizard)
            .state(wizardPopup)
            .state(uiElements)
            .state(modals)
            .state(customer)
            .state(company)
            .state(device)
            .state(deviceStatus)
            .state(error_404)
        console.info('end state!');
    }]);
