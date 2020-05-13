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

$(document).ready(function() {
	$('.eBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');

		$.get(href, function(i, status) {
			$('.editForm #numeroComprobante').val(i.numeroComprobante);
			$('.editForm #aprobado').val(i.aprobado);
			$('.editForm #fecha').val(i.fecha);
			$('.editForm #nombrePagado').val(i.nombrePagado);
			$('.editForm #descripcion').val(i.descripcion);
			$('.editForm #concepto').val(i.concepto);
			$('.editForm #elaborador').val(i.elaborador);
			$('.editForm #identificacion').val(i.identificacion);
            $('.editForm #suma').val(i.suma);
            $('.editForm #metodoPago').val(i.metodoPago);
            
		});
		$('.editForm #editModal').modal();
	});
});