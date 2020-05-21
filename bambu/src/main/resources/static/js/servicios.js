// $(document).ready(function () {
//     var table = $('#table-information').DataTable({
//         lengthChange: false,
//         select: false,
//         language: {
//             "decimal": "",
//             "emptyTable": "No hay información",
//             "info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
//             "infoEmpty": "Mostrando 0 to 0 of 0 Entradas",
//             "loadingRecords": "Cargando...",
//             "processing": "Procesando...",
//             "search": "Buscar:",
//             "zeroRecords": "Sin resultados encontrados",
//             "paginate": {
//                 "first": "Primero",
//                 "last": "Ultimo",
//                 "next": "Siguiente",
//                 "previous": "Anterior"
//             }
//         }
//     });
// });  

$(document).ready(function() {
	$('.nBtn').on('click', function(event) {
		event.preventDefault();
		$('.saveForm #saveModal').modal();
	});
});

$(document).ready(function() {
	$('.nABtn').on('click', function(event) {
		event.preventDefault();
		$('.saveAForm #saveAModal').modal();
	});
});

$(document).ready(function() {
	$('.eBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(servicio, status) {
			$('.editForm #code').val(servicio.code);
			$('.editForm #name').val(servicio.name);
			$('.editForm #price').val(servicio.price);
		});
		$('.editForm #editModal').modal();
	});
});

$(document).ready(function() {
	$('.eABtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(servicio, status) {
			$('.editAForm #code').val(servicio.article.code);
			$('.editAForm #cant').val(servicio.quantity);
		});
		$('.editAForm #editAModal').modal();
	});
});


$(document).ready(function() {
	$('.newArtBtn').on('click', function(event) {
		event.preventDefault();
		$('.newArtForm #newArtModal').modal();
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
	$('.dABtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#deleteAModal #delRef').attr('href',href);
		$('#deleteAModal').modal();	
	});
});

$(document).ready(function() {
	$('.ffBtn').on('click', function(event) {
		event.preventDefault();
		$('.filtrarFechasReporteServicio #filtrarModal').modal();
	});
});