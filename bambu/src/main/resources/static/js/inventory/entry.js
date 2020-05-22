$(document).on('click', '#btn-entry', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(articulo, status) {
            $('#entradaModal .modal-body #input-id-entry').val(articulo.id);
			$('#entradaModal .modal-body #input-name-entry').val(articulo.article.name);
			
		});
		$('#entradaModal').modal();	
});


$(document).on('click', '#entradaModal .modal-body #unitCostEntry', function() {
	var typingTimer;
	var doneTypingInterval = 1000;
	$('#entradaModal .modal-body #unitCostEntry').keyup(function(){
		clearTimeout(typingTimer);
		if ($('#entradaModal .modal-body #unitCostEntry').val()) {
		typingTimer = setTimeout(doneTyping, doneTypingInterval);
		}
	});
	function doneTyping () {
		var quantity = $('#entradaModal .modal-body #quantityEntry').val();
		if(quantity > 0){
		   var total =  quantity * $('#entradaModal .modal-body #unitCostEntry').val();
		   $('#entradaModal .modal-body #totalCostEntry').val(total);
		}
		else{
			$('#entradaModal .modal-body #totalCostEntry').val(0);
		} 
		
	}  
});


$(document).on('click', '#entradaModal .modal-body #quantityEntry', function() {
	var typingTimer;
	var doneTypingInterval = 1000;
	$('#entradaModal .modal-body #quantityEntry').keyup(function(){
		clearTimeout(typingTimer);
		if ($('#entradaModal .modal-body #quantityEntry').val()) {
		typingTimer = setTimeout(doneTyping, doneTypingInterval);
		}
	});
	function doneTyping () {
		var quantity = $('#entradaModal .modal-body #quantityEntry').val();
		var unitCost = $('#entradaModal .modal-body #unitCostEntry').val();
		if(unitCost > 0){
		   var total =  quantity * unitCost ;
		   $('#entradaModal .modal-body #totalCostEntry').val(total);
		}
		
	}  
});