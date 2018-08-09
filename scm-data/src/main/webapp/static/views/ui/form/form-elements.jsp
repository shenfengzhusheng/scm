<%@ page language="java" contentType="application/x-msdownload" pageEncoding="UTF-8" %>

<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><a href="">Home</a></li>
            <li class="active"><span>Form Elements</span></li>
        </ol>
        <h1>Form Elements</h1>
    </div>
</div>
<div class="row">
    <div class="col-lg-6">
        <div class="main-box">
            <header class="main-box-header clearfix">
                <h2>Basic elements</h2>
            </header>
            <div class="main-box-body clearfix">
                <form role="form">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email address</label>
                        <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Password</label>
                        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                    </div>
                    <div class="form-group">
                        <label for="exampleTextarea">Textarea</label>
                        <textarea class="form-control" id="exampleTextarea" rows="3"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="exampleTooltip">Input with Tooltip</label>
                        <input type="text" class="form-control" id="exampleTooltip" data-toggle="tooltip" data-placement="bottom" title="very nice tooltip about this field">
                    </div>
                    <div class="form-group">
                        <label>Default Select</label>
                        <select class="form-control">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Multiple select</label>
                        <select multiple class="form-control">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </div>
                    <div class="form-group form-group-select2">
                        <label>Enhanced Select</label>
                        <select style="width:300px" id="sel2">
                            <option value="United States">United States</option>
                            <option value="United Kingdom">United Kingdom</option>
                            <option value="Afghanistan">Afghanistan</option>
                            <option value="Albania">Albania</option>
                            <option value="Algeria">Algeria</option>
                            <option value="American Samoa">American Samoa</option>
                            <option value="Andorra">Andorra</option>
                            <option value="Angola">Angola</option>
                            <option value="Anguilla">Anguilla</option>
                            <option value="Antarctica">Antarctica</option>
                            <option value="Antigua and Barbuda">Antigua and Barbuda</option>
                            <option value="Argentina">Argentina</option>
                            <option value="Armenia">Armenia</option>
                            <option value="Aruba">Aruba</option>
                            <option value="Australia">Australia</option>
                            <option value="Austria">Austria</option>
                            <option value="Azerbaijan">Azerbaijan</option>
                            <option value="Slovakia">Slovakia</option>
                        </select>
                    </div>
                    <div class="form-group form-group-select2">
                        <label>Multi-Value Select Boxes</label>
                        <select style="width:300px" id="sel2Multi" multiple>
                            <option value="United States">United States</option>
                            <option value="United Kingdom">United Kingdom</option>
                            <option value="Afghanistan">Afghanistan</option>
                            <option value="Albania">Albania</option>
                            <option value="Algeria">Algeria</option>
                            <option value="American Samoa">American Samoa</option>
                            <option value="Andorra">Andorra</option>
                            <option value="Angola">Angola</option>
                            <option value="Anguilla">Anguilla</option>
                            <option value="Antarctica">Antarctica</option>
                            <option value="Antigua and Barbuda">Antigua and Barbuda</option>
                            <option value="Argentina">Argentina</option>
                            <option value="Armenia">Armenia</option>
                            <option value="Aruba">Aruba</option>
                            <option value="Australia">Australia</option>
                            <option value="Austria">Austria</option>
                            <option value="Azerbaijan">Azerbaijan</option>
                            <option value="Slovakia">Slovakia</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="exampleAutocompleteSimple">Autocomplete</label>
                        <input type="text" class="form-control" id="exampleAutocompleteSimple" placeholder="countries">
                    </div>
                    <div class="form-group example-twitter-oss">
                        <label for="exampleAutocomplete">Autocomplete with templating</label>
                        <input type="text" class="form-control" id="exampleAutocomplete" placeholder="open source projects by Twitter">
                    </div>
                    <div class="form-group">
                        <label for="examplePwdMeter">Password strength meter (start typing...)</label>
                        <input type="password" class="form-control" id="examplePwdMeter" placeholder="Enter password" data-indicator="pwindicator">
                        <div id="pwindicator" class="pwdindicator">
                            <div class="bar"></div>
                            <div class="pwdstrength-label"></div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="col-lg-6">
        <div class="main-box">
            <header class="main-box-header clearfix">
                <h2>Other elements</h2>
            </header>
            <div class="main-box-body clearfix">
                <div class="form-group has-success">
                    <label class="control-label" for="inputSuccess">Input with success</label>
                    <input type="text" class="form-control" id="inputSuccess">
                    <span class="help-block"><i class="icon-ok-sign"></i> Success message</span>
                </div>
                <div class="form-group has-warning">
                    <label class="control-label" for="inputWarning">Input with warning</label>
                    <input type="text" class="form-control" id="inputWarning">
                    <span class="help-block"><i class="icon-warning-sign"></i> Warning message</span>
                </div>
                <div class="form-group has-error">
                    <label class="control-label" for="inputError">Input with error</label>
                    <input type="text" class="form-control" id="inputError">
                    <span class="help-block"><i class="icon-remove-sign"></i> Error message</span>
                </div>
                <div class="form-group">
                    <label for="exampleInputFile">Disabled input</label>
                    <input class="form-control" id="exampleInputFile" type="text" placeholder="Disabled input here..." disabled>
                </div>
                <div class="form-group">
                    <label>Checkboxes</label>
                    <div class="checkbox-nice">
                        <input type="checkbox" id="checkbox-1" checked="checked" />
                        <label for="checkbox-1">
                            Option one is this and that&mdash;be sure to include why it's great
                        </label>
                    </div>
                    <div class="checkbox-nice">
                        <input type="checkbox" id="checkbox-2" />
                        <label for="checkbox-2">
                            Option two is this and that&mdash;be sure to include why it's great
                        </label>
                    </div>
                    <div class="checkbox-nice">
                        <input type="checkbox" id="checkbox-3" />
                        <label for="checkbox-3">
                            Option three is this and that&mdash;be sure to include why it's great
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label>Radio inputs</label>
                    <div class="radio">
                        <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
                        <label for="optionsRadios1">
                            Option one is this and that&mdash;be sure to include why it's great
                        </label>
                    </div>
                    <div class="radio">
                        <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                        <label for="optionsRadios2">
                            Option two can be something else and selecting it will deselect option one
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label>Inline checkboxes</label>
                    <br/>
                    <div class="checkbox-nice checkbox-inline">
                        <input type="checkbox" id="checkbox-inl-1" />
                        <label for="checkbox-inl-1">
                            1
                        </label>
                    </div>
                    <div class="checkbox-nice checkbox-inline">
                        <input type="checkbox" id="checkbox-inl-2" />
                        <label for="checkbox-inl-2">
                            2
                        </label>
                    </div>
                    <div class="checkbox-nice checkbox-inline">
                        <input type="checkbox" id="checkbox-inl-3" />
                        <label for="checkbox-inl-3">
                            3
                        </label>
                    </div>
                </div>
                <h3><span>Checkbox buttons</span></h3>
                <div class="btn-group" data-toggle="buttons">
                    <label class="btn btn-primary">
                        <input type="checkbox"> Option 1
                    </label>
                    <label class="btn btn-primary">
                        <input type="checkbox"> Option 2
                    </label>
                    <label class="btn btn-primary">
                        <input type="checkbox"> Option 3
                    </label>
                </div>
                <h3><span>Radio buttons</span></h3>
                <div class="btn-group" data-toggle="buttons">
                    <label class="btn btn-primary">
                        <input type="radio" name="options" id="option1"> Option 1
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="options" id="option2"> Option 2
                    </label>
                    <label class="btn btn-primary">
                        <input type="radio" name="options" id="option3"> Option 3
                    </label>
                </div>
                <br/><br/>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="main-box">
            <header class="main-box-header clearfix">
                <h2>Inline form</h2>
            </header>
            <div class="main-box-body clearfix">
                <form class="form-inline" role="form">
                    <div class="form-group">
                        <label class="sr-only" for="exampleInputEmail2">Email address</label>
                        <input type="email" class="form-control" id="exampleInputEmail2" placeholder="Enter email">
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="exampleInputPassword2">Password</label>
                        <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Password">
                    </div>
                    <div class="checkbox checkbox-nice">
                        <input type="checkbox" id="remember-me" checked="checked" />
                        <label for="remember-me">
                            Remember me
                        </label>
                    </div>
                    <button type="submit" class="btn btn-success">Sign in</button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="main-box">
            <header class="main-box-header clearfix">
                <h2>Horizontal form</h2>
            </header>
            <div class="main-box-body clearfix">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="inputEmail1" class="col-lg-2 control-label">Email</label>
                        <div class="col-lg-10">
                            <input type="email" class="form-control" id="inputEmail1" placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword1" class="col-lg-2 control-label">Password</label>
                        <div class="col-lg-10">
                            <input type="password" class="form-control" id="inputPassword1" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-offset-2 col-lg-10">
                            <div class="checkbox-nice">
                                <input type="checkbox" id="remember-me2" checked="checked" />
                                <label for="remember-me2">
                                    Remember me
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-offset-2 col-lg-10">
                            <button type="submit" class="btn btn-success">Sign in</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="main-box">
            <header class="main-box-header clearfix">
                <h2>Password strength meters</h2>
            </header>
            <div class="main-box-body clearfix">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label for="popoverName">Username</label>
                                <input type="text" class="form-control" id="popoverName"/>
                            </div>
                            <div class="form-group col-md-12">
                                <label for="popoverPwd">Password</label>
                                <input type="password" class="form-control" id="popoverPwd"/>
                                <span class="help-block">format mm-dd-yyyy</span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label for="datepickerDateRange">Date range</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-calendar-o"></i></span>
                                    <input type="text" class="form-control" id="datepickerDateRange">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label for="datepickerDateComponent">Date readonly</label>
                                <div class="input-group" data-date-format="dd-mm-yyyy">
                                    <input type="text" class="form-control" id="datepickerDateComponent" readonly>
                                    <span class="input-group-addon"><i class="fa fa-th"></i></span>
                                </div>
                                <span class="help-block">As component.</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-6">
        <div class="main-box">
            <header class="main-box-header clearfix">
                <h2>Input groups</h2>
            </header>
            <div class="main-box-body clearfix">
                <form role="form">
                    <div class="form-group">
                        <label for="examplePrepend">Prepend</label>
                        <div class="input-group">
                            <span class="input-group-addon">@</span>
                            <input type="text" class="form-control" id="examplePrepend" placeholder="prepend text here">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exampleAppend">Append</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="exampleAppend">
                            <span class="input-group-addon">.00</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exampleAppPre">Append and Prepend</label>
                        <div class="input-group">
                            <span class="input-group-addon">$</span>
                            <input type="text" class="form-control" id="exampleAppPre">
                            <span class="input-group-addon">.00</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exmaplePrependCheck">Prepend checkbox</label>
                        <div class="input-group">
<span class="input-group-addon">
<input type="checkbox">
</span>
                            <input type="text" class="form-control" id="exmaplePrependCheck">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exampleRadio">Prepend radio</label>
                        <div class="input-group">
<span class="input-group-addon">
<input type="radio">
</span>
                            <input type="text" class="form-control" id="exampleRadio">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exmaplePrependButton">Prepend button</label>
                        <div class="input-group">
<span class="input-group-btn">
<button class="btn btn-primary" type="button">Go!</button>
</span>
                            <input type="text" class="form-control" id="exmaplePrependButton">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="examplePrependDropdown">Prepend button dropdown</label>
                        <div class="input-group">
                            <div class="input-group-btn">
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">Action <span class="caret"></span></button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Action</a></li>
                                    <li><a href="#">Another action</a></li>
                                    <li><a href="#">Something else here</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#">Separated link</a></li>
                                </ul>
                            </div>
                            <input type="text" class="form-control" id="examplePrependDropdown">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="col-lg-6">
        <div class="main-box">
            <header class="main-box-header clearfix">
                <h2>Masked inputs</h2>
            </header>
            <div class="main-box-body clearfix">
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="maskedDate">Date</label>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                            <input type="text" class="form-control" id="maskedDate">
                        </div>
                        <span class="help-block">ex. 99/99/9999</span>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="maskedPhone">Phone</label>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-phone"></i></span>
                            <input type="text" class="form-control" id="maskedPhone">
                        </div>
                        <span class="help-block">ex. (999) 999-9999</span>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="maskedPhoneExt">Phone + Ext</label>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-mobile-phone"></i></span>
                            <input type="text" class="form-control" id="maskedPhoneExt">
                        </div>
                        <span class="help-block">ex. (999) 999-9999? x99999</span>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="maskedTax">Tax ID</label>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-money"></i></span>
                            <input type="text" class="form-control" id="maskedTax">
                        </div>
                        <span class="help-block">ex. 99-9999999</span>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="maskedSsn">SSN</label>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-male"></i></span>
                            <input type="text" class="form-control" id="maskedSsn">
                        </div>
                        <span class="help-block">ex. 999-99-9999</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="main-box">
            <header class="main-box-header clearfix">
                <h2>Switches</h2>
            </header>
            <div class="main-box-body clearfix">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="pull-left">
                            <div class="onoffswitch">
                                <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch" checked>
                                <label class="onoffswitch-label" for="myonoffswitch">
                                    <div class="onoffswitch-inner"></div>
                                    <div class="onoffswitch-switch"></div>
                                </label>
                            </div>
                        </div>
                        <div class="pull-left">
                            <div class="onoffswitch onoffswitch-danger">
                                <input type="checkbox" name="onoffswitch2" class="onoffswitch-checkbox" id="myonoffswitch2" checked>
                                <label class="onoffswitch-label" for="myonoffswitch2">
                                    <div class="onoffswitch-inner"></div>
                                    <div class="onoffswitch-switch"></div>
                                </label>
                            </div>
                        </div>
                        <div class="pull-left">
                            <div class="onoffswitch onoffswitch-warning">
                                <input type="checkbox" name="onoffswitch3" class="onoffswitch-checkbox" id="myonoffswitch3" checked>
                                <label class="onoffswitch-label" for="myonoffswitch3">
                                    <div class="onoffswitch-inner"></div>
                                    <div class="onoffswitch-switch"></div>
                                </label>
                            </div>
                        </div>
                        <div class="pull-left">
                            <div class="onoffswitch onoffswitch-success">
                                <input type="checkbox" name="onoffswitch4" class="onoffswitch-checkbox" id="myonoffswitch4" checked>
                                <label class="onoffswitch-label" for="myonoffswitch4">
                                    <div class="onoffswitch-inner"></div>
                                    <div class="onoffswitch-switch"></div>
                                </label>
                            </div>
                        </div>
                        <div class="clearfix"> </div>
                        <br/>
                        <span class="help-block">
Pure CSS3 On/Off FlipSwitch - you can make your own custom on/off switch
<a href="http://proto.io/freebies/onoff/" target="_blank">here</a>
</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="main-box">
            <header class="main-box-header clearfix">
                <h2>Datepickers</h2>
            </header>
            <div class="main-box-body clearfix">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label for="datepickerDate">Date</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                    <input type="text" class="form-control" id="datepickerDate">
                                </div>
                                <span class="help-block">format mm-dd-yyyy</span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label for="datepickerDateRange">Date range</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-calendar-o"></i></span>
                                    <input type="text" class="form-control" id="datepickerDateRange">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label for="datepickerDateComponent">Date readonly</label>
                                <div class="input-group" data-date-format="dd-mm-yyyy">
                                    <input type="text" class="form-control" id="datepickerDateComponent" readonly>
                                    <span class="input-group-addon"><i class="fa fa-th"></i></span>
                                </div>
                                <span class="help-block">As component.</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="main-box">
            <header class="main-box-header clearfix">
                <h2>Timepickers</h2>
            </header>
            <div class="main-box-body clearfix">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label for="timepicker">Time picker</label>
                                <div class="input-group input-append bootstrap-timepicker">
                                    <input type="text" class="form-control" id="timepicker">
                                    <span class="add-on input-group-addon"><i class="fa fa-clock-o"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label for="clockpicker">Clock picker</label>
                                <div class="input-group clockpicker">
                                    <input type="text" class="form-control" value="09:30">
                                    <span class="input-group-addon">
                                        <span class="fa fa-clock-o"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label for="timepicker-ampm">Time picker AM/PM</label>
                                <div class="input-group input-append bootstrap-timepicker">
                                    <input type="text" class="form-control" id="timepicker-ampm">
                                    <span class="add-on input-group-addon"><i class="fa fa-clock-o"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label for="clockpicker">Clock picker with autoclose</label>
                                <div class="input-group clockpicker-autoclose">
                                    <input type="text" class="form-control" value="12:30">
                                    <span class="input-group-addon">
                                      <span class="fa fa-clock-o"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="main-box">
            <header class="main-box-header clearfix">
                <h2>Grid sizing</h2>
            </header>
            <div class="main-box-body clearfix">
                <form role="form">
                    <div class="row">
                        <div class="form-group col-xs-3">
                            <input type="text" class="form-control" placeholder=".col-xs-3">
                        </div>
                        <div class="form-group col-xs-3">
                            <input type="text" class="form-control" placeholder=".col-xs-3">
                        </div>
                        <div class="form-group col-xs-3">
                            <input type="text" class="form-control" placeholder=".col-xs-3">
                        </div>
                        <div class="form-group col-xs-3">
                            <input type="text" class="form-control" placeholder=".col-xs-3">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-xs-4">
                            <input type="text" class="form-control" placeholder=".col-xs-4">
                        </div>
                        <div class="form-group col-xs-4">
                            <input type="text" class="form-control" placeholder=".col-xs-4">
                        </div>
                        <div class="form-group col-xs-4">
                            <input type="text" class="form-control" placeholder=".col-xs-4">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-xs-6">
                            <input type="text" class="form-control" placeholder=".col-xs-6">
                        </div>
                        <div class="form-group col-xs-6">
                            <input type="text" class="form-control" placeholder=".col-xs-6">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-xs-7">
                            <input type="text" class="form-control" placeholder=".col-xs-7">
                        </div>
                        <div class="form-group col-xs-5">
                            <input type="text" class="form-control" placeholder=".col-xs-5">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-xs-12">
                            <input type="text" class="form-control" placeholder=".col-xs-12">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    $(function($) {
        //tooltip init
        $('#exampleTooltip').tooltip();

        //nice select boxes
        $('#sel2').select2();

        $('#sel2Multi').select2({
            placeholder: 'Select a Country',
            allowClear: true
        });

        //masked inputs
        $("#maskedDate").mask("99/99/9999");
        $("#maskedPhone").mask("(999) 999-9999");
        $("#maskedPhoneExt").mask("(999) 999-9999? x99999");
        $("#maskedTax").mask("99-9999999");
        $("#maskedSsn").mask("999-99-9999");

        $("#maskedProductKey").mask("a*-999-a999",{placeholder:" ",completed:function(){alert("You typed the following: "+this.val());}});

        $.mask.definitions['~']='[+-]';
        $("#maskedEye").mask("~9.99 ~9.99 999");

        //datepicker
        $('#datepickerDate').datepicker({
            format: 'mm-dd-yyyy',
            autoclose: true
        });

        $('#datepickerDateComponent').datepicker({
            format: 'mm-dd-yyyy',
            autoclose: true
        });

        //daterange picker
        $('#datepickerDateRange').daterangepicker();

        //timepicker
        $('#timepicker').timepicker({
            minuteStep: 5,
            showSeconds: true,
            showMeridian: false,
            disableFocus: false,
            showWidget: true,
            disableMousewheel: true
        }).focus(function() {
            $(this).next().trigger('click');
        });

        $('#timepicker-ampm').timepicker({
            minuteStep: 5,
            showSeconds: false,
            showMeridian: true,
            disableFocus: false,
            showWidget: true,
            disableMousewheel: true,
            showInputs: false
        }).focus(function() {
            $(this).next().trigger('click');
        });

        //clockpicker
        $('.clockpicker').clockpicker({
            donetext: 'Done'
        });

        $('.clockpicker-autoclose').clockpicker({
            donetext: 'Done',
            autoclose: true,
            placement: 'left',
            align: 'top'
        });

        //autocomplete simple
        $('#exampleAutocompleteSimple').typeahead({
            prefetch: '/data/countries.json',
            limit: 10
        });

        //autocomplete with templating
        $('#exampleAutocomplete').typeahead({
            name: 'twitter-oss',
            prefetch: '/data/repos.json',
            template: [
                '<p class="repo-language">{{language}}</p>',
                '<p class="repo-name">{{name}}</p>',
                '<p class="repo-description">{{description}}</p>'
            ].join(''),
            engine: Hogan
        });

        //password strength meter
        $('#examplePwdMeter').pwstrength({
            label: '.pwdstrength-label'
        });


        var options = {};
        options.ui = {
            showPopover: true,
            showErrors: true,
            showProgressBar: false
        };
        options.rules = {
            activated: {
                wordTwoCharacterClasses: true,
                wordRepetitions: true
            }
        };
        options.common = {
            debug: true,
            usernameField: "#popoverName"
        };
        $('#popoverPwd').pwstrength(options);

    });
</script>