$(function($) {
	setTimeout(function() {
        $('#content-wrapper > .row').css({
            opacity: 1
        });
    },200);
	
	$('#sidebar-nav,#nav-col-submenu').on('click', '.dropdown-toggle',
		function(e) {
			e.preventDefault();
			var $item = $(this).parent();
			if (!$item.hasClass('open')) {
				$item.parent().find('.open .submenu').slideUp('fast');
				$item.parent().find('.open').toggleClass('open');
			}
			$item.toggleClass('open');
			if ($item.hasClass('open')) {
				$item.children('.submenu').slideDown('fast');
			} else {
				$item.children('.submenu').slideUp('fast');
			}
		}
	);
	$('body').on('mouseenter', '#page-wrapper.nav-small #sidebar-nav .dropdown-toggle',
		function(e) {
			if ($(document).width() >= 992) {
				var $item = $(this).parent();
				if ($('body').hasClass('fixed-leftmenu')) {
					var topPosition = $item.position().top;
					if ((topPosition + 4 * $(this).outerHeight()) >= $(window).height()) {
						topPosition -= 6 * $(this).outerHeight();
					}
					$('#nav-col-submenu').html($item.children('.submenu').clone());
					$('#nav-col-submenu > .submenu').css({
						'top': topPosition
					});
				}
				$item.addClass('open');
				$item.children('.submenu').slideDown('fast');
			}
		}
	);
	$('body').on('mouseleave', '#page-wrapper.nav-small #sidebar-nav > .nav-pills > li',
		function(e) {
			if ($(document).width() >= 992) {
				var $item = $(this);
				if ($item.hasClass('open')) {
					$item.find('.open .submenu').slideUp('fast');
					$item.find('.open').removeClass('open');
					$item.children('.submenu').slideUp('fast');
				}
				$item.removeClass('open');
			}
		}
	);

	
	
	$('#make-small-nav').click(function(e) {
        $('#page-wrapper').toggleClass('nav-small');
    });
});
