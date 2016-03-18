function productview(data, type, full) {
  
	// var sizeOfPkey = Object.size(data);
	var dataAsString = new String(data);
	return '<a href="/ims/admin/product/' + data + '">'
			+ convertPkeyToRegistrationId(dataAsString) + '</a>';
}

function productedit(data, type, full) {
	  
	// var sizeOfPkey = Object.size(data);
	var dataAsString = new String(data);
	return '<a href="/ims/admin/product/edit/' + data + '">'+data+'</a>';
}

function purchaseorderedit(data, type, full) {	  
	var dataAsString = new String(data);
	return '<a href="/ims/admin/purchaseorder/edit/' + data + '">'+data+'</a>';
}

function stockedit(data, type, full) {
	  
	// var sizeOfPkey = Object.size(data);
	var dataAsString = new String(data);
	return '<a href="/ims/admin/stock/edit/' + data + '">'+data+'</a>';
}

function stockSearchAction(data, type, full) {
	return '<div class="btn-group pull-right"> '
			+ '<button type="button" class="btn btn-success " style="padding-bottom: 1px;padding-left: 6px;padding-right: 6px;padding-top: 3px;">Action</button>'
			+ '<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="padding-bottom: 10px; padding-top: 10px;">'
			+ '<span class="caret"></span>'
			+ '<span class="sr-only">Toggle Dropdown</span>' + '</button>'
			+ '<ul class="dropdown-menu" role="menu">'
			+ '<li><a href="/ims/admin/stock/delete/' + full.pkey
			+ '">Delete Stock</a></li>' + '</ul></div>';
}

function productSearchAction(data, type, full) {
	return '<div class="btn-group pull-right"> '
			+ '<button type="button" class="btn btn-success " style="padding-bottom: 1px;padding-left: 6px;padding-right: 6px;padding-top: 3px;">Action</button>'
			+ '<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="padding-bottom: 10px; padding-top: 10px;">'
			+ '<span class="caret"></span>'
			+ '<span class="sr-only">Toggle Dropdown</span>' + '</button>'
			+ '<ul class="dropdown-menu" role="menu">'
			+ '<li><a href="/ims/admin/product/' + full.productId
			+ '">Product Details</a></li>' 
			+ '<li><a href="/ims/admin/product/delete/' + full.productId
			+ '">Delete</a></li>' + '</ul></div>';
}

function purchaseOrderSearchAction(data, type, full) {
	return '<div class="btn-group pull-right"> '
			+ '<button type="button" class="btn btn-success " style="padding-bottom: 1px;padding-left: 6px;padding-right: 6px;padding-top: 3px;">Action</button>'
			+ '<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="padding-bottom: 10px; padding-top: 10px;">'
			+ '<span class="caret"></span>'
			+ '<span class="sr-only">Toggle Dropdown</span>' + '</button>'
			+ '<ul class="dropdown-menu" role="menu">'
			+ '<li><a href="/ims/admin/purchaseorder/' + full.pkey
			+ '">Purchase Order Details</a></li>' +
			'<li><a href="/ims/admin/purchaseorder/delete/' + full.pkey
			+ '">Delete</a></li>' +
			'</ul></div>';
}

function userView(data, type, full) {
	return '<a href="/ims/admin/user/' + data + '">' + data + '</a>';
}

function jobView(data, type, full) {
	return '<a href="/ims/admin/job/' + data + '">' + data + '</a>';
}

function jobSchedule(data, type, full) {
	var dataAsString = new String(data);
	if(dataAsString == "false") {
		return "No";
	}
	if(dataAsString == "true") {
		return "Yes";
	} 
	return "No";
}

function jobedit(data, type, full) {
	  
	// var sizeOfPkey = Object.size(data);
	var dataAsString = new String(data);
	return '<a href="/ims/admin/job/edit/' + data + '">'+data+'</a>';
}


function screeningView(data, type, full) {

	// var sizeOfPkey = Object.size(data);

	var dataAsString = new String(data);
	var selectedList = ($('#session').val());
	
	
	function checkerFun(){
		var checkedIds =($('#hdnCheckBoxValues').val()).split(",");
		for(var iLoop=0;checkedIds.length>iLoop;iLoop++){
			if(full.pkey==checkedIds[iLoop])
				return "checked";
			
		}
		
	}
	var isChecked= checkerFun();
	
	
	return '<input id="'+full.pkey+'" type=\"checkbox\" '+isChecked+' name=\"volunteerpkey\"  value="'
			+ full.pkey + '" >' + '<a href="/ims/admin/screening/' + data + '">'
			+ convertPkeyToRegistrationId(dataAsString) + '</a>';
	 
}
function nolength()

{
	var stringchecklength=$("#hdnCheckBoxValues").val();
	 if(stringchecklength=="")
	  {
	  document.getElementById("displaycheckboxlength").innerHTML =0;
	  document.getElementById("countmaster").value =0;
	  }
	 else
	  {
	 var arraychecklength=stringchecklength.split(',');
	 
	 var convertstring=arraychecklength.length;
	 convertstring.tostring;
	 
	 
	 document.getElementById("displaycheckboxlength").innerHTML =convertstring;
	 document.getElementById("countmaster").value =convertstring;
	  }
	
}

function dtConversionView(data, type, full) {

	if (data == null || data == '' || data == 'null') {

		data = "";
	} else {
		return moment(new Date(data)).format('DD-MM-YYYY');
	}
}

function studyView(data, type, full) {

	// var sizeOfPkey = Object.size(data);
	var dataAsString = new String(data);

	return '<a href="/ims/admin/study/' + data + '">'
			+ convertPkeyToRegistrationId(dataAsString) + '</a>';
}

function regScreeningView(data, type, full) {

	// var sizeOfPkey = Object.size(data);
	var dataAsString = new String(data);

	return ('/ims/admin/screening/search/screeninglist/' + data);
}
function volunteerStatusView(str) {
	if (str != null) {
		while (str.indexOf('_') > -1) {
			str = str.replace('_', ' ');
		}

	}
	return str;
}

function convertPkeyToRegistrationId(str) {
	var registrationId = 'AZ';
	for (var i = 0; i < 6 - str.length; i++) {
		registrationId = registrationId + "0";
	}
	registrationId = registrationId + str;
	return registrationId;
}

function toDateTemp(data, type, full) {

 	 if(data == "null" || data == null){
 		 return "";
 	 }
	return (moment(new Date(data)).format('DD-MMM-YYYY')).toUpperCase();
}

function toStatus(data, type, full) {

	var testStatus = 'No';

	if (data != null && data != '') {

		if (data == 1) {

			testStatus = 'Yes'
		}

	}

	return testStatus;
}

function volunteerRegistrationId(data, type, full) {
	var sizeOfPkey = Object.size(data);
	return sizeOfPkey;
}
function volunteerSearchAction(data, type, full) {
	return '<div class="btn-group pull-right"> '
			+ '<button type="button" class="btn btn-success " style="padding-bottom: 1px;padding-left: 6px;padding-right: 6px;padding-top: 3px;">Action</button>'
			+ '<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="padding-bottom: 10px; padding-top: 10px;">'
			+ '<span class="caret"></span>'
			+ '<span class="sr-only">Toggle Dropdown</span>' + '</button>'
			+ '<ul class="dropdown-menu" role="menu">'
			+ '<li><a href="/ims/admin/volunteer/' + full.pkey
			+ '">Volunteer Details</a></li>'
			+ '<li><a href="/ims/admin/volunteer/printPdf/' + full.pkey
			+ '">Print Form</a></li>'
			+ '<li><a href="/ims/admin/volunteer/print/' + full.pkey
			+ '">Print ID Card</a></li>'
			+ '<li><a href="/ims/admin/volunteer/captureImage/' + full.pkey
			+ '">Capture Image</a></li>' + '</ul></div>';
}
function sponserSearchAction(data, type, full) {
	return '<div class="btn-group pull-right"> '
	+ '<button type="button" class="btn btn-success " style="padding-bottom: 1px;padding-left: 6px;padding-right: 6px;padding-top: 3px;">Action</button>'
	+ '<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="padding-bottom: 10px; padding-top: 10px;">'
	+ '<span class="caret"></span>'
	+ '<span class="sr-only">Toggle Dropdown</span>' + '</button>'
	+ '<ul class="dropdown-menu" role="menu">'
	+ '<li><a href="/ims/admin/protocol/' + full.pkey
	+ '">Sponsor Details</a></li>'
	+ '<li><a href="/ims/admin/protocol/delete/' + full.pkey
	+ '">Delete Sponsor</a></li>';
}
function protocolAction(data, type, full) {
	return '<div class="btn-group pull-right"> '
	+ '<button type="button" class="btn btn-success " style="padding-bottom: 1px;padding-left: 6px;padding-right: 6px;padding-top: 3px;">Action</button>'
	+ '<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="padding-bottom: 10px; padding-top: 10px;">'
	+ '<span class="caret"></span>'
	+ '<span class="sr-only">Toggle Dropdown</span>' + '</button>'
	+ '<ul class="dropdown-menu" role="menu">'
	+ '<li><a href="/ims/admin/protocol/protocol/' + full.pkey
	+ '">Get Protocol Document</a></li>';
}
function getProtocolDoc(data, type, full) {
	 return '<div class="btn-group pull-right"> '
	 + '<a class="btn btn-success " href="/ims/admin/protocol/protocol/' + full.pkey
	 + '">Get Protocol Document</a>';
	}
function getCrf(data, type, full) {
	return '<div class="btn-group pull-right"> '
			+ '<a class="btn btn-success " href="/ims/admin/protocol/case/'
			+ full.pkey + '">Get Crf Document</a>';
}

function volunteerScreeninghAction(data, type, full) {
	return '<div class="btn-group pull-right"> '
			+ '<button type="button" class="btn btn-success " style="padding-bottom: 1px;padding-left: 6px;padding-right: 6px;padding-top: 3px;">Action</button>'
			+ '<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="padding-bottom: 10px; padding-top: 10px;">'
			+ '<span class="caret"></span>'
			+ '<span class="sr-only">Toggle Dropdown</span>' + '</button>'
			+ '<ul class="dropdown-menu" role="menu">'
			+ '<li><a href="/ims/admin/screening/statusUpdate/' + full.pkey
			+ '">Update Screening Status</a></li>'
			+ '<li><a href="/ims/admin/volunteer/printForm/' + full.pkey
			+ '">Print Form</a></li>' 
			/*+ '<li><a href="/ims/admin/study/' + full.pkey
			+ '">Study Details</a></li>' */
			/*+ '<li><a href="/ims/admin/study/statusUpdate/' + full.pkey
			+ '">Update Study Status</a></li>'*/
			+ '<li><a href="/ims/admin/study/adverseevents/' + full.pkey
			+ '">List Adverse Events</a></li>'
			+ '</ul></div>';

}

function volunteerStudyAction(data, type, full) {
	return '<div class="btn-group pull-right"> '
			+ '<button type="button" class="btn btn-success " style="padding-bottom: 1px;padding-left: 6px;padding-right: 6px;padding-top: 3px;">Action</button>'
			+ '<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="padding-bottom: 10px; padding-top: 10px;">'
			+ '<span class="caret"></span>'
			+ '<span class="sr-only">Toggle Dropdown</span>' + '</button>'
			+ '<ul class="dropdown-menu" role="menu">'
			/*
			 * + '<li><a href="/ims/admin/study/print/' + full.pkey + '">Print
			 * Subject Id Card</a></li>'
			 */
			+ '<li><a href="/ims/admin/study/statusUpdate/' + full.volunteerPkey
			+ '">Update Study Status</a></li>' + '</ul></div>';
}

function calcBmikgWgt(){
	if(document.registerVolunteer.height.value != null && document.registerVolunteer.height.value != ""){
		if(document.registerVolunteer.weight.value!=null && document.registerVolunteer.weight.value != ""){
			calcBmiKg();
		}
	}
}

function calcBmiKg() {
	var w = document.registerVolunteer.weight.value * 1;
	var HeightMt = document.registerVolunteer.height.value;
	
	h = HeightMt;
	displaybmi = (w) / (h * h);
	var rvalue = true;
	if (!(rvalue = generateAlert('height', h))) {
		document.registerVolunteer.height.focus();
		return rvalue;
	}
	if (!(rvalue = generateAlert('weight', w))) {
		document.registerVolunteer.weight.focus();
		return rvalue;
	}

	document.registerVolunteer.bmi.value = displaybmi.toFixed(2);

	return rvalue;
}
function calcBmiKgForEdit() {

	var w = document.updateVolunteer.weight.value * 1;
	var HeightMt = document.updateVolunteer.height.value;

	h = HeightMt;
	displaybmi = (w) / (h * h);
	var rvalue = true;
	if (!(rvalue = generateAlert('height', h))) {
		document.updateVolunteer.height.focus();
		return rvalue;
	}
	if (!(rvalue = generateAlert('weight', w))) {
		document.updateVolunteer.weight.focus();
		return rvalue;
	}
	document.updateVolunteer.bmi.value = displaybmi.toFixed(2);

	return rvalue;
}

function generateAlert(paramname, paramvalue) {

	var rvalue = true;
	if ((paramvalue == 0) || isNaN(paramvalue)) {
		alert("Invalid data entered as " + paramname
				+ ".  Please check and re-enter " + paramname + "!");
		rvalue = false;
	}

	return rvalue;
}

function calucateAge() {
	var lre = /^\s*/;
	var datemsg = "";

	var inputDate = document.getElementById("dob").value;
	inputDate = inputDate.replace(lre, "");
	datemsg = isValidDate(inputDate);
	if (datemsg != "") {
		alert(datemsg);
		return;
	} else {
		// Now find the Age based on the Birth Date
		age = getAge(new Date(inputDate));
		document.getElementById("age").value = age;
	}
}

function getAge(birth) {

	var today = new Date();
	var nowyear = today.getFullYear();
	var nowmonth = today.getMonth();
	var nowday = today.getDate();

	var birthyear = birth.getFullYear();
	var birthmonth = birth.getMonth();
	var birthday = birth.getDate();

	var age = nowyear - birthyear;
	var age_month = nowmonth - birthmonth;
	var age_day = nowday - birthday;

	if (age_month < 0 || (age_month == 0 && age_day < 0)) {
		age = parseInt(age) - 1;
	}
	
	if(birthmonth>nowmonth){
	    age_month = birthmonth - nowmonth;
	}
	
	if(birthday>nowday){
		age_day = birthday - nowday;
	}
	
	if((birthmonth/6)>1){
		age_month=12 - age_month;
	}
	
	if((age_day/15)>1){
		age_day=31-age_day;
	}
	if(birthday>nowday){
	age_month = age_month - 1;
	}
	if(age_month == 12){
		age_month = 0;
	}
	if(age>=0){
		return age + " years " + age_month + " months ";	
	} else{
		return "";
	}
}


function isValidDate(dateStr) {

	var msg = "";
	// Checks for the following valid date formats:
	// MM/DD/YY MM/DD/YYYY MM-DD-YY MM-DD-YYYY
	// Also separates date into month, day, and year variables

	// To require a 2 & 4 digit year entry, use this line instead:
	// var datePat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{2}|\d{4})$/;
	// To require a 4 digit year entry, use this line instead:
	var datePat = /^(\d{4})(\/|-)(\d{1,2})\2(\d{1,2})$/;

	var matchArray = dateStr.match(datePat); // is the format ok?
	if (matchArray == null) {
		msg = "Date is not in a valid format.";
		return msg;
	}

	month = matchArray[3]; // parse date into variables
	day = matchArray[4];
	year = matchArray[1];

	if (month < 1 || month > 12) { // check month range
		msg = "Month must be between 1 and 12.";
		return msg;
	}

	if (day < 1 || day > 31) {
		msg = "Day must be between 1 and 31.";
		return msg;
	}

	if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
		msg = "Month " + month + " doesn't have 31 days!";
		return msg;
	}

	if (month == 2) { // check for february 29th
		var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
		if (day > 29 || (day == 29 && !isleap)) {
			msg = "February " + year + " doesn't have " + day + " days!";
			return msg;
		}
	}

	if (day.charAt(0) == '0')
		day = day.charAt(1);

	// Incase you need the value in CCYYMMDD format in your server program
	// msg = (parseInt(year,10) * 10000) + (parseInt(month,10) * 100) +
	// parseInt(day,10);

	return msg; // date is valid
}

function userMgmtAction(data, type, full) {
	return '<div class="btn-group pull-right"> '
			+ '<button type="button" class="btn btn-success " style="padding-bottom: 1px;padding-left: 6px;padding-right: 6px;padding-top: 3px;">Action</button>'
			+ '<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="padding-bottom: 10px; padding-top: 10px;">'
			+ '<span class="caret"></span>'
			+ '<span class="sr-only">Toggle Dropdown</span>' + '</button>'
			+ '<ul class="dropdown-menu" role="menu">'
			+ '<li><a href="/ims/admin/user/' + full.pkey
			+ '">User Details</a></li>'
			+ '<li><a href="/ims/admin/user/delete/' + full.pkey
			+ '">Delete User</a></li>';
}
function jobMgmtAction(data, type, full) {
	return '<div class="btn-group pull-right"> '
			+ '<button type="button" class="btn btn-success " style="padding-bottom: 1px;padding-left: 6px;padding-right: 6px;padding-top: 3px;">Action</button>'
			+ '<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="padding-bottom: 10px; padding-top: 10px;">'
			+ '<span class="caret"></span>'
			+ '<span class="sr-only">Toggle Dropdown</span>' + '</button>'
			+ '<ul class="dropdown-menu" role="menu">'
			+ '<li><a href="/ims/admin/job/' + full.id
			+ '">Job Details</a></li>'
			+'<li><a href="/ims/admin/job/execute/' + full.id
			+ '">Execute Now</a></li>'
			+ '<li><a href="/ims/admin/job/delete/' + full.id
			+ '">Delete Job</a></li>';
}

$(document).ready(
		function() {

			$('#btnEnablePasswordUpdate').click(
					function() {
						document.getElementById("password").removeAttribute(
								"disabled");
						document.getElementById("passwordAgain")
								.removeAttribute("disabled");
					});

			$('#btnDisablePasswordUpdate').click(
					function() { // REMOVE ELEMENTS ONE PER CLICK.
						document.getElementById("password").setAttribute(
								"disabled", "disabled");
						document.getElementById("passwordAgain").setAttribute(
								"disabled", "disabled");
					});
		});

function reportsView(data, type, full) {

	// var sizeOfPkey = Object.size(data);
	var reportUrl = 'www.google.com';

	return '<a href="http://' + reportUrl + '">' + data + '</a>';
}

function renderCheckBox(data, type, full) {

	return '<input type=\"checkbox\" value="' + full.pkey + '">';
}

function createMasterListValues() {
	var pkeys = ($("#hdnCheckBoxValues").val());
	var studyNo = document.masterForm.bulkStudyStudyNo.value;
	var countmastername=($("#countmaster").val());
}

function createMasterList() {
	var subjReferences = '';
	var status = false;
 	for (i = 0; i < document.masterListForm.subjReference.length; i++) {
		subjReferences += document.masterListForm.subjReference[i].value + ',';
		status = true;
	}
	if(!status){
		subjReferences = document.masterListForm.subjReference.value
	}
 
	document.masterListForm.subjReferences.value = subjReferences

	return true;

}
function studyFunction()
{
	
		
		  studyNo = document.masterForm.bulkStudyStudyNo.value;
			document.masterForm.studyvalue.value=studyNo;
		
	}
function bulkFunction()
{
	
		
		  studyNo = document.bulkUpdateForm.bulkStudyStudyNo.value;
			document.bulkUpdateForm.studyvalue.value=studyNo;
			document.masterForm.studyvalue.value=studyNo;
		
	}
function demographicalertchekboxempty()
{
	var stringchecklength=$("#hdnCheckBoxValues").val();
	var studycheck = $("#studyvalue").val();
	var studydate=$("#datevalue").val();
	if(stringchecklength==0 || studycheck=="" || studydate=="")
		{
			if(studycheck==""){
				alert("Please Enter Study Number");
				
			}
			else if(studydate==""){
				alert("Please Select the Study Date");
				
			}
			else
				{
				alert("Atleast one volunteer has to be selected for Bulk Update");
				}
		}
	else{
		$("form[name='bulkUpdateForm']").submit();
	}
	
}
function masteralertchekboxempty()
{
	var stringchecklength=$("#hdnCheckBoxValues").val();
	var studycheck = $("#studyvalue").val();
	if(stringchecklength==0 || studycheck=="")
		{
			if(studycheck==""){
				alert("Please Enter Study Number");
			}else{
				
				alert("Atleast one volunteer has to be selected for Master List");
			}
		}
	else{
		$("form[name='masterForm']").submit();
	}
	
}
function dateFunction()
{
	
 		  datemsg = document.bulkUpdateForm.bulkStudyUpdateDate.value;
			document.bulkUpdateForm.datevalue.value=datemsg;
		
	}

function createDemographicList() {
	var subjReferences = '';

	for (i = 0; i < document.demographicForm.subjReference.length; i++) {
		subjReferences += document.demographicForm.subjReference[i].value + ',';

	}
	document.demographicForm.subjReferences.value = subjReferences

	return true;

}

function setValues() {

	//var studyNo = document.searchStudyForm.studyNo.value;
	var formData = document.searchStudyForm;
	
	var studyNo = formData[1].value;
	var id = formData[0].value;
	var token = true;
	if (studyNo == '') {
		/*
		 * alert('Enter Study No for search')
		 * document.searchStudyForm.studyNo.focus();
		 */
		return true;
	}
	
	if(studyNo == "Study No")
		studyNo = '';
	
	if(id == "ID"){
		id = '';
		if(studyNo == ''){
			token = false;
		}
	} else{	
		token = verify();
	}
	if(token)
		window.location.href = "searchWithCriteria?studyNo=" + studyNo+"&id="+id;

}

function setAttributes(val) {
	document.reportsForm.reportType.value = val;
	return true;
}

function downloadConfirmation(){
var	status = confirm('This will take some time to download.\n Are you sure you want to continue.\nPress OK to continue Cancel to abort')
	 
	if(status == true){
		document.reportsform.submit();
	} 
	 
 }

function updateAttributes() {
	var ajaxRequest; // The variable that makes Ajax possible!

	try {
		// Opera 8.0+, Firefox, Safari
		ajaxRequest = new XMLHttpRequest();
	} catch (e) {
		// Internet Explorer Browsers
		try {
			ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				// Something went wrong
				alert("Your browser broke!");
				return false;
			}
		}
	}
	var pkey = document.updateVolunteer.pkey.value;
	var weight = document.updateVolunteer.weight.value;
	var height = document.updateVolunteer.height.value;
	var bmi = document.updateVolunteer.bmi.value;

	ajaxRequest
			.open("GET", "/ims/admin/volunteer/updateVolunteerAttribute?pkey="
					+ pkey + "&weight=" + weight + "&bmi=" + bmi + "&height="
					+ height, true);
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			window.location.href = window.location.href;
		}
	}
	ajaxRequest.send();
}

function modifySubjNo(data, type, full) {

	if (data != null) {
		data = data.replace("<div>", "");
		data = data.replace("</div>", "");
		if (data.length == 1) {
			return '0' + data;
		}

	}
	return data;
}

function daysView(data, type, full) {

	// var sizeOfPkey = Object.size(data);

var dataAsString = new String(data);
	
	function checkerFun(){
		var checkedIds =($('#hdnCheckBoxValues').val()).split(",");
		for(var iLoop=0;checkedIds.length>iLoop;iLoop++){
			if(+full.pkey==checkedIds[iLoop])
				return "checked";
			
		}
	}
	var isChecked= checkerFun();
	
	return '<input id="'+full.pkey+'" type=\"checkbox\" '+isChecked+' name=\"volunteerpkey\"  value="'
			+ full.pkey + '">' + '<a href="/ims/admin/screening/' + data + '">'
			+ convertPkeyToRegistrationId(dataAsString) + '</a>';
	 
}

function dateBoxValues() {

	var pkeys = '';
	var isSelected = false;
	var isDateProvided = false;
	for (i = 0; i < document.searchVolunteerFormDays.volunteerpkey.length; i++) {
		if (document.searchVolunteerFormDays.volunteerpkey[i].checked) {
			pkeys += document.searchVolunteerFormDays.volunteerpkey[i].value
					+ ',';
			isSelected = true;
		}
	}
	if (isSelected) {
		datemsg = isValidDate(document.searchVolunteerFormDays.bulkStudyUpdateDate.value);
		studyNo = document.searchVolunteerFormDays.bulkStudyStudyNo.value;
		if (datemsg == '' && studyNo != '') {
			window.location.href = "/ims/admin/screening/bulkUpdate?bulkStudyUpdateDate="
					+ document.searchVolunteerFormDays.bulkStudyUpdateDate.value
					+ "&pkeys=" + pkeys + "&bulkStudyStudyNo=" + studyNo;
		} else {
			if (datemsg != '') {
				alert(datemsg);
				document.searchVolunteerFormDays.bulkStudyUpdateDate.focus();
			} else if (studyNo == '') {
				alert('Please enter a valid Study No');
				document.searchVolunteerFormDays.bulkStudyStudyNo.focus();
			}

		}

	} else {
		alert('Atleast one volunteer has to be selected for Bulk Update')
	}

	// window.location.href = "/ims/admin/screening/search" ;
}
function createDaysMasterListValues() {

	var pkeys = '';
	var isSelected = false;
	var isDateProvided = false;
	for (i = 0; i < document.searchVolunteerFormDays.volunteerpkey.length; i++) {
		if (document.searchVolunteerFormDays.volunteerpkey[i].checked) {
			pkeys += document.searchVolunteerFormDays.volunteerpkey[i].value
					+ ',';
			isSelected = true;
		}
	}
	if (isSelected) {
		studyNo = document.searchVolunteerFormDays.bulkStudyStudyNo.value;

		if (studyNo == '') {
			alert('Enter  Study No for creating master list')
			document.searchVolunteerFormDays.bulkStudyStudyNo.focus();
		} else {
			window.location.href = "/ims/admin/screening/createMasterList?pkeys="
					+ pkeys + "&studyNo=" + studyNo;
		}

	} else {
		alert('Atleast one volunteer has to be selected for Master list creation')
	}
}

function modifySubjNoScreening(data, type, full) {
    if (data != null) {
        
        if (data.length == 1 || data.substring(data.indexOf("</div>")-2, data.indexOf("</div>")-1) == ">") {
            return '0' + data;
        }
    }
    return data;
}

function uncheck() {
	  
	 $('#myTableId input:checkbox').removeAttr('checked',false);
	 $('#hdnCheckBoxValues').val("");
	 nolength();
}

function checkQuantity(){
	var qty = document.getElementById("quantity").value;
	if(qty == '0'){
		alert("Purchase atleast one Order");
		return false;
	}
return true;
}

function productNameSearch(){
	var ids = document.getElementById("productNames").value;
	var da = [];
	var s = ids.split("^,");
	for(var i=1;i<(s.length-1);i++){
		da.push(s[i]);
	}
	var first = s[0].split("[");
	var end = s[(s.length-1)].split("^]");
	da.push(first[1]);
	da.push(end[0]);
	$( "#productName" ).autocomplete({
	      source:da
	 });
}

function salesOrderSearchAction(data, type, full){
	updateStatus();
	document.getElementById("updateStatus").value = false;
	return '<div class="btn-group pull-right"> '
	+ '<button type="button" class="btn btn-success " style="padding-bottom: 1px;padding-left: 6px;padding-right: 6px;padding-top: 3px;">Action</button>'
	+ '<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" style="padding-bottom: 10px; padding-top: 10px;">'
	+ '<span class="caret"></span>'
	+ '<span class="sr-only">Toggle Dropdown</span>' + '</button>'
	+ '<ul class="dropdown-menu" role="menu">'
	+ '<li><a href="/ims/admin/salesorder/' + full.orderId
	+ '">Order Details</a></li></ul>';

}

function updateStatus(){
	var updateStatus = document.getElementById("updateStatus").value;
	var updateDetails = document.getElementById("updateDetails").value;
	if(updateStatus == 'true'){
		alert("Products Quantitys are update successfulley");
	}
}

function updateStart(){
		alert("UpdateIn Progress");
}

function getProductName(){
	alert("Hai");
}


