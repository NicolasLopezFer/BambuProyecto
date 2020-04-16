/**
 * 
 */

$(document).ready(function() {
	$('.table.eBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');

		$.get(href, function(articulo, status) {
			$('.editForm #code').val(articulo.code);
			$('.editForm #name').val(articulo.name);
			$('.editForm #quantity').val(articulo.quantity);
			$('.editForm #unit_cost').val(articulo.unit_cost);
			$('.editForm #total_cost').val(articulo.total_cost);
			$('.editForm #sale_price').val(articulo.sale_price);
		});
		$('.editForm #editModal').modal();
	})
});

$(document).ready(function() {
	$('.nBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('.saveForm #code').val('');
		$('.saveForm #name').val('');
		$('.saveForm #sale_price').val('');
		$('.saveForm #saveModal').modal();
	})
});