﻿(function() {
	$.fn.alert = function(options) {
		var defaults = {
			type : 'success',
			title : '提示',
			content : '恭喜，操作成功！',
			buttons : [ {
				id : 'chur',
				name : '确定',
				callback : ''
			} ],
			modal : true,
			draggabled : false,
			tourl : '',
		};
		var titles = {
			'success' : '成功提示',
			'info' : '询问提示',
			'warning' : '警告提示',
			'primary' : '权限提示',
			'danger' : '出错提示'
		};
		var contents = {
			'success' : '恭喜，操作成功！',
			'info' : '你确定要删除这条数据吗？',
			'warning' : '警告！数据无价，请谨慎操作！',
			'primary' : '对不起您没有此项操作权限！',
			'danger' : '抱歉，操作失败！'
		};
		if (!options['title'] || options['title'] == "") {
			options['title'] = titles[options['type']];
		}
		;
		if (!options['content'] || options['content'] == "") {
			options['content'] = contents[options['type']];
		}
		;
		var o = $.extend(defaults, options);
		var _modal = '<iframe class="alert-modal"></iframe>';
		function closed() {
			$('.chur-alert').remove();
			$('.alert-modal').remove();
			$.each(o.buttons, function(i, row) {
				row["callback"] = null;
			})
		}
		;
		var _button = "";
		$.each(o.buttons, function(i, row) {
			_button += '<input type="button" class="btn closed btn-' + o.type
					+ '" id="' + row["id"] + '" value="' + row["name"]
					+ '"/>&nbsp;';
			$('#' + row["id"]).live('click', function() {
				try {
					row["callback"]();
				} catch (err) {
				}
			})
		});
		var _html = '<div class="chur-alert"><div class="alert alert-' + o.type
				+ '">' + '<a class="close closed" href="#">&times</a>'
				+ '<h4 class="alert-heading">' + o.title + '</h4>'
				+ '<div class="context">' + o.content + '</div>'
				+ '<div class="rightbtn">' + _button + '</div></div></div>';
		if ($('.alert-' + o.type).length < 1) {
			$('body').append(_html);
			$('.alert-' + o.type).show('fast').find('.closed').live('click',
					closed);
			if (o.modal) {
				$('body').append(_modal);
			}
			;
			if (o.draggabled) {
				$('.alert').draggable({
					'containment' : 'body'
				});
			}
		}
		;
	};
})();