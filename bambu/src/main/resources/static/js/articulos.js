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
			$('.editForm #unitCost').val(articulo.unit_cost);
			$('.editForm #totalCost').val(articulo.total_cost);
			$('.editForm #salePrice').val(articulo.sale_price);
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
		$('.saveForm #salePrice').val('');
		$('.saveForm #saveModal').modal();
	})
});