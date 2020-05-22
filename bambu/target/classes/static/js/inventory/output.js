$(document).on('click', '#btn-output', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(articulo, status) {
            $('#salidaModal .modal-body #input-id').val(articulo.id);
            $('#salidaModal .modal-body #input-name').val(articulo.article.name);
            $('#salidaModal .modal-body #unitCost').val(articulo.article.unitCost);
        });
        $('#salidModal').modal();	
});


$(document).on('click', '#salidaModal .modal-body #quantity', function() {
	var typingTimer;
	var doneTypingInterval = 1000;
	$('#salidaModal .modal-body #quantity').keyup(function(){
		clearTimeout(typingTimer);
		if ($('#salidaModal .modal-body #quantity').val()) {
		typingTimer = setTimeout(doneTyping, doneTypingInterval);
		}
	});
	function doneTyping () {
		var quantity = $('#salidaModal .modal-body #quantity').val();
		if(quantity > 0){
		   var total =  quantity * $('#salidaModal .modal-body #unitCost').val();
		   $('#salidaModal .modal-body #totalCost').val(total);
		}
		else{
			$('#salidaModal .modal-body #totalCost').val(0);
		} 
		
	}  
});






/*
$(function () {
    
    $('#btn-save-ouput').on('click', function () {
        alert($("#salidaModal .modal-body #totalCost").val());
        $.ajax({
            url: "inventario/salidaInventario",
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify({
                id: $("#salidaModal .modal-body #id").val(),
                code: $("#salidaModal .modal-body #code").val(),
                quantity: $("#salidaModal .modal-body #quantity").val(),
                detail: $("#salidaModal .modal-body #detail").val(),
                unitCost: $("#salidaModal .modal-body #unitCost").val(),
                totalCost: $("#salidaModal .modal-body #totalCost").val()
            }),
            async: false
        });
    });
});
*/
