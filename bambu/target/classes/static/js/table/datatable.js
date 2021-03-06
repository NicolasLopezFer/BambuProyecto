
//DataTable JQuery
$(document).ready(function () {
    var table = $('#table-information').DataTable({
        lengthChange: false,
        paging: false,
        retrieve: true,
        dom: 'Bfrtip',
        buttons: [{
            extend: 'pdfHtml5',
            orientation: 'landscape',
            pageSize: 'LEGAL',
        },'excel','colvis'],
        language: {
            "decimal": "",
            "emptyTable": "No hay información",
            "info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
            "infoEmpty": "Mostrando 0 to 0 of 0 Entradas",
            "loadingRecords": "Cargando...",
            "processing": "Procesando...",
            "search": "Buscar:",
            "zeroRecords": "Sin resultados encontrados",
            buttons: {
                colvis: 'Columna Visible'
            },
            "paginate": {
                "first": "Primero",
                "last": "Ultimo",
                "next": "Siguiente",
                "previous": "Anterior"
            }
        }
    });

});   



