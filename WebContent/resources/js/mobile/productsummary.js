var productsummary = (function() {
	var url = '/sportsstore/mobile/product/list/';
	
	function pageInit() {
		$('#page2').live('pageinit', init);
		$('#page2').live('pageremove', remove);
	}

	function init(evt) {
		$(document).bind('scrollstop', contentScrollStop);
		$('#cmdrefresh').click(refresh);
		$(document).jqmData('pagenum', 2);
		$(document).jqmData('scrolltop', 0);
	}

	function remove(evt) {
		$(document).unbind('scrollstop', contentScrollStop);
		$('#cmdrefresh').unbind('click');
		$(document).jqmRemoveData('pagenum');
		$(document).jqmRemoveData('scrolltop');
	}

	function refresh() {
		var pagenum = 1;
		loadContent(pagenum, false);
	}

	function contentScrollStop() {
		var w = $(window);
		var st = w.scrollTop();
		var wh = w.height();
		var dh = $(document).height();
		var a = isNearBottom(st, wh, dh);
		if (a) {
			var pagenum = $(document).jqmData('pagenum');
			var scrolltop = $(document).jqmData('scorlltop');
			
			if (scrolltop > st)
				return;
			
			$(document).jqmData('scrolltop', st);
			loadContent(pagenum, true);
		}
	}

	function loadContent(page, append) {
		var category = $('#category').val();
		var returnUrl = $('#returnurl').val();
		
		var data = {returnUrl: returnUrl};
		if (category != '')
			data['category'] = category;

		var u = url + page;
		
		$.mobile.showPageLoadingMsg();
		$.post(u, data,
			function(result) {
				var res = $.trim(result);
				if (res != '') {
					++page;
					$(document).jqmData('pagenum', page);
					if (append) {
						$('#page2ct > ul').append(result);
						gotoBottom();
					}
						
					else
						$('#page2ct > ul').html(result);

					$('#page2ct').trigger('pagecreate');
					$('#page2ct > ul').listview('refresh');
				}
				
				else {
					if (!append)
						$('#page2ct > ul').empty();
				}
				
				$.mobile.hidePageLoadingMsg();
			});
	}

	/**
	 * This method checks whether the scroll position is near bottom of the page.
	 * @param {Number} scrollTop window's scrollTop
	 * @param {Number} wh window's height
	 * @param {Number} dh document's height
	 * @return true if near bottom, false otherwise
	 */
	function isNearBottom(scrollTop, wh, dh) {
		if ((scrollTop + wh) > (dh - 100))
			return true;
			
		return false;
	}
	
	function gotoBottom() {
		var w = $(window);
		var wh = w.height();
		var dh = $(document).height();
		w.scrollTop(dh - (wh + 30));
	}

	return {
		pageInit: pageInit
	};
}());

productsummary.pageInit();