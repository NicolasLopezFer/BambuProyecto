function change() {
	var date = $('#fechaFacturaDeVenta').val();
	var name = $('#clienteFacturaDeVenta').val();
	var nit = $('#nitFacturaDeVenta').val();
	var code = $('#numeroFacturaDeVenta').val();
	alert(name+"dsdsds");
	var url = '/devoluciones/manetenerInformacion/?code='+code+'&name='+
	name+'&nit='+nit+'&date='+date;
	console.log(url);
	$.get(url);

};

//-- Add Article Modal -----------------------------------------------------------------------------------------
$(document).on('click', '#btn-AddArticle', function(event) {
    change();
}); 

$(document).on('click', '#checkDevolucionDeVenta', function(event) {
	$('#typeReturn').val(1);
	$('#checkDevolucionDeCompra').prop('checked', false);

});

$(document).on('click', '#checkDevolucionDeCompra', function(event) {
	$('#typeReturn').val(0);
	$('#checkDevolucionDeVenta').prop('checked', false);

});

//-- Add Article Modal -----------------------------------------------------------------------------------------

$(document).on('click', '#selectArticle', function() {
	var index = $("#selectArticle").prop('selectedIndex');
	if(index != 0){
		var href = '/devoluciones/obtenerPrecioArticulo/?index='
		var url = href + index ;
		console.log(url);
		$.get(url, function(price, status) {
			console.log(price);
			$('#articleModal .modal-body #price').val(price);
			$('#articleModal .modal-body #idArticle').val(index);
			
			var quantity = $('#articleModal .modal-body .quantity').val(); 

			if( quantity > 0){
				var total = $('#articleModal .modal-body #priceEdit').val()*quantity; 
				$('#articleModal  .modal-body #valueEdit').val(total);
			}
		});
	}    
});

$(document).on('click', '#articleModal .modal-body .quantity', function() {
	var typingTimer;
	var doneTypingInterval = 1000;
	$('#articleModal .modal-body .quantity').keyup(function(){
		clearTimeout(typingTimer);
		if ($('#articleModal .modal-body .quantity').val()) {
		typingTimer = setTimeout(doneTyping, doneTypingInterval);
		}
	});
	function doneTyping () {

		var total = $('#articleModal .modal-body #price').val()*$('#articleModal .modal-body .quantity').val(); 
		$('#articleModal .modal-body #value').val(total);
	}  
});

$(document).on('click', '#articleModal .modal-body #selectNSale', function() {
	if($('#selectNSale').val()!= 'Seleccione'){
		var code = $('#articleModal .modal-body #selectNSale').prop('selectedIndex');
		$('#articleModal .modal-body #nSale').val(code);

	}    
});

$(function() {
	$("form[name='newArticle']").validate({
	  rules: {
		quantity: "required",
		motive: "required"
	  },
	  messages: {
		quantity: "Por favor ingrese la cantidad del artículo a devolver.",
		motive: "Por favor ingrese el motivo de la devolución.",
		selectArticle: "Por favor seleccione un artículo."
	  },
	  submitHandler: function(form) {
		form.submit();
	  }
	});
  });

//-- Edit Article Modal -----------------------------------------------------------------------------------------
$(document).on('click', '#btn-editArticle', function(event) {
	event.preventDefault();
	change();
	var href = $(this).attr('href');
	$.get(href, function(article, status) {
	    if(article.article.name != ""){
			$('#editArticleModal .modal-body #selectArticleEdit option').each(function(){ 
				if($(this).val() == article.article.name){
					$(this).attr('selected','selected');
				}		  
		   });

		   var indexA = $('#selectArticleEdit').prop('selectedIndex');
		   $('#editArticleModal .modal-body #idArticleEdit').val(indexA);
		}
			
		$('#editArticleModal .modal-body #priceEdit').val(article.article.salePrice);
		$('#editArticleModal .modal-body #quantityEdit').val(article.quantity);
		$('#editArticleModal .modal-body #valueEdit').val(article.value);
		$('#editArticleModal .modal-body #motiveEdit').val(article.motive);

		if(article.nSale != ""){
			$('#editArticleModal .modal-body #saleBillsEdit option').each(function(){ 
				if($(this).val() == article.nSale){
					alert($(this).val());
					$(this).attr('selected','selected');
				}
			  
		   });
		   var indexSale = $('#saleBillsEdit').prop('selectedIndex');
		   $('#editArticleModal .modal-body #nSaleEdit').val(indexSale);
		}
		
	});
	$('#editArticleModal').modal();	
});

$(document).on('click', '#selectArticleEdit', function() {
	var index = $("#selectArticleEdit").prop('selectedIndex');
	if(index != 0){
		var href = '/devoluciones/obtenerPrecioArticulo/?index='
		var url = href + index ;
		console.log(url);
		$.get(url, function(price, status) {
			console.log(price);
			$('#editArticleModal .modal-body #priceEdit').val(price);
			$('#editArticleModal .modal-body #idArticleEdit').val(index);

			var quantity = $('#editArticleModal .modal-body .quantity').val(); 

			if( quantity > 0){
				var total = $('#editArticleModal .modal-body #priceEdit').val()*quantity; 
				$('#editArticleModal  .modal-body #valueEdit').val(total);
			}
		});
	}    
});

$(document).on('click', '#editArticleModal .modal-body .quantity', function() {
	var typingTimer;
	var doneTypingInterval = 1000;
	$('#editArticleModal .modal-body .quantity').keyup(function(){
		clearTimeout(typingTimer);
		if ($('#editArticleModal .modal-body .quantity').val()) {
		    typingTimer = setTimeout(doneTyping, doneTypingInterval);
		}
	});
	function doneTyping () {

		var total = $('#editArticleModal .modal-body #priceEdit').val()*$('#editArticleModal .modal-body .quantity').val(); 
		$('#editArticleModal  .modal-body #valueEdit').val(total);
	}  
});

$(document).on('click', '#editArticleModal .modal-body #selectNSaleEdit', function() {
	if($('#selectNSaleEdit').val()!= 'Seleccione'){
		var code = $('#editArticleModal .modal-body #selectNSaleEdit').prop('selectedIndex');
		$('#editArticleModal .modal-body #nSaleEdit').val(code);

	}    
});

$(function() {
	$("form[name='editArticle']").validate({
	  messages: {
		quantity: "Por favor ingrese la cantidad del artículo a devolver.",
		motive: "Por favor ingrese el motivo de la devolución.",
		selectArticle: "Por favor seleccione un artículo."
	  },
	  submitHandler: function(form) {
		form.submit();
	  }
	});
  });

//-- Delete Article Modal -----------------------------------------------------------------------------------------
$(document).on('click', '#btn-deleteArticle', function(event) {
	event.preventDefault();
	change();
	var href = $(this).attr('href');
	$.get(href, function(index, status) {
		   $('#deleteArticleModal .modal-body #indexDelete').val(index);
	});
	$('#deleteArticleModal').modal();	
});