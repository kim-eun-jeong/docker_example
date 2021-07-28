if (typeof (Utils) == "undefined") var Utils = {};

(function($) {
	Utils.Validation = function() {
		this.initialize.apply(this, arguments);
	};

	Utils.Validation.prototype = {
		initialize : function(hash) {

		},

		nonValueCheck : function(id, content, remove) {
			var flag = true;
			var This = id;

			if ($.type(id) == 'string') This = $('#' + id);
			if ($.trim(This.val()) == '') {
				if (This.attr('type') == 'text' || This.attr('type') == 'password') alert(content + '을(를) 입력해야 합니다.');
				else alert(content + '을(를) 선택해야 합니다.');
				This.focus();
				if (remove) This.val('');
				flag = false;
			}

			return flag;
		},

		nonValueCheckTA : function(id, content, remove) {
			var flag = true;
			var This = id;

			if ($.type(id) == 'string') This = $('#' + id);
			if ($.trim(This.val()) == '') {
				alert(content + '을(를) 입력해야 합니다.');
				This.focus();
				if (remove) This.val('');
				flag = false;
			}

			return flag;
		},

		onlyNumCheck : function(id, content, remove) {
			var flag = true;
			var This = id;

			if ($.type(id) == 'string') This = $('#' + id);
			if (/[^0-9]/g.test(This.val())) {
				alert(content + '는 숫자로만 구성되어야 합니다.');
				This.focus();
				if (remove) This.val('');
				flag = false;
			}

			return flag;
		},

		phoneCheck : function(id, content, remove) {
			var flag = true;
			var This = id;

			if ($.type(id) == 'string') This = $('#' + id);
			if (!/^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/.test(This.val())) {
				alert('Phone 형식에 맞게 입력해 주세요.\nex)010-1234-1234');
				This.focus();
				if (remove) This.val('');
				flag = false;
			}

			return flag;
		},

		ipCheck : function(id, content, remove) {
			var flag = true;
			var This = id;

			if ($.type(id) == 'string') This = $('#' + id);
			if (!/^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$/.test(This.val())) {
				alert('IP주소 형식에 맞게 입력해 주세요.\nex)127.0.0.1');
				This.focus();
				if (remove) This.val('');
				flag = false;
			}

			return flag;
		},

		emailCheck : function(id, content, remove) {
			var flag = true;
			var This = id;

			if ($.type(id) == 'string') This = $('#' + id);
			if (!/^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{2,5}$/.test(This.val())) {
				alert('이메일 형식에 맞게 입력해 주세요.\nex)abc@abc.com');
				This.focus();
				if (remove) This.val('');
				flag = false;
			}

			return flag;
		},

		passwordCompare : function(id, rid, content, remove) {
			var flag = true;

			if ($('#' + id).val() != $('#' + rid).val()) {
				alert('비밀번호와 비밀번호 확인은 동일해야 합니다.');
				$('#' + id).focus();
				if (remove) {
					$('#' + id).val('');
					$('#' + rid).val('');
				}
				flag = false;
			}

			return flag;
		},

		passwordPolicy : function(id, content, remove) {
			var flag = true;
			var This = id;
			var check1 = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;
			var check2 = /^(?=.*[a-zA-Z])(?=.*[0-9]).{10,20}$/;

			if ($.type(id) == 'string') This = $('#' + id);
			if (!(check1.test($.trim(This.val())) || check2.test($.trim(This.val())))) {
				alert('영문, 숫자조합은 10~20자리\n영문, 특문, 숫자조합은 8~20 자리로 만들어 주세요.');
				This.focus();
				if (remove) This.val('');
				flag = false;
			}

			return flag;
		},
		
		passwordPolicy1 : function(id, pw, content, remove) {
			var flag = true;
			
			var id = $('#' + id);
			var pw = $('#' + pw);
			var idStr = $.trim(id.val()); 
			var pwStr = $.trim(pw.val()); 
			
			//특수기호
			var reg_exp_special = /.*[~,`,!,@,#,$,%,^,&,*,(,),_,+,=,"{",\[,"}",\],\|,\\,:,;,""","'",<,",",>,.,?,\/,-].*/;
			//대문자
			var reg_exp_upper = /.*[A-Z].*/;
			//소문자
			var reg_exp_lower = /.*[a-z].*/;
			//숫자
			var reg_exp_number = /.*[0-9].*/;
			var count_exp_num = 0;
			
			if(reg_exp_special.test(pwStr)) { count_exp_num += 1; }
			if(reg_exp_upper.test(pwStr)) { count_exp_num += 1; }
			if(reg_exp_lower.test(pwStr)) { count_exp_num += 1; }
			if(reg_exp_number.test(pwStr)) { count_exp_num += 1; }
			
			if(pwStr.indexOf(idStr) > -1) {
				pw.focus();
				alert("비밀번호에 아이디를 사용할 수 없습니다.");
				return false;
			}
			
			if(count_exp_num < 2) {
				pw.focus();
				alert("영문 대/소문자, 숫자, 특수문자 2종류 이상 혼합 10~20자 이하,\n3종류 이상 혼합 8~20자 이하로 설정해주세요");
				return false;
			} else if(count_exp_num == 2) {
				if(!rValidation.strLenCheck(pwStr, 10, 20)) {
					pw.focus();
					alert("영문 대/소문자, 숫자, 특수문자 2종류 이상 혼합 10~20자 이하,\n3종류 이상 혼합 8~20자 이하로 설정해주세요");
					return false;
				}
			} else if(count_exp_num > 2) {
				if(!rValidation.strLenCheck(pwStr, 8, 20)) {
					pw.focus();
					alert("영문 대/소문자, 숫자, 특수문자 2종류 이상 혼합 10~20자 이하,\n3종류 이상 혼합 8~20자 이하로 설정해주세요");
					return false;
				}
			}
			
			var SamePass_0 = 0; //동일문자 카운트
			var SamePass_1 = 0; //연속성(+) 카운드
			var SamePass_2 = 0; //연속성(-) 카운드
			
			var chr_pass_0;
			var chr_pass_1;
			
			for(var i=0; i < pwStr.length-1; i++) {
				chr_pass_0 = pwStr.charAt(i);
				chr_pass_1 = pwStr.charAt(i+1);
				
				//동일문자 카운트
				if(chr_pass_0 == chr_pass_1) {
					SamePass_0 = SamePass_0 + 1;
				} else {
					if(SamePass_0 < 2){
						SamePass_0 = 0;
					}
				}
				
				//연속성(+) 카운드
				if(chr_pass_0.charCodeAt(0) - chr_pass_1.charCodeAt(0) == 1) {
					SamePass_1 = SamePass_1 + 1;
				} else {
					if(SamePass_1 < 2){
						SamePass_1 = 0;
					}
				}
				
				//연속성(-) 카운드
				if(chr_pass_0.charCodeAt(0) - chr_pass_1.charCodeAt(0) == -1) {
					SamePass_2 = SamePass_2 + 1;
				} else {
					if(SamePass_2 < 2){
						SamePass_2 = 0;
					}
				}
			}
			
			if(SamePass_0 > 1) {
				pw.focus();
				alert("동일문자를 3번 이상 사용할 수 없습니다.");		
				return false;
			}
			
			if(SamePass_1 > 2 || SamePass_2 > 2 )  {
				pw.focus();
				alert("연속된 문자열(123 또는 321, abc, cba 등)을 \n3자 이상 사용 할 수 없습니다.");
				return false;
			}
			
			return flag;
		},
		
		passwordPolicyAd : function(id, content, userId, remove) {
			var flag = true;
			var This = id;
			var password = '';
			
			var check1 = /^.*(?=.{7,20})(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[.!@#$%^*+=-]).*$/;    //숫자, 영문 대문자, 영문 소문자, 특수문자
			var check2 = /^.*(?=.{7,20})(?=.*[a-z])(?=.*[A-Z])(?=.*[.!@#$%^*+=-]).*$/;               //영문 대문자, 영문 소문자, 특수문자
			var check3 = /^.*(?=.{7,20})(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$/;                       //숫자, 영문 소문자, 특수문자
			var check4 = /^.*(?=.{7,20})(?=.*[0-9])(?=.*[A-Z])(?=.*[.!@#$%^*+=-]).*$/;               //숫자, 영문 대문자, 특수문자
			var check5 = /^.*(?=.{7,20})(?=.*[0-9])(?=.*[a-z])(?=.*[.!@#$%^*+=-]).*$/;               //숫자, 영문 대문자, 영문 소문자

			if ($.type(id) == 'string'){
				This = $('#' + id);
				password = $.trim(This.val());
			}
			
			if ( !( check1.test(password) || check2.test(password) || check3.test(password) || check4.test(password) || check5.test(password) ) ) {
				alert('비밀번호는 영문 대/소문자, 숫자, 특수문자 중 3종류 이상 혼합하여 7~20자 이하로 설정해 주세요');
				This.focus();
				if (remove) This.val('');
				flag = false;
			} else if(password.search(userId)>-1) {
				alert('ID가 포함된 비밀번호는 사용할 수 없습니다.');
				This.focus();
				if (remove) This.val('');
				flag = false;
			}
			
			return flag;
		},

		lengthOverCheck : function(id, content, len, remove) {
			var flag = true;
			var This = id;

			if ($.type(id) == 'string') This = $('#' + id);
			if ($.trim(This.val()).length > len) {
				alert(content + '은(는) 최대' + len + '자 이하로 입력해야 합니다.');
				This.focus();
				if (remove) This.val('');
				flag = false;
			}

			return flag;
		},

		lengthLessCheck : function(id, content, len, remove) {
			var flag = true;
			var This = id;

			if ($.type(id) == 'string') This = $('#' + id);
			if ($.trim(This.val()).length < len) {
				alert(content + '은(는) 최소' + len + '자 이상 입력해야 합니다.');
				This.focus();
				if (remove) This.val('');
				flag = false;
			}

			return flag;
		},

		numScopeCheck : function(id, content, min, max, remove) {
			var flag = true;
			var This = id;

			if ($.type(id) == 'string') This = $('#' + id);
			if (min > parseInt(This.val(), 10) || max < parseInt(This.val(), 10)) {
				alert(content + '의 범위는 ' + min + ' ~ ' + max + ' 입니다');
				This.focus();
				if (remove) This.val('');
				flag = false;
			}

			return flag;
		},
		
		regNoCheck : function(id1, id2) {
			var flag = true;
			
			var regNo = $.trim($('#' + id1).val()) + $.trim($('#' + id2).val());
			
			if(regNo.length == 13) {
				var arrRegNo = regNo.split('');
				var arrNum = [1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2];
				
				var sum = 0;
				for(var i=0; i < 12; i++) {
					sum += arrRegNo[i] * arrNum[i];
				}
				
				var errorNum = 10 - (sum % 10);
				
				if(arrRegNo[12] != errorNum) {
					flag = false;
					$('#' + id1).focus();
					alert('유효한 법인등록번호를 입력해 주세요');
				}
			} else {
				flag = false;
				$('#' + id1).focus();
				alert('법인등록번호는 13자로 입력해야 합니다.');
			}
			
			return flag;
		},
		
		bizNoCheck : function(id1, id2, id3) {
			var flag = true;
			
			var bizNo = $.trim($('#' + id1).val()) + $.trim($('#' + id2).val()) + $.trim($('#' + id3).val());
			
			if(bizNo.length == 10) {
				var checkID = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5, 1); 
			    var tmpBizID, i, chkSum=0, c2, remander; 
			    bizNo = bizNo.replace(/-/gi,''); 

			     for (i=0; i<=7; i++) chkSum += checkID[i] * bizNo.charAt(i); 
			     c2 = "0" + (checkID[8] * bizNo.charAt(8)); 
			     c2 = c2.substring(c2.length - 2, c2.length); 
			     chkSum += Math.floor(c2.charAt(0)) + Math.floor(c2.charAt(1)); 
			     remander = (10 - (chkSum % 10)) % 10 ; 

			    if(Math.floor(bizNo.charAt(9)) != remander) {
			    	flag = false;
					$('#' + id1).focus();
					alert('유효한 사업자등록번호를 입력해 주세요.');
			    }
			} else {
				flag = false;
				$('#' + id1).focus();
				alert('사업자등록번호는 10자로 입력해야 합니다.');
			}
			
			return flag;
		},
		
		onlyEngNumCheck : function(id, content, remove) {
			var flag = true;
			var This = id;

			if ($.type(id) == 'string') This = $('#' + id);
			if (!/^[A-Za-z0-9+]*$/.test(This.val())) { 
				alert(content + '는 영문 및 숫자로만 구성되어야 합니다.');
				This.focus();
				if (remove) This.val('');
				flag = false;
			} 

			return flag;
		},
		
		phoneCheckStr : function(str, content) {
			var flag = true;

			if (!/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/.test(str)) {
				alert(content + '을(를) 전화번호 형식에 맞게 입력해 주세요.');
				flag = false;
			}

			return flag;
		},
		
		emailCheckStr : function(str, content) {
			var flag = true;

			if (!/^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{2,5}$/.test(str)) {
				alert(content + '을(를) 이메일 형식에 맞게 입력해 주세요.\nex)abc@abc.com');
				flag = false;
			}

			return flag;
		},
		
		strLenCheck : function(str, min, max) {
			var flag = true;
			
			var len = str.length
			if(len < min || len > max) {
				flag = false;
			}
			
			return flag;
		}
	};
})(jQuery);

var rValidation = new Utils.Validation();

var now = new Date();
var year= now.getFullYear();
var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();        
var today = year + '-' + mon + '-' + day;

function complDueCheck(compDue){
	var complDueTm_flag = $('#'+compDue).val() != "" && $('#'+compDue).val() != null;		// end_day가 값이 없을 때
	if (!complDueTm_flag) {
		alert("작업완료 요청일을 입력해 주세요.");
		$('#'+compDue).focus();
		return false;
	} else if($('#'+compDue).val() < today){
		alert("미래 시간을 입력해 주세요.");
		$('#'+compDue).focus();
		return false;
	} else {
		return true;
	}
}

function dateCheck(startDate, endDate){
	var start_day_flag = $('#'+startDate).val() != "" && $('#'+startDate).val() != null;	// start_day가 값이 있을 때
	var end_day_flag = $('#'+endDate).val() != "" && $('#'+endDate).val() != null;		// end_day가 값이 없을 때
    
    if(start_day_flag){
		var one_month = $('#'+startDate).datepicker('getDate');
		one_month.setMonth(one_month.getMonth()+1);
	}
    
    if(end_day_flag){
		end_day = $('#'+endDate).datepicker('getDate');
	}
    
	if(!start_day_flag  && !end_day_flag){
		alert("기간을 입력해주세요.");
		$('#'+startDate).focus();
		return false;
	}else if(start_day_flag  && !end_day_flag ){
		alert("종료일을 입력해주세요.");
		$('#'+endDate).focus();
		return false;
	}else if(!start_day_flag && end_day_flag ){
		alert("시작일을 입력해주세요.");
		$('#'+startDate).focus();
		return false;
	}else if($('#'+startDate).val() > $('#'+endDate).val()){
		alert("종료일이 시작일보다 이전입니다.");
		$('#'+endDate).focus();
		return false;
	} else if($('#'+startDate).val() > today || $('#'+endDate).val() > today){
		alert("미래 시간을 입력할 수 없습니다.");
		$('#'+startDate).focus();
		return false;
	} else if(end_day > one_month){
		alert("1개월 안으로 선택해 주세요. 최대 조회기간은 1개월 입니다.");
		$('#'+endDate).datepicker('setDate',one_month);
		return false;
	} else {
		return true;
	}
}

function lengthCheck(id, content, len) {
	var flag = true;
	var This = id;

	if ($.type(id) == 'string') This = $('#' + id);
	if ($.trim(This.val()).length >= len) {
		alert(content + '은(는) 최대' + len + '자 이하로 입력해야 합니다.');
		This.focus();
		flag = false;
	}

	return flag;
}

function passwordCompare(id, rid) {
	console.log(id+"  "+rid)
	var flag = true;
	if ($('#' + id).val() != $('#' + rid).val()) {
		alert('비밀번호와 비밀번호 확인은 동일해야 합니다.');
		$('#' + id).focus();
		$('#' + id).val('');
		$('#' + rid).val('');
		flag = false;
	}

	return flag;
}

function isFileSizeOver(data){
	var maxSize = 0;
	for (var i in data) {
		if (data[i].name == "maxFileSize") {
			maxSize = data[i].value;
		}
		if (data[i].name == "file") {
			if (data[i].value.size > maxSize) {
				alert('파일 용량은 1GB 이하로 해주세요.');
				return false;
			} else {
				return true;
			}
		}
	}
	return true;
}