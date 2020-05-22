function validarFecha(Firstfecha,Secondfecha){
    if(Secondfecha>Firstfecha)
        return true;
    return false;
}

function validar()
{
    var Firstfecha=document.getElementById("fechaInicio").value;
    var Secondfecha=document.getElementById("fechaFin").value;
    if(!validarFecha(Firstfecha,Secondfecha)){
        //document.getElementById("result").innerHTML="La fecha "+Secondfecha+" es incorrecta";
        swal("¡Error!", "La fecha "+Secondfecha+" es incorrecta", "error");
    
    }
}
   