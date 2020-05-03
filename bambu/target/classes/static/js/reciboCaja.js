
$(document).ready(function() {
	$('.eBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');

		$.get(href, function(articulo, status) {
			$('.editForm #code').val(articulo.code);  //recibo
			$('.editForm #name').val(articulo.name);
			$('.editForm #salePrice').val(articulo.salePrice);
		});
		$('.editForm #editModal').modal();
	});
}); 

$(document).ready(function() {
	$('.nBtn').on('click', function(event) {
		event.preventDefault();
		$('.saveForm #saveModal').modal();
	});
});

$(document).ready(function() {
	$('.dBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#deleteModal #delRef').attr('href',href);
		$('#deleteModal').modal();	
	});
});