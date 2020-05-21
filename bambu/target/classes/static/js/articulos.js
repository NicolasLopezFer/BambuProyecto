/**
 * 
 */

$(document).ready(function() {
	$('.eBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');

		$.get(href, function(articulo, status) {
			$('.editForm #code').val(articulo.code);
			$('.editForm #salePrice').val(articulo.salePrice);
			$('.editForm #name').val(articulo.name);
			$('.editForm #quantity').val(articulo.quantity);
			$('.editForm #unitCost').val(articulo.unitCost);
			$('.editForm #totalCost').val(articulo.totalCost);

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

$(document).on('click', '#saveModal .modal-body #unitCost', function() {
	var typingTimer;
	var doneTypingInterval = 1000;
	$('#saveModal .modal-body #unitCost').keyup(function(){
		clearTimeout(typingTimer);
		if ($('#saveModal .modal-body #unitCost').val()) {
		typingTimer = setTimeout(doneTyping, doneTypingInterval);
		}
	});
	function doneTyping () {
		var quantity = $('#saveModal .modal-body #quantity').val();
		if(quantity > 0){
		   var total =  quantity * $('#saveModal .modal-body #unitCost').val();
		   $('#saveModal  .modal-body #totalCost').val(total);
		}
		else{
			$('#saveModal  .modal-body #totalCost').val(0);
		} 
		
	}  
});


$(document).on('click', '#saveModal .modal-body #quantity', function() {
	var typingTimer;
	var doneTypingInterval = 1000;
	$('#saveModal .modal-body #quantity').keyup(function(){
		clearTimeout(typingTimer);
		if ($('#saveModal .modal-body #quantity').val()) {
		typingTimer = setTimeout(doneTyping, doneTypingInterval);
		}
	});
	function doneTyping () {
		var quantity = $('#saveModal .modal-body #quantity').val();
		var unitCost = $('#saveModal .modal-body #unitCost').val();
		if(unitCost > 0){
		   var total =  quantity * unitCost ;
		   $('#saveModal .modal-body #totalCost').val(total);
		}
		
	}  
});

$(document).ready(function() {
	$('.dBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#deleteArticleModal #delRef').attr('href',href);
		$('#deleteArticleModal').modal();	
	});
});