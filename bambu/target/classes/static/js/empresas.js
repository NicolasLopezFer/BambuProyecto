/**
 * 
 */

$(document).ready(function() {
	$('.nBtn').on('click', function(event) {
		event.preventDefault();
		$('.saveForm #saveModal').modal();
	});
});

$(document).ready(function() {
	$('.eBtn').on('click', function(event) {
		event.preventDefault();
		/*var href = $(this).attr('href');

		$.get(href, function(empresaElegida, status) {
			$('.editForm #nit').val(empresaElegida.nit);
			$('.editForm #name').val(empresaElegida.name);
			$('.editForm #social_reason').val(empresaElegida.social_reason);
			$('.editForm #direction').val(empresaElegida.direction);
			$('.editForm #telephone').val(empresaElegida.telephone);
		});*/
		$('.editForm #editModal').modal();
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