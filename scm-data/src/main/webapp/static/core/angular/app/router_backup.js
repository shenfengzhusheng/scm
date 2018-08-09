app.config(['$routeProvider',"$locationProvider",
    function($routeProvider,$locationProvider) {
        console.info('in router!');

        $locationProvider.hashPrefix('');

        $routeProvider.when("/", {
            redirectTo: 'static/views/common/dashboard.html'
        }).when("view/dasboard", {
            templateUrl: "static/views/common/dashboard.html",
            controller: "mainCtrl",
            title: 'Dashboard'
        }).when("/goods/simple", {
            templateUrl: "static/views/ui/table/tables.jsp",
            controller: "mainCtrl",
            title: '简单表格'
        }).when("/goods/tables-advanced", {
            templateUrl: "static/views/ui/table/tables-advanced.html",
            controller: "mainCtrl",
            title: 'Advanced tables'
        }).when("/busi/order", {
            templateUrl: "static/views/business/order/order/order.jsp",
            controller:'orderCtrl',
            title: '订单列表'
        }).when("/busi/order/add", {
            templateUrl: "static/views/business/order/order/orderAdd.jsp",
            controller: "orderAddCtrl",
            title: '订单列表'
        }).when("/goods/item", {
            templateUrl: "static/views/business/goods/item/item.html",
            controller: "itemCtrl",
            title: '商品'
        }).when("/goods/user", {
            templateUrl: "static/views/ui/table/user.html",
            controller: "mainCtrl",
            title: 'user'
        }).when("/goods/footables", {
            templateUrl: "static/views/ui/table/tables-footables.html",
            controller: "mainCtrl",
            title: 'FooTables'
        }).when("/graphs/graphs-xcharts", {
            templateUrl: "views/graphs-xcharts.html",
            controller: "mainCtrl",
            title: 'XCharts'
        }).when("/graphs/graphs-morris", {
            templateUrl: "views/graphs-morris.html",
            controller: "mainCtrl",
            title: 'Morris charts'
        }).when("/graphs/graphs-flot", {
            templateUrl: "views/graphs-flot.html",
            controller: "mainCtrl",
            title: 'Flot charts'
        }).when("/graphs/graphs-dygraphs", {
            templateUrl: "views/graphs-dygraphs.html",
            controller: "mainCtrl",
            title: 'Dygraphs'
        }).when("/email/email-compose", {
            templateUrl: "static/views/ui/email/email-compose.html",
            controller: "emailCtrl",
            title: 'Email compose'
        }).when("/email/email-inbox", {
            templateUrl: "static/views/ui/email/email-inbox.html",
            controller: "emailCtrl",
            title: 'Email inbox'
        }).when("/email/email-detail", {
            templateUrl: "static/views/ui/email/email-detail.html",
            controller: "emailCtrl",
            title: 'Email detail'
        }).when("/widgets", {
            templateUrl: "static/views/widgets.html",
            controller: "mainCtrl",
            title: 'Widgets'
        }).when("/pages/user-profile", {
            templateUrl: "static/views/common/user-profile.html",
            controller: "mainCtrl",
            title: 'User profile'
        }).when("/pages/calendar", {
            templateUrl: "views/calendar.html",
            controller: "mainCtrl",
            title: 'Calendar'
        }).when("/pages/timeline", {
            templateUrl: "static/views/timeline.html",
            controller: "mainCtrl",
            title: 'Timeline'
        }).when("/pages/timeline-grid", {
            templateUrl: "views/timeline-grid.html",
            controller: "mainCtrl",
            title: 'Timeline grid'
        }).when("/pages/team-members", {
            templateUrl: "views/team-members.html",
            controller: "mainCtrl",
            title: 'Team members'
        }).when("/pages/search", {
            templateUrl: "views/search.html",
            controller: "mainCtrl",
            title: 'Search results'
        }).when("/pages/projects", {
            templateUrl: "views/projects.html",
            controller: "mainCtrl",
            title: 'Projects'
        }).when("/pages/pricing", {
            templateUrl: "views/pricing.html",
            controller: "mainCtrl",
            title: 'Pricing'
        }).when("/pages/invoice", {
            templateUrl: "static/views/invoice.html",
            controller: "mainCtrl",
            title: 'Invoice'
        }).when("/pages/intro", {
            templateUrl: "static/views/intro.html",
            controller: "mainCtrl",
            title: 'Tour layout'
        }).when("/pages/gallery", {
            templateUrl: "views/gallery.html",
            controller: "mainCtrl",
            title: 'Gallery'
        }).when("/pages/gallery-v2", {
            templateUrl: "views/gallery-v2.html",
            controller: "mainCtrl",
            title: 'Gallery v2'
        }).when("/forms/x-editable", {
            templateUrl: "static/views/ui/x-editable.html",
            controller: "mainCtrl",
            title: 'X-Editable'
        }).when("/forms/form-elements", {
            templateUrl: "static/views/ui/form/form-elements.html",
            controller: "mainCtrl",
            title: 'Form elements'
        }).when("/forms/form-ckeditor", {
            templateUrl: "views/form-ckeditor.html",
            controller: "mainCtrl",
            title: 'Ckeditor'
        }).when("/forms/form-wysiwyg", {
            templateUrl: "views/form-wysiwyg.html",
            controller: "mainCtrl",
            title: 'Wysiwyg'
        }).when("/forms/wizard", {
            templateUrl: "static/views/ui/form/wizard/form-wizard.html",
            controller: "mainCtrl",
            title: 'Wizard'
        }).when("/forms/form-wizard-popup", {
            templateUrl: "static/views/ui/form/wizard/form-wizard-popup.html",
            controller: "mainCtrl",
            title: 'Wizard popup'
        }).when("/forms/form-dropzone", {
            templateUrl: "views/form-dropzone.html",
            controller: "mainCtrl",
            title: 'Dropzone'
        }).when("/forms/form-summernote", {
            templateUrl: "views/form-summernote.html",
            controller: "mainCtrl",
            title: 'Wysiwyg Summernote'
        }).when("/ui-kit/ui-elements", {
            templateUrl: "static/views/ui/ui-elements.html",
            controller: "mainCtrl",
            title: 'UI elements'
        }).when("/ui-kit/ui-nestable", {
            templateUrl: "static/views/ui/ui kit/ui-nestable.html",
            controller: "mainCtrl",
            title: 'UI nestable'
        }).when("/ui-kit/video", {
            templateUrl: "views/video.html",
            controller: "mainCtrl",
            title: 'Video'
        }).when("/ui-kit/typography", {
            templateUrl: "views/typography.html",
            controller: "mainCtrl",
            title: 'Typography'
        }).when("/ui-kit/notifications", {
            templateUrl: "static/views/ui/notifications.html",
            controller: "mainCtrl",
            title: 'Notifications and Alerts'
        }).when("/ui-kit/modals", {
            templateUrl: "static/views/ui/modals.html",
            controller: "mainCtrl",
            title: 'Modals'
        }).when("/ui-kit/icons/icons-awesome", {
            templateUrl: "views/icons-awesome.html",
            controller: "mainCtrl",
            title: 'Awesome icons'
        }).when("/ui-kit/icons/icons-halflings", {
            templateUrl: "views/icons-halflings.html",
            controller: "mainCtrl",
            title: 'Halflings icons'
        }).when("/google-maps", {
            templateUrl: "views/maps.html",
            controller: "mainCtrl",
            title: 'Google maps'
        }).when("/extra/faq", {
            templateUrl: "views/faq.html",
            controller: "mainCtrl",
            title: 'FAQ'
        }).when("/extra/extra-grid", {
            templateUrl: "views/extra-grid.html",
            controller: "mainCtrl",
            title: 'Extra grid'
        }).when("/user/account", {
            templateUrl: "static/views/business/user/account/account.jsp",
            controller: "accountCtrl",
            title: '用户管理'
        }).when("/user/driver/:id", {
            templateUrl: "static/views/business/user/driver/driver.jsp",
            controller: "driverCtrl",
            title: '司机资料'
        }).when("/user/shipper/:id", {
            templateUrl: "static/views/business/user/shipper/shipper.jsp",
            controller: "shipperCtrl",
            title: '货主资料'
        }).when("/basic/areacode", {
            templateUrl: "static/views/basic/areacode/areacode.html",
            controller: "areaCtrl",
            title: '地址信息'
        }).when("/basic/dict", {
            templateUrl: "static/views/basic/dict/dict.html",
            controller: "dictCtrl",
            title: '字典管理'
        }).when("/basic/organization", {
            templateUrl: "static/views/basic/organization/organization.jsp",
            controller: "organizationCtrl",
            title: '机管管理'
        }).when("/extra/email-templates", {
            templateUrl: "views/emails.html",
            controller: "mainCtrl",
            title: 'Email templates'
        }).when("/error-404-v2", {
            templateUrl: "static/views/common/dashboard.html",
            controller: "mainCtrl",
            title: 'Error 404'
        }).when("/error-404", {
            templateUrl: "static/views/common/dashboard.html",
            controller: "mainCtrl",
            title: 'Error 404'
        }).otherwise({
            redirectTo: '/dash',
            controller: 'mainCtrl',
            templateUrl: "static/views/common/dashboard.html"

        });
    }]);
