$(document).ready(function() {
	$('.fffBtn').on('click', function(event) {
		event.preventDefault();
		$('.filtrarAFechasRecibo #filtrarModal').modal();
	});
});

$(function() {
	$("form[name='filtrarAFechasRecibo']").validate({
	  rules: {
		fechaInicio: "required",
		fechaFin: "required"
	  },
	  messages: {
		fechaInicio: "Por favor ingrese la fecha de inicio.",
		fechaFin: "Por favor ingrese la fecha de fin."
	  },
	  submitHandler: function(form) {
		form.submit();
	  }
	});
});

