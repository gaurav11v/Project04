$(document).ready(function() {
	$("#udate").dateppicker({
		dateFormat : 'dd-mm-yy',
		changeMonth : true,
		changeYear : true,
		yearRange : '1980:2024',
		minDate : new Date(1999, 0, 1),
		maxDate : new Date(2024, 12, 31)
	});
});