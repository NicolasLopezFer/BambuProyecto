$(document).ready(function() {
	$('#btn-output').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function(articulo, status) {
            $('#salidaModal .modal-body #input-id').val(articulo.id);
            $('#salidaModal .modal-body #input-name').val(articulo.article.name);
            $('#salidaModal .modal-body #unitCost').val(articulo.article.unitCost);
        });
        $('#salidModal').modal();	
	});
});

$(document).ready(function() {
	$('#salidaModal .modal-body #quantity').on('click', function(event) {
		event.preventDefault();
        total =  $('#salidaModal .modal-body #quantity').val() * $('#salidaModal .modal-body #unitCost').val();
        $('#salidaModal .modal-body #totalCost').val(total);
	});
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
