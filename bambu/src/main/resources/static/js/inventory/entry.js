$(document).ready(function() {
	$('#btn-entry').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		alert(href);
		$.get(href, function(articulo, status) {
			console.log(articulo.id + articulo.article.name);
            $('#entradaModal .modal-body #input-id-entry').val(articulo.id);
			$('#entradaModal .modal-body #input-name-entry').val(articulo.article.name);
			
		});
		$('#entradaModal').modal();	
	});
});

$(document).ready(function() {
	$('#entradaModal .modal-body #unitCost').on('click', function(event) {
        total =  $('#entradaModal .modal-body #quantityEntry').val() * $('#entradaModal .modal-body #unitCostEntry').val();
        $('#entradaModal .modal-body #totalCostEntry').val(total);
	});
});