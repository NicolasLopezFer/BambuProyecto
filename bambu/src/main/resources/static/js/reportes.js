$(document).ready(function() {
	$('.ffBtn').on('click', function(event) {
		event.preventDefault();
		$('.filtrarFechas #filtrarModal').modal();
	});
});