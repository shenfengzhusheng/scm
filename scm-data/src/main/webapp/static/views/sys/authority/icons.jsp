<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="iconModal" tabindex="-2" role="dialog" aria-labelledby="iconModalLabel" aria-hidden="true" style="width: 97%">
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-ng-click="closeIcon()"   aria-hidden="true">&times;</button>
                <h4 class="modal-title">{{title}}</h4>
            </div>
            <div class="modal-body" style="height: 400px;">

               <!--
                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2>41 New Icons in 4.7</h2>
                            </header>
                            <div class="main-box-body clearfix">
                                <div class="row the-icons">
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-address-book" aria-hidden="true"></i> address-book</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-address-book-o" aria-hidden="true"></i> address-book-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-address-card" aria-hidden="true"></i> address-card</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-address-card-o" aria-hidden="true"></i> address-card-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bandcamp" aria-hidden="true"></i> bandcamp</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bath" aria-hidden="true"></i> bath</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bathtub" aria-hidden="true"></i> bathtub <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-drivers-license" aria-hidden="true"></i> drivers-license <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-drivers-license-o" aria-hidden="true"></i> drivers-license-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-eercast" aria-hidden="true"></i> eercast</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-envelope-open" aria-hidden="true"></i> envelope-open</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-envelope-open-o" aria-hidden="true"></i> envelope-open-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-etsy" aria-hidden="true"></i> etsy</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-free-code-camp" aria-hidden="true"></i> free-code-camp</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-grav" aria-hidden="true"></i> grav</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-handshake-o" aria-hidden="true"></i> handshake-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-id-badge" aria-hidden="true"></i> id-badge</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-id-card" aria-hidden="true"></i> id-card</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-id-card-o" aria-hidden="true"></i> id-card-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-imdb" aria-hidden="true"></i> imdb</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-linode" aria-hidden="true"></i> linode</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-meetup" aria-hidden="true"></i> meetup</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-microchip" aria-hidden="true"></i> microchip</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-podcast" aria-hidden="true"></i> podcast</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-quora" aria-hidden="true"></i> quora</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-ravelry" aria-hidden="true"></i> ravelry</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-s15" aria-hidden="true"></i> s15 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-shower" aria-hidden="true"></i> shower</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-snowflake-o" aria-hidden="true"></i> snowflake-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-superpowers" aria-hidden="true"></i> superpowers</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-telegram" aria-hidden="true"></i> telegram</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer" aria-hidden="true"></i> thermometer <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-0" aria-hidden="true"></i> thermometer-0 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-1" aria-hidden="true"></i> thermometer-1 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-2" aria-hidden="true"></i> thermometer-2 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-3" aria-hidden="true"></i> thermometer-3 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-4" aria-hidden="true"></i> thermometer-4 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-empty" aria-hidden="true"></i> thermometer-empty</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-full" aria-hidden="true"></i> thermometer-full</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-half" aria-hidden="true"></i> thermometer-half</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-quarter" aria-hidden="true"></i> thermometer-quarter</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-three-quarters" aria-hidden="true"></i> thermometer-three-quarters</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-times-rectangle" aria-hidden="true"></i> times-rectangle <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-times-rectangle-o" aria-hidden="true"></i> times-rectangle-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-user-circle" aria-hidden="true"></i> user-circle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-user-circle-o" aria-hidden="true"></i> user-circle-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-user-o" aria-hidden="true"></i> user-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-vcard" aria-hidden="true"></i> vcard <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-vcard-o" aria-hidden="true"></i> vcard-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-window-close" aria-hidden="true"></i> window-close</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-window-close-o" aria-hidden="true"></i> window-close-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-window-maximize" aria-hidden="true"></i> window-maximize</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-window-minimize" aria-hidden="true"></i> window-minimize</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-window-restore" aria-hidden="true"></i> window-restore</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-wpexplorer" aria-hidden="true"></i> wpexplorer</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2>Web Application Icons</h2>
                            </header>
                            <div class="main-box-body clearfix">
                                <div class="row the-icons">
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-address-book" aria-hidden="true" data-ng-click="selected('address-book')"></i></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-address-book-o" aria-hidden="true" data-ng-click="selected('address-book-o')"></i> </div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-address-card" aria-hidden="true"></i> address-card</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-address-card-o" aria-hidden="true"></i> address-card-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-adjust" aria-hidden="true"></i> adjust</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-american-sign-language-interpreting" aria-hidden="true"></i> american-sign-language-interpreting</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-anchor" aria-hidden="true"></i> anchor</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-archive" aria-hidden="true"></i> archive</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-areacode-chart" aria-hidden="true"></i> areacode-chart</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrows" aria-hidden="true"></i> arrows</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrows-h" aria-hidden="true"></i> arrows-h</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrows-v" aria-hidden="true"></i> arrows-v</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-asl-interpreting" aria-hidden="true"></i> asl-interpreting <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-assistive-listening-systems" aria-hidden="true"></i> assistive-listening-systems</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-asterisk" aria-hidden="true"></i> asterisk</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-at" aria-hidden="true"></i> at</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-audio-description" aria-hidden="true"></i> audio-description</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-automobile" aria-hidden="true"></i> automobile <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-balance-scale" aria-hidden="true"></i> balance-scale</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-ban" aria-hidden="true"></i> ban</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bank" aria-hidden="true"></i> bank <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bar-chart" aria-hidden="true"></i> bar-chart</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bar-chart-o" aria-hidden="true"></i> bar-chart-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-barcode" aria-hidden="true"></i> barcode</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bars" aria-hidden="true"></i> bars</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bath" aria-hidden="true"></i> bath</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bathtub" aria-hidden="true"></i> bathtub <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-battery" aria-hidden="true"></i> battery <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-battery-0" aria-hidden="true"></i> battery-0 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-battery-1" aria-hidden="true"></i> battery-1 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-battery-2" aria-hidden="true"></i> battery-2 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-battery-3" aria-hidden="true"></i> battery-3 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-battery-4" aria-hidden="true"></i> battery-4 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-battery-empty" aria-hidden="true"></i> battery-empty</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-battery-full" aria-hidden="true"></i> battery-full</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-battery-half" aria-hidden="true"></i> battery-half</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-battery-quarter" aria-hidden="true"></i> battery-quarter</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-battery-three-quarters" aria-hidden="true"></i> battery-three-quarters</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bed" aria-hidden="true"></i> bed</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-beer" aria-hidden="true"></i> beer</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bell" aria-hidden="true"></i> bell</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bell-o" aria-hidden="true"></i> bell-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bell-slash" aria-hidden="true"></i> bell-slash</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bell-slash-o" aria-hidden="true"></i> bell-slash-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bicycle" aria-hidden="true"></i> bicycle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-binoculars" aria-hidden="true"></i> binoculars</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-birthday-cake" aria-hidden="true"></i> birthday-cake</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-blind" aria-hidden="true"></i> blind</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bluetooth" aria-hidden="true"></i> bluetooth</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bluetooth-b" aria-hidden="true"></i> bluetooth-b</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bolt" aria-hidden="true"></i> bolt</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bomb" aria-hidden="true"></i> bomb</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-book" aria-hidden="true"></i> book</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bookmark" aria-hidden="true"></i> bookmark</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bookmark-o" aria-hidden="true"></i> bookmark-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-braille" aria-hidden="true"></i> braille</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-briefcase" aria-hidden="true"></i> briefcase</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bug" aria-hidden="true"></i> bug</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-building" aria-hidden="true"></i> building</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-building-o" aria-hidden="true"></i> building-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bullhorn" aria-hidden="true"></i> bullhorn</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bullseye" aria-hidden="true"></i> bullseye</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bus" aria-hidden="true"></i> bus</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cab" aria-hidden="true"></i> cab <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-calculator" aria-hidden="true"></i> calculator</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-calendar" aria-hidden="true"></i> calendar</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-calendar-check-o" aria-hidden="true"></i> calendar-check-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-calendar-minus-o" aria-hidden="true"></i> calendar-minus-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-calendar-o" aria-hidden="true"></i> calendar-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-calendar-plus-o" aria-hidden="true"></i> calendar-plus-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-calendar-times-o" aria-hidden="true"></i> calendar-times-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-camera" aria-hidden="true"></i> camera</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-camera-retro" aria-hidden="true"></i> camera-retro</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-car" aria-hidden="true"></i> car</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-caret-square-o-down" aria-hidden="true"></i> caret-square-o-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i> caret-square-o-left</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-caret-square-o-right" aria-hidden="true"></i> caret-square-o-right</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-caret-square-o-up" aria-hidden="true"></i> caret-square-o-up</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cart-arrow-down" aria-hidden="true"></i> cart-arrow-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cart-plus" aria-hidden="true"></i> cart-plus</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cc" aria-hidden="true"></i> cc</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-certificate" aria-hidden="true"></i> certificate</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-check" aria-hidden="true"></i> check</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-check-circle" aria-hidden="true"></i> check-circle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-check-circle-o" aria-hidden="true"></i> check-circle-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-check-square" aria-hidden="true"></i> check-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-check-square-o" aria-hidden="true"></i> check-square-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-child" aria-hidden="true"></i> child</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-circle" aria-hidden="true"></i> circle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-circle-o" aria-hidden="true"></i> circle-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-circle-o-notch" aria-hidden="true"></i> circle-o-notch</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-circle-thin" aria-hidden="true"></i> circle-thin</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-clock-o" aria-hidden="true"></i> clock-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-clone" aria-hidden="true"></i> clone</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-close" aria-hidden="true"></i> close <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cloud" aria-hidden="true"></i> cloud</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cloud-download" aria-hidden="true"></i> cloud-download</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cloud-upload" aria-hidden="true"></i> cloud-upload</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-code" aria-hidden="true"></i> code</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-code-fork" aria-hidden="true"></i> code-fork</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-coffee" aria-hidden="true"></i> coffee</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cog" aria-hidden="true"></i> cog</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cogs" aria-hidden="true"></i> cogs</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-comment" aria-hidden="true"></i> comment</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-comment-o" aria-hidden="true"></i> comment-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-commenting" aria-hidden="true"></i> commenting</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-commenting-o" aria-hidden="true"></i> commenting-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-comments" aria-hidden="true"></i> comments</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-comments-o" aria-hidden="true"></i> comments-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-compass" aria-hidden="true"></i> compass</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-copyright" aria-hidden="true"></i> copyright</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-creative-commons" aria-hidden="true"></i> creative-commons</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-credit-card" aria-hidden="true"></i> credit-card</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-credit-card-alt" aria-hidden="true"></i> credit-card-alt</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-crop" aria-hidden="true"></i> crop</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-crosshairs" aria-hidden="true"></i> crosshairs</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cube" aria-hidden="true"></i> cube</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cubes" aria-hidden="true"></i> cubes</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cutlery" aria-hidden="true"></i> cutlery</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-dashboard" aria-hidden="true"></i> dashboard <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-database" aria-hidden="true"></i> database</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-deaf" aria-hidden="true"></i> deaf</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-deafness" aria-hidden="true"></i> deafness <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-desktop" aria-hidden="true"></i> desktop</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-diamond" aria-hidden="true"></i> diamond</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-dot-circle-o" aria-hidden="true"></i> dot-circle-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-download" aria-hidden="true"></i> download</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-drivers-license" aria-hidden="true"></i> drivers-license <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-drivers-license-o" aria-hidden="true"></i> drivers-license-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-edit" aria-hidden="true"></i> edit <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-ellipsis-h" aria-hidden="true"></i> ellipsis-h</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-ellipsis-v" aria-hidden="true"></i> ellipsis-v</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-envelope" aria-hidden="true"></i> envelope</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-envelope-o" aria-hidden="true"></i> envelope-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-envelope-open" aria-hidden="true"></i> envelope-open</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-envelope-open-o" aria-hidden="true"></i> envelope-open-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-envelope-square" aria-hidden="true"></i> envelope-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-eraser" aria-hidden="true"></i> eraser</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-exchange" aria-hidden="true"></i> exchange</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-exclamation" aria-hidden="true"></i> exclamation</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-exclamation-circle" aria-hidden="true"></i> exclamation-circle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-exclamation-triangle" aria-hidden="true"></i> exclamation-triangle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-external-link" aria-hidden="true"></i> external-link</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-external-link-square" aria-hidden="true"></i> external-link-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-eye" aria-hidden="true"></i> eye</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-eye-slash" aria-hidden="true"></i> eye-slash</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-eyedropper" aria-hidden="true"></i> eyedropper</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-fax" aria-hidden="true"></i> fax</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-feed" aria-hidden="true"></i> feed <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-female" aria-hidden="true"></i> female</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-fighter-jet" aria-hidden="true"></i> fighter-jet</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-archive-o" aria-hidden="true"></i> file-archive-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-audio-o" aria-hidden="true"></i> file-audio-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-code-o" aria-hidden="true"></i> file-code-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-excel-o" aria-hidden="true"></i> file-excel-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-image-o" aria-hidden="true"></i> file-image-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-movie-o" aria-hidden="true"></i> file-movie-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-pdf-o" aria-hidden="true"></i> file-pdf-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-photo-o" aria-hidden="true"></i> file-photo-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-picture-o" aria-hidden="true"></i> file-picture-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-powerpoint-o" aria-hidden="true"></i> file-powerpoint-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-sound-o" aria-hidden="true"></i> file-sound-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-video-o" aria-hidden="true"></i> file-video-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-word-o" aria-hidden="true"></i> file-word-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-zip-o" aria-hidden="true"></i> file-zip-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-film" aria-hidden="true"></i> film</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-filter" aria-hidden="true"></i> filter</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-fire" aria-hidden="true"></i> fire</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-fire-extinguisher" aria-hidden="true"></i> fire-extinguisher</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-flag" aria-hidden="true"></i> flag</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-flag-checkered" aria-hidden="true"></i> flag-checkered</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-flag-o" aria-hidden="true"></i> flag-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-flash" aria-hidden="true"></i> flash <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-flask" aria-hidden="true"></i> flask</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-folder" aria-hidden="true"></i> folder</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-folder-o" aria-hidden="true"></i> folder-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-folder-open" aria-hidden="true"></i> folder-open</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-folder-open-o" aria-hidden="true"></i> folder-open-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-frown-o" aria-hidden="true"></i> frown-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-futbol-o" aria-hidden="true"></i> futbol-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-gamepad" aria-hidden="true"></i> gamepad</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-gavel" aria-hidden="true"></i> gavel</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-gear" aria-hidden="true"></i> gear <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-gears" aria-hidden="true"></i> gears <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-gift" aria-hidden="true"></i> gift</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-glass" aria-hidden="true"></i> glass</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-globe" aria-hidden="true"></i> globe</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-graduation-cap" aria-hidden="true"></i> graduation-cap</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-group" aria-hidden="true"></i> group <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-grab-o" aria-hidden="true"></i> hand-grab-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-lizard-o" aria-hidden="true"></i> hand-lizard-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-paper-o" aria-hidden="true"></i> hand-paper-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-peace-o" aria-hidden="true"></i> hand-peace-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-pointer-o" aria-hidden="true"></i> hand-pointer-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-rock-o" aria-hidden="true"></i> hand-rock-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-scissors-o" aria-hidden="true"></i> hand-scissors-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-spock-o" aria-hidden="true"></i> hand-spock-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-stop-o" aria-hidden="true"></i> hand-stop-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-handshake-o" aria-hidden="true"></i> handshake-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hard-of-hearing" aria-hidden="true"></i> hard-of-hearing <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hashtag" aria-hidden="true"></i> hashtag</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hdd-o" aria-hidden="true"></i> hdd-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-headphones" aria-hidden="true"></i> headphones</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-heart" aria-hidden="true"></i> heart</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-heart-o" aria-hidden="true"></i> heart-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-heartbeat" aria-hidden="true"></i> heartbeat</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-history" aria-hidden="true"></i> history</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-home" aria-hidden="true"></i> home</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hotel" aria-hidden="true"></i> hotel <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hourglass" aria-hidden="true"></i> hourglass</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hourglass-1" aria-hidden="true"></i> hourglass-1 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hourglass-2" aria-hidden="true"></i> hourglass-2 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hourglass-3" aria-hidden="true"></i> hourglass-3 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hourglass-end" aria-hidden="true"></i> hourglass-end</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hourglass-half" aria-hidden="true"></i> hourglass-half</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hourglass-o" aria-hidden="true"></i> hourglass-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hourglass-start" aria-hidden="true"></i> hourglass-start</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-i-cursor" aria-hidden="true"></i> i-cursor</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-id-badge" aria-hidden="true"></i> id-badge</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-id-card" aria-hidden="true"></i> id-card</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-id-card-o" aria-hidden="true"></i> id-card-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-image" aria-hidden="true"></i> image <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-inbox" aria-hidden="true"></i> inbox</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-industry" aria-hidden="true"></i> industry</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-info" aria-hidden="true"></i> info</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-info-circle" aria-hidden="true"></i> info-circle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-institution" aria-hidden="true"></i> institution <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-key" aria-hidden="true"></i> key</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-keyboard-o" aria-hidden="true"></i> keyboard-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-language" aria-hidden="true"></i> language</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-laptop" aria-hidden="true"></i> laptop</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-leaf" aria-hidden="true"></i> leaf</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-legal" aria-hidden="true"></i> legal <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-lemon-o" aria-hidden="true"></i> lemon-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-level-down" aria-hidden="true"></i> level-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-level-up" aria-hidden="true"></i> level-up</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-life-bouy" aria-hidden="true"></i> life-bouy <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-life-buoy" aria-hidden="true"></i> life-buoy <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-life-ring" aria-hidden="true"></i> life-ring</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-life-saver" aria-hidden="true"></i> life-saver <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-lightbulb-o" aria-hidden="true"></i> lightbulb-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-line-chart" aria-hidden="true"></i> line-chart</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-location-arrow" aria-hidden="true"></i> location-arrow</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-lock" aria-hidden="true"></i> lock</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-low-vision" aria-hidden="true"></i> low-vision</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-magic" aria-hidden="true"></i> magic</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-magnet" aria-hidden="true"></i> magnet</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-mail-forward" aria-hidden="true"></i> mail-forward <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-mail-reply" aria-hidden="true"></i> mail-reply <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-mail-reply-all" aria-hidden="true"></i> mail-reply-all <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-male" aria-hidden="true"></i> male</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-map" aria-hidden="true"></i> map</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-map-marker" aria-hidden="true"></i> map-marker</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-map-o" aria-hidden="true"></i> map-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-map-pin" aria-hidden="true"></i> map-pin</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-map-signs" aria-hidden="true"></i> map-signs</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-meh-o" aria-hidden="true"></i> meh-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-microchip" aria-hidden="true"></i> microchip</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-microphone" aria-hidden="true"></i> microphone</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-microphone-slash" aria-hidden="true"></i> microphone-slash</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-minus" aria-hidden="true"></i> minus</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-minus-circle" aria-hidden="true"></i> minus-circle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-minus-square" aria-hidden="true"></i> minus-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-minus-square-o" aria-hidden="true"></i> minus-square-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-mobile" aria-hidden="true"></i> mobile</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-mobile-phone" aria-hidden="true"></i> mobile-phone <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-money" aria-hidden="true"></i> money</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-moon-o" aria-hidden="true"></i> moon-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-mortar-board" aria-hidden="true"></i> mortar-board <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-motorcycle" aria-hidden="true"></i> motorcycle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-mouse-pointer" aria-hidden="true"></i> mouse-pointer</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-music" aria-hidden="true"></i> music</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-navicon" aria-hidden="true"></i> navicon <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-newspaper-o" aria-hidden="true"></i> newspaper-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-object-group" aria-hidden="true"></i> object-group</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-object-ungroup" aria-hidden="true"></i> object-ungroup</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-paint-brush" aria-hidden="true"></i> paint-brush</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-paper-plane" aria-hidden="true"></i> paper-plane</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-paper-plane-o" aria-hidden="true"></i> paper-plane-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-paw" aria-hidden="true"></i> paw</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-pencil" aria-hidden="true"></i> pencil</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-pencil-square" aria-hidden="true"></i> pencil-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> pencil-square-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-percent" aria-hidden="true"></i> percent</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-phone" aria-hidden="true"></i> phone</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-phone-square" aria-hidden="true"></i> phone-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-photo" aria-hidden="true"></i> photo <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-picture-o" aria-hidden="true"></i> picture-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-pie-chart" aria-hidden="true"></i> pie-chart</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-plane" aria-hidden="true"></i> plane</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-plug" aria-hidden="true"></i> plug</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-plus" aria-hidden="true"></i> plus</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-plus-circle" aria-hidden="true"></i> plus-circle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-plus-square" aria-hidden="true"></i> plus-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-plus-square-o" aria-hidden="true"></i> plus-square-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-podcast" aria-hidden="true"></i> podcast</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-power-off" aria-hidden="true"></i> power-off</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-print" aria-hidden="true"></i> print</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-puzzle-piece" aria-hidden="true"></i> puzzle-piece</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-qrcode" aria-hidden="true"></i> qrcode</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-question" aria-hidden="true"></i> question</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-question-circle" aria-hidden="true"></i> question-circle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-question-circle-o" aria-hidden="true"></i> question-circle-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-quote-left" aria-hidden="true"></i> quote-left</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-quote-right" aria-hidden="true"></i> quote-right</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-random" aria-hidden="true"></i> random</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-recycle" aria-hidden="true"></i> recycle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-refresh" aria-hidden="true"></i> refresh</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-registered" aria-hidden="true"></i> registered</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-remove" aria-hidden="true"></i> remove <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-reorder" aria-hidden="true"></i> reorder <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-reply" aria-hidden="true"></i> reply</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-reply-all" aria-hidden="true"></i> reply-all</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-retweet" aria-hidden="true"></i> retweet</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-road" aria-hidden="true"></i> road</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-rocket" aria-hidden="true"></i> rocket</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-rss" aria-hidden="true"></i> rss</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-rss-square" aria-hidden="true"></i> rss-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-s15" aria-hidden="true"></i> s15 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-search" aria-hidden="true"></i> search</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-search-minus" aria-hidden="true"></i> search-minus</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-search-plus" aria-hidden="true"></i> search-plus</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-send" aria-hidden="true"></i> send <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-send-o" aria-hidden="true"></i> send-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-server" aria-hidden="true"></i> server</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-share" aria-hidden="true"></i> share</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-share-alt" aria-hidden="true"></i> share-alt</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-share-alt-square" aria-hidden="true"></i> share-alt-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-share-square" aria-hidden="true"></i> share-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-share-square-o" aria-hidden="true"></i> share-square-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-shield" aria-hidden="true"></i> shield</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-ship" aria-hidden="true"></i> ship</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-shopping-bag" aria-hidden="true"></i> shopping-bag</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-shopping-basket" aria-hidden="true"></i> shopping-basket</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-shopping-cart" aria-hidden="true"></i> shopping-cart</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-shower" aria-hidden="true"></i> shower</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sign-in" aria-hidden="true"></i> sign-in</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sign-language" aria-hidden="true"></i> sign-language</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sign-out" aria-hidden="true"></i> sign-out</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-signal" aria-hidden="true"></i> signal</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-signing" aria-hidden="true"></i> signing <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sitemap" aria-hidden="true"></i> sitemap</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sliders" aria-hidden="true"></i> sliders</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-smile-o" aria-hidden="true"></i> smile-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-snowflake-o" aria-hidden="true"></i> snowflake-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-soccer-ball-o" aria-hidden="true"></i> soccer-ball-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sort" aria-hidden="true"></i> sort</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sort-alpha-asc" aria-hidden="true"></i> sort-alpha-asc</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sort-alpha-desc" aria-hidden="true"></i> sort-alpha-desc</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sort-amount-asc" aria-hidden="true"></i> sort-amount-asc</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sort-amount-desc" aria-hidden="true"></i> sort-amount-desc</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sort-asc" aria-hidden="true"></i> sort-asc</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sort-desc" aria-hidden="true"></i> sort-desc</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sort-down" aria-hidden="true"></i> sort-down <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sort-numeric-asc" aria-hidden="true"></i> sort-numeric-asc</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sort-numeric-desc" aria-hidden="true"></i> sort-numeric-desc</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sort-up" aria-hidden="true"></i> sort-up <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-space-shuttle" aria-hidden="true"></i> space-shuttle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-spinner" aria-hidden="true"></i> spinner</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-spoon" aria-hidden="true"></i> spoon</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-square" aria-hidden="true"></i> square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-square-o" aria-hidden="true"></i> square-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-star" aria-hidden="true"></i> star</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-star-half" aria-hidden="true"></i> star-half</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-star-half-empty" aria-hidden="true"></i> star-half-empty <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-star-half-full" aria-hidden="true"></i> star-half-full <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-star-half-o" aria-hidden="true"></i> star-half-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-star-o" aria-hidden="true"></i> star-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sticky-note" aria-hidden="true"></i> sticky-note</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sticky-note-o" aria-hidden="true"></i> sticky-note-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-street-view" aria-hidden="true"></i> street-view</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-suitcase" aria-hidden="true"></i> suitcase</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sun-o" aria-hidden="true"></i> sun-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-support" aria-hidden="true"></i> support <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-tablet" aria-hidden="true"></i> tablet</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-tachometer" aria-hidden="true"></i> tachometer</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-tag" aria-hidden="true"></i> tag</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-tags" aria-hidden="true"></i> tags</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-tasks" aria-hidden="true"></i> tasks</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-taxi" aria-hidden="true"></i> taxi</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-television" aria-hidden="true"></i> television</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-terminal" aria-hidden="true"></i> terminal</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer" aria-hidden="true"></i> thermometer <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-0" aria-hidden="true"></i> thermometer-0 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-1" aria-hidden="true"></i> thermometer-1 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-2" aria-hidden="true"></i> thermometer-2 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-3" aria-hidden="true"></i> thermometer-3 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-4" aria-hidden="true"></i> thermometer-4 <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-empty" aria-hidden="true"></i> thermometer-empty</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-full" aria-hidden="true"></i> thermometer-full</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-half" aria-hidden="true"></i> thermometer-half</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-quarter" aria-hidden="true"></i> thermometer-quarter</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thermometer-three-quarters" aria-hidden="true"></i> thermometer-three-quarters</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thumb-tack" aria-hidden="true"></i> thumb-tack</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thumbs-down" aria-hidden="true"></i> thumbs-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i> thumbs-o-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> thumbs-o-up</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thumbs-up" aria-hidden="true"></i> thumbs-up</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-ticket" aria-hidden="true"></i> ticket</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-times" aria-hidden="true"></i> times</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-times-circle" aria-hidden="true"></i> times-circle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-times-circle-o" aria-hidden="true"></i> times-circle-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-times-rectangle" aria-hidden="true"></i> times-rectangle <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-times-rectangle-o" aria-hidden="true"></i> times-rectangle-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-tint" aria-hidden="true"></i> tint</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-toggle-down" aria-hidden="true"></i> toggle-down <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-toggle-left" aria-hidden="true"></i> toggle-left <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-toggle-off" aria-hidden="true"></i> toggle-off</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-toggle-on" aria-hidden="true"></i> toggle-on</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-toggle-right" aria-hidden="true"></i> toggle-right <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-toggle-up" aria-hidden="true"></i> toggle-up <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-trademark" aria-hidden="true"></i> trademark</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-trash" aria-hidden="true"></i> trash</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-trash-o" aria-hidden="true"></i> trash-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-tree" aria-hidden="true"></i> tree</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-trophy" aria-hidden="true"></i> trophy</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-truck" aria-hidden="true"></i> truck</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-tty" aria-hidden="true"></i> tty</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-tv" aria-hidden="true"></i> tv <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-umbrella" aria-hidden="true"></i> umbrella</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-universal-access" aria-hidden="true"></i> universal-access</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-university" aria-hidden="true"></i> university</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-unlock" aria-hidden="true"></i> unlock</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-unlock-alt" aria-hidden="true"></i> unlock-alt</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-unsorted" aria-hidden="true"></i> unsorted <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-upload" aria-hidden="true"></i> upload</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-user" aria-hidden="true"></i> user</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-user-circle" aria-hidden="true"></i> user-circle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-user-circle-o" aria-hidden="true"></i> user-circle-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-user-o" aria-hidden="true"></i> user-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-user-plus" aria-hidden="true"></i> user-plus</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-user-secret" aria-hidden="true"></i> user-secret</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-user-times" aria-hidden="true"></i> user-times</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-users" aria-hidden="true"></i> users</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-vcard" aria-hidden="true"></i> vcard <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-vcard-o" aria-hidden="true"></i> vcard-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-video-camera" aria-hidden="true"></i> video-camera</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-volume-control-phone" aria-hidden="true"></i> volume-control-phone</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-volume-down" aria-hidden="true"></i> volume-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-volume-off" aria-hidden="true"></i> volume-off</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-volume-up" aria-hidden="true"></i> volume-up</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-warning" aria-hidden="true"></i> warning <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-wheelchair" aria-hidden="true"></i> wheelchair</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-wheelchair-alt" aria-hidden="true"></i> wheelchair-alt</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-wifi" aria-hidden="true"></i> wifi</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-window-close" aria-hidden="true"></i> window-close</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-window-close-o" aria-hidden="true"></i> window-close-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-window-maximize" aria-hidden="true"></i> window-maximize</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-window-minimize" aria-hidden="true"></i> window-minimize</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-window-restore" aria-hidden="true"></i> window-restore</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-wrench" aria-hidden="true"></i> wrench</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2>Accessibility Icons</h2>
                            </header>
                            <div class="main-box-body clearfix">
                                <div class="row the-icons">
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-american-sign-language-interpreting" aria-hidden="true"></i> american-sign-language-interpreting</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-asl-interpreting" aria-hidden="true"></i> asl-interpreting <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-assistive-listening-systems" aria-hidden="true"></i> assistive-listening-systems</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-audio-description" aria-hidden="true"></i> audio-description</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-blind" aria-hidden="true"></i> blind</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-braille" aria-hidden="true"></i> braille</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cc" aria-hidden="true"></i> cc</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-deaf" aria-hidden="true"></i> deaf</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-deafness" aria-hidden="true"></i> deafness <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hard-of-hearing" aria-hidden="true"></i> hard-of-hearing <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-low-vision" aria-hidden="true"></i> low-vision</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-question-circle-o" aria-hidden="true"></i> question-circle-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sign-language" aria-hidden="true"></i> sign-language</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-signing" aria-hidden="true"></i> signing <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-tty" aria-hidden="true"></i> tty</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-universal-access" aria-hidden="true"></i> universal-access</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-volume-control-phone" aria-hidden="true"></i> volume-control-phone</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-wheelchair" aria-hidden="true"></i> wheelchair</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-wheelchair-alt" aria-hidden="true"></i> wheelchair-alt</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2>Hand Icons</h2>
                            </header>
                            <div class="main-box-body clearfix">
                                <div class="row the-icons">
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-grab-o" aria-hidden="true"></i> hand-grab-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-lizard-o" aria-hidden="true"></i> hand-lizard-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-o-down" aria-hidden="true"></i> hand-o-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-o-left" aria-hidden="true"></i> hand-o-left</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-o-right" aria-hidden="true"></i> hand-o-right</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-o-up" aria-hidden="true"></i> hand-o-up</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-paper-o" aria-hidden="true"></i> hand-paper-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-peace-o" aria-hidden="true"></i> hand-peace-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-pointer-o" aria-hidden="true"></i> hand-pointer-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-rock-o" aria-hidden="true"></i> hand-rock-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-scissors-o" aria-hidden="true"></i> hand-scissors-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-spock-o" aria-hidden="true"></i> hand-spock-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-stop-o" aria-hidden="true"></i> hand-stop-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thumbs-down" aria-hidden="true"></i> thumbs-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i> thumbs-o-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> thumbs-o-up</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-thumbs-up" aria-hidden="true"></i> thumbs-up</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2>Transportation Icons</h2>
                            </header>
                            <div class="main-box-body clearfix">
                                <div class="row the-icons">
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-ambulance" aria-hidden="true"></i> ambulance</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-automobile" aria-hidden="true"></i> automobile <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bicycle" aria-hidden="true"></i> bicycle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bus" aria-hidden="true"></i> bus</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cab" aria-hidden="true"></i> cab <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-car" aria-hidden="true"></i> car</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-fighter-jet" aria-hidden="true"></i> fighter-jet</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-motorcycle" aria-hidden="true"></i> motorcycle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-plane" aria-hidden="true"></i> plane</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-rocket" aria-hidden="true"></i> rocket</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-ship" aria-hidden="true"></i> ship</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-space-shuttle" aria-hidden="true"></i> space-shuttle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-subway" aria-hidden="true"></i> subway</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-taxi" aria-hidden="true"></i> taxi</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-train" aria-hidden="true"></i> train</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-truck" aria-hidden="true"></i> truck</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-wheelchair" aria-hidden="true"></i> wheelchair</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-wheelchair-alt" aria-hidden="true"></i> wheelchair-alt</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2>Gender Icons</h2>
                            </header>
                            <div class="main-box-body clearfix">
                                <div class="row the-icons">
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-genderless" aria-hidden="true"></i> genderless</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-intersex" aria-hidden="true"></i> intersex <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-mars" aria-hidden="true"></i> mars</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-mars-double" aria-hidden="true"></i> mars-double</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-mars-stroke" aria-hidden="true"></i> mars-stroke</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-mars-stroke-h" aria-hidden="true"></i> mars-stroke-h</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-mars-stroke-v" aria-hidden="true"></i> mars-stroke-v</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-mercury" aria-hidden="true"></i> mercury</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-neuter" aria-hidden="true"></i> neuter</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-transgender" aria-hidden="true"></i> transgender</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-transgender-alt" aria-hidden="true"></i> transgender-alt</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-venus" aria-hidden="true"></i> venus</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-venus-double" aria-hidden="true"></i> venus-double</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-venus-mars" aria-hidden="true"></i> venus-mars</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2>File Type Icons</h2>
                            </header>
                            <div class="main-box-body clearfix">
                                <div class="row the-icons">
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file" aria-hidden="true"></i> file</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-archive-o" aria-hidden="true"></i> file-archive-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-audio-o" aria-hidden="true"></i> file-audio-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-code-o" aria-hidden="true"></i> file-code-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-excel-o" aria-hidden="true"></i> file-excel-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-image-o" aria-hidden="true"></i> file-image-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-movie-o" aria-hidden="true"></i> file-movie-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-o" aria-hidden="true"></i> file-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-pdf-o" aria-hidden="true"></i> file-pdf-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-photo-o" aria-hidden="true"></i> file-photo-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-picture-o" aria-hidden="true"></i> file-picture-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-powerpoint-o" aria-hidden="true"></i> file-powerpoint-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-sound-o" aria-hidden="true"></i> file-sound-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-text" aria-hidden="true"></i> file-text</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-text-o" aria-hidden="true"></i> file-text-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-video-o" aria-hidden="true"></i> file-video-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-word-o" aria-hidden="true"></i> file-word-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-zip-o" aria-hidden="true"></i> file-zip-o <span class="text-muted">(alias)</span></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2>Spinner Icons</h2>
                            </header>
                            <div class="main-box-body clearfix">
                                <div class="row the-icons">
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-circle-o-notch" aria-hidden="true"></i> circle-o-notch</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cog" aria-hidden="true"></i> cog</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-gear" aria-hidden="true"></i> gear <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-refresh" aria-hidden="true"></i> refresh</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-spinner" aria-hidden="true"></i> spinner</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2>Form Control Icons</h2>
                            </header>
                            <div class="main-box-body clearfix">
                                <div class="row the-icons">
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-check-square" aria-hidden="true"></i> check-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-check-square-o" aria-hidden="true"></i> check-square-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-circle" aria-hidden="true"></i> circle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-circle-o" aria-hidden="true"></i> circle-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-dot-circle-o" aria-hidden="true"></i> dot-circle-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-minus-square" aria-hidden="true"></i> minus-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-minus-square-o" aria-hidden="true"></i> minus-square-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-plus-square" aria-hidden="true"></i> plus-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-plus-square-o" aria-hidden="true"></i> plus-square-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-square" aria-hidden="true"></i> square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-square-o" aria-hidden="true"></i> square-o</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2>Payment Icons</h2>
                            </header>
                            <div class="main-box-body clearfix">
                                <div class="row the-icons">
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cc-amex" aria-hidden="true"></i> cc-amex</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cc-diners-club" aria-hidden="true"></i> cc-diners-club</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cc-discover" aria-hidden="true"></i> cc-discover</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cc-jcb" aria-hidden="true"></i> cc-jcb</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cc-mastercard" aria-hidden="true"></i> cc-mastercard</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cc-paypal" aria-hidden="true"></i> cc-paypal</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cc-stripe" aria-hidden="true"></i> cc-stripe</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cc-visa" aria-hidden="true"></i> cc-visa</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-credit-card" aria-hidden="true"></i> credit-card</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-credit-card-alt" aria-hidden="true"></i> credit-card-alt</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-google-wallet" aria-hidden="true"></i> google-wallet</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-paypal" aria-hidden="true"></i> paypal</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2>Chart Icons</h2>
                            </header>
                            <div class="main-box-body clearfix">
                                <div class="row the-icons">
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-areacode-chart" aria-hidden="true"></i> areacode-chart</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bar-chart" aria-hidden="true"></i> bar-chart</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bar-chart-o" aria-hidden="true"></i> bar-chart-o <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-line-chart" aria-hidden="true"></i> line-chart</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-pie-chart" aria-hidden="true"></i> pie-chart</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2>Currency Icons</h2>
                            </header>
                            <div class="main-box-body clearfix">
                                <div class="row the-icons">
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bitcoin" aria-hidden="true"></i> bitcoin <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-btc" aria-hidden="true"></i> btc</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cny" aria-hidden="true"></i> cny <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-dollar" aria-hidden="true"></i> dollar <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-eur" aria-hidden="true"></i> eur</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-euro" aria-hidden="true"></i> euro <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-gbp" aria-hidden="true"></i> gbp</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-gg" aria-hidden="true"></i> gg</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-gg-circle" aria-hidden="true"></i> gg-circle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-ils" aria-hidden="true"></i> ils</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-inr" aria-hidden="true"></i> inr</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-jpy" aria-hidden="true"></i> jpy</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-krw" aria-hidden="true"></i> krw</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-money" aria-hidden="true"></i> money</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-rmb" aria-hidden="true"></i> rmb <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-rouble" aria-hidden="true"></i> rouble <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-rub" aria-hidden="true"></i> rub</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-ruble" aria-hidden="true"></i> ruble <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-rupee" aria-hidden="true"></i> rupee <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-shekel" aria-hidden="true"></i> shekel <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sheqel" aria-hidden="true"></i> sheqel <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-try" aria-hidden="true"></i> try</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-turkish-lira" aria-hidden="true"></i> turkish-lira <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-usd" aria-hidden="true"></i> usd</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-won" aria-hidden="true"></i> won <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-yen" aria-hidden="true"></i> yen <span class="text-muted">(alias)</span></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2>Text Editor Icons</h2>
                            </header>
                            <div class="main-box-body clearfix">
                                <div class="row the-icons">
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-align-center" aria-hidden="true"></i> align-center</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-align-justify" aria-hidden="true"></i> align-justify</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-align-left" aria-hidden="true"></i> align-left</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-align-right" aria-hidden="true"></i> align-right</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bold" aria-hidden="true"></i> bold</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-chain" aria-hidden="true"></i> chain <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-chain-broken" aria-hidden="true"></i> chain-broken</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-clipboard" aria-hidden="true"></i> clipboard</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-columns" aria-hidden="true"></i> columns</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-copy" aria-hidden="true"></i> copy <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cut" aria-hidden="true"></i> cut <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-dedent" aria-hidden="true"></i> dedent <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-eraser" aria-hidden="true"></i> eraser</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file" aria-hidden="true"></i> file</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-o" aria-hidden="true"></i> file-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-text" aria-hidden="true"></i> file-text</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-file-text-o" aria-hidden="true"></i> file-text-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-files-o" aria-hidden="true"></i> files-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-floppy-o" aria-hidden="true"></i> floppy-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-font" aria-hidden="true"></i> font</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-header" aria-hidden="true"></i> header</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-indent" aria-hidden="true"></i> indent</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-italic" aria-hidden="true"></i> italic</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-link" aria-hidden="true"></i> link</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-list" aria-hidden="true"></i> list</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-list-alt" aria-hidden="true"></i> list-alt</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-list-ol" aria-hidden="true"></i> list-ol</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-list-ul" aria-hidden="true"></i> list-ul</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-outdent" aria-hidden="true"></i> outdent</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-paperclip" aria-hidden="true"></i> paperclip</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-paragraph" aria-hidden="true"></i> paragraph</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-paste" aria-hidden="true"></i> paste <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-repeat" aria-hidden="true"></i> repeat</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-rotate-left" aria-hidden="true"></i> rotate-left <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-rotate-right" aria-hidden="true"></i> rotate-right <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-save" aria-hidden="true"></i> save <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-scissors" aria-hidden="true"></i> scissors</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-strikethrough" aria-hidden="true"></i> strikethrough</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-subscript" aria-hidden="true"></i> subscript</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-superscript" aria-hidden="true"></i> superscript</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-table" aria-hidden="true"></i> table</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-text-height" aria-hidden="true"></i> text-height</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-text-width" aria-hidden="true"></i> text-width</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-th" aria-hidden="true"></i> th</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-th-large" aria-hidden="true"></i> th-large</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-th-list" aria-hidden="true"></i> th-list</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-underline" aria-hidden="true"></i> underline</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-undo" aria-hidden="true"></i> undo</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-unlink" aria-hidden="true"></i> unlink <span class="text-muted">(alias)</span></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2>Directional Icons</h2>
                            </header>
                            <div class="main-box-body clearfix">
                                <div class="row the-icons">
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-angle-double-down" aria-hidden="true"></i> angle-double-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-angle-double-left" aria-hidden="true"></i> angle-double-left</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-angle-double-right" aria-hidden="true"></i> angle-double-right</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-angle-double-up" aria-hidden="true"></i> angle-double-up</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-angle-down" aria-hidden="true"></i> angle-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-angle-left" aria-hidden="true"></i> angle-left</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-angle-right" aria-hidden="true"></i> angle-right</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-angle-up" aria-hidden="true"></i> angle-up</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrow-circle-down" aria-hidden="true"></i> arrow-circle-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrow-circle-left" aria-hidden="true"></i> arrow-circle-left</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrow-circle-o-down" aria-hidden="true"></i> arrow-circle-o-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrow-circle-o-left" aria-hidden="true"></i> arrow-circle-o-left</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i> arrow-circle-o-right</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrow-circle-o-up" aria-hidden="true"></i> arrow-circle-o-up</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> arrow-circle-right</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrow-circle-up" aria-hidden="true"></i> arrow-circle-up</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrow-down" aria-hidden="true"></i> arrow-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrow-left" aria-hidden="true"></i> arrow-left</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrow-right" aria-hidden="true"></i> arrow-right</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrow-up" aria-hidden="true"></i> arrow-up</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrows" aria-hidden="true"></i> arrows</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrows-alt" aria-hidden="true"></i> arrows-alt</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrows-h" aria-hidden="true"></i> arrows-h</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrows-v" aria-hidden="true"></i> arrows-v</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-caret-down" aria-hidden="true"></i> caret-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-caret-left" aria-hidden="true"></i> caret-left</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-caret-right" aria-hidden="true"></i> caret-right</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-caret-square-o-down" aria-hidden="true"></i> caret-square-o-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i> caret-square-o-left</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-caret-square-o-right" aria-hidden="true"></i> caret-square-o-right</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-caret-square-o-up" aria-hidden="true"></i> caret-square-o-up</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-caret-up" aria-hidden="true"></i> caret-up</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-chevron-circle-down" aria-hidden="true"></i> chevron-circle-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-chevron-circle-left" aria-hidden="true"></i> chevron-circle-left</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-chevron-circle-right" aria-hidden="true"></i> chevron-circle-right</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-chevron-circle-up" aria-hidden="true"></i> chevron-circle-up</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-chevron-down" aria-hidden="true"></i> chevron-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-chevron-left" aria-hidden="true"></i> chevron-left</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-chevron-right" aria-hidden="true"></i> chevron-right</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-chevron-up" aria-hidden="true"></i> chevron-up</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-exchange" aria-hidden="true"></i> exchange</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-o-down" aria-hidden="true"></i> hand-o-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-o-left" aria-hidden="true"></i> hand-o-left</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-o-right" aria-hidden="true"></i> hand-o-right</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hand-o-up" aria-hidden="true"></i> hand-o-up</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-long-arrow-down" aria-hidden="true"></i> long-arrow-down</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-long-arrow-left" aria-hidden="true"></i> long-arrow-left</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-long-arrow-right" aria-hidden="true"></i> long-arrow-right</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-long-arrow-up" aria-hidden="true"></i> long-arrow-up</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-toggle-down" aria-hidden="true"></i> toggle-down <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-toggle-left" aria-hidden="true"></i> toggle-left <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-toggle-right" aria-hidden="true"></i> toggle-right <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-toggle-up" aria-hidden="true"></i> toggle-up <span class="text-muted">(alias)</span></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2>Video Player Icons</h2>
                            </header>
                            <div class="main-box-body clearfix">
                                <div class="row the-icons">
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-arrows-alt" aria-hidden="true"></i> arrows-alt</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-backward" aria-hidden="true"></i> backward</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-compress" aria-hidden="true"></i> compress</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-eject" aria-hidden="true"></i> eject</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-expand" aria-hidden="true"></i> expand</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-fast-backward" aria-hidden="true"></i> fast-backward</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-fast-forward" aria-hidden="true"></i> fast-forward</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-forward" aria-hidden="true"></i> forward</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-pause" aria-hidden="true"></i> pause</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-pause-circle" aria-hidden="true"></i> pause-circle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-pause-circle-o" aria-hidden="true"></i> pause-circle-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-play" aria-hidden="true"></i> play</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-play-circle" aria-hidden="true"></i> play-circle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-play-circle-o" aria-hidden="true"></i> play-circle-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-random" aria-hidden="true"></i> random</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-step-backward" aria-hidden="true"></i> step-backward</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-step-forward" aria-hidden="true"></i> step-forward</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-stop" aria-hidden="true"></i> stop</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-stop-circle" aria-hidden="true"></i> stop-circle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-stop-circle-o" aria-hidden="true"></i> stop-circle-o</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-youtube-play" aria-hidden="true"></i> youtube-play</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2>Brand Icons</h2>
                            </header>
                            <div class="main-box-body clearfix">
                                <div class="row the-icons">
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-500px" aria-hidden="true"></i> 500px</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-adn" aria-hidden="true"></i> adn</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-amazon" aria-hidden="true"></i> amazon</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-android" aria-hidden="true"></i> android</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-angellist" aria-hidden="true"></i> angellist</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-apple" aria-hidden="true"></i> apple</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bandcamp" aria-hidden="true"></i> bandcamp</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-behance" aria-hidden="true"></i> behance</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-behance-square" aria-hidden="true"></i> behance-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bitbucket" aria-hidden="true"></i> bitbucket</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bitbucket-square" aria-hidden="true"></i> bitbucket-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bitcoin" aria-hidden="true"></i> bitcoin <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-black-tie" aria-hidden="true"></i> black-tie</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bluetooth" aria-hidden="true"></i> bluetooth</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-bluetooth-b" aria-hidden="true"></i> bluetooth-b</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-btc" aria-hidden="true"></i> btc</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-buysellads" aria-hidden="true"></i> buysellads</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cc-amex" aria-hidden="true"></i> cc-amex</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cc-diners-club" aria-hidden="true"></i> cc-diners-club</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cc-discover" aria-hidden="true"></i> cc-discover</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cc-jcb" aria-hidden="true"></i> cc-jcb</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cc-mastercard" aria-hidden="true"></i> cc-mastercard</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cc-paypal" aria-hidden="true"></i> cc-paypal</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cc-stripe" aria-hidden="true"></i> cc-stripe</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-cc-visa" aria-hidden="true"></i> cc-visa</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-chrome" aria-hidden="true"></i> chrome</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-codepen" aria-hidden="true"></i> codepen</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-codiepie" aria-hidden="true"></i> codiepie</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-connectdevelop" aria-hidden="true"></i> connectdevelop</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-contao" aria-hidden="true"></i> contao</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-css3" aria-hidden="true"></i> css3</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-dashcube" aria-hidden="true"></i> dashcube</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-delicious" aria-hidden="true"></i> delicious</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-deviantart" aria-hidden="true"></i> deviantart</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-digg" aria-hidden="true"></i> digg</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-dribbble" aria-hidden="true"></i> dribbble</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-dropbox" aria-hidden="true"></i> dropbox</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-drupal" aria-hidden="true"></i> drupal</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-edge" aria-hidden="true"></i> edge</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-eercast" aria-hidden="true"></i> eercast</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-empire" aria-hidden="true"></i> empire</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-envira" aria-hidden="true"></i> envira</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-etsy" aria-hidden="true"></i> etsy</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-expeditedssl" aria-hidden="true"></i> expeditedssl</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-fa" aria-hidden="true"></i> fa <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-facebook" aria-hidden="true"></i> facebook</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-facebook-f" aria-hidden="true"></i> facebook-f <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-facebook-official" aria-hidden="true"></i> facebook-official</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-facebook-square" aria-hidden="true"></i> facebook-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-firefox" aria-hidden="true"></i> firefox</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-first-order" aria-hidden="true"></i> first-order</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-flickr" aria-hidden="true"></i> flickr</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-font-awesome" aria-hidden="true"></i> font-awesome</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-fonticons" aria-hidden="true"></i> fonticons</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-fort-awesome" aria-hidden="true"></i> fort-awesome</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-forumbee" aria-hidden="true"></i> forumbee</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-foursquare" aria-hidden="true"></i> foursquare</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-free-code-camp" aria-hidden="true"></i> free-code-camp</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-ge" aria-hidden="true"></i> ge <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-get-pocket" aria-hidden="true"></i> get-pocket</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-gg" aria-hidden="true"></i> gg</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-gg-circle" aria-hidden="true"></i> gg-circle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-git" aria-hidden="true"></i> git</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-git-square" aria-hidden="true"></i> git-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-github" aria-hidden="true"></i> github</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-github-alt" aria-hidden="true"></i> github-alt</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-github-square" aria-hidden="true"></i> github-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-gitlab" aria-hidden="true"></i> gitlab</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-gittip" aria-hidden="true"></i> gittip <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-glide" aria-hidden="true"></i> glide</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-glide-g" aria-hidden="true"></i> glide-g</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-google" aria-hidden="true"></i> google</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-google-plus" aria-hidden="true"></i> google-plus</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-google-plus-circle" aria-hidden="true"></i> google-plus-circle <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-google-plus-official" aria-hidden="true"></i> google-plus-official</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-google-plus-square" aria-hidden="true"></i> google-plus-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-google-wallet" aria-hidden="true"></i> google-wallet</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-gratipay" aria-hidden="true"></i> gratipay</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-grav" aria-hidden="true"></i> grav</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hacker-news" aria-hidden="true"></i> hacker-news</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-houzz" aria-hidden="true"></i> houzz</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-html5" aria-hidden="true"></i> html5</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-imdb" aria-hidden="true"></i> imdb</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-instagram" aria-hidden="true"></i> instagram</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-internet-explorer" aria-hidden="true"></i> internet-explorer</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-ioxhost" aria-hidden="true"></i> ioxhost</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-joomla" aria-hidden="true"></i> joomla</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-jsfiddle" aria-hidden="true"></i> jsfiddle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-lastfm" aria-hidden="true"></i> lastfm</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-lastfm-square" aria-hidden="true"></i> lastfm-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-leanpub" aria-hidden="true"></i> leanpub</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-linkedin" aria-hidden="true"></i> linkedin</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-linkedin-square" aria-hidden="true"></i> linkedin-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-linode" aria-hidden="true"></i> linode</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-linux" aria-hidden="true"></i> linux</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-maxcdn" aria-hidden="true"></i> maxcdn</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-meanpath" aria-hidden="true"></i> meanpath</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-medium" aria-hidden="true"></i> medium</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-meetup" aria-hidden="true"></i> meetup</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-mixcloud" aria-hidden="true"></i> mixcloud</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-modx" aria-hidden="true"></i> modx</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-odnoklassniki" aria-hidden="true"></i> odnoklassniki</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-odnoklassniki-square" aria-hidden="true"></i> odnoklassniki-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-opencart" aria-hidden="true"></i> opencart</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-openid" aria-hidden="true"></i> openid</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-opera" aria-hidden="true"></i> opera</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-optin-monster" aria-hidden="true"></i> optin-monster</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-pagelines" aria-hidden="true"></i> pagelines</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-paypal" aria-hidden="true"></i> paypal</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-pied-piper" aria-hidden="true"></i> pied-piper</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-pied-piper-alt" aria-hidden="true"></i> pied-piper-alt</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-pied-piper-pp" aria-hidden="true"></i> pied-piper-pp</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-pinterest" aria-hidden="true"></i> pinterest</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-pinterest-p" aria-hidden="true"></i> pinterest-p</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-pinterest-square" aria-hidden="true"></i> pinterest-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-product-hunt" aria-hidden="true"></i> product-hunt</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-qq" aria-hidden="true"></i> qq</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-quora" aria-hidden="true"></i> quora</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-ra" aria-hidden="true"></i> ra <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-ravelry" aria-hidden="true"></i> ravelry</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-rebel" aria-hidden="true"></i> rebel</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-reddit" aria-hidden="true"></i> reddit</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-reddit-alien" aria-hidden="true"></i> reddit-alien</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-reddit-square" aria-hidden="true"></i> reddit-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-renren" aria-hidden="true"></i> renren</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-resistance" aria-hidden="true"></i> resistance <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-safari" aria-hidden="true"></i> safari</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-scribd" aria-hidden="true"></i> scribd</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-sellsy" aria-hidden="true"></i> sellsy</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-share-alt" aria-hidden="true"></i> share-alt</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-share-alt-square" aria-hidden="true"></i> share-alt-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-shirtsinbulk" aria-hidden="true"></i> shirtsinbulk</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-simplybuilt" aria-hidden="true"></i> simplybuilt</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-skyatlas" aria-hidden="true"></i> skyatlas</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-skype" aria-hidden="true"></i> skype</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-slack" aria-hidden="true"></i> slack</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-slideshare" aria-hidden="true"></i> slideshare</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-snapchat" aria-hidden="true"></i> snapchat</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-snapchat-ghost" aria-hidden="true"></i> snapchat-ghost</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-snapchat-square" aria-hidden="true"></i> snapchat-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-soundcloud" aria-hidden="true"></i> soundcloud</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-spotify" aria-hidden="true"></i> spotify</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-stack-exchange" aria-hidden="true"></i> stack-exchange</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-stack-overflow" aria-hidden="true"></i> stack-overflow</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-steam" aria-hidden="true"></i> steam</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-steam-square" aria-hidden="true"></i> steam-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-stumbleupon" aria-hidden="true"></i> stumbleupon</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-stumbleupon-circle" aria-hidden="true"></i> stumbleupon-circle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-superpowers" aria-hidden="true"></i> superpowers</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-telegram" aria-hidden="true"></i> telegram</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-tencent-weibo" aria-hidden="true"></i> tencent-weibo</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-themeisle" aria-hidden="true"></i> themeisle</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-trello" aria-hidden="true"></i> trello</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-tripadvisor" aria-hidden="true"></i> tripadvisor</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-tumblr" aria-hidden="true"></i> tumblr</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-tumblr-square" aria-hidden="true"></i> tumblr-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-twitch" aria-hidden="true"></i> twitch</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-twitter" aria-hidden="true"></i> twitter</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-twitter-square" aria-hidden="true"></i> twitter-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-usb" aria-hidden="true"></i> usb</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-viacoin" aria-hidden="true"></i> viacoin</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-viadeo" aria-hidden="true"></i> viadeo</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-viadeo-square" aria-hidden="true"></i> viadeo-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-vimeo" aria-hidden="true"></i> vimeo</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-vimeo-square" aria-hidden="true"></i> vimeo-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-vine" aria-hidden="true"></i> vine</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-vk" aria-hidden="true"></i> vk</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-wechat" aria-hidden="true"></i> wechat <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-weibo" aria-hidden="true"></i> weibo</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-weixin" aria-hidden="true"></i> weixin</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-whatsapp" aria-hidden="true"></i> whatsapp</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-wikipedia-w" aria-hidden="true"></i> wikipedia-w</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-windows" aria-hidden="true"></i> windows</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-wordpress" aria-hidden="true"></i> wordpress</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-wpbeginner" aria-hidden="true"></i> wpbeginner</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-wpexplorer" aria-hidden="true"></i> wpexplorer</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-wpforms" aria-hidden="true"></i> wpforms</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-xing" aria-hidden="true"></i> xing</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-xing-square" aria-hidden="true"></i> xing-square</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-y-combinator" aria-hidden="true"></i> y-combinator</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-y-combinator-square" aria-hidden="true"></i> y-combinator-square <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-yahoo" aria-hidden="true"></i> yahoo</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-yc" aria-hidden="true"></i> yc <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-yc-square" aria-hidden="true"></i> yc-square <span class="text-muted">(alias)</span></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-yelp" aria-hidden="true"></i> yelp</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-yoast" aria-hidden="true"></i> yoast</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-youtube" aria-hidden="true"></i> youtube</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-youtube-play" aria-hidden="true"></i> youtube-play</div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-youtube-square" aria-hidden="true"></i> youtube-square</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
               -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box">
                            <header class="main-box-header clearfix">
                                <h2>Medical Icons</h2>
                            </header>
                            <div class="main-box-body clearfix">
                                <div class="row the-icons">
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-ambulance" aria-hidden="true" data-ng-click="selected('ambulance')">ambulance</i> </div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-h-square" aria-hidden="true" data-ng-click="selected('h-square')">h-square</i> </div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-heart" aria-hidden="true" data-ng-click="selected('heart')">heart</i> </div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-heart-o" aria-hidden="true" data-ng-click="selected('heart-o')">heart-o</i> </div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-heartbeat" aria-hidden="true" data-ng-click="selected('heartbeat')">heartbeat</i> </div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-hospital-o" aria-hidden="true" data-ng-click="selected('hospital-o')">hospital-o</i> </div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-medkit" aria-hidden="true" data-ng-click="selected('medkit')">medkit</i> </div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-plus-square" aria-hidden="true" data-ng-click="selected('plus-square')">plus-square</i> </div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-stethoscope" aria-hidden="true" data-ng-click="selected('stethoscope')">stethoscope</i> </div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-user-md" aria-hidden="true" data-ng-click="selected('user-md')">user-md</i></div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-wheelchair" aria-hidden="true" data-ng-click="selected('wheelchair')">wheelchair</i> </div>
                                    <div class="col-md-3 col-sm-4 col-xs-6"><i class="fa fa-wheelchair-alt" aria-hidden="true" data-ng-click="selected('wheelchair-alt')">wheelchair-alt</i> </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" ng-show="btnShow">提交</button>
                <button type="button" class="btn btn-default" data-ng-click="closeIcon()">关闭</button>
            </div>
        </div>
    </div>
</div>
