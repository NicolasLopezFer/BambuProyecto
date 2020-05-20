$(document).ready(function() {
	$('#ch-avarage').on('click', function(event) {
		$('#metodoModal .modal-body #method').val('Promedio Ponderado');
		$('#ch-fifo').prop('checked', false);
        $('#ch-lifo').prop('checked', false);

	});
});

$(document).ready(function() {
	$('#ch-fifo').on('click', function(event) {
		$('#metodoModal .modal-body #method').val('FiFo');
		$('#ch-avarage').prop('checked', false);
		$('#ch-lifo').prop('checked', false);
	});
});

$(document).ready(function() {
	$('#ch-lifo').on('click', function(event) {
    	$('#metodoModal .modal-body #method').val('LiFo');
		$('#ch-avarage').prop('checked', false);
		$('#ch-fifo').prop('checked', false);
	});
});
