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


// $(document).ready(function() {
// 	$('.verarticulosBtn').on('click', function(event) {
// 		event.preventDefault();
// 	//	var href = $(this).attr('href');
// 		// alert("asda");
			
// 		$('.verArtForm #verArtModal').modal();

// 		//$.get(href, function(id, status) {
// 		//	$('.verArtForm #id').val(id);
// 	//	alert("da"+id);
// 	//	});

// 	});
// });

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