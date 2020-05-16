$(document).ready(function() {
	$('.nBtn').on('click', function(event) {
		event.preventDefault();
		$('.saveForm #saveModal').modal();
	});
});


$(document).ready(function() {
	$('.eBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');

		$.get(href, function(servicio, status) {
			$('.editForm #code').val(servicio.code);
			$('.editForm #name').val(servicio.name);
			$('.editForm #salePrice').val(servicio.salePrice);
		});
		$('.editForm #editModal').modal();
	});
});