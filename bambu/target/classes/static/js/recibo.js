$(document).ready(function() {
	$('.nBtn').on('click', function(event) {
		event.preventDefault();
		$('.saveForm #saveModal').modal();
	});
});