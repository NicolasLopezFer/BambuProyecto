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

		$.get(href, function(articulo, status) {
			$('.editForm #code').val(articulo.code);
			$('.editForm #name').val(articulo.name);
			$('.editForm #salePrice').val(articulo.salePrice);
		});
		$('.editForm #editModal').modal();
	});
});