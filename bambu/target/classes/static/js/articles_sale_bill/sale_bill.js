function change() {
	var date = $('#fechaFacturaDeVenta').val();
	var dateEx = $('.vencimiento').val();
	var name = $('.customer-name').val();
	var nit = $('#nitFacturaDeVenta').val();
	var code = $('#numeroFacturaDeVenta').val();
	alert(name+"dsdsds");
	var url = '/factura-de-venta-articulos/manetenerInformacion/?code='+code+'&name='+
	name+'&nit='+nit+'&date='+date+'&dateEx='+dateEx;
	console.log(url);
	$.get(url);

};

//-- Add Article Modal -----------------------------------------------------------------------------------------

$(document).on('click', '#btn-AddArticle', function(event) {
    change();
}); 

$(document).on('click', '#btn-addTax', function(event) {
	if($('#selectTax').val()!= 'Seleccione'){
		var nameTax = $('#articleModal .modal-body #selectTax').val();
		var href = '/factura-de-venta-articulos/insertarImpuesto/?name='
		var url = href + nameTax ;
		console.log(url);
		$.get(url, function(tax, status) {
			 var name = tax.name;
			 var value = tax.value;
			$('#tableTaxes tr:last').after('<tr><td id="td-name">'+ name +'</td><td id="td-value">'+ value +'</td>' +
			  '<td><button type="button" class="btn btn-primary" id="btn-delete-tax"><i class="fa fa-trash"></i></button></td></tr>'
			);

     		var valueT = $('#articleModal .modal-body #total').val();
			if( valueT > 0){
				var quantity = $('#articleModal .modal-body .quantity').val(); 
				var base = $('#articleModal .modal-body #price').val()*quantity;
				var discount = $('#articleModal .modal-body #discount').val(); 
				if( discount > 0){
                   base *= (1 - (discount/100));
				}
				var total = (valueT * 1 ) + (base*(value/100)); 
				$('#articleModal .modal-body #total').val(total);
			}

		});
		
	}

});

$(document).on('click', '#btn-delete-tax', function(event)  {
    var nameTax = $(this).closest('tr')  
                       .find('#td-name')   
					   .text();   
	
    var valueTax = $(this).closest('tr')  
                       .find('#td-value')   
                       .text();  				       
	 
	var href = '/factura-de-venta-articulos/eliminarImpuesto/?name='
    var url = href + nameTax ;
    console.log(url);
    $.get(url);
	
	$(this).closest('tr').remove();
	
	var valueT = $('#articleModal .modal-body #total').val();

	if( valueT > 0){
		var quantity = $('#articleModal .modal-body .quantity').val(); 
		var base = $('#articleModal .modal-body #price').val()*quantity;
		var discount = $('#articleModal .modal-body #discount').val(); 
		if( discount > 0){
             base *= (1 - (discount/100));
		}
		var total =  valueT - (base * (valueTax/100) );
		$('#articleModal .modal-body #total').val(total);
	}
    
});


//-- Add Article Modal -----------------------------------------------------------------------------------------
$(document).on('click', '#selectArticle', function() {
	var index = $("#selectArticle").prop('selectedIndex');
	if(index != 0){
		var href = '/factura-de-venta-articulos/obtenerPrecioArticulo/?index='
		var url = href + index ;
		console.log(url);
		$.get(url, function(price, status) {
			console.log(price);
			$('#articleModal .modal-body #price').val(price);
			$('#articleModal .modal-body #idArticle').val(index);
			
			var quantity = $('#articleModal .modal-body .quantity').val(); 
			var total = 0;
			if( quantity > 0 ){
				var base = price*quantity; 

				var discount = $('#articleModal .modal-body #discount').val();
		   	    if( discount > 0 ){
			       total += (base * ( 1 - (discount/100)));
				}
				
				var urlI = '/factura-de-venta-articulos/obtenerImpuestos'
				console.log(urlI);
				$.get(urlI, function(taxes, status) {
					console.log(taxes[0].name);
					if(taxes != NaN){
					   var totalTaxes = 0;
					   taxes.forEach(function(tax, index) {
						  totalTaxes += (total * (tax.value/100));
						  console.log(totalTaxes);
						});
						total += totalTaxes;
						console.log('soy total' + total);
					}
					$('#articleModal  .modal-body #total').val(total);
				});
				
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
		$('#articleModal .modal-body #total').val(total);
	}  
});

$(document).on('click', '#articleModal .modal-body #discount', function() {
	var typingTimer;
	var doneTypingInterval = 1000;
	$('#articleModal .modal-body #discount').keyup(function(){
		clearTimeout(typingTimer);
		if ($('#articleModal .modal-body #discount').val()) {
		typingTimer = setTimeout(doneTyping, doneTypingInterval);
		}
	});
	function doneTyping () {

		var base = $('#articleModal .modal-body #price').val()*$('#articleModal .modal-body .quantity').val();
		if( base > 0){
			var discount = $('#articleModal .modal-body #discount').val();
			if(discount == null){
				$('#articleModal .modal-body #discount').val(0);
				discount = 0;
			}

			var total = base * (1 - (discount/100))  
			$('#articleModal .modal-body #total').val(total);

		}
		
	}  
});


$(function() {
	$("form[name='newArticle']").validate({
	  rules: {
		quantity: "required",
		discount: "required"
	  },
	  messages: {
		quantity: "Por favor ingrese la cantidad del artículo a devolver.",
		discount: "Por favor ingrese el motivo de la devolución.",
		selectArticle: "Por favor seleccione un artículo."
	  },
	  submitHandler: function(form) {
		form.submit();
	  }
	});
  });

//-- Edit Article Modal -----------------------------------------------------------------------------------------
function validationTotal(){

	var quantity = $('#editArticleModal .modal-body .quantity').val(); 
	var price = $('#editArticleModal .modal-body #priceEdit').val(); 
			var total = 0;
			if( quantity > 0 ){
				var base = price*quantity; 

				var discount = $('#editArticleModal .modal-body #discountEdit').val();
		   	    if( discount > 0 ){
			       total += (base * ( 1 - (discount/100)));
				}
				
				var i = $('#editArticleModal .modal-body #indexArticle').val();
				var urlI = '/factura-de-venta-articulos/obtenerImpuestosArticulo/?index='+ i;
				console.log(urlI);
				$.get(urlI, function(taxes, status) {
					if(taxes != NaN){
					   var totalTaxes = 0;
					   taxes.forEach(function(tax, index) {
						  totalTaxes += (total * (tax.value/100));
						  console.log(totalTaxes);
						});
						total += totalTaxes;
						$('#editArticleModal  .modal-body #taxEdit').val(totalTaxes);
						console.log('soy total' + total);
					}
					$('#editArticleModal  .modal-body #totalEdit').val(total);
				});
				
			}
} 

$(document).on('click', '#btn-editArticle', function(event) {
	change();
	event.preventDefault();
	var href = $(this).attr('href');
	$.get(href, function(article, status) {
	    if(article.article.name != ""){
			$('#editArticleModal .modal-body #selectArticleEdit option').each(function(){ 
				if($(this).val() == article.article.name){
					$(this).attr('selected','selected');
				}		  
		   });

		   var index = href.split("=");
		   var indexA = index[1];
		   
		   var idA = $('#selectArticleEdit').prop('selectedIndex');
		   $('#editArticleModal .modal-body #idArticleEdit').val(idA);
		   $('#editArticleModal .modal-body #indexArticle').val(indexA);

		}

		$('#editArticleModal .modal-body #taxEdit').val(article.tax);	
		$('#editArticleModal .modal-body #priceEdit').val(article.article.salePrice);
		$('#editArticleModal .modal-body #quantityEdit').val(article.quantity);
		$('#editArticleModal .modal-body #discountEdit').val(article.discount);	
		$('#editArticleModal .modal-body #totalEdit').val(article.total);	

        var hrefI = '/factura-de-venta-articulos/obtenerImpuestosArticulo/?index='
		var url = hrefI + indexA ;
		console.log(url);
		$.get(url, function(taxes, status) {
			 if(taxes != NaN){
				taxes.forEach(function(tax, index) {
					var name = tax.name;
					var value = tax.value
					$('#tableTaxesEdit tr:last').after('<tr><td id="td-nameEdit">'+ name +'</td><td id="td-valueEdit">'+ value +'</td>' +
					'<td><button type="button" class="btn btn-primary" id="btn-delete-tax-edit"><i class="fa fa-trash"></i></button></td></tr>'
				   );
				 });
			
			 }
		});	
        

	});

	$('#editArticleModal').modal();	
});

$(document).on('click', '#selectArticleEdit', function() {
	var index = $('#editArticleModal .modal-body #idArticleEdit').val();
	if(index != 0){
		var href = '/factura-de-venta-articulos/obtenerPrecioArticulo/?index='
		var url = href + index ;
		console.log(url);
		$.get(url, function(price, status) {
			console.log(price);
			$('#editArticleModal .modal-body #priceEdit').val(price);
			$('#editArticleModal .modal-body #idArticleEdit').val(index);
            validationTotal();			
			

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
		  validationTotal();
	}  
});

$(document).on('click', '#editArticleModal .modal-body #discountEdit', function() {
	var typingTimer;
	var doneTypingInterval = 1000;
	$('#editArticleModal .modal-body #discountEdit').keyup(function(){
		clearTimeout(typingTimer);
		if ($('#editArticleModal .modal-body #discountEdit').val()) {
		typingTimer = setTimeout(doneTyping, doneTypingInterval);
		}
	});
	function doneTyping () {
          validationTotal();
	}  
});

$(document).on('click', '#btn-addTaxEdit', function(event) {
	if($('#selectTaxEdit').val()!= 'Seleccione'){
		var nameTax = $('#editArticleModal .modal-body #selectTaxEdit').val();
		var i = $('#editArticleModal .modal-body #indexArticle').val();
		var url = '/factura-de-venta-articulos/insertarImpuestoArticulo/?'+'index='+ i +'&name=' + nameTax;
		console.log(url);
		$.get(url, function(tax, status) {
			 var name = tax.name;
			 var value = tax.value;
			$('#tableTaxesEdit tr:last').after('<tr><td id="td-nameEdit">'+ name +'</td><td id="td-valueEdit">'+ value +'</td>' +
			  '<td><button type="button" class="btn btn-primary" id="btn-delete-tax-edit"><i class="fa fa-trash"></i></button></td></tr>'
			);

		});

		validationTotal();
		
	}

});

$(document).on('click', '#editArticleModal .modal-body #btn-delete-tax-edit', function(event)  {
    var nameTax = $(this).closest('tr')  
                       .find('#td-nameEdit')   
					   .text();   
	 
	console.log('el impuesto es' +nameTax);		   				       
	var i = $('#editArticleModal .modal-body #indexArticle').val();
	var href = '/factura-de-venta-articulos/eliminarImpuestoArticulo/?'+'index='+ i +'&name=' + nameTax;
    var url = href;
    console.log(url);
    $.get(url);
	
	$(this).closest('tr').remove();
	
	validationTotal();
    
});

$(function() {
	$("form[name='editArticle']").validate({
	  rules: {
		quantityEdit: "required",
		discountEdit: "required"
	  },
	  messages: {
		quantityEdit: "Por favor ingrese la cantidad del artículo a devolver.",
		discountEdit: "Por favor ingrese el motivo de la devolución.",
		selectArticleEdit: "Por favor seleccione un artículo."
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