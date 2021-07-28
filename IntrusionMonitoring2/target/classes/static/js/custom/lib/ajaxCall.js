if (typeof (Utils) == "undefined") var Utils = {};

$(function() {
	rAjaxCall.afterInitialize();
});

(function($) {
	Utils.AjaxCall = function() {
		this.contextPath = '';

		this.initialize.apply(this, arguments);
	};

	Utils.AjaxCall.prototype = {
		initialize : function(hash) {
		},

		afterInitialize : function() {
			// -- ContextPath Set
			if ($('#contextPath').val() != undefined && $('#contextPath').val() != '/') {
				this.contextPath = $('#contextPath').val();
			}

			// -- preFilter
		},

		// -- 비동기 처리 Ajax (내부)
		async : function(url, type, data, successFunc, errorFunc) {
			$.ajax({
				url : this.contextPath + url,
				type : type,
				data : data,
				dataType : 'JSON',
				cache : false,
				error : errorFunc,
				success : successFunc
			});
		},

		// -- 동기 처리 Ajax (내부)
		sync : function(url, type, data) {
			var result = null;

			$.ajax({
				url : this.contextPath + url,
				type : type,
				data : data,
				dataType : 'JSON',
				cache : false,
				async : false,
				success : function(r) {
					result = r;
				}
			});

			return result;
		},

		// -- 비동기 처리 Ajax (외부)
		asyncP : function(url, type, data, successFunc) {
			$.ajax({
				url : url,
				type : type,
				data : data,
				dataType : 'JSONP',
				cache : false,
				success : successFunc
			});
		},

		// -- 동기 처리 Ajax (외부)
		syncP : function(url, type, data) {
			var result = null;

			$.ajax({
				url : url,
				type : type,
				data : data,
				dataType : 'JSONP',
				cache : false,
				async : false,
				success : function(r) {
					result = r;
				}
			});

			return result;
		},
		
		// -- 비동기 처리 Ajax HTML
		asyncH : function(url, type, data, successFunc, errorFunc) {
			$.ajax({
				url : this.contextPath + url,
				type : type,
				data : data,
				dataType : 'HTML',
				cache : false,
				error : errorFunc,
				success : successFunc
			});
		},

		// -- 동기 처리 Ajax  HTML
		syncH : function(url, type, data) {
			var result = null;

			$.ajax({
				url : this.contextPath + url + '.do',
				type : type,
				data : data,
				dataType : 'HTML',
				cache : false,
				async : false,
				success : function(r) {
					result = r;
				}
			});

			return result;
		},

		// -- popup Html body 부분만 호출 (내부)
		popHtml : function(url) {
			var html = null;

			$.ajax({
				url : this.contextPath + '/popup' + url + '.html',
				type : 'GET',
				dataType : 'html',
				cache : false,
				async : false,
				success : function(r) {
					if (r.indexOf('<body>') != -1 && r.indexOf('</body>') != -1) {
						html = r.split('<body>')[1].split('</body>')[0];
					}
				}
			});

			return html;
		}
	};
})(jQuery);

var rAjaxCall = new Utils.AjaxCall();